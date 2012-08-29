package com.syncnapsis.data.service;

import java.util.Date;

import com.syncnapsis.data.model.Parameter;

/**
 * Manager-Interface f�r den Zugriff auf Parameter.
 * 
 * @author ultimate
 */
public interface ParameterManager extends GenericManager<Parameter, String>
{
	/**
	 * L�dt den Wert eines Parameters als String
	 * 
	 * @param id - die ID des Parameters
	 * @return der Parameter als String
	 */
	public String getString(String id);

	/**
	 * L�dt den Wert eines Parameters als Long
	 * 
	 * @param id - die ID des Parameters
	 * @return der Parameter als Long
	 */
	public Long getLong(String id);

	/**
	 * L�dt den Wert eines Parameters als Integer
	 * 
	 * @param id - die ID des Parameters
	 * @return der Parameter als Integer
	 */
	public Integer getInteger(String id);

	/**
	 * L�dt den Wert eines Parameters als Short
	 * 
	 * @param id - die ID des Parameters
	 * @return der Parameter als Short
	 */
	public Short getShort(String id);

	/**
	 * L�dt den Wert eines Parameters als Byte
	 * 
	 * @param id - die ID des Parameters
	 * @return der Parameter als Byte
	 */
	public Byte getByte(String id);

	/**
	 * L�dt den Wert eines Parameters als Double
	 * 
	 * @param id - die ID des Parameters
	 * @return der Parameter als Double
	 */
	public Double getDouble(String id);

	/**
	 * L�dt den Wert eines Parameters als Float
	 * 
	 * @param id - die ID des Parameters
	 * @return der Parameter als Float
	 */
	public Float getFloat(String id);

	/**
	 * L�dt den Wert eines Parameters als Boolean
	 * 
	 * @param id - die ID des Parameters
	 * @return der Parameter als Boolean
	 */
	public Boolean getBoolean(String id);

	/**
	 * L�dt den Wert eines Parameters als Date
	 * 
	 * @param id - die ID des Parameters
	 * @return der Parameter als Date
	 */
	public Date getDate(String id);
}
