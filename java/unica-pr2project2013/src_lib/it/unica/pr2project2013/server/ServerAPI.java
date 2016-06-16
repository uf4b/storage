package it.unica.pr2project2013.server;

import java.net.URL;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Servizio web generico
 * Fornisce metodi per interfacciarsi e interrogare una base di dati remota
 */
public abstract class ServerAPI<T> {
	/**
	 * Base URL da interrogare 
	 */
	private String API_URL = "";
	/**
	 * Mappa richiesta -> risultati
	 */
	private Map<String, Collection<? extends T>> storage;

	/**
	 * Costruttore: Inizializza l'URL di base
	 * @param url URL
	 */
	public ServerAPI(String url) {
		this.API_URL = url;
		storage = new HashMap<String, Collection<? extends T>>();
	}
	/**
	 * Processa i risultati della richiesta
	 * @param inputStream Input json
	 * @return Lista
	 */
	protected abstract Collection<? extends T> computaDati(InputStream inputStream) 
		throws Exception;
	
	/**
	 * Restituisce i risultati processati per la richiesta specificata
	 * 
	 * @param richiesta Frammento URL specifico per la richiesta dati
	 * @param limit Limite numero risultati
	 * @return Lista risultati
	 * @throws Exception 
	 */
	public Collection<? extends T> getDati(String richiesta, int limit) 
		throws Exception {
		if (storage.get(richiesta) == null) {
			//Costruisce l'URL completo per la richiesta
			String strUrl = String.format("http://" + API_URL, richiesta);
			if (limit > 0) {
				strUrl += "&limit=" + limit;
			}
			URL url = new URL(strUrl);
			//Memorizza i risultati processati
			storage.put(richiesta, computaDati(url.openStream()));
		}
		return storage.get(richiesta);
	}
}

