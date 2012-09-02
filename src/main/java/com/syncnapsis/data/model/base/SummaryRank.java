package com.syncnapsis.data.model.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.syncnapsis.data.model.annotations.RankCriterion;

/**
 * �bergeordnete Klasse f�r die Darstellung von R�ngen f�r Instanzen, die mehrere Imperien enthalten
 * und deren Rang zusammenfassen (z.B. User und Alliance)
 * 
 * @author ultimate
 * @param <E> - die Klasse auf die sich die Bewertung bezieht
 */
@MappedSuperclass
public class SummaryRank<E extends BaseObject<?>> extends Rank<E>
{
	// TODO anzahl kolonien und anzahl bl�cke plus durchschnitt
	
	/**
	 * Die Anzahl der Imperien
	 */
	@RankCriterion(averageAmount = true)
	protected int	numberOfEmpires;

	/**
	 * Die Punktbewertung der wirtschaftlichen St�rke der Oberinstanz
	 */
	@RankCriterion
	protected int	economy;
	/**
	 * Die durchschnittliche wirtschaftliche Punktzahl pro Imperium
	 */
	@RankCriterion(averageFor = "economy")
	protected int	averageEconomy;

	/**
	 * Die Punktbewertung der milit�rischen St�rke des Oberinstanz
	 */
	@RankCriterion
	protected int	military;
	/**
	 * Die durchschnittliche milit�rische Punktzahl pro Imperium
	 */
	@RankCriterion(averageFor = "military")
	protected int	averageMilitary;

	/**
	 * Die Punktbewertung der wissenschaftlichen St�rke der Oberinstanz
	 */
	@RankCriterion
	protected int	science;
	/**
	 * Die durchschnittliche wissenschaftliche Punktzahl pro Imperium
	 */
	@RankCriterion(averageFor = "science")
	protected int	averageScience;

	/**
	 * Die Gesamtpunkte der Oberinstanz
	 */
	@RankCriterion(primary = true)
	protected int	total;
	/**
	 * Die durchschnittliche Gesamtpunktzahl pro Imperium
	 */
	@RankCriterion(averageFor = "total")
	protected int	averageTotal;

	/**
	 * Die Anzahl der Mitgliedsimperien
	 * 
	 * @return die Anzahl
	 */
	@Column(nullable = false)
	public int getNumberOfEmpires()
	{
		return numberOfEmpires;
	}

	/**
	 * Die Punktbewertung der wirtschaftlichen St�rke der Oberinstanz
	 * 
	 * @return die Wirtschaftspunkte
	 */
	@Column(nullable = false)
	public int getEconomy()
	{
		return economy;
	}

	/**
	 * Die durchschnittliche wirtschaftliche Punktzahl pro Imperium
	 * 
	 * @return die durchschnittlichen Wirtschaftspunkte
	 */
	@Column(nullable = false)
	public int getAverageEconomy()
	{
		return averageEconomy;
	}

	/**
	 * Die Punktbewertung der milit�rischen St�rke der Oberinstanz
	 * 
	 * @return die Milit�rpunkte
	 */
	@Column(nullable = false)
	public int getMilitary()
	{
		return military;
	}

	/**
	 * Die durchschnittliche milit�rische Punktzahl pro Imperium
	 * 
	 * @return die durchschnittlichen Milit�rpunkte
	 */
	@Column(nullable = false)
	public int getAverageMilitary()
	{
		return averageMilitary;
	}

	/**
	 * Die Punktbewertung der wissenschaftlichen St�rke der Oberinstanz
	 * 
	 * @return die Wissenschaftspunkte
	 */
	@Column(nullable = false)
	public int getScience()
	{
		return science;
	}

	/**
	 * Die durchschnittliche wissenschaftliche Punktzahl pro Imperium
	 * 
	 * @return die durchschnittlichen Wissenschaftspunkte
	 */
	@Column(nullable = false)
	public int getAverageScience()
	{
		return averageScience;
	}

	/**
	 * Die Gesamtpunkte der Oberinstanz
	 * 
	 * @return die Gesamtpunkte
	 */
	@Column(nullable = false)
	public int getTotal()
	{
		return total;
	}

	/**
	 * Die durchschnittliche Gesamtpunktzahl pro Imperium
	 * 
	 * @return die durchschnittliche Gesamtpunktzahl
	 */
	@Column(nullable = false)
	public int getAverageTotal()
	{
		return averageTotal;
	}

	/**
	 * Die Anzahl der Mitgliedsimperien
	 * 
	 * @param numberOfEmpires - die Anzahl
	 */
	public void setNumberOfEmpires(int numberOfEmpires)
	{
		this.numberOfEmpires = numberOfEmpires;
	}

	/**
	 * Die Punktbewertung der wirtschaftlichen St�rke der Oberinstanz
	 * 
	 * @param economy - die Wirtschaftspunkte
	 */
	public void setEconomy(int economy)
	{
		this.economy = economy;
	}

	/**
	 * Die durchschnittliche wirtschaftliche Punktzahl pro Imperium
	 * 
	 * @param averageEconomy - die durchschnittlichen Wirtschaftspunkte
	 */
	public void setAverageEconomy(int averageEconomy)
	{
		this.averageEconomy = averageEconomy;
	}

	/**
	 * Die Punktbewertung der milit�rischen St�rke der Oberinstanz
	 * 
	 * @param military - die Milit�rpunkte
	 */
	public void setMilitary(int military)
	{
		this.military = military;
	}

	/**
	 * Die durchschnittliche milit�rische Punktzahl pro Imperium
	 * 
	 * @param averageMilitary - die durchschnittlichen Milit�rpunkte
	 */
	public void setAverageMilitary(int averageMilitary)
	{
		this.averageMilitary = averageMilitary;
	}

	/**
	 * Die Punktbewertung der wissenschaftlichen St�rke der Oberinstanz
	 * 
	 * @param science - die Wissenschaftspunkte
	 */
	public void setScience(int science)
	{
		this.science = science;
	}

	/**
	 * Die durchschnittliche wissenschaftliche Punktzahl pro Imperium
	 * 
	 * @param averageScience - die durchschnittlichen Wissenschaftspunkte
	 */
	public void setAverageScience(int averageScience)
	{
		this.averageScience = averageScience;
	}

	/**
	 * Die Gesamtpunkte der Oberinstanz
	 * 
	 * @param total - die Gesamtpunkte
	 */
	public void setTotal(int total)
	{
		this.total = total;
	}

	/**
	 * Die durchschnittliche Gesamtpunktzahl pro Imperium
	 * 
	 * @param averageTotal - die durchschnittliche Gesamtpunktzahl
	 */
	public void setAverageTotal(int averageTotal)
	{
		this.averageTotal = averageTotal;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + averageEconomy;
		result = prime * result + averageMilitary;
		result = prime * result + averageScience;
		result = prime * result + averageTotal;
		result = prime * result + economy;
		result = prime * result + military;
		result = prime * result + numberOfEmpires;
		result = prime * result + science;
		result = prime * result + total;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(!super.equals(obj))
			return false;
		if(getClass() != obj.getClass())
			return false;
		SummaryRank<?> other = (SummaryRank<?>) obj;
		if(averageEconomy != other.averageEconomy)
			return false;
		if(averageMilitary != other.averageMilitary)
			return false;
		if(averageScience != other.averageScience)
			return false;
		if(averageTotal != other.averageTotal)
			return false;
		if(economy != other.economy)
			return false;
		if(military != other.military)
			return false;
		if(numberOfEmpires != other.numberOfEmpires)
			return false;
		if(science != other.science)
			return false;
		if(total != other.total)
			return false;
		return true;
	}

}
