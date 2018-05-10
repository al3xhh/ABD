package abd.p1.view.listElements;

import abd.p1.model.Pregunta;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * CellRender que permite mostrar el título de la pregunta con su número de opciones.
 * 
 * @author Alejandro Huertas Herrero 3ºB.
 */
@SuppressWarnings("serial")
public class questionListCellRender extends QuestionListElement implements ListCellRenderer<Pregunta>
{
    @Override
    public Component getListCellRendererComponent(JList<? extends Pregunta> list, Pregunta value, int index, boolean isSelected, boolean cellHasFocus)
    {
        this.setTitle(value.getEnunciado());

        this.setNumber(value.getNumOpciones());

        this.setOpaque(true);

        if (isSelected)
            this.setBackground(Color.BLUE);
        else
            this.setBackground(Color.WHITE);

        return this;
    }
}