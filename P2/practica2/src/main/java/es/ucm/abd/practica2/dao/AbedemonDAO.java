package es.ucm.abd.practica2.dao;

import es.ucm.abd.practica2.model.Abedemon;
import java.util.List;

/**
 * Acceso a datos del Abedemon
 * 
 * @author Manuel Montenegro (mmontene@ucm.es)
 */
public interface AbedemonDAO {
    List<String> getTypes();
    List<Abedemon> getAbedemonsOf(String type);
    String getAbedemonDescription(String id);
    Integer getDamage(String idAttacker, String idReceiver);
}
