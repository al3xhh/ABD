package abd.p1.bd;

import abd.p1.model.Genero;
import abd.p1.model.MensajePregunta;
import abd.p1.model.Usuario;
import abd.p1.view.observers.questionInvitationObserver;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Clase que se comunica con la base de datos para poder añadir una invitación a
 * contestar una pregunta.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
public class questionInvitationBd
{
    /**
     * Session factory necesario para obtener las sesiones necesarias.
     */
    private SessionFactory factory;

    /**
     * Observador necesario para mostrar todos los datos recuperados de la base
     * de datos se utiliza ya que se sigue el patrón modelo vista controlador.
     */
    private questionInvitationObserver observer;

    /**
     * Constructor de la clase.
     *
     * @param factory sessionFactory necesario.
     */
    public questionInvitationBd(SessionFactory factory)
    {
        this.factory = factory;
    }

    /**
     * Función que añade el observador.
     *
     * @param obs observador necesario.
     */
    public void setObserver(questionInvitationObserver obs)
    {
        observer = obs;
    }

    /**
     * Función que recupera todos los usuarios existentes en la base de datos
     * excepto el usuario que está conectado.
     *
     * @param email del usuario conectado.
     */
    @SuppressWarnings("unchecked")
    public void getAllUsers(String email)
    {
        List<Usuario> users;

        try (Session session = factory.openSession())
        {
            Query query = session.createQuery("FROM Usuario AS u WHERE u.usuario <> :email ORDER BY u.nombre");
            query.setString("email", email);
            users = query.list();
        }

        observer.showUsers(users);
    }

    /**
     * Función que recupera de la base de datos los usuarios que contiene en su
     * nombre la cadena de caracteres pasada y cuyo género coincide con las
     * preferencias del usuario conectado en ese momento.
     *
     * @param name cadena de caracteres que deben contener los nombres.
     * @param gender género buscado por el usuario.
     * @param email del usuario conectado.
     */
    @SuppressWarnings("unchecked")
    public void getFilteredUsers(String name, Genero gender, String email)
    {
        List<Usuario> users;

        try (Session session = factory.openSession())
        {
            Query query;

            if (!gender.equals(Genero.AMBOS))
            {
                query = session.createQuery("FROM Usuario AS u WHERE u.nombre LIKE :name AND u.genero = :gender AND u.usuario <> :email");
                query.setInteger("gender", gender.ordinal());
            }
            else
                query = session.createQuery("FROM Usuario AS u WHERE u.nombre LIKE :name AND u.usuario <> :email");

            query.setString("name", "%" + name + "%");
            query.setString("email", email);
            users = query.list();
        }

        observer.showUsers(users);
    }

    /**
     * Función que añade un mensaje de invitación a contestar una pregunta.
     *
     * @param message nensaje que se desea guardar en la base de datos.
     */
    public void addQuestionInvitation(MensajePregunta message)
    {
        try (Session session = factory.openSession())
        {
            Query query = session.createQuery("FROM MensajePregunta AS m WHERE m.emisor = :transmitter AND m.receptor = :receiver AND m.pregunta = :question");
            query.setString("transmitter", message.getEmisor().getUsuario());
            query.setString("receiver", message.getReceptor().getUsuario());
            query.setInteger("question", message.getPregunta().getId());

            MensajePregunta oldMessage = (MensajePregunta) query.uniqueResult();

            Transaction tr = session.beginTransaction();

            if (oldMessage == null)
                session.save(message);

            tr.commit();
        }
    }

    /**
     * Función que recupera de la base de datos los amigos del usuario
     * conectado, ordenados por cercanía.
     *
     * @param email del usuario conectado.
     */
    @SuppressWarnings("unchecked")
    public void getFriends(String email)
    {
        List<Usuario> friends;

        try (Session session = factory.openSession())
        {
            String formula = " ((u.latitud - u1.latitud) * (u.latitud - u1.latitud)) + ((u.longitud - u1.longitud) * (u.longitud - u1.longitud)) ASC";
            Query query = session.createQuery("SELECT u FROM Usuario AS u, Usuario as u1 WHERE u MEMBER OF u1.amigos AND u1.usuario = :email ORDER BY" + formula);
            query.setString("email", email);
            query.setMaxResults(20);
            friends = query.list();
        }

        observer.showUsers(friends);
    }

    /**
     * Función que recupera los amigos del usuario conectado y que contiene en
     * su nombre la cadena de carecteres pasada como parámetro.
     *
     * @param user usuario conectado.
     * @param filter cadena que debe aparecer en el nombre de los amigos.
     */
    @SuppressWarnings("unchecked")
    public void getFilterFriends(Usuario user, String filter)
    {
        List<Usuario> friends;

        try (Session session = factory.openSession())
        {
            String formula = " ((u.latitud - u1.latitud) * (u.latitud - u1.latitud)) + ((u.longitud - u1.longitud) * (u.longitud - u1.longitud)) ASC";
            Query query = session.createQuery("SELECT u FROM Usuario AS u, Usuario as u1 WHERE u.nombre LIKE :name AND u MEMBER OF u1.amigos AND u1.usuario = :user ORDER BY" + formula);
            query.setString("user", user.getUsuario());
            query.setString("name", "%" + filter + "%");
            query.setMaxResults(20);
            friends = query.list();
        }

        observer.showUsers(friends);
    }
}