package it.unica.pr2project2013.server;

import it.unica.pr2project2013.tipi.DatoTabella;
import it.unica.pr2project2013.tipi.Filtro;
import it.unica.pr2project2013.tipi.TRMovie;

import java.util.Set;

/**
 * Fornitore dati Trakt.tv
 */
public class FornitoreDatiTrakt implements FornitoreDati {
	/**
	 * Tipologia elenco
	 */
	private String tipo;
	/**
	 * Servizi web Trakt.tv
	 */
	private TraktAPI apiMovies= new TraktAPI();
	private TraktAPI apiShows = new TraktAPIShow();
	
	/**
	 * Costruttore
	 * @param tipo Tipologia elenco
	 */
	public FornitoreDatiTrakt(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * @see {@link FornitoreDati#getElenco(Filtro)}
	 */
	@Override
	public Set<? extends DatoTabella> getElenco(Filtro filtro) throws Exception {
		TraktAPI api = tipo.equals("shows") ? apiShows : apiMovies;
		return (Set<? extends TRMovie>)api.getDati(tipo + "/trending", -1);
	}
}