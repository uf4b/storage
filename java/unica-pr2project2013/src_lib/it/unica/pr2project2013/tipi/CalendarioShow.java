package it.unica.pr2project2013.tipi;


/**
 * Informazioni calendario sulla trasmissione di serie tv
 */
public class CalendarioShow {
	
	private String day;
	private String time;
	
	/**
	 * Costruttore
	 */
	public CalendarioShow(String day, String time) {
		this.day = day;
		this.time = time;
	}
	
	/**
	 * @return Rappresentazione testuale orario
	 */
	@Override
	public String toString() {
		return String.format("%s at %s", day, time);
	}
}
