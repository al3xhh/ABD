package abd.p1.controller;

import org.hibernate.SessionFactory;

import abd.p1.bd.profileEditorBd;
import abd.p1.model.Genero;
import abd.p1.model.Usuario;
import abd.p1.view.observers.profileEditorObserver;

import java.util.Date;
import java.util.Set;

/**
 * Clase que es utilizada por la vista del editor de perfil, para poder
 * comunicarse con la clase de la base de datos.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
public class profileEditorController
{
    /**
     * Necesario para poder comunicarse con la base de datos.
     */
    private profileEditorBd bd;
    
    /**
     * Usuario conectado en la app y.
     */
    private Usuario user;

    /**
     * Constructor de la clase.
     *
     * @param factory SessionFactory necesario.
     * @param user usuario conectado en la app.
     */
    public profileEditorController(SessionFactory factory, Usuario user)
    {
        this.bd = new profileEditorBd(factory);
        this.user = user;
    }

    /**
     * Añade el observador necesario en el modelo.
     *
     * @param obs observador a añadir.
     */
    public void setObserver(profileEditorObserver obs)
    {
        this.bd.setObserver(obs);
    }

    /**
     * Función que muestra la información del usuario.
     */
    public void showUser()
    {
        this.bd.showUserInformation(this.user);
    }

    /**
     * Función que cambia la contraseña del usuario.
     * 
     * @param passWord nueva contraseña.
     */
    public void changePassWord(String passWord)
    {
        this.user.setPassword(passWord);
    }

   /**
     * Funcón que actualiza la infocmación del usuario en la base de datos.
     *
     * @param name nombre del usuario.
     * @param bornDate fecha de nacimiento del usuario.
     * @param icon avatar del usuario.
     * @param hobbies lista que contiene las aficiones del usuario.
     * @param gender género del usuario.
     * @param preferences preferencias del usuario.
     * @param description descripción del usuario.
     */
    public void updateProfile(String name, Date bornDate, byte[] icon, Set<String> hobbies, Genero gendre, Genero preference, String description)
    {
        this.user.setNombre(name);
        this.user.setFecha_nacimiento(bornDate);
        this.user.setAvatar(icon);
        this.user.setAficiones(hobbies);
        this.user.setGenero(gendre);
        this.user.setGenero_interes(preference);
        this.user.setDescripcion(description);

        this.bd.updateProfile(user);
    }

    /**
     * Función que muestra las aficiones del usuario conectado.
     * 
     * @param user usuario conectado.
     */
    public void getHobbies(Usuario user)
    {
        this.bd.getHobbies(user);
    }
}