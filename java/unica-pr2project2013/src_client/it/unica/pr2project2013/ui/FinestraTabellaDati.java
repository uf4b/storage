package it.unica.pr2project2013.ui;
import  it.unica.pr2project2013.AbstractTabellaDati;
import  it.unica.pr2project2013.TabellaDati;
import  it.unica.pr2project2013.ui.assets.*;
import  it.unica.pr2project2013.utility.ControlloreConnessione;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.table.TableModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Classe finestra principale tabella dati
 */
public final class FinestraTabellaDati extends AbstractFinestra 
	implements TableModelListener, Runnable {
	
	/**
	 * Esecutori controllo connessione periodico
	 */
	private ScheduledExecutorService aggiornamentoStatoConnessione;
	private ExecutorService controlloConnessione;
	/**
	 * Stato connessione internet
	 */
	private boolean connessioneAttiva = false;
	
	/**
	 * Costruttore finestra principale
	 */
	public FinestraTabellaDati()  {
		super("tabellaDati");
		setResizable(false);
		aggiornamentoStatoConnessione = Executors.newScheduledThreadPool(1);
		aggiornamentoStatoConnessione.scheduleAtFixedRate(this, 5, 30, TimeUnit.SECONDS);
	}
	
	
	/**
	 * Implementazione Runnable.run(): 
	 * Controlla ogni 30 secondi lo stato della connessione a internet
	 * Imposta il testo di notifica nella barra di stato
	 */
	@Override
	public void run() {
		controlloConnessione = Executors.newSingleThreadExecutor();
		Future<Boolean> fnConnessione = controlloConnessione.submit(new ControlloreConnessione());
		try {
			connessioneAttiva = fnConnessione.get(5, TimeUnit.SECONDS);
		} catch (Exception e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
		if (connessioneAttiva) 
			((JLabel) getCmpStatoConnessione()).setText(
				(String)getImpostazioneConf(String.class, "statusConnesso"));
		else
			((JLabel) getCmpStatoConnessione()).setText(
				(String)getImpostazioneConf(String.class, "statusNonConnesso"));
		
		controlloConnessione.shutdown();
	}
	
	/**
	 * Implementazione AbstractFinestra.inizializzaControlli()
	 */
	@Override
	protected void inizializzaControlli(Container panel) {

		JPanel bg = new ImmagineSfondo("tv2.png");
		bg.setLayout(new BorderLayout());
		bg.setBorder(new EmptyBorder(20, 20, 10, 15));
		bg.add(creaImmagineTestata(), BorderLayout.PAGE_START);
		bg.add(creaTabella(), BorderLayout.CENTER);
		bg.add(creaBarraDiStato(), BorderLayout.PAGE_END);
		panel.add(bg);
	}
	
	
	/**
	 * @return Componente: Testata (Titolo e firma)
	 */
	private JPanel creaImmagineTestata() {
		JPanel testata = new JPanel();
		testata.setLayout(new BoxLayout(testata, BoxLayout.PAGE_AXIS));
		testata.setOpaque(false);
		
		testata.add(creaIntestazione());
		testata.add(creaFirma());

		return testata;
	}
	
	/**
	 * @return Componente: Titolo progetto
	 */
	private JLabel creaIntestazione() {
		Font fontTitolo = new Font(Font.DIALOG, Font.BOLD, 20);
		TestoFormattato testoFormattato = new TestoFormattato(fontTitolo, Color.WHITE);
		
		String titolo = (String)getImpostazioneConf(String.class, "titoloIntestazione");
		JLabel lblTitolo = testoFormattato.creaComponente(titolo);
		lblTitolo.setBorder(new EmptyBorder(20, 100, 0, 0));

		return lblTitolo;
	}
	
	/**
	 * @return Componente: Firma intestazione
	 */
	private JLabel creaFirma() {
		Font fontTitolo = new Font(Font.DIALOG, Font.ITALIC, 16);
		TestoFormattato testoFormattato = new TestoFormattato(fontTitolo, Color.WHITE);
		
		String firma = (String)getImpostazioneConf(String.class, "credits");
		JLabel lblFirma = testoFormattato.creaComponente(firma);
		lblFirma.setBorder(new EmptyBorder(10, 100, 30, 0));

		return lblFirma;
	}
	
	/**
	 * @return Componente: Tabella
	 */
	private JScrollPane creaTabella() {
		AbstractTabellaDati tabellaDati = TabellaDati.getTabellaDati();
		JTable tabella = new JTable(tabellaDati);
		tabella.getModel().addTableModelListener(this);

		JScrollPane tabPane = new JScrollPane(tabella);
		tabPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		return tabPane;
	}

	/**
	 * Implementazione TableModelListener.tableChanged()
	 * Imposta il testo di aggiornamento dati sulla barra di stato
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		if (((TableModel)e.getSource()).getRowCount() > 3)
			((JLabel) getCmpStatoDati()).setText(getStatusAggiornamento(false));
	}

	
	/**
	 * @return Componente: Barra di stato
	 */
	private JPanel creaBarraDiStato() {
		JPanel status = new JPanel();
		status.setLayout(new BoxLayout(status, BoxLayout.LINE_AXIS));
		status.setBorder(new EmptyBorder(140, 10, 0, 10));
		status.setOpaque(false);

		Font fontTesto = new Font(Font.DIALOG, Font.BOLD, 12);
		TestoFormattato testoFormattato = new TestoFormattato(fontTesto, Color.BLACK);

		final JLabel lblStatoConn = testoFormattato.creaComponente("Controllo connessione in corso...");
		lblStatoConn.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if (!e.getPropertyName().equals("text"))
					return;
				String msgNonConnesso = (String)getImpostazioneConf(String.class, "statusNonConnesso");
				if (((String)e.getNewValue()).equals(msgNonConnesso))
					lblStatoConn.setForeground(Color.RED);
			}
		});
		status.add(lblStatoConn);
		status.add(Box.createHorizontalGlue());
		status.add(testoFormattato.creaComponente(getStatusAggiornamento(true)));
		
		return status;
	}
	
	/**
	 * @return Componente: barra di stato
	 */
	private JPanel getBarraStato() {
		return (JPanel)((JPanel)getContentPane().getComponent(0)).getComponent(2);
	}
	
	/**
	 * @return Componente barra di stato: Testo stato aggiornamento  dati
	 */
	private Component getCmpStatoDati() {
		return getBarraStato().getComponent(2);
	}
	
	/**
	 * @return Componente barra di stato: Testo stato connessione
	 */
	private Component getCmpStatoConnessione() {
		return getBarraStato().getComponent(0);
	}
	

	/**
	 * Genera il testo di aggiornamento per la barra di stato
	 * @param iniziale Indica primo avvio programma
	 * @return Testo
	 */
	private String getStatusAggiornamento(boolean iniziale) {
		final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		final Calendar cal = Calendar.getInstance();
		if (!iniziale) {
			return "Ultimo aggiornamento: (" + dateFormat.format(cal.getTime()) + ")";
		} else {
			return "Stato: Pronto";
		}
	}
}
