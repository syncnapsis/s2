package com.syncnapsis.enums;

/**
 * Enum f�r die Spezifizierung des Geschlechts eines Users. Es k�nnen hier
 * sowohl echte Angaben, als auch Spa�-Angaben gemacht werden. Ggf. kann die
 * Liste der Geschlechter auch noch erweitert werden, in Anlehnung an das
 * Spiel...
 * Die Geschlechtsnamen sind selbsterkl�rend...
 * 
 * @author ultimate
 */
public enum EnumSex
{
	/**
	 * unbekanntes Geschlecht (default)
	 */
	unknown,
	/**
	 * m�nnlich
	 */
	male,
	/**
	 * weiblich
	 */
	female,
	/**
	 * transsexuell
	 */
	transsexual,
	/**
	 * Maschine
	 */
	machine;

	/**
	 * Gibt das default Geschlect zur�ck: unknown
	 * 
	 * @return das default Geschlect
	 */
	public static EnumSex getDefault()
	{
		return unknown;
	}
}
