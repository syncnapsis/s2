package com.syncnapsis.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import com.syncnapsis.data.model.base.ActivatableInstance;

/**
 * Model-Klasse "Messenger"
 * Diese Klasse repräsentiert einen Messenger. Ein User kann so zu dynamisch
 * anlegbaren Messengern seine IDs/Adressen erfassen.
 * 
 * @author ultimate
 */
@Entity
@Table(name = "messenger")
public class Messenger extends ActivatableInstance<Long>
{
	/**
	 * Der Name des Messengers
	 */
	protected String	name;

	/**
	 * Leerer Standard Constructor
	 */
	public Messenger()
	{
	}

	/**
	 * Der Name des Messengers
	 * 
	 * @return name
	 */
	@Column(nullable = false, unique = true, length = LENGTH_NAME_NORMAL)
	public String getName()
	{
		return name;
	}

	/**
	 * Der Name des Messengers
	 * 
	 * @param name - der Name
	 */
	public void setName(String name)
	{
		this.name = name;
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
		if(!(obj instanceof Messenger))
			return false;
		Messenger other = (Messenger) obj;
		if(activated != other.activated)
			return false;
		if(name == null)
		{
			if(other.name != null)
				return false;
		}
		else if(!name.equals(other.name))
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
		result = prime * result + (activated ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		builder.append("id", id).append("version", version).append("activated", activated).append("name", name);
		return builder.toString();
	}
}
