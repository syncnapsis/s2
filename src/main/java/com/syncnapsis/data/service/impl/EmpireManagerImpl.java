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
package com.syncnapsis.data.service.impl;

import java.util.List;

import com.syncnapsis.data.dao.EmpireDao;
import com.syncnapsis.data.model.Empire;
import com.syncnapsis.data.service.EmpireManager;

/**
 * Manager-Implementierung f�r den Zugriff auf Empire.
 * 
 * @author ultimate
 */
public class EmpireManagerImpl extends GenericNameManagerImpl<Empire, Long> implements EmpireManager
{
	/**
	 * EmpireDao f�r den Datenbankzugriff
	 */
	private EmpireDao	empireDao;

	/**
	 * Standard-Constructor
	 * 
	 * @param empireDao - EmpireDao f�r den Datenbankzugriff
	 */
	public EmpireManagerImpl(EmpireDao empireDao)
	{
		super(empireDao);
		this.empireDao = empireDao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.EmpireManager#getByPlayer(java.lang.Long)
	 */
	@Override
	public List<Empire> getByPlayer(Long userId)
	{
		return empireDao.getByPlayer(userId);
	}
}
