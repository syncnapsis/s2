package com.syncnapsis.data.service;

import java.util.List;

import com.syncnapsis.data.model.AllianceMemberRank;

/**
 * Manager-Interface f�r den Zugriff auf AllianceMemberRank
 * 
 * @author ultimate
 */
public interface AllianceMemberRankManager extends GenericManager<AllianceMemberRank, Long>
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
