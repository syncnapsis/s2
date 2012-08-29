package com.syncnapsis.data.service.impl;

import com.syncnapsis.data.dao.GUIActionDao;
import com.syncnapsis.data.model.GUIAction;
import com.syncnapsis.data.service.GUIActionManager;

/**
 * Manager-Implementierung f�r den Zugriff auf GUIAction.
 * 
 * @author ultimate
 */
public class GUIActionManagerImpl extends GenericNameManagerImpl<GUIAction, Long> implements GUIActionManager
{
	/**
	 * GUIActionDao f�r den Datenbankzugriff
	 */
	private GUIActionDao guiActionDao;
	
	/**
	 * Standard-Constructor
	 * @param guiActionDao - GUIActionDao f�r den Datenbankzugriff
	 */
	public GUIActionManagerImpl(GUIActionDao guiActionDao)
	{
		super(guiActionDao);
		this.guiActionDao = guiActionDao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.GUIActionManager#getByWindowId(java.lang.String)
	 */
	@Override
	public GUIAction getByWindowId(String windowId)
	{
		return guiActionDao.getByWindowId(windowId);
	}
}
