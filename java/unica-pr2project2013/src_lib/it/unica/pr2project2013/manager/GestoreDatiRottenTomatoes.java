package it.unica.pr2project2013.manager;

import it.unica.pr2project2013.server.FornitoreDatiRottenTomatoes;
import it.unica.pr2project2013.tipi.RTMovie;

/**
 * Gestore Dati RottenTomatoes.com
 */
public class GestoreDatiRottenTomatoes extends GestoreDati {
	/**
	 * Input film in uscita
	 */
 	private static String CMD_OPENING = "A";
	/**
	 * Input prossimi film
	 */
	private static String CMD_UPCOMING = "B";
	
	/**
	 * Costruttore
	 */
	public GestoreDatiRottenTomatoes() {

		super(new String[]{CMD_OPENING, CMD_UPCOMING}); 
		
		txtMenu.setContenuto("A) In uscita, B) Prossimamente");

		mapColonne(CMD_OPENING, RTMovie.getColonne());
		mapColonne(CMD_UPCOMING,RTMovie.getColonne());
		
		mapFornitoreDati(CMD_OPENING, new FornitoreDatiRottenTomatoes("opening"));
		mapFornitoreDati(CMD_UPCOMING,new FornitoreDatiRottenTomatoes("upcoming"));
	}
}
