package es.ucm.abd.practica2.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Modelo para el JTable de cada panel de búsqueda.
 * 
 * @author Manuel Montenegro (mmontene@ucm.es)
 */
@SuppressWarnings("serial")
public class AbedemonTableModel extends AbstractTableModel {
    private static final int NUM_COLUMNS = 3;
    private static final String[] COLUMN_NAMES = 
            new String[] { "Id", "Nombre", "Num. Poderes" };
    
    private Abedemon[] model;
    
    public AbedemonTableModel() {
        model = new Abedemon[0];
    }
    
    public Abedemon getAbedemon(int i) {
        return model[i];
    }
    
    public void changeModel(List<Abedemon> newModel) {
        model = newModel.toArray(new Abedemon[newModel.size()]);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return model.length;
    }

    @Override
    public int getColumnCount() {
        return NUM_COLUMNS;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Abedemon row = model[rowIndex];
        switch (columnIndex) {
            case 0: return row.getId();
            case 1: return row.getNombre();
            case 2: return row.getNumAtaques();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
}
