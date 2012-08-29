package com.syncnapsis.enums;

/**
 * Enum f�r die Spezifizierung des Accountsstatus eines Users.
 * 
 * @author ultimate
 */
public enum EnumAccountStatus
{
	/**
	 * User ist aktiv (Normalzustand)
	 */
	active,
	/**
	 * User ist gesperrt (wegen Regelversto� oder nach Neuanmeldung bis zum Freischalten)
	 */
	locked,
	/**
	 * User ist im Urlaubsmodus
	 */
	vacation,
	/**
	 * User ist inaktiv bzw. gel�scht
	 */
	inactive;
}
