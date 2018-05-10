package es.ucm.abd.practica2.model;

/**
 * Clase para almacenar los resultados de la consulta 2
 *
 * @author Manuel Montenegro (mmontene@ucm.es)
 */
public class Abedemon {
    private final String id;
    private final String nombre;
    private final int numAtaques;

    public Abedemon(String id, String nombre, int numPoderes) {
        this.id = id;
        this.nombre = nombre;
        this.numAtaques = numPoderes;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumAtaques() {
        return numAtaques;
    }
}
