package com.syncnapsis.utils.reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Interface zum pr�fen von Fields in ReflectionsUtil.findFields.
 * Anhand der Methode isValidField kann gepr�ft werden, ob es sich um ein g�ltiges Field handelt. Im
 * Normalfall m�ssten dazu Getter und Setter vorhanden sein.
 * 
 * @author ultimate
 */
public abstract class FieldCriterion
{
	/**
	 * Pr�ft obe ein Field g�ltig ist. Es werden die gefundenen Getter und Setter �bergeben um
	 * pr�fen zu k�nnen, ob diese vorhanden sind oder z.B. geforderte Annotations aufweisen.
	 * 
	 * @see FieldCriterion#isValidField(com.syncnapsis.utils.reflections.Field)
	 * @param field - das Field das Field, Getter und Setter enth�lt
	 * @return true oder false
	 */
	public boolean isValidField(com.syncnapsis.utils.reflections.Field field)
	{
		return isValidField(field.getField(), field.getGetter(), field.getSetter());
	}

	/**
	 * Pr�ft obe ein Field g�ltig ist. Es werden die gefundenen Getter und Setter �bergeben um
	 * pr�fen zu k�nnen, ob diese vorhanden sind oder z.B. geforderte Annotations aufweisen.
	 * 
	 * @param field - das Field
	 * @param getter - die Get-Methode
	 * @param setter - die Set-Methode
	 * @return true oder false
	 */
	public abstract boolean isValidField(Field field, Method getter, Method setter);

	/**
	 * Default FieldCriterion, das nur pr�ft ob Get- und Set-Methode vorhanden sind.
	 */
	public static final FieldCriterion	DEFAULT	= new FieldCriterion() {
													/*
													 * (non-Javadoc)
													 * @see
													 * com.syncnapsis.utils.reflections.FieldCriterion#
													 * isValidField(java.lang.reflect.Field,
													 * java.lang.reflect.Method,
													 * java.lang.reflect.Method)
													 */
													@Override
													public boolean isValidField(Field field, Method getter, Method setter)
													{
														return getter != null && setter != null;
													}
												};

	/**
	 * FieldCriterion, das pr�ft ob typische Annotations f�r Spalten in einer Entity vorhanden sind:
	 * 
	 * @see Column
	 * @see OneToMany
	 * @see OneToOne
	 * @see ManyToOne
	 * @see ManyToMany
	 */
	public static final FieldCriterion	ENTITY	= new FieldCriterion() {
													/*
													 * (non-Javadoc)
													 * @see
													 * com.syncnapsis.utils.reflections.FieldCriterion#
													 * isValidField(java.lang.reflect.Field,
													 * java.lang.reflect.Method,
													 * java.lang.reflect.Method)
													 */
													@Override
													public boolean isValidField(Field field, Method getter, Method setter)
													{
														if(!DEFAULT.isValidField(field, getter, setter))
															return false;
														return getter.isAnnotationPresent(Column.class) || getter.isAnnotationPresent(Id.class)
																|| getter.isAnnotationPresent(OneToMany.class)
																|| getter.isAnnotationPresent(OneToOne.class)
																|| getter.isAnnotationPresent(ManyToOne.class)
																|| getter.isAnnotationPresent(ManyToMany.class);
													}
												};

}
