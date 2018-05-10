package abd.p1.model;

import java.util.Date;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase que representa un mensaje de texto en la base de datos.
 * 
 * @author Alejandro Huertas Herrero 3ºB.
 */
@Entity
@Table(name = "MensajeTexto")
public class MensajeTexto extends Mensaje
{
    @Column(nullable = false)
    private String texto;

    public MensajeTexto()
    {
    }

    public MensajeTexto(String texto, boolean leido, Date fecha, Date hora, Usuario emisor, Usuario receptor)
    {
        super(leido, fecha, hora, emisor, receptor);
        this.texto = texto;
    }

    public String getTexto()
    {
        return texto;
    }

    public void setTexto(String texto)
    {
        this.texto = texto;
    }

    @Override
    public String getFormatText()
    {
        return "<span style = color:red> [" + fecha.toString() + " - " + hora.toString() + "] " + emisor.getNombre() + " escribió:" + "</span" + "\n" + "<br/>" + texto + "<br/>"  +"\n";
    }
}