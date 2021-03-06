/**
 * Syncnapsis Framework - Copyright (c) 2012-2014 ultimate
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation; either version
 * 3 of the License, or any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MECHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Plublic License along with this program;
 * if not, see <http://www.gnu.org/licenses/>.
 */
package com.syncnapsis.data.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.syncnapsis.data.dao.ParticipantDao;
import com.syncnapsis.data.model.Empire;
import com.syncnapsis.data.model.Match;
import com.syncnapsis.data.model.Participant;
import com.syncnapsis.data.model.SolarSystemInfrastructure;
import com.syncnapsis.data.model.SolarSystemPopulation;
import com.syncnapsis.data.model.help.Vector;
import com.syncnapsis.data.service.EmpireManager;
import com.syncnapsis.data.service.ParticipantManager;
import com.syncnapsis.data.service.SolarSystemInfrastructureManager;
import com.syncnapsis.data.service.SolarSystemPopulationManager;
import com.syncnapsis.enums.EnumDestructionType;
import com.syncnapsis.enums.EnumJoinType;
import com.syncnapsis.enums.EnumMatchState;
import com.syncnapsis.security.BaseGameManager;
import com.syncnapsis.security.accesscontrol.MatchAccessController;
import com.syncnapsis.universe.Calculator;
import com.syncnapsis.utils.data.ExtendedRandom;

/**
 * Manager-Implementation for access to Participant.
 * 
 * @author ultimate
 */
public class ParticipantManagerImpl extends GenericManagerImpl<Participant, Long> implements ParticipantManager, InitializingBean
{
	/**
	 * ParticipantDao for database access
	 */
	protected ParticipantDao					participantDao;

	/**
	 * The EmpireManager
	 */
	protected EmpireManager						empireManager;
	/**
	 * The SolarSystemPopulationManager
	 */
	protected SolarSystemPopulationManager		solarSystemPopulationManager;
	/**
	 * The SolarSystemInfrastructureManager
	 */
	protected SolarSystemInfrastructureManager	solarSystemInfrastructureManager;

	/**
	 * The universe-conquest {@link Calculator}
	 */
	protected Calculator						calculator;

	/**
	 * The SecurityManager
	 */
	protected BaseGameManager					securityManager;

	/**
	 * Standard Constructor
	 * 
	 * @param participantDao - ParticipantDao for database access
	 * @param empireManager - the EmpireManager
	 * @param solarSystemPopulationManager - the SolarSystemPopulationManager
	 */
	public ParticipantManagerImpl(ParticipantDao participantDao, EmpireManager empireManager,
			SolarSystemPopulationManager solarSystemPopulationManager, SolarSystemInfrastructureManager solarSystemInfrastructureManager)
	{
		super(participantDao);
		this.empireManager = empireManager;
		this.participantDao = participantDao;
		this.solarSystemPopulationManager = solarSystemPopulationManager;
		this.solarSystemInfrastructureManager = solarSystemInfrastructureManager;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception
	{
		Assert.notNull(calculator, "calculator must not be null!");
		Assert.notNull(securityManager, "securityManager must not be null!");
	}

	/**
	 * The universe conquenst {@link Calculator}
	 * 
	 * @return calculator
	 */
	public Calculator getCalculator()
	{
		return calculator;
	}

	/**
	 * The universe conquenst {@link Calculator}
	 * 
	 * @param calculator - the Calculator
	 */
	public void setCalculator(Calculator calculator)
	{
		this.calculator = calculator;
	}

	/**
	 * The SecurityManager (BaseGameManager)
	 * 
	 * @return securityManager
	 */
	public BaseGameManager getSecurityManager()
	{
		return securityManager;
	}

	/**
	 * The SecurityManager (BaseGameManager)
	 * 
	 * @param securityManager - the SecurityManager
	 */
	public void setSecurityManager(BaseGameManager securityManager)
	{
		this.securityManager = securityManager;
	}

	/**
	 * Shortcut for
	 * <code>securityManager.getAccessController(Match.class).isAccessibe(match, operation, securityManager.getPlayerProvider().get())</code>
	 * 
	 * @see MatchAccessController
	 * @return true or false
	 */
	protected boolean isAccessible(Match match, int operation)
	{
		Object context = null;
		return securityManager.getAccessController(Match.class).isAccessible(match, operation, context, securityManager.getAuthorityProvider().get());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.data.service.ParticipantManager#joinMatch(com.syncnapsis.data.model.Match)
	 */
	@Override
	public Participant joinMatch(Match match)
	{
		// security-check
		Assert.isTrue(isAccessible(match, MatchAccessController.OPERATION_JOIN));

		Empire empire = securityManager.getEmpireProvider().get();

		Assert.notNull(empire, "current empire must not be null!");

		// check the match state and join type
		switch(match.getState())
		{
			case planned:
				if(match.getPlannedJoinType() == EnumJoinType.joiningEnabled)
					break;
				// joining ist not allowed at this state -> return participant if existing
				return getByMatchAndEmpire(match.getId(), empire.getId());
			case active:
				if(match.getStartedJoinType() == EnumJoinType.joiningEnabled)
					break;
			case canceled:
			case finished:
			case created:
				// joining ist not allowed at this state -> return participant if existing
				return getByMatchAndEmpire(match.getId(), empire.getId());
		}

		return addParticipant(match, empire);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.data.service.ParticipantManager#leaveMatch(com.syncnapsis.data.model.Match)
	 */
	@Override
	public boolean leaveMatch(Match match)
	{
		// security-check
		Assert.isTrue(isAccessible(match, MatchAccessController.OPERATION_JOIN));

		Empire empire = securityManager.getEmpireProvider().get();

		Assert.notNull(empire, "current empire must not be null!");

		return removeParticipant(match, empire);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.data.service.ParticipantManager#addParticipant(com.syncnapsis.data.model.Match
	 * , long)
	 */
	@Override
	public Participant addParticipant(Match match, long empireId)
	{
		// security-check
		Assert.isTrue(isAccessible(match, MatchAccessController.OPERATION_ADD_PARTICIPANT));

		// check the match state and join type
		switch(match.getState())
		{
			case created:
				break;
			case planned:
				if(match.getPlannedJoinType() != EnumJoinType.none)
					break;
				// joining ist not allowed at this state -> return participant if existing
				return getByMatchAndEmpire(match.getId(), empireId);
			case active:
				if(match.getStartedJoinType() != EnumJoinType.none)
					break;
			case canceled:
			case finished:
				// joining ist not allowed at this state -> return participant if existing
				return getByMatchAndEmpire(match.getId(), empireId);
		}

		return addParticipant(match, empireManager.get(empireId));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.data.service.ParticipantManager#removeParticipant(com.syncnapsis.data.model
	 * .Match, long)
	 */
	@Override
	public boolean removeParticipant(Match match, long empireId)
	{
		// security-check
		Assert.isTrue(isAccessible(match, MatchAccessController.OPERATION_REMOVE_PARTICIPANT));

		return removeParticipant(match, empireManager.get(empireId));
	}

	/**
	 * Internal method for adding participants to a match to be called from
	 * {@link ParticipantManagerImpl#addParticipant(Match, long)} and
	 * {@link ParticipantManagerImpl#joinMatch(Match)}
	 * 
	 * @param match - the match to add the participant to
	 * @param empire - the empire to add as an participant
	 * @return the (maybe newly created) Participant associating the Empire with the Match
	 */
	protected Participant addParticipant(Match match, Empire empire)
	{
		Participant participant = getByMatchAndEmpire(match.getId(), empire.getId());

		if(participant != null && participant.isActivated())
			return participant; // already participating
		else if(match.getState() == EnumMatchState.canceled || match.getState() == EnumMatchState.finished)
			return null; // adding participants not allowed anymore
		// TODO operation is not thread-safe here
		else if(match.getParticipantsMax() > 0 && getNumberOfParticipants(match) >= match.getParticipantsMax())
			return null; // the match is 'full'

		Date now = new Date(securityManager.getTimeProvider().get());

		if(participant == null)
			participant = new Participant();

		participant.setActivated(true);
		participant.setDestructionDate(null);
		participant.setDestructionType(null);
		participant.setEmpire(empire);
		participant.setJoinedDate(now);
		participant.setMatch(match);
		participant.getRank().setRank(getNumberOfParticipants(match));
		participant.getRank().setDate(now);
		participant.getRank().setFinal(false);
		participant.getRank().setRawValue(0);
		participant.getRank().setValue(0);
		participant.getRank().setVictoryDate(null);
		participant.setRivals(new ArrayList<Participant>());
		participant.setStartSystemsSelected(0);

		if(participant.getPopulations() != null)
		{
			// participant has selected start systems earlier, but has left -> reset them
			for(SolarSystemPopulation population : participant.getPopulations())
			{
				population.setActivated(true); // re-activate
				population.setDestructionDate(null);
				population.setDestructionType(null);
			}
		}
		else
		{
			participant.setPopulations(new ArrayList<SolarSystemPopulation>());
		}

		participant = save(participant);

		match.getParticipants().add(participant);

		return participant;
	}

	/**
	 * Internal method for removing participants to a match to be called from
	 * {@link ParticipantManagerImpl#removeParticipant(Match, long)} and
	 * {@link ParticipantManagerImpl#joinMatch(Match)}
	 * 
	 * @param match - the match to remove the participant to
	 * @param empire - the empire to remove as an participant
	 * @return true if removing was successful, false otherwise
	 */
	protected boolean removeParticipant(Match match, Empire empire)
	{
		Participant participant = getByMatchAndEmpire(match.getId(), empire.getId());

		if(participant == null)
			return false;

		Date now = new Date(securityManager.getTimeProvider().get());

		if(match.getState() == EnumMatchState.canceled || match.getState() == EnumMatchState.finished)
		{
			return false;
		}
		else if(match.getState() == EnumMatchState.planned)
		{
			// remove the participant "completely"
			participant.setActivated(false);
		}

		EnumDestructionType destructionType;
		if(empire.getPlayer().equals(securityManager.getPlayerProvider().get()))
		{
			if(match.getState() == EnumMatchState.planned)
				destructionType = EnumDestructionType.left;
			else
				destructionType = EnumDestructionType.givenUp;
		}
		else
		{
			destructionType = EnumDestructionType.removed;
		}

		// destroy will perform the save
		destroy(participant, destructionType, now);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.data.service.ParticipantManager#getNumberOfParticipants(com.syncnapsis.data
	 * .model.Match)
	 */
	@Override
	public int getNumberOfParticipants(Match match)
	{
		int count = 0;
		for(Participant p : match.getParticipants())
		{
			if(p.isActivated())
				count++;
		}
		return count;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.data.service.ParticipantManager#destroy(com.syncnapsis.data.model.Participant)
	 */
	@Override
	public Participant destroy(Participant participant, EnumDestructionType destructionType, Date destructionDate)
	{
		// destroy all remaining populations
		for(SolarSystemPopulation population : participant.getPopulations())
		{
			if(population.isActivated())
			{
				solarSystemPopulationManager.destroy(population, destructionType, destructionDate);
				solarSystemPopulationManager.save(population);
			}
		}

		participant.setDestructionType(destructionType);
		participant.setDestructionDate(destructionDate);
		// participant.getRank().setFinal(true); // rank will be finalized on next rank update

		return save(participant);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.ParticipantManager#getByMatch(long)
	 */
	@Override
	public List<Participant> getByMatch(long matchId)
	{
		return participantDao.getByMatch(matchId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.ParticipantManager#getByMatchAndEmpire(long, long)
	 */
	@Override
	public Participant getByMatchAndEmpire(long matchId, long empireId)
	{
		return participantDao.getByMatchAndEmpire(matchId, empireId);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.data.service.ParticipantManager#startParticipating(com.syncnapsis.data.model
	 * .Participant, java.util.Date)
	 */
	@Override
	public Participant startParticipating(Participant participant, Date participationDate)
	{
		int  populations = 0;
		long pop = 0;
		for(SolarSystemPopulation population : participant.getPopulations())
		{
			if(population.isActivated())
			{
				populations++;
				pop += population.getPopulation();
			}
		}
		
		logger.debug("population: count=" + populations + " amount=" + pop);
		
		if(populations < participant.getMatch().getStartSystemCount())
			throw new IllegalArgumentException("not all start systems selected!");
		if(pop < participant.getMatch().getStartPopulation())
			throw new IllegalArgumentException("not all start population assigned!");

		// mark populations with start date
		for(SolarSystemPopulation population : participant.getPopulations())
		{
			population.setColonizationDate(participationDate);
			population.setLastUpdateDate(participationDate);
			solarSystemPopulationManager.save(population);
		}

		return participant;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.data.service.ParticipantManager#selectStartSystem(com.syncnapsis.data.model
	 * .SolarSystemInfrastructure, long)
	 */
	@Override
	public SolarSystemPopulation selectStartSystem(SolarSystemInfrastructure infrastructure, long population)
	{
		Empire empire = securityManager.getEmpireProvider().get();
		if(empire == null)
			throw new IllegalArgumentException("current Empire must not be null!");
		Match match = infrastructure.getMatch();
		for(Participant p : match.getParticipants())
		{
			if(p.getEmpire().getId().equals(empire.getId()))
				return solarSystemPopulationManager.selectStartSystem(p, infrastructure, population);
		}
		throw new IllegalArgumentException("current Empire does not participate in the specified match!");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.data.service.ParticipantManager#randomSelectStartSystems(com.syncnapsis.data
	 * .model.Participant, com.syncnapsis.utils.data.ExtendedRandom)
	 */
	@Override
	public List<SolarSystemPopulation> randomSelectStartSystems(Participant participant, ExtendedRandom random)
	{
		List<SolarSystemInfrastructure> infrastructures = new ArrayList<SolarSystemInfrastructure>(
				solarSystemInfrastructureManager.getByMatch(participant.getMatch().getId()));

		if(logger.isDebugEnabled())
		{
			logger.debug("available infrastructures:");
			for(int i = 0; i < infrastructures.size(); i++)
			{
				logger.debug(i + ": " + infrastructures.get(i).getId() + " pop=" + infrastructures.get(i).getPopulations());
			}
		}

		int startSystems = participant.getMatch().getStartSystemCount();
		List<SolarSystemPopulation> populations = new ArrayList<SolarSystemPopulation>(startSystems);
		populations.addAll(participant.getPopulations());

		// calculate center of current selection and total selected population
		Vector.Integer centerOfSelection = getCenter(participant.getPopulations());
		if(centerOfSelection == null)
		{
			// select a random center
			centerOfSelection = random.nextEntry(infrastructures).getSolarSystem().getCoords();
		}
		sortByDistance(infrastructures, centerOfSelection);
		
		int selectedPops = 0;
		long selectedPop = 0;
		for(SolarSystemPopulation population : participant.getPopulations())
		{
			if(population.isActivated())
			{
				selectedPops++;
				selectedPop += population.getPopulation();
			}
		}
		
		long remainingPop = participant.getMatch().getStartPopulation() - selectedPop;
		
		if(logger.isDebugEnabled())
		{
			logger.debug("selectedPops = " + selectedPops);
			logger.debug("selectedPop  = " + selectedPop);
			logger.debug("remainingPop = " + remainingPop);
		}
		
		long pop;
		long maxPop;
		long avgPopRemaining;
		int infrastructureIndex;

		SolarSystemPopulation population;
		SolarSystemInfrastructure infrastructure;
		while(selectedPops < startSystems)
		{
			avgPopRemaining = remainingPop / (startSystems - selectedPops);
			// select a random system / infrastructure
			do
			{
				infrastructureIndex = Math.abs(random.nextGaussian(-infrastructures.size() + 1, infrastructures.size() - 1));
				logger.debug("selecting infrastructure @ " + infrastructureIndex);
				infrastructure = infrastructures.get(infrastructureIndex);
				maxPop = calculator.getMaxPopulation(infrastructure);
			} while(maxPop < avgPopRemaining); // assure no (almost) unhabitable system is chosen
			// select a random amount of remaining pop
			pop = random.nextGaussian(0, 2 * avgPopRemaining);
			pop = Math.min(pop, maxPop);
			pop = Math.min(pop, remainingPop);
			// reduce remaining pop
			selectedPops++;
			selectedPop += pop;
			remainingPop -= pop;

			if(logger.isDebugEnabled())
			{
				logger.debug("creating population @ " + infrastructureIndex + " size=" + pop);
				logger.debug("selectedPops = " + selectedPops);
				logger.debug("selectedPop  = " + selectedPop);
				logger.debug("remainingPop = " + remainingPop);
			}
			
			// select the start system
			population = solarSystemPopulationManager.selectStartSystem(participant, infrastructure, pop);
			populations.add(population);
		}

		// some population may be left due to rounding, randomization or player's selection
		// add the remaining pop arbitrary
		if(remainingPop != 0)
		{
			logger.debug("destributing remaining pop...");
			
			// randomly traverse all selected populations
			int[] perm = random.nextPerm(populations.size());
			for(int i : perm)
			{
				population = populations.get(i);
				// get the max allowed population
				maxPop = calculator.getMaxPopulation(population.getInfrastructure());
				// check if there is room for the remaining pop and add as much as possible
				if(population.getPopulation() + remainingPop > maxPop)
				{
					remainingPop = population.getPopulation() + remainingPop - maxPop;
					population.setPopulation(maxPop);
				}
				else
				{
					population.setPopulation(population.getPopulation() + remainingPop);
					remainingPop = 0;
					break;
				}
			}
			if(remainingPop != 0)
			{
				// selected systems are not able to support the required amount of population
				// overfill a random population (we accept this population will start to decrease
				// immediately on match start, but player still has the opportunity to move them
				// away if he is fast enough...)
				population = populations.get(perm[0]);
				population.setPopulation(population.getPopulation() + remainingPop);
			}
		}

		// save all populations
		for(SolarSystemPopulation p : populations)
		{
			solarSystemPopulationManager.save(p);
		}

		// update the participant
		participant.setPopulations(populations);
		participant.setStartSystemsSelected(startSystems);
		save(participant);
		
		return populations;
	}

	/**
	 * Get the center of a list of populations
	 * 
	 * @param populations - the list of populations
	 * @return the center point
	 */
	protected Vector.Integer getCenter(List<SolarSystemPopulation> populations)
	{
		Vector.Integer centerOfSelection = new Vector.Integer();
		if(populations.size() != 0)
		{
			for(SolarSystemPopulation population : populations)
			{
				centerOfSelection.setX(centerOfSelection.getX() + population.getInfrastructure().getSolarSystem().getCoords().getX());
				centerOfSelection.setY(centerOfSelection.getY() + population.getInfrastructure().getSolarSystem().getCoords().getY());
				centerOfSelection.setZ(centerOfSelection.getZ() + population.getInfrastructure().getSolarSystem().getCoords().getZ());
			}
			// finish average calculation
			centerOfSelection.setX(centerOfSelection.getX() / populations.size());
			centerOfSelection.setY(centerOfSelection.getY() / populations.size());
			centerOfSelection.setZ(centerOfSelection.getZ() / populations.size());
		}
		else
		{
			return null;
		}
		return centerOfSelection;
	}

	/**
	 * Sort the given list of infrastructures by their distance to the reference point starting with
	 * the infrastructure with the smallest distance.
	 * 
	 * @param infrastructures - the list of infrastructures to sort
	 * @param ref - the reference point
	 */
	protected void sortByDistance(List<SolarSystemInfrastructure> infrastructures, final Vector.Integer ref)
	{
		// sort systems by distance from center of selection
		Collections.sort(infrastructures, new Comparator<SolarSystemInfrastructure>() {
			@Override
			public int compare(SolarSystemInfrastructure o1, SolarSystemInfrastructure o2)
			{
				Vector.Integer c1 = o1.getSolarSystem().getCoords();
				Vector.Integer c2 = o2.getSolarSystem().getCoords();
				// @formatter:off
				int distSq1 = 	(c1.getX() - ref.getX())*(c1.getX() - ref.getX()) +
								(c1.getY() - ref.getY())*(c1.getY() - ref.getY()) + 
								(c1.getZ() - ref.getZ())*(c1.getZ() - ref.getZ()); 
				int distSq2 = 	(c2.getX() - ref.getX())*(c2.getX() - ref.getX()) +
								(c2.getY() - ref.getY())*(c2.getY() - ref.getY()) + 
								(c2.getZ() - ref.getZ())*(c2.getZ() - ref.getZ()); 
				// @formatter:on
				return (int) Math.signum(distSq1 - distSq2);
			}
		});
	}
}
