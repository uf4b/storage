package it.unica.pr2project2013.ui;

import javax.swing.UIManager;

public class UtilityAspetto {	
	/**
	 * Imposta l'interfaccia grafica nativa in base al sistema operativo usato
	 */
	private static void setInterfacciaGraficaNativa() {
		String sistemaOperativo = System.getProperty("os.name");
	    try {
	    	if (sistemaOperativo.equals("Linux")) 
	    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
	    	else if (sistemaOperativo.equals("Windows")) 
	    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }  
	}
	
	/**
	 * Imposta l'interfaccia grafica del tipo specificato
	 */
	public static void setInterfacciaGrafica(String tipo) {
		if (tipo.equals("os")) {
			setInterfacciaGraficaNativa();
		} else if (tipo.equals("nimbus")) {
		    try {
		    	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		    } catch (Exception e) {
		    	e.printStackTrace();
		    } 
		}
	}
}
