package it.unica.pr2project2013.ui.assets;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

/**
 * Classe Testo formattato
 */
public class TestoFormattato extends JLabel implements Cloneable {
	
	public TestoFormattato(Font font) {
		super();
		setFont(font);
	}
	
	public TestoFormattato(Font font, Color foreground) {
		this(font);
		setForeground(foreground);
	}
	
	public TestoFormattato(Font font, Color foreground, Color background) {
		this(font, foreground);
		setBackground(background);
	}
	
	public JLabel creaComponente(String testo) {
		JLabel componente;
		try {
			componente = (JLabel) this.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		componente.setText(testo);
		return componente;
	}
}

