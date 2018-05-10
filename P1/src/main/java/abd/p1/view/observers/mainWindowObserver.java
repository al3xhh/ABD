package abd.p1.view.observers;

import java.util.List;

import abd.p1.model.Mensaje;
import abd.p1.model.Pregunta;
import abd.p1.model.Usuario;

/**
 * Interfaz que permite a la ventana principal mostrar toda la información
 * necesaria.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
public interface mainWindowObserver
{
    /**
     * Función que muestra los usuarios en la pestaña de usuarios, ya sea
     * filtrados de alguna forma o todos los usuario sin filtro alguno.
     *
     * @param usersList lista que contiene todos los usuarios que hay que
     * mostrar.
     */
    public void showUsers(List<Usuario> usersList);

    /**
     * Función que muestra las preguntas en la pestaña de preguntas.
     *
     * @param questionsList lista que contiene todas las preguntas que hay que
     * mostrar.
     */
    public void showQuestions(List<Pregunta> questionsList);

    /**
     * Función que muestra todos los mensajes no leídos por el usuario.
     *
     * @param messagesList lista que contiene todos los mensajes que hay que
     * mostrar.
     */
    public void showNonReadMessages(List<Mensaje> messagesList);
}