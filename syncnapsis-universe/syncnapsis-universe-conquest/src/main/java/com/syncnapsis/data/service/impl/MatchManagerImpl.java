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
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.syncnapsis.client.ConquestManager;
import com.syncnapsis.constants.UniverseConquestConstants;
import com.syncnapsis.data.dao.MatchDao;
import com.syncnapsis.data.model.Galaxy;
import com.syncnapsis.data.model.Match;
import com.syncnapsis.data.model.Participant;
import com.syncnapsis.data.model.SolarSystem;
import com.syncnapsis.data.model.SolarSystemInfrastructure;
import com.syncnapsis.data.model.SolarSystemPopulation;
import com.syncnapsis.data.model.help.Rank;
import com.syncnapsis.data.service.GalaxyManager;
import com.syncnapsis.data.service.MatchManager;
import com.syncnapsis.data.service.ParticipantManager;
import com.syncnapsis.data.service.SolarSystemInfrastructureManager;
import com.syncnapsis.data.service.SolarSystemPopulationManager;
import com.syncnapsis.enums.EnumJoinType;
import com.syncnapsis.enums.EnumMatchState;
import com.syncnapsis.enums.EnumStartCondition;
import com.syncnapsis.enums.EnumVictoryCondition;
import com.syncnapsis.security.BaseGameManager;
import com.syncnapsis.security.accesscontrol.MatchAccessController;
import com.syncnapsis.universe.Calculator;
import com.syncnapsis.utils.HibernateUtil;
import com.syncnapsis.utils.MathUtil;
import com.syncnapsis.utils.SessionFactoryUtil;
import com.syncnapsis.utils.StringUtil;
import com.syncnapsis.utils.data.ExtendedRandom;
import com.syncnapsis.utils.mail.UniverseConquestMailer;

/**
 * Manager-Implementation for access to Match.
 * 
 * @author ultimate
 */
public class MatchManagerImpl extends GenericNameManagerImpl<Match, Long> implements MatchManager, InitializingBean
{
	/**
	 * MatchDao for database access
	 */
	protected MatchDao							matchDao;

	/**
	 * The GalaxyManager
	 */
	protected GalaxyManager						galaxyManager;
	/**
	 * The ParticipantManager
	 */
	protected ParticipantManager				participantManager;
	/**
	 * The SolarSystemPopulationManager
	 */
	protected SolarSystemPopulationManager		solarSystemPopulationManager;
	/**
	 * The SolarSystemInfrastructureManager
	 */
	protected SolarSystemInfrastructureManager	solarSystemInfrastructureManager;

	/**
	 * The ConquestManager
	 */
	protected ConquestManager					conquestManager;

	/**
	 * The {@link Calculator}
	 */
	protected Calculator						calculator;

	/**
	 * The SecurityManager
	 */
	protected BaseGameManager					securityManager;

	/**
	 * The {@link SessionFactoryUtil}
	 */
	protected SessionFactoryUtil				sessionFactoryUtil;

	/**
	 * Standard Constructor
	 * 
	 * @param matchDao - MatchDao for database access
	 * @param galaxyManager - the GalaxyManager
	 * @param participantManager - the ParticipantManager
	 * @param solarSystemPopulationManager - the SolarSystemPopulationManager
	 * @param solarSystemInfrastructureManager - the PolarSystemInfrastructureManager
	 */
	public MatchManagerImpl(MatchDao matchDao, GalaxyManager galaxyManager, ParticipantManager participantManager,
			SolarSystemPopulationManager solarSystemPopulationManager, SolarSystemInfrastructureManager solarSystemInfrastructureManager,
			ConquestManager conquestManager)
	{
		super(matchDao);
		this.matchDao = matchDao;
		this.galaxyManager = galaxyManager;
		this.participantManager = participantManager;
		this.solarSystemPopulationManager = solarSystemPopulationManager;
		this.solarSystemInfrastructureManager = solarSystemInfrastructureManager;
		this.conquestManager = conquestManager;
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
		Assert.notNull(sessionFactoryUtil, "sessionFactoryUtil must not be null!");

		// create general channel(s)
		conquestManager.createChannel(UniverseConquestConstants.CHANNEL_MATCH_CREATED, null);
		// create individual match channel(s)
		// this will create a separate session, since no session is present on startup otherwise
		boolean sessionAlreadyOpen = sessionFactoryUtil.isSessionBound();
		if(!sessionAlreadyOpen) // add this check to prevent duplicate session during tests
			sessionFactoryUtil.openBoundSession();
		List<Match> matches = getAll();
		for(Match m : matches)
		{
			createChannels(m);
		}
		if(!sessionAlreadyOpen)
			sessionFactoryUtil.closeBoundSession();
	}

	/**
	 * The {@link Calculator}
	 * 
	 * @return calculator
	 */
	public Calculator getCalculator()
	{
		return calculator;
	}

	/**
	 * The {@link Calculator}
	 * 
	 * @param calculator - the calculator
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
	 * The {@link SessionFactoryUtil}
	 * 
	 * @return sessionFactoryUtil
	 */
	public SessionFactoryUtil getSessionFactoryUtil()
	{
		return sessionFactoryUtil;
	}

	/**
	 * The {@link SessionFactoryUtil}
	 * 
	 * @param sessionFactoryUtil - the SessionFactoryUtil
	 */
	public void setSessionFactoryUtil(SessionFactoryUtil sessionFactoryUtil)
	{
		this.sessionFactoryUtil = sessionFactoryUtil;
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
	 * @see com.syncnapsis.data.service.MatchManager#getByCreator(long, boolean, boolean, boolean,
	 * boolean)
	 */
	@Override
	public List<Match> getByCreator(long creatorId, boolean planned, boolean active, boolean finished, boolean canceled)
	{
		Date referenceDate = new Date(securityManager.getTimeProvider().get());
		return matchDao.getByCreator(creatorId, planned, active, finished, canceled, referenceDate);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.MatchManager#getByPlayer(long, boolean, boolean, boolean,
	 * boolean)
	 */
	@Override
	public List<Match> getByPlayer(long playerId, boolean planned, boolean active, boolean finished, boolean canceled)
	{
		Date referenceDate = new Date(securityManager.getTimeProvider().get());
		return matchDao.getByPlayer(playerId, planned, active, finished, canceled, referenceDate);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.MatchManager#getByGalaxy(long, boolean, boolean, boolean,
	 * boolean)
	 */
	@Override
	public List<Match> getByGalaxy(long galaxyId, boolean planned, boolean active, boolean finished, boolean canceled)
	{
		Date referenceDate = new Date(securityManager.getTimeProvider().get());
		return matchDao.getByGalaxy(galaxyId, planned, active, finished, canceled, referenceDate);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.MatchManager#getByState(com.syncnapsis.enums.EnumMatchState)
	 */
	public List<Match> getByState(EnumMatchState state)
	{
		return matchDao.getByState(state);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.MatchManager#createMatch(java.lang.String, long, int,
	 * java.lang.Long, com.syncnapsis.enums.EnumStartCondition, java.util.Date, boolean, int, int,
	 * com.syncnapsis.enums.EnumVictoryCondition, int, int, int, Long[],
	 * com.syncnapsis.enums.EnumJoinType, com.syncnapsis.enums.EnumJoinType)
	 */
	@Override
	public Match createMatch(String title, long galaxyId, int speed, Long seed, EnumStartCondition startCondition, Date startDate,
			boolean startSystemSelectionEnabled, int startSystemCount, long startPopulation, EnumVictoryCondition victoryCondition,
			int victoryParameter, int participantsMax, int participantsMin, Long[] empireIds, EnumJoinType plannedJoinType,
			EnumJoinType startedJoinType)
	{
		Assert.isTrue(isAccessible(null, MatchAccessController.OPERATION_CREATE), "no access rights for 'create match'");
		Assert.isTrue(participantsMax == 0 || participantsMax >= participantsMin,
				"participantsMax must either be 0 or be greater or equals to participantsMin");
		Assert.isTrue(participantsMin > 0, "participantsMin must be greater than 0");
		Assert.isTrue(
				speed >= UniverseConquestConstants.PARAM_MATCH_SPEED_MIN.asInt() && speed <= UniverseConquestConstants.PARAM_MATCH_SPEED_MAX.asInt(),
				"speed must be between " + UniverseConquestConstants.PARAM_MATCH_SPEED_MIN.asInt() + " and "
						+ UniverseConquestConstants.PARAM_MATCH_SPEED_MAX.asInt() + " (inclusive)");
		Assert.isTrue(startSystemCount > 0, "startSystemCount must be greater than 0");
		Assert.isTrue(startPopulation > startSystemCount, "startPopulation must be greater than startSystemCount");

		Galaxy galaxy = galaxyManager.get(galaxyId);
		Assert.notNull(galaxy, "galaxy with ID " + galaxyId + " not found!");

		if(startCondition != EnumStartCondition.planned)
			startDate = null;

		logger.info("creating match '" + title + "'");

		Match match = new Match();
		match.setActivated(true);
		match.setCreationDate(new Date(securityManager.getTimeProvider().get()));
		match.setCreator(securityManager.getPlayerProvider().get());
		match.setFinishedDate(null);
		match.setGalaxy(galaxy);
		match.setParticipants(new ArrayList<Participant>());
		match.setParticipantsMax(participantsMax);
		match.setParticipantsMin(participantsMin);
		// set joining enabled here temporarily otherwise adding participants won't work
		match.setPlannedJoinType(plannedJoinType);
		match.setPlannedJoinType(EnumJoinType.joiningEnabled);
		match.setSeed(seed);
		match.setSpeed(speed);
		match.setState(EnumMatchState.created);
		match.setStartCondition(startCondition);
		match.setStartDate(startDate);
		match.setStartedJoinType(startedJoinType);
		match.setStartPopulation(startPopulation);
		match.setStartSystemCount(startSystemCount);
		match.setStartSystemSelectionEnabled(startSystemSelectionEnabled);
		match.setTitle(title);
		match.setVictoryCondition(victoryCondition);
		match.setVictoryParameter(victoryParameter);

		match = save(match);

		// create infrastructures
		ExtendedRandom random = new ExtendedRandom(seed);
		for(SolarSystem system : galaxy.getSolarSystems())
		{
			solarSystemInfrastructureManager.initialize(match, system, random);
		}

		// add participants
		for(long empireId : empireIds)
		{
			if(participantManager.addParticipant(match, empireId) == null)
				logger.warn("could not add empire " + empireId + " to match " + match.getId());
		}

		// set real join type & state now...
		match.setState(EnumMatchState.planned);
		match.setPlannedJoinType(plannedJoinType);
		match = save(match);

		HibernateUtil.currentSession().flush();
		
		match = get(match.getId());
		createChannels(match); // create channels for the new match

		if(logger.isDebugEnabled())
		{
			logger.debug("participants:");
			for(Participant p : match.getParticipants())
			{
				logger.debug(p.getId() + ": user=" + (p.getEmpire() == null ? null : p.getEmpire().getPlayer().getUser().getUsername())
						+ " activated?" + p.isActivated());
			}
			logger.debug("participantCount: " + participantManager.getNumberOfParticipants(match));
			logger.debug("match.participantsMin: " + match.getParticipantsMin());
			logger.debug("match.participantsMax: " + match.getParticipantsMax());
			logger.debug("matchStartNecessary: " + isStartNecessary(match));
			logger.debug("matchStartNotPossibleReasons: " + getMatchStartNotPossibleReasons(match));
			logger.debug("match.startPopulation: " + match.getStartPopulation());
		}

		match = startMatchIfNecessary(match);

		conquestManager.update(UniverseConquestConstants.CHANNEL_MATCH_CREATED, match);

		return match;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.MatchManager#createMatch(java.lang.String, long, int,
	 * java.lang.String, com.syncnapsis.enums.EnumStartCondition, java.util.Date, boolean, int, int,
	 * com.syncnapsis.enums.EnumVictoryCondition, int, int, int, Long[],
	 * com.syncnapsis.enums.EnumJoinType, com.syncnapsis.enums.EnumJoinType)
	 */
	@Override
	public Match createMatch(String title, long galaxyId, int speed, String seedString, EnumStartCondition startCondition, Date startDate,
			boolean startSystemSelectionEnabled, int startSystemCount, long startPopulation, EnumVictoryCondition victoryCondition,
			int victoryParameter, int participantsMax, int participantsMin, Long[] empireIds, EnumJoinType plannedJoinType,
			EnumJoinType startedJoinType)
	{
		Long seed = null;
		if(seedString != null)
		{
			try
			{
				seed = Long.parseLong(seedString);
			}
			catch(NumberFormatException e)
			{
				seed = StringUtil.hashCode64(seedString);
			}
		}
		return createMatch(title, galaxyId, speed, seed, startCondition, startDate, startSystemSelectionEnabled, startSystemCount, startPopulation,
				victoryCondition, victoryParameter, participantsMax, participantsMin, empireIds, plannedJoinType, startedJoinType);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.MatchManager#startMatch(com.syncnapsis.data.model.Match)
	 */
	@Override
	public Match startMatch(Match match)
	{
		Assert.isTrue(isAccessible(match, MatchAccessController.OPERATION_START), "no access rights for 'start match " + match.getId() + "'");
		return performStartMatch(match);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.data.service.MatchManager#startMatchIfNecessary(com.syncnapsis.data.model.
	 * Match)
	 */
	@Override
	public Match startMatchIfNecessary(Match match)
	{
		if(isStartNecessary(match))
		{
			List<String> notPossibleReasons = getMatchStartNotPossibleReasons(match);
			if(notPossibleReasons.size() == 0)
			{
				return performStartMatch(match);
			}
			else
			{
				logger.info("match start failed: " + notPossibleReasons);
				getMailer().sendMatchStartFailedNotification(match, notPossibleReasons.get(0));
				// automatic starting is not possible -> set to manually
				match.setStartCondition(EnumStartCondition.manually);
				match = save(match);
			}
		}
		return match;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.data.service.MatchManager#isStartNecessary(com.syncnapsis.data.model.Match)
	 */
	@Override
	public boolean isStartNecessary(Match match)
	{
		Date now = new Date(securityManager.getTimeProvider().get());

		switch(match.getStartCondition())
		{
			case manually:
				return false;
			case planned:
				if(match.getStartDate().after(now))
					return false;
			case immediately:
				return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.data.service.MatchManager#isStartPossible(com.syncnapsis.data.model.Match)
	 */
	@Override
	public boolean isStartPossible(Match match)
	{
		return getMatchStartNotPossibleReasons(match).size() == 0;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.data.service.MatchManager#getMatchStartNotPossibleReasons(com.syncnapsis.data
	 * .model.Match)
	 */
	@Override
	public List<String> getMatchStartNotPossibleReasons(Match match)
	{
		int participantCount = participantManager.getNumberOfParticipants(match);

		List<String> reasons = new ArrayList<String>(5);
		if(match.getState() != EnumMatchState.planned)
			reasons.add(UniverseConquestConstants.REASON_ALREADY_STARTED);
		if(participantCount < match.getParticipantsMin())
			reasons.add(UniverseConquestConstants.REASON_NOT_ENOUGH_PARTICIPANTS);
		if(match.getParticipantsMax() > 0 && (participantCount > match.getParticipantsMax()))
			reasons.add(UniverseConquestConstants.REASON_TOO_MANY_PARTICIPANTS);
		return reasons;
	}

	/**
	 * Same as {@link MatchManager#startMatch(long)} just taking the match directly instead of the
	 * ID. Used from {@link MatchManagerImpl#startMatch(long)} and
	 * {@link MatchManagerImpl#startMatchIfNecessary(long)}
	 * 
	 * @see MatchManager#startMatch(long)
	 * @param match - the match to start
	 * @return the match entity (for convenience)
	 */
	protected Match performStartMatch(Match match)
	{
		List<String> notPossibleReasons = getMatchStartNotPossibleReasons(match);
		if(notPossibleReasons.size() > 0)
			throw new IllegalArgumentException("match can not be started at current state: " + notPossibleReasons.get(0));

		Date startDate;
		if(match.getStartCondition() == EnumStartCondition.planned)
		{
			startDate = match.getStartDate();
		}
		else
		{
			startDate = new Date(securityManager.getTimeProvider().get());
			match.setStartDate(startDate);
		}
		match.setState(EnumMatchState.active);

		switch(match.getVictoryCondition())
		{
			case domination:
				// set during game creation
				break;
			case extermination:
				// set during game creation
				break;
			case vendetta:
				// TODO make vendetta percentage selectable?
				match.setVictoryParameter(UniverseConquestConstants.PARAM_VICTORY_VENDETTA_PARAM_DEFAULT.asInt());
				break;
		}
		match.setVictoryTimeout(calculator.calculateVictoryTimeout(match));

		// create the randon - since this is the second step in randomization, so let's add 1 :-)
		ExtendedRandom random = new ExtendedRandom(match.getSeed() + 1);

		// get the list of "true" (activated) participants
		List<Participant> participants = new ArrayList<Participant>(match.getParticipants().size());
		for(Participant p : match.getParticipants())
		{
			if(p.isActivated())
				participants.add(p);
		}

		// assign the rivals
		int numberOfRivals = getNumberOfRivals(match);
		if(numberOfRivals > 0)
			assignRivals(participants, numberOfRivals, random);

		match = save(match);

		for(Participant p : participants)
		{
			// assign start populations
			participantManager.randomSelectStartSystems(p, random);

			if(logger.isDebugEnabled())
			{
				logger.debug(p.getEmpire().getPlayer().getUser().getUsername() + ":");
				for(SolarSystemPopulation pop : p.getPopulations())
					logger.debug(pop.getPopulation() + " @ " + pop.getInfrastructure().getInfrastructure());
			}
		}

		HibernateUtil.currentSession().flush();

		for(Participant p : participants)
		{
			// HibernateUtil.currentSession().refresh(p);
			// start participating
			participantManager.startParticipating(p, startDate);
		}

		return get(match.getId());
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.MatchManager#assignRivals(java.util.List, int,
	 * com.syncnapsis.utils.data.ExtendedRandom)
	 */
	@Override
	public void assignRivals(List<Participant> participants, int rivals, ExtendedRandom random)
	{
		// make a copy of the list since we do not want to modify the original
		List<Participant> parts = new ArrayList<Participant>(participants);
		// sort list to guarantee same pre-conditions
		Collections.sort(parts, Participant.BY_EMPIRE);
		// we need two copies:
		// 1 for the rivals to be shuffled (parts)
		// 1 for the sorted parts being accessed in the end (backup)
		List<Participant> backup = new ArrayList<Participant>(parts);

		// Get a random permutation which will give us a pattern for assigning the rivals.
		// We will check the permutation for the partial cycle length since we must assure no
		// participants is it's own rival...
		int[] randomization;
		if(rivals < parts.size())
		{
			// Use nextPerm to have the possibility of smaller cycles than number of participants.
			// But perform a check to assure cycle is long enough to prevent duplicates!
			do
			{
				randomization = random.nextPerm(parts.size());
			} while(MathUtil.permPartialCycleLength(randomization) <= rivals);
		}
		else
		{
			// use nextPerm2 to guarantee cycle length equal to participants size
			randomization = random.nextPerm2(parts.size());
		}

		// now assign the desired number of rivals
		int r;
		for(int i = 0; i < rivals; i++)
		{
			parts = MathUtil.perm(parts, randomization);

			r = 0;
			for(Participant p : backup)
			{
				if(p.getRivals() == null)
					p.setRivals(new ArrayList<Participant>(rivals));
				p.getRivals().add(parts.get(r++));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.data.service.MatchManager#getNumberOfRivals(com.syncnapsis.data.model.Match)
	 */
	@Override
	public int getNumberOfRivals(Match match)
	{
		int participantCount = participantManager.getNumberOfParticipants(match);

		if(match.getVictoryCondition() == EnumVictoryCondition.vendetta)
			return (int) Math.ceil((participantCount - 1) * match.getVictoryParameter() / 100.0);
		else
			return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.MatchManager#cancelMatch(com.syncnapsis.data.model.Match)
	 */
	@Override
	public Match cancelMatch(Match match)
	{
		Assert.isTrue(isAccessible(match, MatchAccessController.OPERATION_CANCEL), "no access rights for 'cancel match " + match.getId() + "'");

		if(match.getCanceledDate() != null || match.getFinishedDate() != null)
			return match; // throw Exception?

		Date now = new Date(securityManager.getTimeProvider().get());

		// finalize ranking
		for(Participant p : match.getParticipants())
		{
			p.getRank().setFinal(true);
			if(match.getState() == EnumMatchState.planned)
				p.setActivated(false);
			participantManager.save(p);
		}

		match.setCanceledDate(now);
		match.setState(EnumMatchState.canceled);

		return save(match);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.MatchManager#finishMatch(com.syncnapsis.data.model.Match)
	 */
	@Override
	public Match finishMatch(Match match)
	{
		// called by System

		if(match.getState() != EnumMatchState.active)
			return match; // throw Exception?

		// check victory condition
		if(!isVictoryConditionMet(match))
			return match; // throw Exception?

		Date now = new Date(securityManager.getTimeProvider().get());

		// finalize ranking
		for(Participant p : match.getParticipants())
		{
			p.getRank().setFinal(true);
			participantManager.save(p);
		}

		match.setFinishedDate(now);
		match.setState(EnumMatchState.finished);

		return save(match);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.data.service.MatchManager#isVictoryConditionMet(com.syncnapsis.data.model.
	 * Match)
	 */
	@Override
	public boolean isVictoryConditionMet(Match match)
	{
		// a leader/winner (if existing)
		// the leader is the participant with the oldest rankVictoryDate
		Participant leader = null;

		for(Participant p : match.getParticipants())
		{
			if(p.getRank().getVictoryDate() != null)
			{
				if(leader == null)
					leader = p;
				else if(p.getRank().getVictoryDate().before(leader.getRank().getVictoryDate()))
					leader = p;
				else if(p.getRank().getVictoryDate().equals(leader.getRank().getVictoryDate()) && p.getRank().getRank() < leader.getRank().getRank())
					leader = p;
			}
		}

		if(leader == null) // no one meets victory condition
			return false;

		long timeout = match.getVictoryTimeout();
		if(timeout > 0)
		{
			// victory condition is bound to a timeout
			long now = securityManager.getTimeProvider().get();

			if(leader.getRank().getVictoryDate().getTime() + timeout <= now)
				return true; // timeout passed
			else
				return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.MatchManager#updateRanking(com.syncnapsis.data.model.Match)
	 */
	@Override
	public Match updateRanking(Match match)
	{
		Date now = new Date(securityManager.getTimeProvider().get());

		long totalPopulation = 0;
		for(Participant p : match.getParticipants())
		{
			if(p.isActivated())
			{
				for(SolarSystemPopulation pop : p.getPopulations())
				{
					if(pop.getDestructionDate() == null)
						totalPopulation += pop.getPopulation();
				}
			}
		}

		logger.debug("total population = " + totalPopulation);

		int rankValue;
		double ref;
		int destroyed = 0;
		List<Participant> updatedParticipants = new LinkedList<Participant>();
		for(Participant p : match.getParticipants())
		{
			if(p.isActivated())
			{
				if(p.getDestructionDate() != null)
					destroyed++;

				// do not update rank, when rank is final (participant already destroyed)
				if(p.getRank().isFinal())
					continue;

				rankValue = 0;
				ref = 0;

				switch(match.getVictoryCondition())
				{
					case domination:
						ref = match.getGalaxy().getSolarSystems().size();
						for(SolarSystemPopulation pop : p.getPopulations())
						{
							// count +1/totalSystems for each existing population if the participant
							// is alone
							if(pop.getDestructionDate() == null)
							{
								int nonDestroyed = 0;
								// count total non-destroyed pops
								for(SolarSystemPopulation otherPop : pop.getInfrastructure().getPopulations())
								{
									if(otherPop.getDestructionDate() == null)
										nonDestroyed++;
								}

								if(nonDestroyed == 1) // alone
									rankValue++;
							}
						}
						break;
					case extermination:
						ref = totalPopulation;
						for(SolarSystemPopulation pop : p.getPopulations())
						{
							// count +pop for each existing population
							if(pop.getDestructionDate() == null)
								rankValue += pop.getPopulation();
						}
						break;
					case vendetta:
						ref = p.getRivals().size();
						for(Participant rival : p.getRivals())
						{
							// count 1/rivals for each destroyed rival
							if(rival.getDestructionDate() != null)
								rankValue++;
						}
						break;
				}

				// set raw rank value
				p.getRank().setRawValue(rankValue);
				// scale rank value to have percent
				rankValue = (int) Math.floor(100.0 * rankValue / ref);
				p.getRank().setValue(rankValue);

				// check victory condition
				if(p.getRank().getValue() >= match.getVictoryParameter())
				{
					// participants rank meets victory condition
					// set victory date if not yet set
					if(p.getRank().getVictoryDate() == null)
						p.getRank().setVictoryDate(now);
				}
				else
				{
					p.getRank().setVictoryDate(null);
				}

				updatedParticipants.add(p);
			}
		}

		Collections.sort(updatedParticipants, Participant.BY_RANKVALUE);

		int count = 0;
		int rank = 0;
		int value = 0;
		for(Participant p : updatedParticipants)
		{
			if(p.getRank().getValue() != value)
			{
				value = p.getRank().getValue();
				rank = count + 1;
			}

			if(p.getDestructionDate() == null)
			{
				count++;
				p.getRank().setRank(rank);
			}
			else
			{
				p.getRank().setRank(match.getParticipants().size() + 1 - destroyed);
				p.getRank().setFinal(true);
			}
			p.getRank().setDate(now);

			participantManager.save(p);
		}

		return get(match.getId());
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.MatchManager#createChannels(com.syncnapsis.data.model.Match)
	 */
	@Override
	public void createChannels(Match match)
	{
		// if(m.getState() != EnumMatchState.finished && m.getState() != EnumMatchState.canceled)
		// {
		// create channel for this match
		conquestManager.createChannel(
				UniverseConquestConstants.CHANNEL_MATCH_RANKS.replace(UniverseConquestConstants.CHANNEL_ID_PLACEHOLDER, "" + match.getId()), null);
		conquestManager.createChannel(
				UniverseConquestConstants.CHANNEL_MATCH_SYSTEMS.replace(UniverseConquestConstants.CHANNEL_ID_PLACEHOLDER, "" + match.getId()), null);
		conquestManager
				.createChannel(
						UniverseConquestConstants.CHANNEL_MATCH_MOVEMENTS.replace(UniverseConquestConstants.CHANNEL_ID_PLACEHOLDER,
								"" + match.getId()), null);
		// }
		updateChannels(match, true, true, true);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.MatchManager#updateChannels(com.syncnapsis.data.model.Match,
	 * boolean, boolean, boolean)
	 */
	@Override
	public void updateChannels(Match match, boolean updateRanks, boolean updateSystems, boolean updateMovements)
	{
		Date now = new Date(securityManager.getTimeProvider().get());

		if(updateRanks)
			conquestManager.update(
					UniverseConquestConstants.CHANNEL_MATCH_RANKS.replace(UniverseConquestConstants.CHANNEL_ID_PLACEHOLDER, "" + match.getId()),
					getRankList(match));
		if(updateSystems)
			conquestManager.update(
					UniverseConquestConstants.CHANNEL_MATCH_SYSTEMS.replace(UniverseConquestConstants.CHANNEL_ID_PLACEHOLDER, "" + match.getId()),
					getSystemList(match, now));
		if(updateMovements)
			conquestManager.update(
					UniverseConquestConstants.CHANNEL_MATCH_MOVEMENTS.replace(UniverseConquestConstants.CHANNEL_ID_PLACEHOLDER, "" + match.getId()),
					getMovementList(match, now));
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.MatchManager#getRankList(com.syncnapsis.data.model.Match)
	 */
	@Override
	public List<Rank> getRankList(Match match)
	{
		List<Rank> ranks = new LinkedList<Rank>();
		for(Participant p : match.getParticipants())
		{
			if(p.isActivated())
			{
				p.getRank().setDisplayName(p.getEmpire().getPlayer().getUser().getUsername());
				ranks.add(p.getRank());
			}
		}
		return ranks;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.MatchManager#getSystemList(com.syncnapsis.data.model.Match,
	 * java.util.Date)
	 */
	@Override
	public List<List<Long>> getSystemList(Match match, Date time)
	{
		List<List<Long>> populationList = new LinkedList<List<Long>>();
		List<Long> infEntry;
		for(SolarSystemInfrastructure inf : match.getInfrastructures())
		{
			// only add if population or infrastructure values have changed
			if(inf.isModified())
			{
				infEntry = new LinkedList<Long>();
				infEntry.add(inf.getSolarSystem().getId());
				infEntry.add((long) inf.getHabitability());
				infEntry.add(inf.getInfrastructure());

				Collections.sort(inf.getPopulations(), SolarSystemPopulation.BY_COLONIZATIONDATE);

				for(SolarSystemPopulation pop : inf.getPopulations())
				{
					if(pop.isPresent(time))
					{
						infEntry.add(pop.getParticipant().getEmpire().getId());
						infEntry.add(pop.getPopulation());
					}
				}
				populationList.add(infEntry);
			}
		}
		return populationList;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.syncnapsis.data.service.MatchManager#getMovementList(com.syncnapsis.data.model.Match,
	 * java.util.Date)
	 */
	@Override
	public List<List<Long>> getMovementList(Match match, Date time)
	{
		List<List<Long>> movementsList = new LinkedList<List<Long>>();
		List<Long> movEntry;
		for(SolarSystemInfrastructure inf : match.getInfrastructures())
		{
			for(SolarSystemPopulation pop : inf.getPopulations())
			{
				if(pop.isActivated() && pop.getColonizationDate().after(time))
				{
					movEntry = new LinkedList<Long>();
					movEntry.add(inf.getSolarSystem().getId());
					movEntry.add(pop.getOrigin().getInfrastructure().getSolarSystem().getId());
					movEntry.add(pop.getParticipant().getEmpire().getId());
					// movEntry.add(pop.getPopulation());
					// movEntry.add(pop.getStoredInfrastructure());
					long totalTravelTime = pop.getColonizationDate().getTime() - pop.getOriginationDate().getTime();
					long remainingTravelTime = pop.getColonizationDate().getTime() - time.getTime();
					movEntry.add(totalTravelTime);
					movEntry.add(remainingTravelTime);
					movementsList.add(movEntry);
				}
			}
		}
		return movementsList;
	}

	/**
	 * Get the mailer from the security manager (casted to {@link UniverseConquestMailer})
	 * 
	 * @return the mailer
	 */
	protected UniverseConquestMailer getMailer()
	{
		return (UniverseConquestMailer) securityManager.getMailer();
	}
}
