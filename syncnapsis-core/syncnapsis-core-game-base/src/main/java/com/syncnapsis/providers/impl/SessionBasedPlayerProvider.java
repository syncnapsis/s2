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
package com.syncnapsis.providers.impl;

import com.syncnapsis.constants.GameBaseConstants;
import com.syncnapsis.data.model.Player;
import com.syncnapsis.providers.PlayerProvider;
import com.syncnapsis.providers.SessionBasedProvider;

/**
 * Extension of SessionBasedProvider for the current Player.
 * 
 * @author ultimate
 */
public class SessionBasedPlayerProvider extends SessionBasedProvider<Player> implements PlayerProvider
{
	/**
	 * Default-Constructor configuring SessionBasedProvider with
	 * {@link GameBaseConstants#SESSION_PLAYER_KEY}.
	 */
	public SessionBasedPlayerProvider()
	{
		super(GameBaseConstants.SESSION_PLAYER_KEY);
	}
}
