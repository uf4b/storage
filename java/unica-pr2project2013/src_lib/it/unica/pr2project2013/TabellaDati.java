package it.unica.pr2project2013;

import it.unica.pr2project2013.manager.GestoreDati;
import it.unica.pr2project2013.manager.GestoreDatiRottenTomatoes;
import it.unica.pr2project2013.manager.GestoreDatiTrakt;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * Implementazione AbstractTabellaDati
 */
public class TabellaDati extends AbstractTabellaDati 
	implements TableModelListener { 
	
	/**
	 * Intestazione colonna predefinita
	 */
	public final static String INTESTAZIONE_DEFAULT = "Menu";
	/**
	 * Elenco listeners
	 */
	private List<TableModelListener> listeners = new ArrayList<TableModelListener>();
	/**
	 * Gestore dati tabella
	 */
	private GestoreDati gd = null;	
	/**
	 * Costruttore: Registra il listener e inizializza il gestore dati
	 */
	public TabellaDati() {
		addTableModelListener(this); 
		loadGestoreDati(); 
	}
	
	/**
	 * Implementazione AbstractTabellaDati.getTabellaDati()
	 */
	public static AbstractTabellaDati getTabellaDati() {
		return new TabellaDati();
	}

	
	/**
	 * Inizializza il gestore dati definito nella configurazione: 
	 * In caso di problemi con un determinato servizio web in uso
	 * si può passare al servizio alternativo modificando il file di configurazione
	 */
	private void loadGestoreDati() {
		final String CONF_PATH = "it/unica/pr2project2013/manager.config";
		String manager;

		Properties gdConf = new Properties();
		try {
			InputStream in = TabellaDati.class.getClassLoader().getResourceAsStream(CONF_PATH);
			gdConf.load(in);
			manager = gdConf.getProperty("manager");
		} catch (java.io.IOException e) {
			manager = "rottentomatoes";
		}
		if (manager.equals("trakt")) {
			gd = new GestoreDatiTrakt();
		} else {
			gd = new GestoreDatiRottenTomatoes();
		}
	}

	
	/**
	 * Implementazione TableModel.getValueAt()
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return gd.getRighe().get(rowIndex).get(columnIndex);
	}

	/**
	 * Implementazione TableModel.setValueAt(). 
	 * Notifica i listeners che è stata modificata una cella e c'è bisogno di ricaricare la tabella
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		//Memorizza l'input utente
		gd.getFiltro().set(columnIndex, (String)aValue);
		//Notifica i listener
		for (TableModelListener listener : listeners) {
			listener.tableChanged(new TableModelEvent(this, TableModelEvent.HEADER_ROW));
		}
	}
	
	/**
	 * Implementazione TableModelListener.tableChanged(). 
	 * Aggiorna la sorgente dati utilizzata dal gestore dati
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		//E' stata modificata la prima colonna, aggiorna la sorgente dati del gestore
		if (gd.getFiltro().getColonna() == 0) {
			gd.setInput(gd.getFiltro().getValore());
		} 
	}
	
	/**
	 * Implementazione TableModel.isCellEditable(). 
	 * Imposta la prima prima riga come editabile
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return rowIndex == 0;
	}
	
	/**
	 * Implementazione TableModel.addTableModelListener()
	 * Registra il listener per gli eventi di aggiornamento della tabella
	 */
	@Override
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}
	
	/**
	 * Implementazione TableModel.removeTableModelListener()
	 */
	@Override
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}
	
	/**
	 * Implementazione TableModel.getRowCount()
	 */
	@Override
	public int getRowCount() {
		return gd.getRighe().size();
	}
	
	/**
	 * Implementazione TableModel.getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return gd.getColonne().length;
	}
	
	/**
	 * Implementazione TableModel.getColumnName()
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return gd.getColonne()[columnIndex];
	}

	/**
	 * Implementazione TableModel.getColumnClass()
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}
}
