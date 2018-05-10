package abd.p1.bd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import abd.p1.model.Usuario;

/**
 * Clase que se encarga de comunicarse con la base de datos
 * para insertar el nuevo usuario registrado en la aplicación.
 * 
 * @author Alejandro Huertas Herrero 3ºB.
 */
public class newUserBd
{
    /**
     * Session factory necesario para obtener las sesiones necesarias.
     */
    private SessionFactory factory;

    /**
     * Constructor de la clase.
     * @param factory sessionFactory necesario.
     */
    public newUserBd(SessionFactory factory)
    {
        this.factory = factory;
    }

    /**
     * Función que inserta el nuevo usuario en la base de datos.
     * @param user usuario que se desea guardar en la base de datos.
     */
    public void newUser(Usuario user)
    {
        try (Session session = factory.openSession())
        {
            Transaction tr = session.beginTransaction();
            session.save(user);
            tr.commit();
        }
    }
}