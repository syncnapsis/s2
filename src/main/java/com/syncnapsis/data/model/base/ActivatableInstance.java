package com.syncnapsis.data.model.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.syncnapsis.security.annotations.Accessible;

/**
 * Abstrakte Klasse als Basis f�r alle Instanzen, die nicht gel�scht, sondern
 * deaktiviert werden. Beim L�schen eines solchen Objektes wird dann
 * automatische setActivated(false) aufgerufen und das Objekt anschlie�end
 * deaktiviert gespeichert, anstatt dies endg�ltig zu l�schen.
 * Deaktivierte Instanzen bleiben demnach in der Datenbank vorhanden. Dies ist
 * insbesondere f�r Fremschl�sselverkn�pfungen hilfreich, oder wenn die Daten
 * f�r sp�tere Zwecke noch abrufbar sein sollen.
 * 
 * @author ultimate
 * @param <PK> - die Klasse f�r den Prim�rschl�ssel
 */
@MappedSuperclass
public abstract class ActivatableInstance<PK extends Serializable> extends BaseObject<PK>
{
	/**
	 * Ist diese Instanz aktiviert?
	 */
	protected boolean	activated;

	/**
	 * Ist diese Instanz aktiviert?
	 * 
	 * @return activated
	 */
	@Column(nullable = false)
	@Accessible(defaultAccessible = false)
	public boolean isActivated()
	{
		return activated;
	}

	/**
	 * Ist diese Instanz aktiviert?
	 * 
	 * @param activated - true oder false
	 */
	@Accessible(defaultAccessible = false)
	public void setActivated(boolean activated)
	{
		this.activated = activated;
	}
}
