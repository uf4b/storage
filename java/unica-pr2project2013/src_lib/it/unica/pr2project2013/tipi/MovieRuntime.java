package it.unica.pr2project2013.tipi;

/**
 * Classe durata film
 */
public class MovieRuntime {

	private int minutes;
	
	/**
	 * Costruttore 
	 * @param minutes Minuti durata
	 */
	public MovieRuntime(int minutes) {
		this.minutes = minutes;
	}
	
	/**
	 * @return Rappresentazione testuale durata
	 */
	@Override
	public String toString() {
		if (minutes <= 0) {
			return "N.D";
		}
		return new String(minutes + " min");
	}
}
