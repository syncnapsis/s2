package com.syncnapsis.data.service;

import java.io.Serializable;
import java.util.List;

import com.syncnapsis.data.model.base.BaseObject;
import com.syncnapsis.data.model.base.Rank;

/**
 * Manager-Interface f�r den Zugriff auf Rank.
 * 
 * @author ultimate
 * @param <R> - die Rank-Klasse
 * @param <T> - die Klasse auf die sich die Bewertung bezieht
 * @param <PK> - der Primary-Key-Typ des vom Rank aus referenzierten Objekts
 */
public interface RankManager<R extends Rank<T>, T extends BaseObject<PK>, PK extends Serializable> extends GenericManager<R, Long>
{
	/**
	 * Gibt den aktuellen Rang zu einem Objekt zur�ck
	 * 
	 * @param entityId - die ID des Objekts
	 * @return der Rang
	 */
	public R getByEntity(PK entityId);

	/**
	 * Gibt die Historie der R�nge zu einem Objekt zur�ck
	 * 
	 * @param entityId - die ID des Objekts
	 * @return der Historie der R�nge
	 */
	public List<R> getHistoryByEntity(PK entityId);

	/**
	 * Gibt alle aktuellen R�nge sortiert nach dem Bewertungskriterium zur�ck.
	 * 
	 * @param criterion - das Bewertungskriterium
	 * @return die Liste der R�nge
	 */
	public List<R> getByCriterion(String criterion);

	/**
	 * Gibt alle aktuellen R�nge sortiert nach dem prim�ren Bewertungskriterium der Default-Kategorie zur�ck.
	 * 
	 * @return die Liste der R�nge
	 */
	public List<R> getByDefaultPrimaryCriterion();
}