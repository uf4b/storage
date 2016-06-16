package it.unica.pr2project2013.tipi;

/**
 * Classe Valutazioni
 * Contiene informazioni sulle valutazioni fornite dagli utenti
 */
public class TRatings  {

    private int loved;
    private int hated;
    
    /**
     * Costruttore
     */
    public TRatings(int loved, int hated) {
    	this.loved = loved;
    	this.hated = hated;
    }

	/**
	 * @return the loved
	 */
	public int getLoved() {
		return loved;
	}

	/**
	 * @param loved the loved to set
	 */
	public void setLoved(int loved) {
		this.loved = loved;
	}

	/**
	 * @return the hated
	 */
	public int getHated() {
		return hated;
	}

	/**
	 * @param hated the hated to set
	 */
	public void setHated(int hated) {
		this.hated = hated;
	}
}
