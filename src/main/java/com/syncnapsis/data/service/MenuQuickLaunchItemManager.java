package com.syncnapsis.data.service;

import java.util.List;

import com.syncnapsis.data.model.MenuQuickLaunchItem;

/**
 * Manager-Interface f�r den Zugriff auf MenuQuickLaunchItem.
 * 
 * @author ultimate
 */
public interface MenuQuickLaunchItemManager extends GenericManager<MenuQuickLaunchItem, Long>
{

	/**
	 * Lade eine Liste aller Schnellstart-Men�eintr�ge f�r einen Benutzer.
	 * 
	 * @param userId - die ID des Benutzers
	 * @return die Liste der Schnellstart-Men�eintr�ge
	 */
	public List<MenuQuickLaunchItem> getByUser(Long userId);

	/**
	 * F�gt einen Men�eintrag in das Schnellstartmen� eines Benutzers an einer
	 * bestimmten Position ein.
	 * 
	 * @param userId - der Prim�rschl�ssel des Benutzers
	 * @param miId - der Prim�rschl�ssel des Men�eintrags
	 * @param qlPosTo - die Position f�r den Men�eintrag
	 * @return das aktualisierte Schnellstartmen� des Benutzers
	 */
	public List<MenuQuickLaunchItem> addQuickLaunchItem(Long userId, String miId, int qlPosTo);

	/**
	 * L�scht einen Men�eintrag aus dem Schnellstartmen� eines Benutzers von
	 * einer bestimmten Position
	 * 
	 * @param userId - der Prim�rschl�ssel des Benutzers
	 * @param qlPosFrom - die Position des zu l�schenden Men�eintrags
	 * @return das aktualisierte Schnellstartmen� des Benutzers
	 */
	public List<MenuQuickLaunchItem> removeQuickLaunchItem(Long userId, int qlPosFrom);

	/**
	 * Verschiebt einen Men�eintrag im Schnellstartmen� eines Benutzers von
	 * einer Position an eine andere
	 * 
	 * @param userId - der Prim�rschl�ssel des Benutzers
	 * @param qlPosFrom - die Position von der der Men�eintrag entfernt werden
	 *            soll
	 * @param qlPosTo - die neue Position an der der Men�eintrag eingef�gt
	 *            werden soll
	 * @return das aktualisierte Schnellstartmen� des Benutzers
	 */
	public List<MenuQuickLaunchItem> moveQuickLaunchItem(Long userId, int qlPosFrom, int qlPosTo);
}
