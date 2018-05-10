package abd.p1.model;

import javax.persistence.Column;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase que representa una respuesta de un usuario en la base de datos.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
@Entity
@Table(name = "Responde")
public class Respuesta
{
    @Column(nullable = false)
    private int valoracion;

    @EmbeddedId
    private RespuestaID id;

    public Respuesta()
    {
    }

    public Respuesta(int valoracion, RespuestaID id)
    {
        this.valoracion = valoracion;
        this.id = id;
    }

    //--------------------------GETTERS--------------------------//
    public int getValoracion()
    {
        return valoracion;
    }

    public RespuestaID getId()
    {
        return id;
    }

    //--------------------------SETTERS--------------------------//
    public void setValoracion(int valoracion)
    {
        this.valoracion = valoracion;
    }

    public void setId(RespuestaID id)
    {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj)
    {
        return (((Respuesta) obj).id.getRespuesta().getPreguntaMadre().getId() == id.getRespuesta().getPreguntaMadre().getId());
    }
}