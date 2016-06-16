package it.unica.pr2project2013.tipi;

/**
 * Classe Valutazioni
 * Contiene informazioni sulle valutazioni fornite dagli utenti
 */
public class RTRatings {
	
    private int critics;
    private int audience;
    
    /**
     * Costruttore
     */
    public RTRatings(int critics_score, int audience_score) {
    	this.critics = critics_score;
    	this.audience = audience_score;
    }


	/**
	 * @return the critics_score
	 */
	public int getCritics_score() {
		return critics;
	}
	
	/**
	 * @return the critics_score (String)
	 */
	public String getCritics_score(String format) {
		return critics > 0 ? String.format(format, critics):"n.d";
	}
	
	/**
	 * @param critics_score the score to set
	 */
	public void setCritics_score(int score) {
		this.critics = score;
	}

	
	/**
	 * @return the audience_score
	 */
	public int getAudience_score() {
		return audience;
	}

	
	/**
	 * @return the critics_score (String)
	 */
	public String getAudience_score(String format) {
		return audience > 0 ? String.format(format, audience):"n.d";
	}
	
	/**
	 * @param audience_score the score to set
	 */
	public void setAudience_score(int score) {
		this.audience = score;
	}
}
