package es.ucm.abd.practica2.controller;

import es.ucm.abd.practica2.dao.AbedemonDAO;
import es.ucm.abd.practica2.model.Abedemon;
import es.ucm.abd.practica2.model.AbedemonTableModel;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 * Controlador de los paneles de búsqueda
 *  
 * @author Manuel Montenegro (mmontene@ucm.es)
 */
public class SearchPanelController {
    private final DefaultComboBoxModel<String> types;
    private final AbedemonTableModel abedemons;
    private final AbedemonDAO dao;
    
    public SearchPanelController(
            List<String> types,
            List<Abedemon> abedemons,
            AbedemonDAO dao) {
        this.types = new DefaultComboBoxModel<>(types.toArray(new String[types.size()]));
        this.types.insertElementAt("[Seleccionar]", 0);
        this.abedemons = new AbedemonTableModel();
        this.abedemons.changeModel(abedemons);
        this.dao = dao;
    }
    
    public void searchAbedemonsOf(int typeIndex) {
        if (typeIndex != 0) {
            String type = types.getElementAt(typeIndex);
            List<Abedemon> abedemonsList = dao.getAbedemonsOf(type);
            if (abedemonsList != null) {
                abedemons.changeModel(abedemonsList);
            }
        }
    }

    
    public String getAbedemonInfo(int rowIndex) {
        if (rowIndex != -1) {
            String id = (String) abedemons.getValueAt(rowIndex, 0);
            return getAbedemonInfo(id);
        }
        return "";
    }

    public String getAbedemonInfo(String id) {
        String description = dao.getAbedemonDescription(id);
        return (description == null ? "" : description);
    }
    
    public DefaultComboBoxModel<String> getTypes() {
        return types;
    }
    
    public Abedemon getAbedemon(int index) {
        return index == -1 ? null : abedemons.getAbedemon(index);
    }

    public AbedemonTableModel getAbedemons() {
        return abedemons;
    }
}
