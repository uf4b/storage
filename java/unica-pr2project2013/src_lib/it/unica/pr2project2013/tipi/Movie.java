package it.unica.pr2project2013.tipi;

import java.util.ArrayList;

/**
 * Classe Film generico
 */
public class Movie extends DatoTabella  {

	/**
	 * Titolo
	 */
	private String title;
	/**
	 * Anno
	 */
	private int year;
	/**
	 * Anteprima
	 */
	private String overview;
	/**
	 * Durata
	 */
	private MovieRuntime runtime;
	
	/**
	 * Override DatoTabella.getColonne()
	 */
	public static String[] getColonne() {
		return new String[] { 
			"Titolo", "Anno", "Anteprima", "Durata"
		};
	}
	
	/**
	 * Implementazione DatoTabella.getDatiRiga()
	 */
	@Override
	public ArrayList<Object> getDatiRiga() {
		ArrayList<Object> riga = new ArrayList<Object>();
		riga.add(getTitle());
		riga.add(getYear());
		riga.add(getOverview());
		riga.add(getRuntime()); 
		return riga;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the overview
	 */
	public String getOverview() {
		return overview;
	}

	/**
	 * @param overview the overview to set
	 */
	public void setOverview(String overview) {
		this.overview = overview;
	}

	/**
	 * @return the runtime
	 */
	public MovieRuntime getRuntime() {
		return runtime;
	}

	/**
	 * @param runtime the runtime to set
	 */
	public void setRuntime(int minutes) {
		this.runtime = new MovieRuntime(minutes);
	}
}
