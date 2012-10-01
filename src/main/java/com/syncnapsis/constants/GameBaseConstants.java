package com.syncnapsis.constants;

/**
 * Klasse f�r das Festhalten von Konstanten, die f�r die gesamte Applikation
 * g�ltig sind.<br/>
 * Durch die Verwendung von Konstanten, statt z.B. ausgeschriebener String
 * k�nnen Auswirkungen von Tippfehlern vermieden werden.
 * 
 * @author ultimate
 */
public class GameBaseConstants
{
	/**
	 * Rollennamen: Premium-User
	 */
	public static final String	ROLE_PREMIUM_PLAYER	= "PREMIUM_PLAYER";
	/**
	 * Rollennamen: Normaler User
	 */
	public static final String	ROLE_NORMAL_PLAYER	= "NORMAL_PLAYER";
	/**
	 * Rollennamen: Demo-User
	 */
	public static final String	ROLE_DEMO_PLAYER	= "DEMO_PLAYER";
	/**
	 * Schl�ssel f�r die Spieler-ID in der Session
	 */
	public static final String	SESSION_PLAYER_KEY	= "j_player";
	/**
	 * Schl�ssel f�r die Imperiums-ID in der Session
	 */
	public static final String	SESSION_EMPIRE_KEY	= "j_empire";
}