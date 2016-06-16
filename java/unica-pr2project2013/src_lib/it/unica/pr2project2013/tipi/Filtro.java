package it.unica.pr2project2013.tipi;

/**
 * Input attuale tabella: Indice colonna e valore
 */
public class Filtro {
	/**
	 * Indice colonna
	 */
	private int colonna;
	/**
	 * Valore
	 */
	private String valore;
	
	/**
	 * Costruttore vuoto
	 */
	public Filtro() { }
	
	/**
	 * Costruttore: Imposta il filtro
	 * @param colonna Indice colonna
	 * @param valore Valore input
	 */
	public Filtro(int colonna, String valore) { 
		set(colonna, valore);
	}
	
	/**
	 * Imposta il filtro
	 * @param colonna Indice colonna
	 * @param valore Valore input
	 */
	public void set(int colonna, String valore) {
		this.colonna = colonna;
		this.valore = valore;
	}

	/**
	 * @return Indice colonna
	 */
	public int getColonna() {
		return colonna;
	}

	/**
	 * @return Valore input
	 */
	public String getValore() {
		return valore;
	}
}
