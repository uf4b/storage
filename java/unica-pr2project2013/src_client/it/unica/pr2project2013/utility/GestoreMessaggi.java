package it.unica.pr2project2013.utility;
import  it.unica.pr2project2013.ClientGrafico;

import java.util.Map;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 */
public class GestoreMessaggi {
	/**
	 * Mappa messaggi: Stato ->  Testo del messaggio
	 */
	private static Map<Stato, String> messaggi;


	/**
	 * Inizializzazione: Assegna i messaggi ai relativi stati
	 */
	public static void inizializzaMessaggi() {
		messaggi = new HashMap<Stato, String>();
		messaggi.put(Stato.ERRORE_CARICAMENTO_CONFIGURAZIONE, "Errore caricamento configurazione!");
	}
	
	/**
	 * Carica un messaggio dal file di configurazione
	 * 
	 * @param idMessaggio (String) ID messaggio
	 * @return Testo del messaggio
	 */
	public static String loadMessaggio(String idMessaggio) {
		return ClientGrafico.getConfigurazione("msg." + idMessaggio);
	}
	
	
	/**
	 * Mostra un message box di errore o informazione
	 * 
	 * @param stato Stato del programma
	 * @param tipoMsg (TipoMessaggio) Tipo messaggio (Errore , info)
	 */
	private static void showMessaggio(Stato stato, TipoMessaggio tipoMsg) {
		String titoloMsgBox = "Informazione:";
		int tipoMsgBox = JOptionPane.INFORMATION_MESSAGE;
		if (tipoMsg == TipoMessaggio.ERRORE) {
			titoloMsgBox = "Errore!";
			tipoMsgBox = JOptionPane.ERROR_MESSAGE;
		}
		JOptionPane.showMessageDialog(null, messaggi.get(stato), titoloMsgBox, tipoMsgBox);
	}
	
	/**
	 * Mostra un errore
	 * @param stato Stato del programma
	 */
	public static void showErrore(Stato stato) {
		showMessaggio(stato, TipoMessaggio.ERRORE);
	}
}
