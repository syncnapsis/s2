package com.syncnapsis.data.service.impl;

import java.util.List;

import com.syncnapsis.data.dao.AllianceDao;
import com.syncnapsis.data.model.Alliance;
import com.syncnapsis.data.service.AllianceManager;

/**
 * Manager-Implementierung f�r den Zugriff auf Alliance.
 * 
 * @author ultimate
 */
public class AllianceManagerImpl extends GenericNameManagerImpl<Alliance, Long> implements AllianceManager
{
	/**
	 * AllianceDao f�r den Datenbankzugriff
	 */
	private AllianceDao	allianceDao;

	/**
	 * Standard-Constructor
	 * @param allianceDao - AllianceDao f�r den Datenbankzugriff
	 */
	public AllianceManagerImpl(AllianceDao allianceDao)
	{
		super(allianceDao);
		this.allianceDao = allianceDao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.AllianceManager#getByEmpire(java.lang.Long)
	 */
	@Override
	public List<Alliance> getByEmpire(Long empireId)
	{
		return allianceDao.getByEmpire(empireId);
	}
}
