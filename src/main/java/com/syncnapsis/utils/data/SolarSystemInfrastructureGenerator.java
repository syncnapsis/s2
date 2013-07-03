/**
 * Syncnapsis Framework - Copyright (c) 2012 ultimate
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
package com.syncnapsis.utils.data;

import com.syncnapsis.constants.UniverseConquestConstants;
import com.syncnapsis.data.model.Match;
import com.syncnapsis.data.model.Parameter;
import com.syncnapsis.data.model.SolarSystem;
import com.syncnapsis.data.model.SolarSystemInfrastructure;
import com.syncnapsis.data.service.ParameterManager;

/**
 * Generator randomly generating SolarSystemInfrastructures for the SolarSystems within a Galaxy.<br>
 * The generation is influenced by the parameters for
 * <ul>
 * <li>habitability (min & max) : see {@link SolarSystemInfrastructure#getHabitability()}</li>
 * <li>infrastructure (min & max) : see {@link SolarSystemInfrastructure#getInfrastructure()}</li>
 * <li>size (min & max) : see {@link SolarSystemInfrastructure#getSize()}</li>
 * </ul>
 * obtained from the database (see {@link Parameter}
 * 
 * @author ultimate
 */
public class SolarSystemInfrastructureGenerator extends Generator<SolarSystemInfrastructure>
{
	/**
	 * The ParameterManager
	 */
	protected ParameterManager	parameterManager;

	/**
	 * Construct a new SolarSystemInfrastructureGenerator with the given referenced managers
	 * 
	 * @param parameterManager - the ParameterManager
	 */
	public SolarSystemInfrastructureGenerator(ParameterManager parameterManager)
	{
		super();
		this.parameterManager = parameterManager;
	}

	/**
	 * Construct a new SolarSystemInfrastructureGenerator with the given referenced managers
	 * 
	 * @param parameterManager - the ParameterManager
	 * @param random - the extended random number generator
	 */
	public SolarSystemInfrastructureGenerator(ParameterManager parameterManager, ExtendedRandom random)
	{
		super(random);
		this.parameterManager = parameterManager;
	}

	/**
	 * Construct a new SolarSystemInfrastructureGenerator with the given referenced managers
	 * 
	 * @param parameterManager - the ParameterManager
	 * @param seed - the seed for the Random
	 */
	public SolarSystemInfrastructureGenerator(ParameterManager parameterManager, long seed)
	{
		super(seed);
		this.parameterManager = parameterManager;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.utils.data.Generator#generate(com.syncnapsis.utils.data.ExtendedRandom,
	 * java.lang.Object[])
	 */
	@Override
	public SolarSystemInfrastructure generate(ExtendedRandom random, Object... args)
	{
		if(args.length < 2)
			throw new IllegalArgumentException("match & solarsystem must be passed as arguments!");
		Match match = (Match) args[0];
		SolarSystem system = (SolarSystem) args[1];

		int hab = random.nextGaussian(0, parameterManager.getInteger(UniverseConquestConstants.PARAM_SOLARSYSTEM_HABITABILITY_MAX));
		int size = random.nextGaussian(0, parameterManager.getInteger(UniverseConquestConstants.PARAM_SOLARSYSTEM_SIZE_MAX));
		long maxPop = (long) hab * size * parameterManager.getInteger(UniverseConquestConstants.PARAM_SOLARSYSTEM_MAX_POPULATION_FACTOR);
		// for the infrastructure we would like 0 most of the time
		long inf = random.nextGaussian(-maxPop, maxPop);
		// 50% chance for negative values (= no infrastructure)
		if(inf < 0)
			inf = 0;

		SolarSystemInfrastructure infrastructure = new SolarSystemInfrastructure();
		infrastructure.setActivated(true);
		infrastructure.setFirstColonizationDate(null);
		infrastructure.setHabitability(hab);
		infrastructure.setInfrastructure(inf);
		infrastructure.setMatch(match);
		infrastructure.setSize(size);
		infrastructure.setSolarSystem(system);

		return infrastructure;
	}
}
