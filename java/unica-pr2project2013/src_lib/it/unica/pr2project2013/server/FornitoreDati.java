package it.unica.pr2project2013.server;

import it.unica.pr2project2013.tipi.DatoTabella;
import it.unica.pr2project2013.tipi.Filtro;

import java.util.Set;

/**
 * Interfaccia Fornitore dati tabella
 */
public interface FornitoreDati
{
	/**
	 * Restituisce l'elenco dati eleborato
	 * @param filtro Input tabella
	 * @return Set
	 * @throws Exception 
	 */
	public Set<? extends DatoTabella> getElenco(Filtro filtro) throws Exception;
}
