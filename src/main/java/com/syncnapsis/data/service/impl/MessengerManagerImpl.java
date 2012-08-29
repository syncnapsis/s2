package com.syncnapsis.data.service.impl;

import com.syncnapsis.data.dao.MessengerDao;
import com.syncnapsis.data.model.Messenger;
import com.syncnapsis.data.service.MessengerManager;

/**
 * Manager-Implementierung f�r den Zugriff auf Messenger.
 * 
 * @author ultimate
 */
public class MessengerManagerImpl extends GenericNameManagerImpl<Messenger, Long> implements MessengerManager
{
	/**
	 * MessengerDao f�r den Datenbankzugriff
	 */
	@SuppressWarnings("unused")
	private MessengerDao messengerDao;
	
	/**
	 * Standard-Constructor
	 * @param messengerDao - MessengerDao f�r den Datenbankzugriff
	 */
	public MessengerManagerImpl(MessengerDao messengerDao)
	{
		super(messengerDao);
		this.messengerDao = messengerDao;
	}
}
