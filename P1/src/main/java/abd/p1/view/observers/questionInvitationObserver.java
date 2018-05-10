package abd.p1.view.observers;

import abd.p1.model.Usuario;
import java.util.List;

/**
 * Interfaz que permite a la vista de invitación a responder una pregunta
 * mostrar todos los datos necesarios.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
public interface questionInvitationObserver
{
    /**
     * Función que muestra todos los usuarios, ya sea con o sin filtros.
     *
     * @param users lista que contiene los usuarios que tienen que ser
     * mostrados.
     */
    public void showUsers(List<Usuario> users);
}