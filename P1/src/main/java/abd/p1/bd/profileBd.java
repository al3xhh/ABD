package abd.p1.bd;

import abd.p1.model.Mensaje;
import abd.p1.model.Respuesta;
import abd.p1.model.Usuario;
import abd.p1.view.observers.profileObserver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Clase que se encarga de comunicarse con la base de datos para obtener toda la
 * información necesaria para cargar el perfil de un usuario en concreto.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
public class profileBd
{
    /**
     * Session factory necesario para obtener las sesiones necesarias.
     */
    private SessionFactory factory;

    /**
     * Observador necesario para mostrar todos los datos recuperados de la base
     * de datos se utiliza ya que se sigue el patrón modelo vista controlador.
     */
    private profileObserver observer;

    /**
     * Constructor de la clase.
     *
     * @param factory sessionFactory necesario.
     */
    public profileBd(SessionFactory factory)
    {
        this.factory = factory;
    }

    /**
     * Función que añade el observador.
     *
     * @param obs observador necesario.
     */
    public void setObserver(profileObserver obs)
    {
        observer = obs;
    }

    /**
     * Función encargada de llamar al observador para mostrar los datos del
     * usuario.
     *
     * @param user usuario que hay que mostrar en la vista.
     */
    public void getUser(Usuario user)
    {
        observer.seeUser(user);
    }

    /**
     * Función encargada de llamar al observador para mostrar la distancia entre
     * los dos usuarios pasados por parámetro.
     *
     * @param conectedUser usuario conectado.
     * @param profileUser usuario del perfil visitado.
     */
    public void getDistance(Usuario conectedUser, Usuario profileUser)
    {
        observer.showDistance(conectedUser.calculateDistance(profileUser));
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

    /**
     * Función que calcula la compatibilidad entre los dos usuarios pasados por
     * parámetro, utilizando las preguntas respondidas por ambos y las
     * respuestas que cada uno ha dado a cada pregunta.
     *
     * @param email1 de uno de los usuarios.
     * @param email2 del otro de los usuarios.
     */
    @SuppressWarnings("unchecked")
    public void getCompatibility(String email1, String email2)
    {
        List<Object[]> answers;

        try (Session session = factory.openSession())
        {
            Query query = session.createQuery("FROM Respuesta r, Respuesta r1 WHERE r.id.usuario = :email1 AND "
                    + "r1.id.usuario = :email2 AND r.id.respuesta.preguntaMadre = r1.id.respuesta.preguntaMadre");
            query.setString("email1", email1);
            query.setString("email2", email2);
            answers = query.list();
        }

        double MTotal = answers.size(), MAcierto = 0;

        for (int i = 0; i < answers.size(); i++)
        {
            Respuesta r = (Respuesta) answers.get(i)[0], r1 = (Respuesta) answers.get(i)[1];

            if (r.getId().getRespuesta().getId() == r1.getId().getRespuesta().getId())
                MAcierto++;
        }

        if (MTotal != 0)
            observer.showCompatibility(100 * (MAcierto / MTotal));
        else
            observer.showCompatibility(0);
    }

    /**
     * Función que recupera de la base de datos los hobbies de los dos usuarios
     * y calcula la intersección de ambos para ver los hobbies comunes.
     *
     * @param user1 usuario conectado.
     * @param user2 usuario cuyo perfil está siendo visitado.
     */
    public void getEqualHobbies(Usuario user1, Usuario user2)
    {
        Set<String> user1list, user2list, result = new HashSet<>();

        try (Session session = factory.openSession())
        {
            session.refresh(user1);
            user1.getAficiones().size();
            user1list = user1.getAficiones();

            session.refresh(user2);
            user2.getAficiones().size();
            user2list = user2.getAficiones();
        }

        String hobbies1[] = new String[user1list.size()], hobbies2[] = new String[user2list.size()];

        user1list.toArray(hobbies1);
        user2list.toArray(hobbies2);

        for (int i = 0; i < Math.max(user1list.size(), user2list.size()); i++)
        {
            if (user1list.size() >= user2list.size())
            {
                if (user2list.contains(hobbies1[i]))
                    result.add(hobbies1[i]);
            }
            else if (user1list.contains(hobbies2[i]))
                result.add(hobbies2[i]);

        }

        observer.showEqualsHobbies(result);
    }

    /**
     * Función que añade un amigo a cada uno de los dos usuarios mutuamente y
     * que añade un mensaje de petición de amistad a la base de datos.
     *
     * @param user1 usuario conectado.
     * @param user2 usuario con el que se desea tener amistad.
     * @param message mensaje de petición de amistad.
     */
    public void addFriend(Usuario user1, Usuario user2, Mensaje message)
    {
        try (Session session = factory.openSession())
        {
            Transaction tr = session.beginTransaction();
            session.refresh(user1);
            user1.getAmigos().size();
            session.refresh(user2);
            user2.getAmigos().size();
            user1.setAmigo(user2);
            user2.setAmigo(user1);
            session.update(user1);
            session.update(user2);
            session.save(message);
            tr.commit();
        }
    }

    /**
     * Función que devuelve true si los usuarios son amigos, false en caso
     * contrario.
     *
     * @param user1 usuario conectado.
     * @param user2 usuario cuyo perfil está siendo visitado.
     */
    @SuppressWarnings("unchecked")
    public void areFriends(Usuario user1, Usuario user2)
    {
        List<Usuario> friends;

        try (Session session = factory.openSession())
        {
            Query query = session.createQuery("SELECT u.amigos FROM Usuario AS u WHERE u.usuario = :user");
            query.setString("user", user1.getUsuario());
            friends = query.list();
        }

        if (friends.contains(user2))
            observer.activeButton(false);
        else
            observer.activeButton(true);
    }

    /**
     * Función que envía un mensaje.
     *
     * @param message mensaje que se desea enviar.
     */
    public void sendMessage(Mensaje message)
    {
        try (Session session = factory.openSession())
        {
            Transaction tr = session.beginTransaction();
            session.save(message);
            tr.commit();
            session.refresh(message);
        }

        observer.showMessage(message);
    }

    /**
     * Función que recupera de la base de datos el chat completo entre dos
     * usuarios.
     *
     * @param email1 del usuario conectado.
     * @param email2 del usuario cuyo perfil está siendo visitado.
     */
    @SuppressWarnings("unchecked")
    public void getAllChat(String email1, String email2)
    {
        List<Mensaje> messages;

        try (Session session = factory.openSession())
        {
            Query query = session.createQuery("SELECT m FROM MensajeTexto AS m WHERE (m.emisor = :transmitter1 AND m.receptor = :receiver1) OR "
                    + "(m.emisor = :transmitter2 AND m.receptor = :receiver2) ORDER BY m.fecha, m.hora");
            query.setString("transmitter1", email1);
            query.setString("receiver1", email2);
            query.setString("transmitter2", email2);
            query.setString("receiver2", email1);
            messages = query.list();
        }

        observer.showMessages(messages);
    }
}