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
package com.syncnapsis.data.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.syncnapsis.data.model.base.ActivatableInstance;
import com.syncnapsis.enums.EnumAccountStatus;
import com.syncnapsis.security.Ownable;

/**
 * Model-Klasse "Benutzer/Spieler"
 * Nur mit besitz eines Benutzerkontos kann ein Spieler noch nicht am Spiel
 * teilnehmen. Dazu muss erst ein Imperium gegr�ndet werden, welches der Spieler
 * beherrschen kann und mit dem am Spielgeschehen teilgenommen werden kann.
 * 
 * @author ultimate
 */
@Entity
@Table(name = "player")
public class Player extends ActivatableInstance<Long> implements Ownable<User>
{
	/**
	 * The user that belongs to this player
	 */
	protected User				user;
	/**
	 * Benutzerrolle
	 */
	protected PlayerRole		role;
	/**
	 * Datum, zu dem die Benutzerrolle f�r diesen Benutzer/Spieler abl�uft
	 * Wenn die Benutzerrolle abgelaufen ist, wird der Spieler wieder zum
	 * 'NORMAL_USER'
	 */
	protected Date				roleExpireDate;
	/**
	 * Aktueller Kontostatus
	 * TODO necessary for player, too?
	 */
	protected EnumAccountStatus	accountStatus;
	/**
	 * Datum, zu dem der Kontostatus abl�uft
	 * Wenn der Kontostatus abgelaufen ist, wird der Kontostatus wieder auf
	 * 'active' gesetzt.
	 */
	protected Date				accountStatusExpireDate;

	/**
	 * Is this player played by a bot?
	 */
	protected boolean			bot;

	/**
	 * Liste der Sitter
	 */
	protected List<Player>		sitters;
	/**
	 * Liste der Gesitteten
	 */
	protected List<Player>		sitted;

	/**
	 * Liste der Imperien
	 */
	protected List<Empire>		empires;
	/**
	 * Aktuell aktives bzw. zuletzt verwendetes Imperium
	 */
	protected Empire			currentEmpire;

	/**
	 * Leerer Standard Constructor
	 */
	public Player()
	{
	}

	/**
	 * The user that belongs to this player
	 * 
	 * @return user
	 */
	@OneToOne
	@JoinColumn(name = "fkUser", nullable = false)
	public User getUser()
	{
		return user;
	}

	/**
	 * Benutzerrolle
	 * 
	 * @return role
	 */
	@ManyToOne
	@JoinColumn(name = "fkPlayerRole", nullable = false)
	public PlayerRole getRole()
	{
		return role;
	}

	/**
	 * Datum, zu dem die Benutzerrolle f�r diesen Benutzer/Spieler abl�uft
	 * Wenn die Benutzerrolle abgelaufen ist, wird der Spieler wieder zum
	 * 'NORMAL_USER'
	 * 
	 * @return roleExpireDate
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	public Date getRoleExpireDate()
	{
		return roleExpireDate;
	}

	/**
	 * Aktueller Kontostatus
	 * 
	 * @return accountStatus
	 */
	@Column(nullable = true, length = LENGTH_ENUM)
	@Enumerated(value = EnumType.STRING)
	public EnumAccountStatus getAccountStatus()
	{
		return accountStatus;
	}

	/**
	 * Datum, zu dem der Kontostatus abl�uft
	 * Wenn der Kontostatus abgelaufen ist, wird der Kontostatus wieder auf
	 * 'active' gesetzt.
	 * 
	 * @return accountStatusExpireDate
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	public Date getAccountStatusExpireDate()
	{
		return accountStatusExpireDate;
	}

	/**
	 * 
	 * Is this player played by a bot?
	 * 
	 * @return bot
	 */
	@Column(nullable = false)
	public boolean isBot()
	{
		return bot;
	}

	/**
	 * Liste der Sitter
	 * 
	 * @return sitters
	 */
	@ManyToMany
	@JoinTable(name = "sittingstatus", joinColumns = { @JoinColumn(name = "fkPlayer") }, inverseJoinColumns = { @JoinColumn(name = "fkSitter") })
	public List<Player> getSitters()
	{
		return sitters;
	}

	/**
	 * Liste der Gesitteten
	 * 
	 * @return sitted
	 */
	@ManyToMany(mappedBy = "sitters")
	public List<Player> getSitted()
	{
		return sitted;
	}

	/**
	 * Liste der Imperien
	 * 
	 * @return empires
	 */
	@OneToMany(mappedBy = "player")
	public List<Empire> getEmpires()
	{
		return empires;
	}

	/**
	 * Aktuell aktives bzw. zuletzt verwendetes Imperium
	 * 
	 * @return currentEmpire
	 */
	@OneToOne
	@JoinTable(name = "PlayerCurrentEmpire", joinColumns = @JoinColumn(name = "fkPlayer"), inverseJoinColumns = @JoinColumn(name = "fkEmpire"))
	public Empire getCurrentEmpire()
	{
		return currentEmpire;
	}

	/**
	 * The user that belongs to this player
	 * 
	 * @param user - the User
	 */
	public void setUser(User user)
	{
		this.user = user;
	}

	/**
	 * Benutzerrolle
	 * 
	 * @param role - die Benutzerrolle
	 */
	public void setRole(PlayerRole role)
	{
		this.role = role;
	}

	/**
	 * Datum, zu dem die Benutzerrolle f�r diesen Benutzer/Spieler abl�uft
	 * Wenn die Benutzerrolle abgelaufen ist, wird der Spieler wieder zum
	 * 'NORMAL_USER'
	 * 
	 * @param roleExpireDate - das Datum
	 */
	public void setRoleExpireDate(Date roleExpireDate)
	{
		this.roleExpireDate = roleExpireDate;
	}

	/**
	 * Aktueller Kontostatus
	 * 
	 * @param accountStatus - der Kontostatus
	 */
	public void setAccountStatus(EnumAccountStatus accountStatus)
	{
		this.accountStatus = accountStatus;
	}

	/**
	 * Datum, zu dem der Kontostatus abl�uft
	 * Wenn der Kontostatus abgelaufen ist, wird der Kontostatus wieder auf
	 * 'active' gesetzt.
	 * 
	 * @param accountStatusExpireDate - das Datum
	 */
	public void setAccountStatusExpireDate(Date accountStatusExpireDate)
	{
		this.accountStatusExpireDate = accountStatusExpireDate;
	}

	/**
	 * 
	 * Is this player played by a bot?
	 * 
	 * @param bot - true or false
	 */
	public void setBot(boolean bot)
	{
		this.bot = bot;
	}

	/**
	 * Liste der Sitter
	 * 
	 * @param sitters - die Liste
	 */
	public void setSitters(List<Player> sitters)
	{
		this.sitters = sitters;
	}

	/**
	 * Liste der Gesitteten
	 * 
	 * @param sitted - die Liste
	 */
	public void setSitted(List<Player> sitted)
	{
		this.sitted = sitted;
	}

	/**
	 * Liste der Imperien
	 * 
	 * @param empires - die Liste
	 */
	public void setEmpires(List<Empire> empires)
	{
		this.empires = empires;
	}

	/**
	 * Aktuell aktives bzw. zuletzt verwendetes Imperium
	 * 
	 * @param currentEmpire - das Imperium
	 */
	public void setCurrentEmpire(Empire currentEmpire)
	{
		this.currentEmpire = currentEmpire;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.security.Ownable#getOwners()
	 */
	@Transient
	@Override
	public List<User> getOwners()
	{
		return Collections.nCopies(1, getUser());
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.model.base.BaseObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(!super.equals(obj))
			return false;
		if(!(obj instanceof Player))
			return false;
		Player other = (Player) obj;
		if(accountStatus == null)
		{
			if(other.accountStatus != null)
				return false;
		}
		else if(!accountStatus.equals(other.accountStatus))
			return false;
		if(accountStatusExpireDate == null)
		{
			if(other.accountStatusExpireDate != null)
				return false;
		}
		else if(!accountStatusExpireDate.equals(other.accountStatusExpireDate))
			return false;
		if(activated != other.activated)
			return false;
		if(user == null)
		{
			if(other.user != null)
				return false;
		}
		else if(!user.getId().equals(other.user.getId()))
			return false;
		if(role == null)
		{
			if(other.role != null)
				return false;
		}
		else if(!role.getId().equals(other.role.getId()))
			return false;
		if(roleExpireDate == null)
		{
			if(other.roleExpireDate != null)
				return false;
		}
		else if(!roleExpireDate.equals(other.roleExpireDate))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.syncnapsis.data.model.base.BaseObject#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((accountStatus == null) ? 0 : accountStatus.hashCode());
		result = prime * result + ((accountStatusExpireDate == null) ? 0 : accountStatusExpireDate.hashCode());
		result = prime * result + (activated ? 1231 : 1237);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((roleExpireDate == null) ? 0 : roleExpireDate.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("id", id).append("version", version).append("activated", activated).append("accountStatus", accountStatus)
				.append("accountStatusExpireDate", accountStatusExpireDate).append("role", role.getRolename())
				.append("roleExpireDate", roleExpireDate).append("user", user.getUsername());
		return builder.toString();
	}
}
