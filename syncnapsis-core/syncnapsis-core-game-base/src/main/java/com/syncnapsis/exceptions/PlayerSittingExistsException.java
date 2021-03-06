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

import com.syncnapsis.data.model.Player;

/**
 * Exception, die geworfen wird, wenn eine Sittingbeziehung erstellt werden soll, die bereits
 * vorhanden ist.
 * 
 * @author ultimate
 */
public class PlayerSittingExistsException extends RuntimeException
{
	/**
	 * Default serialVersionUID
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * Der bereits vorhandene Player
	 */
	private Player				player;

	/**
	 * Erzeugt eine neue RuntimeException mit gegebener Nachricht
	 * 
	 * @param message - die Nachricht
	 * @param Player - der bereits vorhandene Player
	 */
	public PlayerSittingExistsException(String message, Player player)
	{
		super(message);
		this.player = player;
	}

	/**
	 * Der bereits vorhandene Player
	 * 
	 * @return player
	 */
	public Player getPlayer()
	{
		return player;
	}
}
