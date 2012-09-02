package com.syncnapsis.enums;

/**
 * Enum f�r die Spezifizierung eines Galaxie-Typs. Die Galaxie-Typen sind den
 * folgenden Quellen entnommen. 'x' steht dabei f�r einen Platzhalter, der bei
 * der Generierung durch Parameter ersetzt wird.
 * http://de.wikipedia.org/wiki/Galaxie
 * http://upload.wikimedia.org/wikipedia/commons/8/8a/Hubble_sequence_photo.png
 * 
 * @author ultimate
 */
public enum EnumGalaxyType
{
	/**
	 * Elliptische Galaxien
	 */
	Ex,
	/**
	 * Lentikul�re (linsenf�rmige) Galaxien
	 */
	S0,
	/**
	 * Balkengalaxien (als Grundlage f�r SBx)
	 */
	SB0,
	/**
	 * Spiralgalaxien
	 */
	Sx,
	/**
	 * Balkenspiralgalaxien
	 */
	SBx,
	/**
	 * Irregul�re (unregelm��ige) Galaxien
	 */
	Rx,
	/**
	 * zus�tzliche Bogensegmente f�r die Gestaltung von Galaxien
	 */
	Ax;
}
