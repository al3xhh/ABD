package abd.p1.bd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import abd.p1.model.Usuario;

/**
 * Clase que se encarga de comunicarse con la base de datos para realizar el
 * login correspondiente y devolver el usuario que ha iniciado la sesión en la
 * aplicación.
 *
 * @author Alejandro Huertas Herrero 3ºB
 */
public class loginBd
{
    /**
     * Session factory necesario para obtener las sesiones necesarias.
     */
    private SessionFactory factory;

    /**
     * Constructor de la clase.
     *
     * @param factory sessionFactory necesario.
     */
    public loginBd(SessionFactory factory)
    {
        this.factory = factory;
    }

    /**
     * Función que comprueba que el usuario existe y que su contraseña coincide
     * con la introducida por el usuario.
     *
     * @param email del usuario que quiere iniciar sesión.
     * @param pass contraseña del usuario que quiere inciar sesión.
     * @return usuario que ha iniciado la sesión si todo ha ido bien.
     * @throws Exception excepcion en caso de que el usuario no exista o no
     * coincida su contraseña.
     */
    public Usuario checkUser(String email, String pass) throws Exception
    {
        Usuario user;

        try (Session session = factory.openSession())
        {
            user = session.get(Usuario.class, email);
        }

        if (user != null)
            if (user.getPassword().equals(pass))
                return user;
            else
                throw new Exception("Usuario y contraseña no coinciden.");
        else
            throw new Exception("Usuario no encontrado.");
    }

    /**
     * Función que comprueba si el usuario ya está registrado en la base de
     * datos.
     *
     * @param email del usuario que se necesita comprobar.
     * @return true en caso de existir, false en caso contrario.
     */
    public boolean isAlredyInDataBase(String email)
    {
        Usuario user;

        try (Session session = factory.openSession())
        {
            user = session.get(Usuario.class, email);
        }

        return (user != null);
    }
}