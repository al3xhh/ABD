package abd.p1.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase que representa una solicitud de amistad en la base de datos.
 * 
 * @author Alejandro Huertas Herrero 3ºB.
 */
@Entity
@Table(name = "mensajeSolicitud")
public class MensajeSolicitud extends Mensaje
{
    public MensajeSolicitud()
    {
    }

    public MensajeSolicitud(String texto_solicitud, boolean leido, Date fecha, Date hora, Usuario emisor, Usuario receptor)
    {
        super(leido, fecha, hora, emisor, receptor);
    }

    @Override
    public String getFormatText()
    {
        return "<span style = color:red> [" + fecha.toString() + " - " + hora.toString() + "] Solicitud de amistad de: \n " + "</span>" + 
                "<br/><a href = amigo:" + emisor.getUsuario() + ">" + emisor.getNombre() + "</a><br/" + "\n";
    }
}