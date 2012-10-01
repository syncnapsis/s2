package com.syncnapsis.enums;

import com.syncnapsis.constants.ApplicationBaseConstants;

/**
 * Enum f�r die Spezifizierung des Alters eines News-Objekts. Bei der Suche nach
 * News kann ein EnumNewsAge-Objekt �bergeben werden und es werden alle News
 * zur�ckgeliefert, die neuer sind als das spezifizierte Alter. Die Aufl�sung
 * der Enum-Werte als Zeitangeben geschiet �ber die Tabelle Parameter und das
 * Namens-Pattern Constants.PARAM_NEWS_MAXAGE = "news.%L.maxAge"
 * 
 * @author ultimate
 */
public enum EnumNewsAge
{
	/**
	 * Alters-Interval 1 (k�rzester Wert)
	 */
	length1,
	/**
	 * Alters-Interval 2 (2. k�rzester Wert)
	 */
	length2,
	/**
	 * Alters-Interval 3
	 */
	length3,
	/**
	 * Alters-Interval 4
	 */
	length4,
	/**
	 * Alters-Interval 5
	 */
	length5,
	/**
	 * Alters-Interval 6
	 */
	length6,
	/**
	 * Alters-Interval 7
	 */
	length7,
	/**
	 * Alters-Interval 8 (2. l�ngster Wert)
	 */
	length8,
	/**
	 * Alters-Interval 9 (l�ngster Wert)
	 */
	length9;

	/**
	 * Wandelt dieses Enum in einen Primary-Key f�r die Verwendung mit der
	 * Parameter-Tabelle um. Es wird das Namens-Pattern
	 * Constants.PARAM_NEWS_MAXAGE = "news.%L.maxAge" verwendet.
	 * 
	 * @return der Parameter-Key zu diesem Enum zu diesem Enum
	 */
	public String getParameterKey()
	{
		return ApplicationBaseConstants.PARAM_NEWS_MAXAGE.replace("%L", this.toString());
	}
}
