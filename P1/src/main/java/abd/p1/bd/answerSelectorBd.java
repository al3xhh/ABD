package abd.p1.bd;

import abd.p1.model.Pregunta;
import abd.p1.model.Respuesta;
import abd.p1.view.observers.answerSelectorObserver;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Clase encargada de acceder a la base de datos para guardar las respuestas que
 * se realicen a cada pregunta.
 *
 * @author al3x_hh
 */
public class answerSelectorBd
{
    /**
     * Session factory necesario para obtener las sesiones necesarias.
     */
    private SessionFactory factory;

    /**
     * Observador necesario para mostrar las opciones que tiene la pregunta se
     * utiliza ya que se sigue el patrón modelo vista controlador.
     */
    private answerSelectorObserver observer;

    /**
     * Constructor de la clase.
     *
     * @param factory sessionFactory necesario.
     */
    public answerSelectorBd(SessionFactory factory)
    {
        this.factory = factory;
    }

    /**
     * Función que añade el observador.
     *
     * @param obs observador necesario.
     */
    public void setObserver(answerSelectorObserver obs)
    {
        observer = obs;
    }

    /**
     * Función que añade una respuesta a la base de datos.
     *
     * @param answer respuesta a insertar en la base.
     */
    public void addAnswer(Respuesta answer)
    {
        try (Session session = factory.openSession())
        {
            Query query = session.createQuery("FROM Respuesta AS r WHERE r.id.respuesta.preguntaMadre = :id AND r.id.usuario = :user");
            query.setInteger("id", answer.getId().getRespuesta().getPreguntaMadre().getId());
            query.setString("user", answer.getId().getUsuario().getUsuario());
            Respuesta oldAnswer = (Respuesta) query.uniqueResult();

            Transaction tr = session.beginTransaction();

            if (oldAnswer != null)
                session.delete(oldAnswer);

            session.save(answer);
            tr.commit();
        }
    }

    /**
     * Función que muestra las opciones de la pregunta en la vista.
     *
     * @param question pregunta de la que mostrar las opciones.
     */
    public void getOptions(Pregunta question)
    {
        observer.showOptions(question);
    }
}