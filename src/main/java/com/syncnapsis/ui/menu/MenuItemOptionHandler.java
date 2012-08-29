package com.syncnapsis.ui.menu;

import java.util.List;

import com.syncnapsis.data.model.MenuItem;

/**
 * Klasse, die die Erstellung von Submen�s f�r bestimmte Typen behandelt.
 * 
 * @author ultimate
 */
public interface MenuItemOptionHandler
{
	/**
	 * Soll dieser Handler angewendet werden?
	 * 
	 * @param dynamicSubType - der dynamicSubType
	 * @return true oder false
	 */
	public boolean applies(String dynamicSubType);

	/**
	 * Behandelt die Erstellung des Submen�s
	 * 
	 * @param menuItem - der Men�eintrag f�r das Submen�
	 * @return die Liste der erstellen Men�punkte
	 */
	public List<MenuItem> createOptions(MenuItem menuItem);

	/**
	 * Behandelt die Erstellung eines ausgew�hlten Eintrags
	 * 
	 * @param menuItem - der Men�eintrag f�r das Submen�
	 * @return die Liste der erstellen Men�punkte
	 */
	public List<MenuItem> createCurrent(MenuItem menuItem);

	/**
	 * Gibt die Bezeichnung f�r einen Men�eintrag zur�ck
	 * 
	 * @param menuItem - der Menueintrag
	 * @return die Bezeichnung
	 */
	public String getLabel(MenuItem menuItem);
}
