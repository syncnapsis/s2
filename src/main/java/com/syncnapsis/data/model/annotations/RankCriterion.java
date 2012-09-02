package com.syncnapsis.data.model.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation f�r die Kennzeichnung von Kriterien f�r die Darstellung von Objekten in Ranking-Listen
 * 
 * @author ultimate
 */
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RankCriterion
{
	/**
	 * Die default Kategorie
	 */
	public static final String defaultCategory = "main";
	
	/**
	 * Gibt die Kategorie eines Bewertungskriteriums an. Die Kriterien werden in Kategorien
	 * zusammengefasst, um so gemeinsam ausgegeben werden zu k�nnen.
	 * 
	 * @return category
	 */
	String category() default defaultCategory;

	/**
	 * Kennzeichnung des prim�renen Kriteriums einer Kategorie. Es sollte nur ein prim�res Kriterium
	 * geben.
	 * 
	 * @return primary
	 */
	boolean primary() default false;

	/**
	 * Ist dieses Kriterium ein Durchschnittswert eines anderen Kriteriums?
	 * 
	 * @return average
	 */
	boolean average() default false;

	/**
	 * Das Kriterium, zu dem dieses Kriterium ein Durchschnittswert ist.
	 * 
	 * @return averageFor
	 */
	String averageFor() default "";

	/**
	 * Kennzeichnung dieses Kriteriums als Anzahl f�r die Durchschnittsberechnung.
	 * 
	 * @return averageAmount
	 */
	boolean averageAmount() default false;
}
