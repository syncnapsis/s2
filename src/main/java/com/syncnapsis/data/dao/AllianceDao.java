package com.syncnapsis.data.dao;

import java.util.List;

import com.syncnapsis.data.model.Alliance;

/**
 * Dao-Interface f�r den Zugriff auf Alliance
 * 
 * @author ultimate
 */
public interface AllianceDao extends GenericNameDao<Alliance, Long>
{
	/**
	 * Lade alle Allianzen zu einem Imperium.<br/>
	 * Da die Verkn�pfung von Imperium und Allianz �ber Allianzr�nge geschieht,
	 * muss hier eine Filterung erfolgen, die verhindert, dass eine Allianz, zu
	 * der ein Imperium mehrere R�nge hat, doppelt zur�ck gegeben wird.
	 * 
	 * @param empireId - die ID des Imperiums
	 * @return eine Liste aller Allianzen des Imperiums
	 */
	public List<Alliance> getByEmpire(Long empireId);
}
