/**
 * Syncnapsis Framework - Copyright (c) 2012 ultimate
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
//@requires("UI")

PlayerManager = function()
{
	this.login = function(username, password)
	{
		// return Player
		return function(player) { UI.onLogin(player); };
	};
	
	this.logout = function()
	{
		// return boolean
		return function(success) { UI.onLogout(success); };
	};
	
	this.register = function(username, email, password, passwordConfirm)
	{
		// return Player
	};
};