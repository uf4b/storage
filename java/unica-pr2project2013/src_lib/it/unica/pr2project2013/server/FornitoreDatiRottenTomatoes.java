package it.unica.pr2project2013.server;

import it.unica.pr2project2013.tipi.DatoTabella;
import it.unica.pr2project2013.tipi.Filtro;
import it.unica.pr2project2013.tipi.RTMovie;

import java.util.Set;

/**
 * Fornitore dati RottenTomatoes.com
 */
public class FornitoreDatiRottenTomatoes implements FornitoreDati {
	/**
	 * Tipologia elenco
	 */
	private String tipo;
	/**
	 * Servizio web Rottentomatoes.com
	 */
	private RottenTomatoesAPI api = new RottenTomatoesAPI();
	
	/**
	 * Costruttore
	 * @param tipo Tipologia elenco
	 */
	public FornitoreDatiRottenTomatoes(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * @see {@link FornitoreDati#getElenco(Filtro)}
	 */
	@Override
	public Set<? extends DatoTabella> getElenco(Filtro filtro) throws Exception {
		return (Set<RTMovie>) api.getDati("lists/movies/" + tipo, 20);
	}
}


