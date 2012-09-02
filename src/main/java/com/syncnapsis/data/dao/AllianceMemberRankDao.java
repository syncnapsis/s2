package com.syncnapsis.data.dao;

import java.util.List;

import com.syncnapsis.data.model.AllianceMemberRank;

/**
 * Dao-Interface f�r den Zugriff auf AllianceMemberRank
 * 
 * @author ultimate
 */
public interface AllianceMemberRankDao extends GenericDao<AllianceMemberRank, Long>
{
	/**
	 * Lade alle Allianzr�nge zu einem Imperium.<br/>
	 * Im gegensatz zu AllianceDao.getByEmpire(...) findet hier keine Filterung
	 * statt und es werden auch mehrfache R�nge zu einer Allianz zur�ckgegeben.
	 * 
	 * @param empireId - die ID des Imperiums
	 * @return die Liste der Allianz-R�nge
	 */
	public List<AllianceMemberRank> getByEmpire(Long empireId);
}
