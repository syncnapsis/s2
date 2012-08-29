package com.syncnapsis.data.dao;

import java.util.List;

import com.syncnapsis.data.model.MenuQuickLaunchItem;

/**
 * Dao-Interface f�r den Zugriff auf MenuQuickLaunchItem
 * 
 * @author ultimate
 */
public interface MenuQuickLaunchItemDao extends GenericDao<MenuQuickLaunchItem, Long>
{
	/**
	 * Lade eine Liste aller Schnellstart-Men�eintr�ge f�r einen Benutzer.
	 * 
	 * @param userId - die ID des Benutzers
	 * @return die Liste der Schnellstart-Men�eintr�ge
	 */
	public List<MenuQuickLaunchItem> getByUser(Long userId);
}
