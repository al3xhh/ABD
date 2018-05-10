package abd.p1.view.observers;

import abd.p1.model.Mensaje;
import abd.p1.model.Usuario;
import java.util.List;
import java.util.Set;

/**
 * Interfaz que permite a la vista del perfil de un usuario mostrar toda la
 * información de dicho usuario y todos los datos de interacción con el usuario
 * conectado.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
public interface profileObserver
{
    /**
     * Función que muestra la información del usuario cuyo perfil está siendo
     * visitado.
     *
     * @param user usuario cuya información tiene que ser mostrada.
     */
    public void seeUser(Usuario user);

    /**
     * Función que muestra la distancia que existe entre el usuario conectado y
     * el usuario que se está viendo su perfil.
     *
     * @param distance distancia entre ambos usuarios.
     */
    public void showDistance(double distance);

    /**
     * Función que muestra las aficiones del usuario cuyo perfil está siendo
     * visitado.
     *
     * @param hobbies lista que contiene las aficiones que hay que mostrar.
     */
    public void showUserHobbies(Set<String> hobbies);

    /**
     * Función que muestra la compatibilidad que hay entre los dos usuarios.
     *
     * @param compatibility compatibilidad que hay entre ambos.
     */
    public void showCompatibility(double compatibility);

    /**
     * Fnción que muestra las aficiones que tiene en común el usuario conectado
     * y el usuario cuyo perfil está siendo visitado.
     *
     * @param hobbies lista que contiene las aficiones que hay que mostrar.
     */
    public void showEqualsHobbies(Set<String> hobbies);

    /**
     * Función que activa o desactiva el botón de añadir amigo en función de si
     * son amigos ya o no.
     *
     * @param active true si hay que activarlo, false en caso contrario.
     */
    public void activeButton(boolean active);

    /**
     * Función que muestra un mensaje sólo, se utiliza cada vez que se envía un
     * mensaje en el chat con el usuario.
     *
     * @param message mensaje que tiene que ser mostrado.
     */
    public void showMessage(Mensaje message);

    /**
     * Función que muestra todo el chat entre los dos usuarios.
     *
     * @param messages lista que contiene los mensajes que han de ser mostrados.
     */
    public void showMessages(List<Mensaje> messages);
}