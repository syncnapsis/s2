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

import com.syncnapsis.data.dao.EmpireRankDao;
import com.syncnapsis.data.model.Empire;
import com.syncnapsis.data.model.EmpireRank;
import com.syncnapsis.data.service.EmpireRankManager;

/**
 * Manager-Implementierung f�r den Zugriff auf EmpireRank.
 * 
 * @author ultimate
 */
public class EmpireRankManagerImpl extends GenericRankManagerImpl<EmpireRank, Empire, Long> implements EmpireRankManager
{
	/**
	 * EmpireRankDao f�r den Datenbankzugriff
	 */
	@SuppressWarnings("unused")
	private EmpireRankDao empireRankDao;

	/**
	 * Standard-Constructor, der die DAO speichert
	 * 
	 * @param empireRankDao - die Dao
	 */
	public EmpireRankManagerImpl(EmpireRankDao empireRankDao)
	{
		super(empireRankDao);
		this.empireRankDao = empireRankDao;
	}

}
