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
	@SuppressWarnings("unused")
	private PlayerRoleDao			playerRoleDao;

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
}
