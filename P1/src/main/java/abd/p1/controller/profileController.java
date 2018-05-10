package abd.p1.controller;

import abd.p1.bd.profileBd;
import abd.p1.model.MensajeSolicitud;
import abd.p1.model.MensajeTexto;
import abd.p1.model.Usuario;
import abd.p1.view.observers.profileObserver;

import java.util.Date;

import org.hibernate.SessionFactory;

/**
 * Clase que es utilizada por la vista del perfil del usuario, para poder
 * comunicarse con la clase de la base de datos.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
public class profileController
{
    /**
     * Necesario para poder comunicarse con la base de datos.
     */
    private profileBd bd;

    /**
     * Usuario conectado en la app y usuario cuyo perfil está siendo visitado.
     */
    private Usuario conectedUser, profileUser;

    /**
     * Constructor de la clase.
     *
     * @param conectedUser usuario conectado.
     * @param profileUser usuario cuyo perfil está siendo visitado.
     * @param factory SessionFactory necesario.
     */
    public profileController(Usuario conectedUser, Usuario profileUser, SessionFactory factory)
    {
        this.conectedUser = conectedUser;
        this.profileUser = profileUser;
        bd = new profileBd(factory);
    }

    /**
     * Añade el observador necesario en el modelo.
     *
     * @param obs observador a añadir.
     */
    public void setObserver(profileObserver obs)
    {
        bd.setObserver(obs);
    }

    /**
     * Función que utiliza el observador de la base de datos para mostrar la
     * información del usuario en la vista.
     */
    public void getUser()
    {
        bd.getUser(profileUser);
    }

    /**
     * Función que obtiene la distancia entre los dos usuarios y la pone en la
     * vista.
     */
    public void getDistance()
    {
        bd.getDistance(conectedUser, profileUser);
    }

    /**
     * Función que recupera las aficiones del usuario.
     */
    public void getHobbies()
    {
        bd.getHobbies(profileUser);
    }

    /**
     * Función que calcula la compatibilidad entre ambos usuarios y la muestra
     * en la vista.
     */
    public void getCompatibility()
    {
        bd.getCompatibility(conectedUser.getUsuario(), profileUser.getUsuario());
    }

    /**
     * Función que recupera las aficiones comunes a ambos usuarios.
     */
    public void getEqualsHobbies()
    {
        bd.getEqualHobbies(conectedUser, profileUser);
    }

    /**
     * Función que averigua si los usuarios son amigos o no.
     */
    public void areFriends()
    {
        bd.areFriends(conectedUser, profileUser);
    }

    /**
     * Función que recupera el chat completo entre ambos usuarios.
     */
    public void getAllChat()
    {
        bd.getAllChat(conectedUser.getUsuario(), profileUser.getUsuario());
    }

    /**
     * Función que envía un mensaje y lo guarda en la base de datos.
     *
     * @param text texto del mensaje que hay que enviar.
     */
    public void sendMessage(String text)
    {
        bd.sendMessage(new MensajeTexto(text, false, new Date(), new Date(), conectedUser, profileUser));
    }

    /**
     * Función que añade un amigo a la base de datos.
     */
    public void addFriend()
    {
        bd.addFriend(conectedUser, profileUser, new MensajeSolicitud(null, false, new Date(), new Date(), conectedUser, profileUser));
    }
}