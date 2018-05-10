package abd.p1.model;

import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que representa un usuario en la base de datos.
 * 
 * @author Alejandro Huertas Herrero 3ºB.
 */
@Entity
@Table(name = "Usuarios")
public class Usuario
{
    @Id
    private String usuario;

    @Column(nullable = false)
    private String password, nombre;

    @Column(nullable = false)
    private Genero genero, genero_interes;

    private String descripcion;

    @Lob
    private byte[] avatar;

    @Column(nullable = false)
    private Double latitud, longitud;

    @Temporal(TemporalType.DATE)
    private Date fecha_nacimiento;

    @ManyToMany
    @JoinTable(name = "Amistad")
    private Set<Usuario> amigos;

    @OneToMany(mappedBy = "id.usuario")
    private List<Respuesta> respuestas;

    @OneToMany(mappedBy = "emisor")
    private List<Mensaje> mensajes_enviados;

    @ElementCollection
    @CollectionTable(name = "Aficiones")
    private Set<String> aficiones;

    public Usuario()
    {
        amigos = new HashSet<>();
        respuestas = new ArrayList<>();
        mensajes_enviados = new ArrayList<>();
        aficiones = new HashSet<>();
        latitud = 0.0;
        longitud = 0.0;
    }

    public Usuario(String usuario, String password, String nombre, Genero genero, Genero genero_interes, byte[] avatar, Date fecha_nacimiento, String descripcion)
    {
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.genero = genero;
        this.genero_interes = genero_interes;
        this.avatar = avatar;
        this.latitud = 0.0;
        this.longitud = 0.0;
        this.fecha_nacimiento = fecha_nacimiento;
        this.amigos = new HashSet<>();
        this.respuestas = new ArrayList<>();
        this.mensajes_enviados = new ArrayList<>();
        this.aficiones = new HashSet<>();
        this.descripcion = descripcion;
    }

    public Usuario(String usuario, String password)
    {
        this.usuario = usuario;
        this.password = password;
        latitud = 0.0;
        longitud = 0.0;
    }

    //--------------------------GETTERS--------------------------//
    public String getUsuario()
    {
        return usuario;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public String getPassword()
    {
        return password;
    }

    public String getNombre()
    {
        return nombre;
    }

    public Genero getGenero()
    {
        return genero;
    }

    public Genero getGenero_interes()
    {
        return genero_interes;
    }

    public byte[] getAvatar()
    {
        return avatar;
    }

    public Double getLatitud()
    {
        return latitud;
    }

    public Double getLongitud()
    {
        return longitud;
    }

    public Date getFecha_nacimiento()
    {
        return fecha_nacimiento;
    }

    public List<Respuesta> getRespuestas()
    {
        return respuestas;
    }

    public List<Mensaje> getMensajes_enviados()
    {
        return mensajes_enviados;
    }

    public Set<String> getAficiones()
    {
        return aficiones;
    }

    //--------------------------SETTERS--------------------------//
    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setGenero(Genero genero)
    {
        this.genero = genero;
    }

    public void setGenero_interes(Genero genero_interes)
    {
        this.genero_interes = genero_interes;
    }

    public void setAvatar(byte[] avatar)
    {
        this.avatar = avatar;
    }

    public void setLatitud(Double latitud)
    {
        this.latitud = latitud;
    }

    public void setLongitud(Double longitud)
    {
        this.longitud = longitud;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento)
    {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setAmigo(Usuario amigo)
    {
        this.amigos.add(amigo);
    }

    public Set<Usuario> getAmigos()
    {
        return amigos;
    }

    public void setRespuesta(Respuesta respuesta)
    {
        this.respuestas.add(respuesta);
    }

    public void setMensajes_enviados(Mensaje mensaje)
    {
        this.mensajes_enviados.add(mensaje);
    }

    public void setAficion(String aficion)
    {
        this.aficiones.add(aficion);
    }

    public void setAficiones(Set<String> hobbies)
    {
        this.aficiones = hobbies;
    }

    /**
     * Función que calcula la edad del usuario a partir de su fecha de nacimiento.
     * @param date fecha de nacimiento.
     * @return edad de ese usuario.
     */
    @SuppressWarnings("deprecation")
    public long calculateAge(Date date)
    {
        int year = date.getYear() + 1900, currentYear = Calendar.getInstance().get(Calendar.YEAR),
            day = Integer.parseInt(date.toString().substring(8, 10)), currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
            month = date.getMonth() + 1, currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;

        LocalDate startDate = LocalDate.of(year, month, day), endDate = LocalDate.of(currentYear, currentMonth, currentDay);

        return ChronoUnit.YEARS.between(startDate, endDate);
    }

    /**
     * Función que calcula la distancia entre dos usuarios.
     * @param user usuario con el que calcular la distancia.
     * @return distancia entre los dos usuarios.
     */
    public double calculateDistance(Usuario user)
    {
        int R = 6371000;

        double incrementOne = latitud - user.getLatitud(), incrementTwo = longitud - user.getLongitud(),
               a = pow(sin(incrementOne / 2), 2) + (cos(latitud) * cos(user.getLatitud()) * pow(sin(incrementTwo / 2), 2)),
               c = 2 * atan(sqrt(a) / sqrt(1 - a));

        return R * c;
    }

    @Override
    public boolean equals(Object user)
    {
        return usuario.equals(((Usuario) user).getUsuario());
    }
}