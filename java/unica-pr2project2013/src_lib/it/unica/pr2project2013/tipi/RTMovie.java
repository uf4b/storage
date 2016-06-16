package it.unica.pr2project2013.tipi;

import java.util.ArrayList;

/**
 * Classe Film RottenTomatoes
 */
public class RTMovie extends Movie {
	
	/**
	 * Classificazione MPAA
	 */	
	private String mpaa_rating;
	/**
	 * Valutazione
	 */
	private RTRatings ratings;
	
	/**
	 * Override Movie.getColonne()
	 */
	public static String[] getColonne() {
		return new String[] { 
			"Titolo", "Anno", "MPAA",
			"Anteprima", "Durata", "Gradimento", "Critiche"
		};
	}
	
	/**
	 * Override Movie.getDatiRiga()
	 */
	@Override
	public ArrayList<Object> getDatiRiga() {
		ArrayList<Object> riga = new ArrayList<Object>();
		riga.add(getTitle());
		riga.add(getYear());
		riga.add(getMpaa_rating());
		riga.add(getOverview());
		riga.add(getRuntime()); 
		riga.add(getRatings().getAudience_score("%d%%"));
		riga.add(getRatings().getCritics_score("%d%%"));
		return riga;
	}

	/**
	 * @return the mpaa_rating
	 */
	public String getMpaa_rating() {
		return mpaa_rating;
	}

	/**
	 * @param mpaa_rating the mpaa_rating to set
	 */
	public void setMpaa_rating(String mpaa_rating) {
		this.mpaa_rating = mpaa_rating;
	}

	/**
	 * @return the ratings
	 */
	public RTRatings getRatings() {
		return ratings;
	}
	
	/**
	 * @param ratings the ratings to set
	 */
	public void setRatings(RTRatings ratings) {
		this.ratings = ratings;
	}
}
