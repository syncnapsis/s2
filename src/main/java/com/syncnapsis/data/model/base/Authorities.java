package com.syncnapsis.data.model.base;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Abstrakte Oberklasse f�r alle Rechte-Objekte.
 * Rechte k�nnen auf verschiedene Weise implementiert und somit strukturiert werden.
 * 
 * @author ultimate
 */
@MappedSuperclass
public abstract class Authorities extends BaseObject<Long>
{
	/**
	 * Schaltet ein Recht frei, sofern es f�r dieses Authorities-Objekt g�ltig ist
	 * 
	 * @param authorityName - das freizuschaltende Recht
	 * @return der vorherige Zustand: freigeschaltet oder nicht
	 */
	@Transient
	public abstract boolean grantAuthority(String authorityName);

	/**
	 * Entzieht ein Recht, sofern es f�r dieses Authorities-Objekt g�ltig ist
	 * 
	 * @param authorityName - das zu entziehende Recht
	 * @return der vorherige Zustand: freigeschaltet oder nicht
	 */
	@Transient
	public abstract boolean withdrawAuthority(String authorityName);

	/**
	 * Pr�ft ob ein bestimmtes Recht freigeschaltet ist oder nicht
	 * 
	 * @param authorityName - das zu pr�fende Recht
	 * @return der Zustand: freigeschaltet oder nicht
	 */
	@Transient
	public abstract boolean isAuthorityGranted(String authorityName);

	/**
	 * Gibt die Liste der freigeschalteten Rechte zur�ck
	 * 
	 * @return die Liste
	 */
	@Transient
	public abstract List<String> getAuthorityNamesGranted();

	/**
	 * Gibt die Liste der nicht freigeschalteten Rechte zur�ck
	 * 
	 * @return die Liste
	 */
	@Transient
	public abstract List<String> getAuthorityNamesNotGranted();
}
