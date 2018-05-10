package abd.p1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que representa una opción de una pregunta en la base de datos.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
@Entity
@Table(name = "Respuestas")
public class Opcion
{
    @ManyToOne
    @JoinColumn(name = "pregunta")
    private Pregunta preguntaMadre;

    @Column(name = "numero_orden", nullable = false)
    private int numeroOrden;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "texto_respuesta", nullable = false)
    private String texto;

    public Opcion()
    {
    }

    public Opcion(Pregunta preguntaMadre, int numeroOrden, String texto)
    {
        this.preguntaMadre = preguntaMadre;
        this.numeroOrden = numeroOrden;
        this.id = null;
        this.texto = texto;
    }

    //--------------------------GETTERS--------------------------//
    public int getId()
    {
        return id;
    }

    public Pregunta getPreguntaMadre()
    {
        return preguntaMadre;
    }

    public int getNumeroOrden()
    {
        return numeroOrden;
    }

    public String getTexto()
    {
        return texto;
    }

    //--------------------------SETTERS--------------------------//
    public void setId(int id)
    {
        this.id = id;
    }

    public void setPreguntaMadre(Pregunta preguntaMadre)
    {
        this.preguntaMadre = preguntaMadre;
    }

    public void setNumeroOrden(int numeroOrden)
    {
        this.numeroOrden = numeroOrden;
    }

    public void setTexto(String texto)
    {
        this.texto = texto;
    }

    @Override
    public String toString()
    {
        return numeroOrden + ". " + texto;
    }

    @Override
    public boolean equals(Object obj)
    {
        return (((Opcion)obj).getId() == id);
    }
}