package com.syncnapsis.data.dao;

import java.util.List;

import com.syncnapsis.data.model.MenuItem;

/**
 * Dao-Interface f�r den Zugriff auf MenuItem
 * 
 * @author ultimate
 */
public interface MenuItemDao extends GenericDao<MenuItem, String>
{
	/**
	 * L�dt einen Men�eintrag und l�st diesen ggf. von der Session, damit er
	 * manipulierbar ist, ohne gespeichert werden zu m�ssen.
	 * 
	 * @param id - die ID des Men�eintrags
	 * @param evict - soll das MenuItem-Objekt von der Session gel�st werden?
	 * @return das MenuItem-Objekt
	 */
	public MenuItem get(String id, boolean evict);

	/**
	 * L�dt die Submen�eintr�ge zu einem �bergeordneten Men�eintrag.
	 * 
	 * @param parentId - die ID des �bergeordneten Men�eintrags
	 * @param includeAdvanced - sollen erweiterte Menueintr�ge mit geladen
	 *            werden?
	 * @return die Liste der Submen�eintr�ge
	 */
	public List<MenuItem> getChildren(String parentId, boolean includeAdvanced);
}
