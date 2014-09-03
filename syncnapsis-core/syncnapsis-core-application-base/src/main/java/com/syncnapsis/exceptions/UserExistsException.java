/**
 * Syncnapsis Framework - Copyright (c) 2012-2014 ultimate
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation; either version
 * 3 of the License, or any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MECHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Plublic License along with this program;
 * if not, see <http://www.gnu.org/licenses/>.
 */
package com.syncnapsis.exceptions;

/**
 * Exception, die geworfen wird, falls versucht wird einen neuen Benutzer mit
 * bereits vorhandenem Benutzernamen oder vorhandener e-Mail-Adresse zu
 * speichern. Ein Benutzername gilt auch unter Ignorierung der Gro�- und
 * Kleinschreibung als vorhanden.
 * 
 * @author ultimate
 */
public class UserExistsException extends RuntimeException
{
	/**
	 * Default serialVersionUID
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * Erzeugt eine neue RuntimeException mit gegebener Nachricht
	 * 
	 * @param message - die Nachricht
	 */
	public UserExistsException(String message)
	{
		super(message);
	}
}
