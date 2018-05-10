package abd.p1.controller;

import abd.p1.bd.answerSelectorBd;
import abd.p1.model.Opcion;
import abd.p1.model.Pregunta;
import abd.p1.model.Respuesta;
import abd.p1.model.RespuestaID;
import abd.p1.model.Usuario;
import abd.p1.view.QuestionInvitation;
import abd.p1.view.observers.answerSelectorObserver;

import org.hibernate.SessionFactory;

/**
 * Clase que es utilizada por la vista de elección de respuesta a la pregunta,
 * para poder comunicarse con la clase de la base de datos.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
public class answerSelectorController
{
    /**
     * Necesario para poder comunicarse con la base de datos.
     */
    private answerSelectorBd bd;

    /**
     * SessionFactory necesario para la creación de otra vista en este
     * controlador.
     */
    private SessionFactory factory;

    /**
     * Usuario que va a responder la pregunta.
     */
    private Usuario user;

    /**
     * Pregunta que va a ser respondida.
     */
    private Pregunta question;

    /**
     * Constructor de la clase.
     *
     * @param factory SessionFactory necesario.
     * @param user usuario que está conectado en la app.
     * @param question pregunta a ser respondida.
     */
    public answerSelectorController(SessionFactory factory, Usuario user, Pregunta question)
    {
        this.bd = new answerSelectorBd(factory);
        this.user = user;
        this.factory = factory;
        this.question = question;
    }

    /**
     * Añade el observador necesario en el modelo.
     *
     * @param obs observador a añadir.
     */
    public void setObserver(answerSelectorObserver obs)
    {
        bd.setObserver(obs);
    }

    /**
     * Función utilizada para mostrar las opciones de la pregunta a partir del
     * observador que hay en la clase de la base.
     */
    public void getOptions()
    {
        bd.getOptions(question);
    }

    /**
     * Función que añade una nueva respuesta del usuario.
     *
     * @param points relevancia dada a la pregunta.
     * @param answer opción elegida por el usuario.
     */
    public void userAnswer(int points, Opcion answer)
    {
        bd.addAnswer(new Respuesta(points, new RespuestaID(answer, user)));
    }

    /**
     * Función que crea una nueva vista para invitar a un usuario a responder la
     * pregunta en cuestión.
     *
     * @param parent cuadro de diálogo desde el que se abre la nueva vista.
     */
    public void invitation(javax.swing.JDialog parent)
    {
        QuestionInvitation qe = new QuestionInvitation(parent, true, new questionInvitationController(this.factory, user, question));
        qe.setModal(true);
        qe.setVisible(true);
    }
}