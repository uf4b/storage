package it.unica.pr2project2013.tipi;

import java.util.ArrayList;

/**
 * Classe Dato tabella: Fornisce le colonne e dati
 */
abstract public class DatoTabella {
	
	/**
	 * @return Array colonne
	 */
	public static String[] getColonne() {
		return new String[]{""};
	}
	
	/**
	 * @return Valori riga
	 */
	public abstract ArrayList<? extends Object> getDatiRiga();
}
