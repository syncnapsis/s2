package com.syncnapsis.enums;

/**
 * Enum f�r die Spezifizierung der Haupttypen der Knoten eines Menu-Baums. Die
 * Knoten werden beim Rendern des Baumes gesondert behandelt und auf
 * verschiedene Weise dargestellt.
 * 
 * @author ultimate
 */
public enum EnumMenuItemType
{
	/**
	 * Wurzelknoten. Dient als Ausgangspunkt f�r das Laden eines Baumes
	 */
	root,
	/**
	 * Knoten. Gew�hnliches Element innerhalb des Baumes
	 */
	node,
	/**
	 * Trennelement. Dient der Formatierung des Baumes und der optischen
	 * Trennung mehrere Men�bereiche der gleichen Ebene von einander.
	 */
	separator;
}
