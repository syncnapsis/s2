package com.syncnapsis.data.service;

import java.util.Date;
import java.util.List;

import com.syncnapsis.data.model.News;
import com.syncnapsis.enums.EnumLocale;
import com.syncnapsis.enums.EnumNewsAge;

/**
 * Manager-Interface f�r den Zugriff auf News.
 * 
 * @author ultimate
 */
public interface NewsManager extends GenericManager<News, Long>
{
	/**
	 * Lade alle NewsIds bis zu einem bestimmten maximalen Alter, ausgehend von
	 * einem Referenzdatum.
	 * 
	 * @param maxAge - die Konstante f�r das maximale Alter
	 * @param referenceDate - das Referenzdatum
	 * @return eine Liste mit den NewsIds
	 */
	public List<String> getIdsByMaxAge(EnumNewsAge maxAge, Date referenceDate);

	/**
	 * L�dt die NewsIds aller News, die zu dem gegebenen Datum aktuell sind.
	 * Dazu wird getIdsByMaxAge f�r alle Werte aus EnumNewsAge mit referenceDate
	 * aufgerufen.
	 * 
	 * @param referenceDate - das Referenzdatum
	 * @return eine Liste mit den NewsIds
	 */
	public List<String> getActualIds(Date referenceDate);

	/**
	 * L�dt eine Liste aller aktuellen News bis zu einer bestimmten H�chstzahl
	 * sortiert nach Datum. Dabei sind dies alle News, deren NewsIds �ber
	 * getActualIds ermittelt wurden, sortiert nach Priorit�t. Wird eine
	 * bestimmte Maximalzahl an News �berschritten, so werden nur die
	 * obersten/wichtigsten bis zur Maximalzahl zur�ckgegeben.
	 * 
	 * @param locale - die Sprache
	 * @param referenceDate - das Referenzdatum
	 * @return eine Liste mit den News
	 */
	public List<News> getActual(EnumLocale locale, Date referenceDate);

	/**
	 * Lade ein News-Objekt anhand einer NewsId und einer vorgegebene Sprache.
	 * Zu jeder NewsId kann es ein News-Objekt in jeder verf�gbaren Sprache
	 * geben, sofern diese vom Verfasser der Nachricht angelegt wurden.
	 * 
	 * @param newsId - die NewsId
	 * @param locale - die Sprache
	 * @return das News-Objekt
	 */
	public News getByNewsIdAndLocale(String newsId, EnumLocale locale);
}
