package abd.p1.controller;

import org.hibernate.SessionFactory;

import abd.p1.bd.mainWindowBd;
import abd.p1.model.Pregunta;
import abd.p1.model.Usuario;
import abd.p1.view.AnswerSelector;
import abd.p1.view.Profile;
import abd.p1.view.ProfileEditor;
import abd.p1.view.observers.mainWindowObserver;

/**
 * Clase que es utilizada por la vista de la ventana principal, para poder
 * comunicarse con la clase de la base de datos.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
public class mainWindowController
{
    /**
     * Necesario para poder comunicarse con la base de datos.
     */
    private mainWindowBd bd;

    /**
     * SessionFactory necesario para la creación de otra vista en este
     * controlador.
     */
    private SessionFactory factory;

    /**
     * Usuario que ha iniciado sesión en la aplicación.
     */
    private Usuario user;

    /**
     * Constructor de la clase.
     *
     * @param factory SessionFactory necesario.
     * @param user usuario que está conectado en la app.
     */
    public mainWindowController(SessionFactory factory, Usuario user)
    {
        bd = new mainWindowBd(factory);
        this.user = user;
        this.factory = factory;
    }

    /**
     * Añade el observador necesario en el modelo.
     *
     * @param obs observador a añadir.
     */
    public void setObserver(mainWindowObserver obs)
    {
        bd.setObserver(obs);
    }

    /**
     * Función que recupera todos los usuarios de la base de datos.
     */
    public void getAllUsers()
    {
        bd.getAllUsers(user.getUsuario());
    }

    /**
     * Función que recupera los usuarios de la base de datos cuyo nombre
     * contiene la cadena de caracteres que se pasa por parámetro.
     *
     * @param filter cadena de caracteres a contener por el nombre.
     */
    public void getFilterUsers(String filter)
    {
        bd.getFilteredUsers(filter, user.getGenero_interes(), user.getUsuario());
    }

    /**
     * Función que crea la vista para actualizar el perfil del usuario.
     *
     * @param parent cuadro de diálogo padre desde el que se abre la vista.
     */
    public void updateProfile(javax.swing.JDialog parent)
    {
        ProfileEditor pe = new ProfileEditor(parent, true, new profileEditorController(factory, user));
        pe.setModal(true);
        pe.setVisible(true);
    }

    /**
     * Función que recupera el perfil del usuario seleccionado para poder
     * mostrar todos sus datos.
     *
     * @param user usuario que se desea consultar.
     * @param parent cuadro de diálogo padre desde el que se abre la vista.
     */
    public void seeProfile(Usuario user, javax.swing.JDialog parent)
    {
        Profile profile = new Profile(parent, true, new profileController(this.user, user, factory));
        profile.setModal(true);
        profile.setVisible(true);
    }

    /**
     * Función que crea una vista para responder una determinada pregunta.
     *
     * @param pregunta que se desea responder.
     * @param parent cuadro de diálogo padre desde el que se abre la vista.
     */
    public void answerQuestion(Pregunta pregunta, javax.swing.JDialog parent)
    {
        AnswerSelector as = new AnswerSelector(parent, true, new answerSelectorController(factory, user, pregunta));
        as.setModal(true);
        as.setVisible(true);
    }

    /**
     * Función que recupera una pregunta aleatoria de la base de datos.
     */
    public void randomQuestion(javax.swing.JDialog parent)
    {
        AnswerSelector as = new AnswerSelector(parent, true, new answerSelectorController(factory, user, bd.randomQuestion()));
        as.setModal(true);
        as.setVisible(true);
    }

    /**
     * Función que recupera el usuario a partir de su email y muestra la vista
     * con el perfil del mismo
     *
     * @param email del usuario que se desea recuperar.
     * @param parent cuadro de diálogo padre desde el que se abre la vista
     */
    public void getUser(String email, javax.swing.JDialog parent)
    {
        seeProfile(bd.getUser(email), parent);
    }

    /**
     * Función que recupera una pregunta a partir de su identificador y muestra
     * la vista de selección de respuesta.
     *
     * @param questionId identificador de la pregunta que se desea recuperar.
     * @param parent cuadro de diálogo padre desde el que se abre la vista.
     */
    public void getQuestion(String questionId, javax.swing.JDialog parent)
    {
        answerQuestion(bd.getQuestion(Integer.parseInt(questionId)), parent);
    }

    /**
     * Función que recupera los mensajes no leídos del usuario conectado.
     */
    public void getNonReadMessages()
    {
        bd.getNonReadUserMessages(user.getUsuario());
    }

    /**
     * Función que marca todos los mensajes como leídos.
     */
    public void markAllAsRead()
    {
        bd.markAllAsRead(user.getUsuario());
    }

    /**
     * Funcón que recupera los amigos del usuario conectado.
     */
    public void getFriends()
    {
        bd.getFriends(user.getUsuario());
    }

    /**
     * Función que recupera los amigos del usuario que contiene en su nombre la
     * cadena de caracteres pasada por parámetro.
     *
     * @param filter cadena de caracteres a contener por el nombre.
     */
    public void getFilterFriends(String filter)
    {
        bd.getFilterFriends(user, filter);
    }

    /**
     * Recupera las preguntas con mejor valoración media.
     */
    public void getBestQuestion()
    {
        bd.getBestQuestions();
    }
}