package abd.p1.controller;

import abd.p1.bd.questionInvitationBd;
import abd.p1.model.MensajePregunta;
import abd.p1.model.Pregunta;
import abd.p1.model.Usuario;
import abd.p1.view.observers.questionInvitationObserver;

import java.util.Date;

import org.hibernate.SessionFactory;

/**
 * Clase que es utilizada por la vista de invitación a responder pregunta, para
 * poder comunicarse con la clase de la base de datos.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
public class questionInvitationController
{
    /**
     * Usuario que ha iniciado sesión en la aplicación.
     */
    private Usuario user;

    /**
     * Necesario para poder comunicarse con la base de datos.
     */
    private questionInvitationBd bd;

    /**
     * Pregunta que contiene la invitación.
     */
    private Pregunta question;

    /**
     * Constructor de la clase.
     *
     * @param factory SessionFactory necesario.
     * @param user usuario que está conectado en la app.
     * @param question pregunta que contendrá la invitación.
     */
    public questionInvitationController(SessionFactory factory, Usuario user, Pregunta question)
    {
        this.user = user;
        this.question = question;
        this.bd = new questionInvitationBd(factory);
    }

    /**
     * Añade el observador necesario en el modelo.
     *
     * @param obs observador a añadir.
     */
    public void setObserver(questionInvitationObserver obs)
    {
        this.bd.setObserver(obs);
    }

    /**
     * Función que recupera todos los usuarios de la base de datos.
     */
    public void getAllUsers()
    {
        this.bd.getAllUsers(user.getUsuario());
    }

    /**
     * Función que recupera los usuarios de la base de datos cuyo nombre
     * contiene la cadena de caracteres que se pasa por parámetro.
     *
     * @param filter cadena de caracteres a contener por el nombre.
     */
    public void getFiltredUser(String filter)
    {
        this.bd.getFilteredUsers(user.getUsuario(), user.getGenero_interes(), filter);
    }

    /**
     * Función que añade una invitación a responder una pregunta.
     *
     * @param user usuario al que se desea invitar.
     */
    public void addQuestionInvitation(Usuario user)
    {
        MensajePregunta message = new MensajePregunta(question, false, new Date(), new Date(), this.user, user);
        this.bd.addQuestionInvitation(message);
    }

    /**
     * Funcón que recupera los amigos del usuario conectado.
     */
    public void getFriends()
    {
        this.bd.getFriends(user.getUsuario());
    }

    /**
     * Función que recupera los amigos del usuario que contiene en su nombre la
     * cadena de caracteres pasada por parámetro.
     *
     * @param filter cadena de caracteres a contener por el nombre.
     */
    public void getFilterFriends(String filter)
    {
        this.bd.getFilterFriends(user, filter);
    }
}