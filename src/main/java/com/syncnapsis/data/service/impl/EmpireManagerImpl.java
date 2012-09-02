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
