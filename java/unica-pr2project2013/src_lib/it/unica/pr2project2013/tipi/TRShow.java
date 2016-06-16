package it.unica.pr2project2013.tipi;

import java.util.ArrayList;

/**
 * Classe Serie TV
 */
public class TRShow extends TRMovie {
	
	
	private String network;
	private String certification;
	private CalendarioShow cal;

	
	/**
	 * Override TRMovie.getColonne()
	 */
	public static String[] getColonne() {
		return new String[] { 
			"Titolo", "Genere", "Anno", "Rete", "Certificazione",
			"In onda", "Visualizzazioni", "Piace", "Non piace"
		};
	}
	
	/**
	 * Override TRMovie.getDatiRiga()
	 */
	@Override
	public ArrayList<Object> getDatiRiga() {
		ArrayList<Object> riga = new ArrayList<Object>();
		riga.add(getTitle());
		riga.add(getGenresStr());
		riga.add(getYear());
		riga.add(getNetwork());
		riga.add(getCertification()); 
		riga.add(getCal()); 
		riga.add(getWatchers()); 
		riga.add(getRatings().getLoved());
		riga.add(getRatings().getHated());
		return riga;
	}

	/**
	 * @return the network
	 */
	public String getNetwork() {
		return network;
	}

	/**
	 * @param network the network to set
	 */
	public void setNetwork(String network) {
		this.network = network;
	}

	/**
	 * @return the certification
	 */
	public String getCertification() {
		return certification;
	}

	/**
	 * @param certification the certification to set
	 */
	public void setCertification(String certification) {
		this.certification = certification;
	}

	/**
	 * @return the cal
	 */
	public CalendarioShow getCal() {
		return cal;
	}

	/**
	 * @param cal the cal to set
	 */
	public void setCal(CalendarioShow cal) {
		this.cal = cal;
	}
}
