package it.unica.pr2project2013.ui;
import  it.unica.pr2project2013.ClientGrafico;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Classe Finestra generica
 *  - Fornisce dei metodi per automatizzare la costruzione iniziale
 */
public abstract class AbstractFinestra extends JFrame {
	/**
	 * ID Globale
	 */
	protected String id;
	
	/**
	 * Costruttore finestra astratta
	 * - Inizializza la finestra con titolo, larghezza e altezza da configurazione file
	 * 
	 * @param id Identificativo globale finestra
	 */
	public AbstractFinestra(String id) {
		super();
		this.id = id;
		//Imposta il titolo
		setTitle((String) this.getImpostazioneConf(String.class, "titoloFinestra"));

		//Imposta le dimensioni della finestra
		int larghezza = (Integer) this.getImpostazioneConf(Integer.class, "larghezza");
		int altezza = (Integer) this.getImpostazioneConf(Integer.class, "altezza");
		Dimension dim = new Dimension(larghezza, altezza);
		setPreferredSize(dim);
		setMaximumSize(dim);

  		//Imposta l'azione alla chisura della finestra
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Crea i controlli da aggiungere alla finestra
 		inizializzaControlli(getContentPane());
 		pack();
	}
	
	
	/**
	 * Restituisce una data impostazione dal file di configurazione per questa finestra
	 * 
	 * @param tipo Tipo di dato da restituire
	 * @param im Nome impostazione
	 * @return Valore impostazione 
	 */
	protected Object getImpostazioneConf(Class<?> tipo, String im) {
		String identificativo = "ui." + id + "." + im;
		if (tipo.equals(String.class)) {
			return ClientGrafico.getConfigurazione(identificativo);
		}
		return ClientGrafico.getConfigurazioneN((Class<Number>)tipo, identificativo);
	}
	
	/**
	 * Crea e aggiunge i vari controlli alla finestra
	 */
	protected abstract void inizializzaControlli(Container panel);
	

	/**
	 * Visualizza la finestra e i controlli associati
	 */
	public void visualizza() {
		setVisible(true);
	}
}
