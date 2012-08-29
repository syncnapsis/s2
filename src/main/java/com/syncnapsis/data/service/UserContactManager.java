package com.syncnapsis.data.service;

import java.util.List;

import com.syncnapsis.data.model.UserContact;
import com.syncnapsis.exceptions.UserContactExistsException;
import com.syncnapsis.exceptions.UserSelectionInvalidException;

/**
 * Manager-Interface f�r den Zugriff auf UserRole.
 * 
 * @author ultimate
 */
public interface UserContactManager extends GenericManager<UserContact, Long>
{
	/**
	 * Hole alle UserContacts zu einem User.
	 * 
	 * @param userId - die id des users
	 * @return die Liste der UserContacts
	 */
	public List<UserContact> getByUser(Long userId);

	/**
	 * L�dt einen UserContact anhand der beiden User. Die Reigenfolge der User muss nicht mit dem
	 * UserContact �bereinstimmen.
	 * 
	 * @param userId1 - der erste User
	 * @param userId2 - der zweite User
	 * @return der UserContact, falls er existiert
	 */
	public UserContact getUserContact(Long userId1, Long userId2);

	/**
	 * Erstellt einen neuen UserContact, bei dem User1 best�tigt ist, User2 jedoch noch nicht.
	 * 
	 * @param userId1 - der erste User
	 * @param userId2 - der zweite User
	 * @return der erstellte UserContact
	 */
	public UserContact addUserContact(Long userId1, Long userId2) throws UserContactExistsException, UserSelectionInvalidException;

	/**
	 * Best�tigt einen UserContact. Da vom erstellenden bereits immer eine Best�tigung vorliegt
	 * braucht kein User �bergeben werden. Es werden beide Flags auf true gesetzt.
	 * 
	 * @param userContactId
	 * @return der best�tigte UserContact
	 */
	public UserContact approveUserContact(Long userContactId);
}
