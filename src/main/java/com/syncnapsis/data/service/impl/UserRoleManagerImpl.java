package com.syncnapsis.data.service.impl;

import com.syncnapsis.data.dao.UserRoleDao;
import com.syncnapsis.data.model.UserRole;
import com.syncnapsis.data.service.UserRoleManager;

/**
 * Manager-Implementierung f�r den Zugriff auf UserRole.
 * 
 * @author ultimate
 */
public class UserRoleManagerImpl extends GenericNameManagerImpl<UserRole, Long> implements UserRoleManager
{
	/**
	 * UserRoleDao f�r den Datenbankzugriff
	 */
	@SuppressWarnings("unused")
	private UserRoleDao			userRoleDao;

	/**
	 * Standard Constructor
	 * 
	 * @param userRoleDao - UserRoleDao f�r den Datenbankzugriff
	 */
	public UserRoleManagerImpl(UserRoleDao userRoleDao)
	{
		super(userRoleDao);
		this.userRoleDao = userRoleDao;
	}
}
