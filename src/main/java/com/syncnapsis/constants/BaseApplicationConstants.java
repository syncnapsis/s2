package com.syncnapsis.constants;

/**
 * Klasse f�r das Festhalten von Konstanten, die f�r die gesamte Applikation
 * g�ltig sind.<br/>
 * Durch die Verwendung von Konstanten, statt z.B. ausgeschriebener String
 * k�nnen Auswirkungen von Tippfehlern vermieden werden.
 * 
 * @author ultimate
 */
public class BaseApplicationConstants
{	
	/**
	 * Namen der Spielparameter: Maximale Anzahl angezeigter News
	 */
	public static final String	PARAM_NEWS_MAXITEMS	= "news.maxItems";
	/**
	 * Namen der Spielparameter: Maximales Alter der News mit Platzhalter f�r
	 * Abstufungen
	 */
	public static final String	PARAM_NEWS_MAXAGE	= "news.%L.maxAge";
	
	/**
	 * Rollennamen: Administrator
	 */
	public static final String	ROLE_ADMIN				= "ADMIN";
	/**
	 * Rollennamen: Moderator
	 */
	public static final String	ROLE_MODERATOR			= "MODERATOR";
	/**
	 * Rollennamen: Normaler User
	 */
	public static final String	ROLE_NORMAL_USER		= "NORMAL_USER";
	/**
	 * Rollennamen: Demo-User
	 */
	public static final String	ROLE_DEMO_USER			= "DEMO_USER";
	
	/**
	 * Schl�ssel f�r die User-ID in der Session
	 */
	public static final String	SESSION_USER_KEY		= "j_user";
	/**
	 * Schl�ssel f�r den Infinite-Status in der Session. Dieser gibt an, ob die
	 * Session des Benutzers niemals abl�uft.
	 */
	public static final String	SESSION_INFINITE_KEY	= "j_infinite";
	/**
	 * Schl�ssel f�r den Timeout der Session. Dieser gibt an, wann ein Relogin f�llig ist, falls die
	 * Session nicht "infinite" ist.
	 */
	public static final String	SESSION_TIMEOUT_KEY		= "j_timeout";
	/**
	 * Schl�ssel f�r die Queue der letzten Logins.
	 */
	public static final String	SESSION_LAST_LOGINS_KEY	= "j_last_logins";
	/**
	 * Schl�ssel f�r die Locale in der Session
	 */
	public static final String	SESSION_LOCALE_KEY		= "j_locale";
	/**
	 * Schl�ssel f�r die Refresh-Status in der Session. Der Refresh-Status gibt
	 * an, ob derzeit das Passwort neu eigegeben werden muss, weil die Session
	 * des Benutzers abgelaufen ist.
	 */
	public static final String	SESSION_REFRESH_KEY		= "j_refresh";
	
	/**
	 * Maximale Anzahl der Relogin-Versuche. Nach dieser Anzahl von Versuchen
	 * wird der Benutzer automatisch ausgeloggt.
	 */
	public static final int		LOGIN_MAXFAILS			= 3;
}
