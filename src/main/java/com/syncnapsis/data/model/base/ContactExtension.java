package com.syncnapsis.data.model.base;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.syncnapsis.data.model.base.Contact;
import com.syncnapsis.data.model.ContactGroup;
import com.syncnapsis.data.model.AuthoritiesGenericImpl;

/**
 * Erweiterung der abstrakten Klasse Contact f�r die Zuordnung zu ContactGroups
 * 
 * @author ultimate
 * @param <C1> - die Klasse f�r die eine Seite der Kontaktzuordnung
 * @param <C2> - die Klasse f�r die andere Seite der Kontaktzuordnung
 */
@MappedSuperclass
public abstract class ContactExtension<C1 extends BaseObject<?>, C2 extends BaseObject<?>> extends Contact<C1, C2, AuthoritiesGenericImpl>
{

	/**
	 * Die Liste der Kontaktgruppen, denen diese Kontaktzuordnung zugeordnet
	 * ist. Eine Kontaktzuordnung kann grunds�tzlich in mehreren Kontaktgruppen
	 * enthalten sein.
	 */
	private List<ContactGroup>	contactGroups;

	/**
	 * Die Liste der Kontaktgruppen, denen diese Kontaktzuordnung zugeordnet
	 * ist. Eine Kontaktzuordnung kann grunds�tzlich in mehreren Kontaktgruppen
	 * enthalten sein.
	 * 
	 * @return contactGroups
	 */
	// Mapping in Subclass
	@Transient
	public List<ContactGroup> getContactGroups()
	{
		return contactGroups;
	}

	/**
	 * Die Liste der Kontaktgruppen, denen diese Kontaktzuordnung zugeordnet
	 * ist. Eine Kontaktzuordnung kann grunds�tzlich in mehreren Kontaktgruppen
	 * enthalten sein.
	 * 
	 * @param contactGroups - die Liste
	 */
	public void setContactGroups(List<ContactGroup> contactGroups)
	{
		this.contactGroups = contactGroups;
	}

}
