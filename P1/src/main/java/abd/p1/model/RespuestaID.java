package abd.p1.model;

import java.io.Serializable;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Clase que se utiliza como ID de las respuestas de los usuarios.
 * 
 * @author Alejandro Huertas Herrero 3ºB.
 */
@SuppressWarnings("serial")
public class RespuestaID implements Serializable
{
    @OneToOne
    private Opcion respuesta;

    @ManyToOne
    private Usuario usuario;

    public RespuestaID()
    {
    }

    public RespuestaID(Opcion respuesta, Usuario usuario)
    {
        super();
        this.respuesta = respuesta;
        this.usuario = usuario;
    }

    //--------------------------GETTERS--------------------------//
    public Opcion getRespuesta()
    {
        return respuesta;
    }
    
    public Usuario getUsuario()
    {
        return usuario;
    }
    
    //--------------------------SETTERS--------------------------//
    public void setRespuesta(Opcion respuesta)
    {
        this.respuesta = respuesta;
    }
    
    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((respuesta == null) ? 0 : respuesta.hashCode());
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RespuestaID other = (RespuestaID) obj;
        if (respuesta == null)
        {
            if (other.respuesta != null)
                return false;
        }
        else if (!respuesta.equals(other.respuesta))
            return false;
        if (usuario == null)
        {
            if (other.usuario != null)
                return false;
        }
        else if (!usuario.equals(other.usuario))
            return false;
        return true;
    }
}