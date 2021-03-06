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

import com.syncnapsis.data.dao.PlayerRoleDao;
import com.syncnapsis.data.model.PlayerRole;
import com.syncnapsis.data.service.PlayerRoleManager;

/**
 * Manager-Implementierung f�r den Zugriff auf PlayerRole.
 * 
 * @author ultimate
 */
public class PlayerRoleManagerImpl extends GenericNameManagerImpl<PlayerRole, Long> implements PlayerRoleManager
{
	/**
	 * UserRoleDao f�r den Datenbankzugriff
	 */
	private PlayerRoleDao	playerRoleDao;

	/**
	 * Standard Constructor
	 * 
	 * @param playerRoleDao - PlayerRoleDao f�r den Datenbankzugriff
	 */
	public PlayerRoleManagerImpl(PlayerRoleDao playerRoleDao)
	{
		super(playerRoleDao);
		this.playerRoleDao = playerRoleDao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.PlayerRoleManager#getByMask(int)
	 */
	@Override
	public PlayerRole getByMask(int mask)
	{
		return playerRoleDao.getByMask(mask);
	}
}
