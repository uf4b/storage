package it.unica.pr2project2013.manager;

import it.unica.pr2project2013.TabellaDati;
import it.unica.pr2project2013.server.FornitoreDati;
import it.unica.pr2project2013.tipi.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe Gestore Dati: Elabora l'elenco dei dati per la tabella in base all'input
 */
public class GestoreDati {
	
	/**
	 * Identificatore input vuoto
	 */
	public final static String CMD_VUOTO = "";
	/**
	 * Identificatore input errore
	 */
	public final static String CMD_ERRORE = "errore";
	/**
	 * Identificatore input menu
	 */
	public final static String CMD_INIZIALE = "menu";
	/**
	 * Menu input
	 */
	private String[] inputMap;
	/**
	 * Testo menu 
	 */
	protected Testo txtMenu = new Testo();
	/**
	 * Mappa filtro -> colonne
	 */	
	private Map<String, String[]> colonne = new HashMap<String, String[]>();
	/**
	 * Mappa filtro -> fornitore dati 
	 */
	private Map<String, FornitoreDati> fornitoreDati = new HashMap<String, FornitoreDati>();
	/**
	 * Input prima colonna che stabilisce che dati visualizzare e relative colonne
	 */
	private String input = CMD_INIZIALE;
	/**
	 * Input attualmente impostato: Indice colonna e valore
	 */
	private Filtro filtro = new Filtro();
	/**
	 * Dati variabili ottenuti dinamicamente dai fornitori addetti
	 */
	private Collection<? extends Object> dati = null;
	
	
	/**
	 * Costruttore: Inizializza le colonne predefinite e il menu
	 */
	public GestoreDati(String[] inputMap) {
		//Imposta le colonne per la griglia di partenza con il menu
		colonne.put(CMD_INIZIALE, new String[]{TabellaDati.INTESTAZIONE_DEFAULT});
		//Imposta le colonne per la griglia senza risultati
		colonne.put(CMD_VUOTO, new String[]{TabellaDati.INTESTAZIONE_DEFAULT});
		//Imposta le colonne per la griglia in caso di errore
		colonne.put(CMD_ERRORE, new String[]{TabellaDati.INTESTAZIONE_DEFAULT});
		
		this.inputMap = inputMap;
	}
	
	/**
	 * Registra il fornitore dati per il dato menu
	 * @param input Valore menu
	 * @param implementazione Implementazione fornitore
	 */
	protected void mapFornitoreDati(String input, FornitoreDati implementazione) {
		fornitoreDati.put(input, implementazione);
	}
	
	
	/**
	 * Registra le colonne il dato menu
	 * @param input  Valore menu
	 * @param colonne Colonne
	 */
	protected void mapColonne(String input, String[] colonne) {
		this.colonne.put(input, colonne);
	}
	
	
	/**
	 * Restituisce i dati per le righe
	 *  - In base all'input fornito verrà dinamicamente popolata la tabella
	 * 
	 * @return Valori righe  
	 */
	public List<ArrayList<Object>> getRighe() {
		ArrayList<Object> primaRiga, menu = new ArrayList<Object>();
		
		//Messaggi in caso di menu iniziale, dati non trovati, errore
		Testo messaggio = new Testo();
		if (input.equals(CMD_INIZIALE)) {
			menu.add(txtMenu);
		} else if (input.equals(CMD_VUOTO)) {
			menu.add(messaggio.setContenuto("Nessun risultato con questi criteri! Riprova"));
			menu.add(txtMenu);
		} else if (input.equals(CMD_ERRORE)) {
			menu.add(messaggio.setContenuto("Si è verificato un errore interno! Riprova..."));
		} 
		//Altrimenti, elenco dinamico in base al menu
		else {
			try {
				dati = fornitoreDati.get(input).getElenco(filtro);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		//Se è stato impostato il menu, assegna come dati i messaggi creati precedentemente
		if (!menu.isEmpty()) {
			dati = menu;
		}
		ArrayList<ArrayList<Object>> righe = new ArrayList<ArrayList<Object>>();
		
		//Riempie la prima riga e colonne con i valori immessi dall'utente in input
		primaRiga = new ArrayList<Object>();
		for (int y = 0; y < getColonne().length; y++) {
			primaRiga.add(y, filtro.getColonna() == y ? filtro.getValore() : "");
		}
		righe.add(0, primaRiga);
		//Riempie le rimanenti righe con i dati dinamici
		int i = 1;
		for (Object o: dati) {
			//Ottengo i valori delle colonne per la riga
			ArrayList<Object> riga = (ArrayList<Object>) ((DatoTabella) o).getDatiRiga();
			//Non riempie la prima colonna riservata per il menu
			if (menu.isEmpty()) {
				riga.add(0, "");
			}
			righe.add(i, riga);
			i++;
		}
		return righe;
	}

	/**
	 * Restituisce l'elenco di colonne per l'input attuale impostato:
	 * La prima colonna verrà sempre riservata per fornire l'input
	 * 
	 * @return Colonne
	 */
	public String[] getColonne() {
		//Se si sta popolando la tabella con dati dinamici, riserva la
		//colonna per il menu nella prima posizione
		if (!this.colonne.get(input)[0].equals(TabellaDati.INTESTAZIONE_DEFAULT)) {
			String[] colonne = new String[this.colonne.get(input).length + 1];
			colonne[0] = TabellaDati.INTESTAZIONE_DEFAULT;
			for (int y = 1; y < colonne.length; y++) {
				colonne[y] = this.colonne.get(input)[y-1];
			}
			return colonne;
		}
		//Altrimenti colonne predefinite
		return this.colonne.get(input);
	}
	
	/**
	 * Gestisce l'input utente
	 * - L'input di menu deve essere valido altrimenti si visualizzerà un elenco vuoto
	 * - Se l'input è valido e si ottiene una lista nulla significa errore durante
	 *   il reperimento/elaborazione dei dati e si visualizzerà l'avviso di errore
	 * @param input Valore menu
	 */
	public void setInput(String input) {
		//Se l'input è valido, verrà mantenuto il valore impostato 
		//per gli step di elaborazione successivi del gestore
		this.input = input;
		//Se l'input non è un menu valido, imposta il gestore per elenchi vuoti
		if (!input.equals(CMD_INIZIALE) 
		 && !(Arrays.binarySearch(inputMap, input) >= 0)) {
			this.input = CMD_VUOTO;
		}
		//Altrimenti, in caso di errore di elaborazione, imposta il gestore per errori
		else if (getRighe() == null) {
			this.input = CMD_ERRORE;
		}
	}
	
	/**
	 * Restituiste il filtro attuale
	 * @return Filtro
	 */
	public Filtro getFiltro() {
		return filtro;
	}
}
