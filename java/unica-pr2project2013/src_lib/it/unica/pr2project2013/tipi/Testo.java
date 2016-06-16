package it.unica.pr2project2013.tipi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.unica.pr2project2013.TabellaDati;

/**
 * Messaggio tabella
 */
public class Testo extends DatoTabella  {

	/**
	 * Contenuto
	 */
	private String contenuto;

	/**
	 * Override DatoTabella.getColonne()
	 */
	public static String[] getColonne() {
		return new String[] { TabellaDati.INTESTAZIONE_DEFAULT };
	}
	
	/**
	 * Implementazione DatoTabella.getDatiRiga()
	 */
	@Override
	public ArrayList<String> getDatiRiga() {
		ArrayList<String> riga = new ArrayList<String>();
		riga.add(getContenuto());
		return riga;
	}

	/**
	 * @return Contenuto
	 */
	public String getContenuto() 
	{
		return contenuto;
	}
	/**
	 * Imposta il contenuto del testo
	 * @return Oggetto testo
	 */
	public Testo setContenuto(String contenuto) 
	{
		this.contenuto = contenuto;
		return this;
	}
}
