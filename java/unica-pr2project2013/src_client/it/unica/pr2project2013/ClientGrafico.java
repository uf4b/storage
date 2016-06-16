package it.unica.pr2project2013;
import  it.unica.pr2project2013.ui.*;
import  it.unica.pr2project2013.utility.*;

import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

/**
 * Programma principale: Client Grafico 
 */
public class ClientGrafico {

	/**
	 * Percorso del file di configurazione
	 */
	private final static String CONFIG_FILE = "it/unica/pr2project2013/config.properties";
	/**
	 * Contenuto file di configurazione del client grafico
	 * Contiene informazioni, testi e settaggi utilizzati nell'interfaccia utente
	 */
	private static Properties configurazione;
	
	/**
	 * Programma principale: visualizza la finestra principale
	 * 
	 * @param args Parametri programma
	 */
	public static void main(String[] args) {
		boolean configurazione = loadConfigurazione();
		GestoreMessaggi.inizializzaMessaggi();
		
		if (configurazione) {
			UtilityAspetto.setInterfacciaGrafica("nimbus");
			AbstractFinestra fTabellaDati = new FinestraTabellaDati();
			fTabellaDati.visualizza();
		}
	}
	
	
	/**
	 * Carica il file di configurazione principale
	 */
	private static boolean loadConfigurazione() {
		configurazione = new Properties();
		try {
			InputStream in = ClientGrafico.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
			configurazione.load(in);
		} catch (java.io.IOException e) {
			GestoreMessaggi.showErrore(Stato.ERRORE_CARICAMENTO_CONFIGURAZIONE);
			return false;
		}
		return true;
	}

	
	/**
	 * Restituisce una data stringa di configurazione del programma
	 * 
	 * @return Configurazione 
	 */
	public static String getConfigurazione(String identificativo) {
		if (configurazione != null) {
			return configurazione.getProperty(identificativo);
		} else {
			return null;
		}
	}
	
	
	/**
	 * Variante "numerica" del metodo getConfigurazione()
	 * 
	 * @return Configurazione 
	 */
	public static Number getConfigurazioneN(Class<?> tipo, String identificativo){
		if (tipo.equals(Integer.class)) {
			return Integer.valueOf(getConfigurazione(identificativo));
		}
		else if (tipo.equals(Double.class)) {
			return Double.valueOf(getConfigurazione(identificativo));
		}
		return null;
	}
}
