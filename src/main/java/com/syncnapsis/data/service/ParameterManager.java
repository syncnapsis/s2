package com.syncnapsis.data.service;

import java.util.Date;

import com.syncnapsis.data.model.Parameter;

/**
 * Manager-Interface f�r den Zugriff auf Parameter.
 * 
 * @author ultimate
 */
public interface ParameterManager extends GenericNameManager<Parameter, Long>
{
	/**
	 * L�dt den Wert eines Parameters als String
	 * 
	 * @param name - der Name des Parameters
	 * @return der Parameter als String
	 */
	public String getString(String name);

	/**
	 * L�dt den Wert eines Parameters als Long
	 * 
	 * @param name - der Name des Parameters
	 * @return der Parameter als Long
	 */
	public Long getLong(String name);

	/**
	 * L�dt den Wert eines Parameters als Integer
	 * 
	 * @param name - der Name des Parameters
	 * @return der Parameter als Integer
	 */
	public Integer getInteger(String name);

	/**
	 * L�dt den Wert eines Parameters als Short
	 * 
	 * @param name - der Name des Parameters
	 * @return der Parameter als Short
	 */
	public Short getShort(String name);

	/**
	 * L�dt den Wert eines Parameters als Byte
	 * 
	 * @param name - der Name des Parameters
	 * @return der Parameter als Byte
	 */
	public Byte getByte(String name);

	/**
	 * L�dt den Wert eines Parameters als Double
	 * 
	 * @param name - der Name des Parameters
	 * @return der Parameter als Double
	 */
	public Double getDouble(String name);

	/**
	 * L�dt den Wert eines Parameters als Float
	 * 
	 * @param name - der Name des Parameters
	 * @return der Parameter als Float
	 */
	public Float getFloat(String name);

	/**
	 * L�dt den Wert eines Parameters als Boolean
	 * 
	 * @param name - der Name des Parameters
	 * @return der Parameter als Boolean
	 */
	public Boolean getBoolean(String name);

	/**
	 * L�dt den Wert eines Parameters als Date
	 * 
	 * @param name - der Name des Parameters
	 * @return der Parameter als Date
	 */
	public Date getDate(String name);
}
