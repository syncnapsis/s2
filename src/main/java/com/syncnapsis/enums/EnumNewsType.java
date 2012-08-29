package com.syncnapsis.enums;

/**
 * Enum f�r die Spezifizierung der Kategorie eines News-Objektes. Je nach
 * Kategorie einer News, wird dies besonders hervorgehoben oder ist f�r
 * den Benutzer besonders wichtig. News sind Nachrichten, die f�r alle 
 * Spieler gelten.
 * 
 * @author ultimate
 */
@Deprecated
public enum EnumNewsType
{
	/**
	 * Systemneuigkeiten:
	 * - Serverwartung
	 * - Serverausf�lle
	 * - ...
	 */
	system,
	/**
	 * Neuigkeiten zum Entwicklungsstand des Spiels:
	 * - neue Features
	 * - Bug-Fixes
	 * - Versions�nderungen
	 * - ...
	 */
	development,
	/**
	 * Universumsneuigkeiten:
	 * - Neue Galaxien
	 * - Neue Planeten/Systeme
	 * - Zufallsereignisse (Supernova, Galaktischer Sturm...)
	 * - ...
	 */
	universe,
	/**
	 * Handelsneuigkeiten:
	 * - Besondere Wechselkursschwankungen
	 * - Sonderangebote
	 * - ...
	 */
	trade,
	/**
	 * Besonders wichtige diplomatische Neuigkeiten:
	 * - Diplomatische �nderungen zwischen den gr��ten Allianzen oder Spielern
	 * - ...
	 */
	diplomacy, 
	/**
	 * Neuigkeiten zu Preisen/Gewinnen:
	 * - Allianzen/Spieler mit besonderen Verdiensten
	 * - Allianzen/Spieler mit Wochen-/Monatstiteln
	 * - ...
	 */
	awards,
	/**
	 * Sonstige lustige Neuigkeiten:
	 * - Spa�nachrichten
	 * - Witze
	 * - ...
	 */
	fun, 
	/**
	 * Sonstige Neuigkeiten
	 */
	others;
}
