package abd.p1.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Clase que representa una invitación a responder una pregunta en la base de datos.
 * 
 * @author Alejandro Huertas Herrero 3ºB.
 */
@Entity
@Table(name = "mensajePregunta")
public class MensajePregunta extends Mensaje
{
    @OneToOne
    @JoinColumn(name = "pregunta")
    private Pregunta pregunta;

    public MensajePregunta()
    {
    }

    public MensajePregunta(Pregunta pregunta, boolean leido, Date fecha, Date hora, Usuario emisor, Usuario receptor)
    {
        super(leido, fecha, hora, emisor, receptor);
        this.pregunta = pregunta;
    }

    public Pregunta getPregunta()
    {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta)
    {
        this.pregunta = pregunta;
    }

    @Override
    public String getFormatText()
    {
        return "<span style = color:red> [" + fecha.toString() + " - " + hora.toString() + "] " + emisor.getNombre() + " te ha invitado a responder la pregunta:" 
                + "\n" + "</span></br><a href = preg:" + pregunta.getId() + ">" + pregunta.getEnunciado() + "</a><br/>" +"\n";
    }
    
    @Override
    public boolean equals(Object obj)
    {
        return (((MensajePregunta)obj)).getEmisor().equals(emisor) && ((MensajePregunta)obj).getReceptor().equals(receptor) && ((MensajePregunta)obj).getPregunta().equals(pregunta);
    }
}