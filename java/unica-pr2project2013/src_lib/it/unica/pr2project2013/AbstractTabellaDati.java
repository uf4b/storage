package it.unica.pr2project2013;

import javax.swing.table.TableModel;

public abstract class AbstractTabellaDati implements TableModel {

	/* Da utilizzare per creare oggetti (no costruttori) */
	public static AbstractTabellaDati getTabellaDati() {
		throw new UnsupportedOperationException("da implementare");
	}

	/* Restituisce true se la cella in posizione (rowIndex,columnIndex) è modificabile. Deve restituire sempre true per tutte le celle con rowIndex == 0 */
	public abstract boolean isCellEditable(int rowIndex, int columnIndex);

}
