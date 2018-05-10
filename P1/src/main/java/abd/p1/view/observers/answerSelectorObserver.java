package abd.p1.view.observers;

import abd.p1.model.Pregunta;

/**
 * Interfaz que permite a la selcción de respuesta a una pregunta mostrar en la
 * vista las opciones de esa pregunta.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 */
public interface answerSelectorObserver
{
    /**
     * Función que muestra todas las opciones de la pregunta.
     *
     * @param question pregunta cuyas opciones tienen que ser mostradas.
     */
    public void showOptions(Pregunta question);
}