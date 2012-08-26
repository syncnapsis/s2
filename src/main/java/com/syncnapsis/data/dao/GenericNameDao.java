package com.syncnapsis.data.dao;

import java.io.Serializable;
import java.util.List;

import com.syncnapsis.data.model.base.BaseObject;

/**
 * Generische DAO (Data Access Object), die erweiterte Operationen nach Name zur Verf�gung stellt.
 * 
 * @param <T> die Model-Klasse
 * @param <PK> die Klasse des Prim�rschl�ssels
 * @author ultimate
 */
public interface GenericNameDao<T extends BaseObject<PK>, PK extends Serializable> extends GenericDao<T, PK>
{
	/**
	 * Lade eine Liste aller Eintr�ge, sortiert nach Name
	 * 
	 * @param returnOnlyActivated - sollen nur aktivierte Eintr�ge zur�ckgegeben
	 *            werden?
	 * @return die Liste aller Eintr�ge
	 */
	public List<T> getOrderedByName(boolean returnOnlyActivated);
	
	/**
	 * Lade eine Liste aller Eintr�ge, sortiert nach Name mit eingeschr�nktem Pr�fix des Namens
	 * 
	 * @param prefix - die Anfangsbuchstaben des Namens
	 * @param nRows - die maximale Anzahl an zur�ckzugebenen Ergebnisses (negativ = alle)
	 * @param returnOnlyActivated - sollen nur aktivierte Eintr�ge zur�ckgegeben
	 *            werden?
	 * @return die Liste aller Eintr�ge
	 */
	public List<T> getByPrefix(String prefix, int nRows, boolean returnOnlyActivated);
	
	/**
	 * Lade einen Eintrag anhand seines Namens
	 * 
	 * @param name - der Name
	 * @return de Eintrag
	 */
	public T getByName(String name);

	/**
	 * Pr�ft, ob ein gew�nschter Name f�r diese Klasse noch verf�gbar ist.
	 * 
	 * @param name - der zu pr�fende Name
	 * @return true oder false
	 */
	public boolean isNameAvailable(String name);
}