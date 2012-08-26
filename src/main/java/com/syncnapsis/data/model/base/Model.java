package com.syncnapsis.data.model.base;

/**
 * Interface zur Indentifizierung von Model-Klassen. Auf diese Weise sind in
 * allen Model-Klassen die unten definierten Konstanten f�r die Spaltenl�ngen in
 * der Datenbank bekannt.
 * Desweiteren k�nnen so in den Tests Model-Klassen von anderen Klassen
 * unterschieden werden. Bei der Vollst�ndigkeitspr�fung der Tests werden die
 * Model-Klassen ausgeschlossen.
 * 
 * @author ultimate
 */
public interface Model
{
	/**
	 * Standard-L�nge f�r kurze Namen
	 */
	public static final int	LENGTH_NAME_SHORT		= 10;
	/**
	 * Standard-L�nge f�r normale Namen
	 */
	public static final int	LENGTH_NAME_NORMAL		= 30;
	/**
	 * Standard-L�nge f�r lange Namen
	 */
	public static final int	LENGTH_NAME_LONG		= 100;

	/**
	 * Standard-L�nge f�r Passw�rter
	 */
	public static final int	LENGTH_PASSWORD			= 200;
	/**
	 * Standard-L�nge f�r e-Mail-Adressen
	 */
	public static final int	LENGTH_EMAIL			= 200;
	/**
	 * Standard-L�nge f�r URLs
	 */
	public static final int	LENGTH_URL				= 200;
	/**
	 * Standard-L�nge f�r Beschreibungen
	 */
	public static final int	LENGTH_DESCRIPTION		= 2000;
	/**
	 * Standard-L�nge f�r freie Titel
	 */
	public static final int	LENGTH_TITLE			= 200;
	/**
	 * Standard-L�nge f�r freie Texte
	 */
	public static final int	LENGTH_TEXT				= 2000;

	/**
	 * Standard-L�nge f�r ID
	 */
	public static final int	LENGTH_ID				= 50;
	/**
	 * Standard-L�nge f�r Action-Namen
	 */
	public static final int	LENGTH_ACTION			= 50;
	/**
	 * Standard-L�nge f�r Parameter-Namen
	 */
	public static final int	LENGTH_PARAMETER		= 50;
	/**
	 * Standard-L�nge f�r Parameter-Werte
	 */
	public static final int	LENGTH_PARAMETERVALUE	= 50;

	/**
	 * Standard-L�nge f�r Enum-Werte
	 */
	public static final int	LENGTH_ENUM				= 50;

	/**
	 * Standard-L�nge f�r Sprach-Schl�ssel
	 */
	public static final int	LENGTH_LANGUAGE_KEY		= 100;

	/**
	 * Standard-L�nge f�r ZK-Drag&Drop-Spezifikationen
	 */
	public static final int	LENGTH_ZK_DRAG_AND_DROP	= 100;
	/**
	 * Standard-L�nge f�r Positionsangaben
	 */
	public static final int	LENGTH_ZK_POSITION		= 20;
}
