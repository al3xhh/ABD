package abd.p1.controller;

import org.hibernate.SessionFactory;

import abd.p1.bd.loginBd;
import abd.p1.model.Usuario;
import abd.p1.view.NewUser;

/**
 * Clase que es utilizada por la vista de login para poder comunicarse con la
 * clase de la base de datos.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
public class loginController
{
    /**
     * Necesario para poder comunicarse con la base de datos.
     */
    private loginBd bd;

    /**
     * SessionFactory necesario para la creación de otra vista en este
     * controlador.
     */
    private SessionFactory factory;

    /**
     * Usuario que ha iniciado sesión.
     */
    private Usuario user;

    /**
     * Constructor de la clase.
     *
     * @param factory SessionFactory necesario.
     */
    public loginController(SessionFactory factory)
    {
        this.bd = new loginBd(factory);
        this.factory = factory;
    }

    /**
     * Funcón que comprueba la existencia el usuario introducido y que su
     * contraseña coincida con la introducida.
     *
     * @param email del usuario que quiere iniciar sesión.
     * @param pass del usuario que quiere iniciar sesión.
     * @throws Exception se lanza en caso de no existir el usuario o de no
     * coincidir las contraseñas.
     */
    public void chekUser(String email, String pass) throws Exception
    {
        user = bd.checkUser(email, pass);
    }

    /**
     * @return usuario que ha iniciado sesión.
     */
    public Usuario getConectedUser()
    {
        return user;
    }

    /**
     * Función que añade un nuevo usuario a la base de datos.
     *
     * @param email del usuario que se quiere registrar.
     * @param pass del usuario que se quiere registrar.
     * @param parent cuadro de diálogo desde el que se abre esta ventana.
     */
    public void newUser(String email, String pass, javax.swing.JDialog parent)
    {
        Usuario auxUser = new Usuario(email, pass), oldUser = new Usuario(email, pass);

        NewUser pe = new NewUser(parent, true, new newUserController(factory, auxUser));
        pe.setVisible(true);
        pe.setModal(true);

        //Al ser el nombre obligatorio, si el nombre coincide el usuario ha cancelado la
        //operación de registrarse.
        if (oldUser.getNombre() == auxUser.getNombre())
            user = null;
        else
            user = auxUser;
    }

    /**
     * Función que comprueba si el usuario está ya registrado.
     *
     * @param email del usuario que hay que comprobar si existe.
     * @return true en caso de que exista, false en caso contrario.
     */
    public boolean isAlredyInDataBase(String email)
    {
        return bd.isAlredyInDataBase(email);
    }
}