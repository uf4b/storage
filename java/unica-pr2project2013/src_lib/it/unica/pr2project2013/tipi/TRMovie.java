package it.unica.pr2project2013.tipi;

import java.util.ArrayList;

/**
 * Classe Film RottenTomatoes
 */
public class TRMovie extends Movie {

	/**
	 * Generi
	 */	
	private String[] genres;
	/**
	 * Visualizzazioni correnti
	 */	
	private int watchers;
	/**
	 * Valutazione
	 */	
	private TRatings ratings;

	
	/**
	 * Override Movie.getColonne()
	 */
	public static String[] getColonne() {
		return new String[] { 
			"Titolo", "Genere", "Anno", 
			"Durata", "Visualizzazioni", "Piace", "Non piace"
		};
	}
	
	/**
	 * Override Movie.getDatiRiga()
	 */
	@Override
	public ArrayList<Object> getDatiRiga() {
		ArrayList<Object> riga = new ArrayList<Object>();
		riga.add(getTitle());
		riga.add(getGenresStr());
		riga.add(getYear());
		riga.add(getRuntime()); 
		riga.add(getWatchers()); 
		riga.add(getRatings().getLoved());
		riga.add(getRatings().getHated());
		return riga;
	}

	/**
	 * @return the genres
	 */
	public String[] getGenres() {
		return genres;
	}
	
	/**
	 * @return the genres (String)
	 */ 
	public String getGenresStr() {
		String strGenres = "";
		for (int i = 0; i < genres.length; i++) {
			strGenres += genres[i] + (i < genres.length-1 ? ", ":"");
		}
		return strGenres;
	}
	
	/**
	 * @param genres the genres to set
	 */
	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	/**
	 * @return the watchers
	 */
	public int getWatchers() {
		return watchers;
	}

	/**
	 * @param watchers the watchers to set
	 */
	public void setWatchers(int watchers) {
		this.watchers = watchers;
	}

	/**
	 * @return the ratings
	 */
	public TRatings getRatings() {
		return ratings;
	}

	/**
	 * @param ratings the ratings to set
	 */
	public void setRatings(TRatings ratings) {
		this.ratings = ratings;
	}
}
