package com.syncnapsis.data.service.impl;

import java.util.List;

import com.syncnapsis.data.dao.UserContactDao;
import com.syncnapsis.data.model.User;
import com.syncnapsis.data.model.UserContact;
import com.syncnapsis.data.service.UserContactManager;
import com.syncnapsis.data.service.UserManager;
import com.syncnapsis.exceptions.UserContactExistsException;
import com.syncnapsis.exceptions.UserSelectionInvalidException;

/**
 * Manager-Implementierung f�r den Zugriff auf UserContact.
 * 
 * @author ultimate
 */
public class UserContactManagerImpl extends GenericManagerImpl<UserContact, Long> implements UserContactManager
{
	/**
	 * UserContactDao f�r den Datenbankzugriff
	 */
	private UserContactDao userContactDao;
	/**
	 * UserManager f�r den Datenbankzugriff
	 */
	private UserManager userManager;
	
	/**
	 * Standard-Constructor
	 * @param userContactDao - UserContactDao f�r den Datenbankzugriff
	 * @param userManager - UserManager f�r den Datenbankzugriff 
	 */
	public UserContactManagerImpl(UserContactDao userContactDao, UserManager userManager)
	{
		super(userContactDao);
		this.userContactDao = userContactDao;
		this.userManager = userManager;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.UserContactManager#getByUser(java.lang.Long)
	 */
	@Override
	public List<UserContact> getByUser(Long userId)
	{
		return userContactDao.getByUser(userId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.UserContactManager#getUserContact(java.lang.Long, java.lang.Long)
	 */
	@Override
	public UserContact getUserContact(Long userId1, Long userId2)
	{
		return userContactDao.getUserContact(userId1, userId2);
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.UserContactManager#addUserContact(java.lang.Long, java.lang.Long)
	 */
	@Override
	public UserContact addUserContact(Long userId1, Long userId2) throws UserContactExistsException, UserSelectionInvalidException
	{
		if(userId2.equals(userId1))
			throw new UserSelectionInvalidException("user2 equals user1");

		User user1 = userManager.get(userId1);
		User user2 = userManager.get(userId2);
		
		for(UserContact contact : user1.getUserContacts())
		{
			if(contact.getOtherUser(user1).equals(user2))
			{
				throw new UserContactExistsException("UserContact already exists!", contact);
			}
		}
		
		UserContact newContact = new UserContact();
		newContact.setUser1(user1);
		newContact.setApprovedByUser1(true);
		newContact.setUser2(user2);
		newContact.setApprovedByUser2(false);
		newContact = save(newContact);
		
		return newContact;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.service.UserContactManager#approveUserContact(java.lang.Long)
	 */
	@Override
	public UserContact approveUserContact(Long userContactId)
	{
		UserContact contact = get(userContactId);
		contact.setApprovedByUser1(true);
		contact.setApprovedByUser2(true);	
		return save(contact);
	}
}
