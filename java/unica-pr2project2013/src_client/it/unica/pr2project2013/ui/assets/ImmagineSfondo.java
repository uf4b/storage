package it.unica.pr2project2013.ui.assets;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Pannello con sfondo caricato da file
 */
public class ImmagineSfondo extends JPanel {
	/**
	 * Immagine sfondo
	 */
	Image sfondo;
	
	/**
	 * Costruttore 
	 * @param file File immagine
	 */
	public ImmagineSfondo(String file) {
		super();
		sfondo = Toolkit.getDefaultToolkit()
				.createImage(this.getClass().getResource("img/" + file));
	}
	/**
	 * Secondo Costruttore 
	 * @param file File immagine
	 * @param dimensioni Dimensioni
	 */
	public ImmagineSfondo(String file, Dimension dimensioni) {
		this(file);
		setSize(dimensioni);
		setPreferredSize(dimensioni);
		setMaximumSize(dimensioni);
	}
	
	/**
	 * Ovverride metodo JComponent.paintComponent() per applicare lo sfondo
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;  
		g2.setRenderingHint(
			RenderingHints.KEY_INTERPOLATION, 
			RenderingHints.VALUE_INTERPOLATION_BICUBIC
		); 
		g2.drawImage(sfondo, 0, 0, getWidth(), getHeight(),this);
	}
}	