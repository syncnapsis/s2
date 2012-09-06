package com.syncnapsis.data.service;

import com.syncnapsis.data.model.GUIAction;

/**
 * Manager-Interface f�r den Zugriff auf GUIAction.
 * 
 * @author ultimate
 */
public interface GUIActionManager extends GenericNameManager<GUIAction, Long>
{
	/**
	 * L�dt eine GUIAction anhand ihrer WindowId.
	 * 
	 * @param windowId - der WindowId
	 * @return das GUIAction-Objekt
	 */
	public GUIAction getByWindowId(String windowId);
}
