package it.unica.pr2project2013.manager;

import it.unica.pr2project2013.server.FornitoreDatiTrakt;
import it.unica.pr2project2013.tipi.TRMovie;
import it.unica.pr2project2013.tipi.TRShow;

/**
 * Gestore Dati Trackt.tv
 */
public class GestoreDatiTrakt extends GestoreDati {
	/**
	 * Input serie tv
	 */
	private final static String CMD_TV = "A";
	/**
	 * Input film 
	 */
	private final static String CMD_MOVIES = "B";
	
	/**
	 * Costruttore
	 */
	public GestoreDatiTrakt() {

		super(new String[]{CMD_TV, CMD_MOVIES}); 
		
		txtMenu.setContenuto("A) Serie TV, B) Film");
		
		mapColonne(CMD_TV,     TRShow.getColonne());
		mapColonne(CMD_MOVIES, TRMovie.getColonne());

		mapFornitoreDati(CMD_TV,     new FornitoreDatiTrakt("shows"));
		mapFornitoreDati(CMD_MOVIES, new FornitoreDatiTrakt("movies"));
	}
}
