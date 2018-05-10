package abd.p1.controller;

import abd.p1.bd.newUserBd;
import abd.p1.model.Genero;
import abd.p1.model.Usuario;

import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.Set;

/**
 * Clase que es utilizada por la vista del nuevo usuario, para poder comunicarse
 * con la clase de la base de datos.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
public class newUserController
{
    /**
     * Necesario para poder comunicarse con la base de datos.
     */
    private newUserBd bd;

    /**
     * Usuario nuevo registrado.
     */
    private Usuario user;

    /**
     * Constructor de la clase.
     *
     * @param factory SessionFactory necesario.
     * @param user usuario que contiene el email y la pass del usuario que se
     * quiere registrar.
     */
    public newUserController(SessionFactory factory, Usuario user)
    {
        bd = new newUserBd(factory);
        this.user = user;
    }

    /**
     * Función que permite cambiar la contraseña del usuario.
     *
     * @param passWord nueva contraseña.
     */
    public void changePassWord(String passWord)
    {
        user.setPassword(passWord);
    }

    /**
     * @return nuevo usuario registrado con el que iniciar la app.
     */
    public Usuario getNewUser()
    {
        return user;
    }

    /**
     * Funcón que añade el nuevo usuario a la base de datos.
     *
     * @param name nombre del usuario.
     * @param bornDate fecha de nacimiento del usuario.
     * @param icon avatar del usuario.
     * @param hobbies lista que contiene las aficiones del usuario.
     * @param gender género del usuario.
     * @param preferences preferencias del usuario.
     * @param description descripción del usuario.
     */
    public void addNewUser(String name, Date bornDate, byte[] icon, Set<String> hobbies, Genero gender, Genero preferences, String description)
    {
        user.setNombre(name);
        user.setFecha_nacimiento(bornDate);
        user.setAvatar(icon);
        user.setAficiones(hobbies);
        user.setGenero(gender);
        user.setGenero_interes(preferences);
        user.setDescripcion(description);
        
        bd.newUser(user);
    }
}