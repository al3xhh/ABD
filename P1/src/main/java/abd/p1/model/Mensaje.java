package abd.p1.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que representa un mensaje en la base de datos.
 * 
 * @author Alejandro Huertas Herrero 3ºB.
 */
@Entity
@Table(name = "mensajes")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Mensaje
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private boolean leido;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    protected Date fecha;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    protected Date hora;

    @ManyToOne
    protected Usuario emisor, receptor;

    public abstract String getFormatText();

    public Mensaje()
    {
    }

    public Mensaje(boolean leido, Date fecha, Date hora, Usuario emisor, Usuario receptor)
    {
        this.id = null;
        this.leido = leido;
        this.fecha = fecha;
        this.hora = hora;
        this.emisor = emisor;
        this.receptor = receptor;
    }
    
    //--------------------------GETTERS--------------------------//
    public int getId()
    {
        return id;
    }

    public boolean isLeido()
    {
        return leido;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public Date getHora()
    {
        return hora;
    }
    
    public Usuario getEmisor()
    {
        return emisor;
    }
    
    public Usuario getReceptor()
    {
        return receptor;
    }
    
    //--------------------------SETTERS--------------------------//
    public void setId(int id)
    {
        this.id = id;
    }

    public void setLeido(boolean leido)
    {
        this.leido = leido;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    public void setHora(Date hora)
    {
        this.hora = hora;
    }
    
    public void setEmisot(Usuario emisor)
    {
        this.emisor = emisor;
    }
    
    public void setReceptor(Usuario receptor)
    {
        this.receptor = receptor;
    }
}