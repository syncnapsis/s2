package com.syncnapsis.data.dao.hibernate;

import com.syncnapsis.data.dao.MessengerDao;
import com.syncnapsis.data.model.Messenger;

/**
 * Dao-Implementierung f�r Hibernate f�r den Zugriff auf AllianceAllianceContactGroup
 * 
 * @author ultimate
 */
public class MessengerDaoHibernate extends GenericNameDaoHibernate<Messenger, Long> implements MessengerDao
{
	/**
	 * Erzeugt eine neue DAO-Instanz durch die Super-Klasse GenericDaoHibernate
	 * mit der Modell-Klasse Messenger
	 */
	public MessengerDaoHibernate()
	{
		super(Messenger.class, "name");
	}
}
