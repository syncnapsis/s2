package com.syncnapsis.enums;

/**
 * Enum f�r die Spezifizierung der dynamischen Knoten eines Menu-Baums. Die
 * dynamischen Knoten werden beim Rendern des Baumes gesondert behandelt und mit
 * Parametern versehen und ggf. mehrfach mit verschiedenen Werten eingef�gt.
 * 
 * @author ultimate
 */
public enum EnumMenuItemDynamicType
{
	/**
	 * Elternknoten f�r eine Liste aus Spezialeintr�gen (Imperien/Allianzen etc.)
	 */
	list,
	/**
	 * Knoten f�r ein Eintrag innerhalb einer Liste
	 */
	option,
	/**
	 * Knoten f�r ein Eintrag innerhalb einer Liste, der gleichzeitig wieder eine Liste ist
	 * @see EnumMenuItemDynamicType#list
	 */
	option_list,
	/**
	 * Knoten unterhalb eines option-Knotens, der den Parameter erbt
	 */
	choice,
	/**
	 * Knoten mit einem aktuell ausgew�hlten Element als Parameter
	 */
	current;
}
