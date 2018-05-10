package abd.p1.bd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import abd.p1.model.Usuario;
import abd.p1.view.observers.profileEditorObserver;

/**
 * Clase que se encarga de comunicarse con la base de datos para actualizar la
 * información relacionada con el usuario que está conectado en ese momento.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
public class profileEditorBd
{
    /**
     * Session factory necesario para obtener las sesiones necesarias.
     */
    private SessionFactory factory;

    /**
     * Observador necesario para mostrar todos los datos recuperados de la base
     * de datos se utiliza ya que se sigue el patrón modelo vista controlador.
     */
    private profileEditorObserver observer;

    /**
     * Constructor de la clase.
     *
     * @param factory sessionFactory necesario.
     */
    public profileEditorBd(SessionFactory factory)
    {
        this.factory = factory;
    }

    /**
     * Función que añade el observador.
     *
     * @param obs observador necesario.
     */
    public void setObserver(profileEditorObserver obs)
    {
        observer = obs;
    }

    /**
     * Función que actualiza la información relacionada con el usuario.
     *
     * @param user usuario que hay que actualizar.
     */
    public void updateProfile(Usuario user)
    {
        try (Session session = factory.openSession())
        {
            Transaction tr = session.beginTransaction();
            session.merge(user);
            tr.commit();
        }
    }

    /**
     * Función que llama al observador para mostrar la información del usuario
     * en la vista correspondiente.
     *
     * @param user usuario que se desea mostrar por la vista.
     */
    public void showUserInformation(Usuario user)
    {
        observer.showUserInformation(user);
    }

    /**
     * Función que recupera las aficiones del usuario y utiliza el observador
     * para mostrarlas en la vista.
     *
     * @param user usuario del que se necesitan sus aficiones.
     */
    public void getHobbies(Usuario user)
    {
        try (Session session = factory.openSession())
        {
            session.refresh(user);
            user.getAficiones().size();
        }

        observer.showUserHobbies(user.getAficiones());
    }
}