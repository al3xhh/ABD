package abd.p1.view.observers;

import abd.p1.model.Usuario;
import java.util.Set;

/**
 * Interfaz que permite al editor de perfil mostrar toda la información asociada
 * al usuario en concreto.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
public interface profileEditorObserver
{
    /**
     * Función que muestra la información del usuario.
     *
     * @param user usuario cuya información tiene que ser mostrada.
     */
    public void showUserInformation(Usuario user);

    /**
     * Función que muestra todas las aficiones del usuario.
     *
     * @param hobbies lista que contiene todas las aficiones que hay que
     * mostrar.
     */
    public void showUserHobbies(Set<String> hobbies);
}