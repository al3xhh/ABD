package abd.p1.bd;

import abd.p1.model.Genero;
import abd.p1.model.Mensaje;
import abd.p1.model.Pregunta;
import abd.p1.model.Usuario;
import abd.p1.view.observers.mainWindowObserver;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Clase que se encarga de comunicarse con la base de datos con la función de
 * obtener todos los datos que la ventana principal de la aplicación necesita.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
public class mainWindowBd
{
    /**
     * Session factory necesario para obtener las sesiones necesarias.
     */
    private SessionFactory factory;

    /**
     * Observador necesario para mostrar todos los datos recuperados de la base
     * de datos se utiliza ya que se sigue el patrón modelo vista controlador.
     */
    private mainWindowObserver observer;

    /**
     * Constructor de la clase.
     *
     * @param factory sessionFactory necesario.
     */
    public mainWindowBd(SessionFactory factory)
    {
        this.factory = factory;
    }

    /**
     * Función que añade el observador.
     *
     * @param obs observador necesario.
     */
    public void setObserver(mainWindowObserver obs)
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
     * Función que recupera de la base de datos las 20 preguntas que contienen
     * la mayor media de sus valoraciones.
     */
    @SuppressWarnings("unchecked")
    public void getBestQuestions()
    {
        List<Pregunta> questions;

        try (Session session = factory.openSession())
        {
            Query query = session.createQuery("SELECT a.preguntaMadre FROM Respuesta AS r RIGHT JOIN r.id.respuesta AS a GROUP BY a.preguntaMadre ORDER BY AVG(r.valoracion) DESC");
            query.setMaxResults(20);
            questions = query.list();
        }
        
        for(Pregunta p: questions)
            p.sort(p.getOpciones());

        observer.showQuestions(questions);
    }

    /**
     * Función que recupera de la base de datos todos los mensajes que no han
     * sido leídos por el usuario conectado en ese momento.
     *
     * @param email del usuario conectado.
     */
    @SuppressWarnings("unchecked")
    public void getNonReadUserMessages(String email)
    {
        List<Mensaje> messages;

        try (Session session = factory.openSession())
        {
            Query query = session.createQuery("FROM Mensaje AS m WHERE m.receptor = :receiver AND m.leido = :read ORDER BY m.fecha, m.hora");
            query.setString("receiver", email);
            query.setBoolean("read", false);
            messages = query.list();
        }

        observer.showNonReadMessages(messages);
    }

    /**
     * Función que pone todos los mensajes del usuario como leídos.
     *
     * @param email del usuario conectado.
     */
    public void markAllAsRead(String email)
    {
        try (Session session = factory.openSession())
        {
            Transaction tr = session.beginTransaction();
            Query query = session.createQuery("UPDATE FROM Mensaje AS m SET m.leido = :read WHERE m.receptor = :receiver");
            query.setBoolean("read", true);
            query.setString("receiver", email);
            query.executeUpdate();
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

    /**
     * Función que devuelve el usuario que coincide con el email que se pasa por
     * parámetro.
     *
     * @param email del usuario buscado.
     * @return usuario encontrado con ese email.
     */
    public Usuario getUser(String email)
    {
        Usuario user;

        try (Session session = factory.openSession())
        {
            user = session.get(Usuario.class, email);
        }

        return user;
    }

    /**
     * Función que devuelce la pregunta que coincide con el identificado que se
     * pasa por parámetro.
     *
     * @param questionId de la pregunta buscada.
     * @return pregunta encontrada con ese identificador.
     */
    public Pregunta getQuestion(int questionId)
    {
        Pregunta question;

        try (Session session = factory.openSession())
        {
            question = session.get(Pregunta.class, questionId);
        }

        return question;
    }

    /**
     * Función que recupera una pregunta aleatoria de la base de datos.
     *
     * @return pregunta obtenida aleatoriamente.
     */
    public Pregunta randomQuestion()
    {
        Pregunta question;

        try (Session session = factory.openSession())
        {
            Query query = session.createQuery("FROM Pregunta AS p ORDER BY RAND()");
            query.setMaxResults(1);
            question = (Pregunta) query.uniqueResult();
        }

        return question;
    }
}