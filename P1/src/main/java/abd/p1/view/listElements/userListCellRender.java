package abd.p1.view.listElements;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import abd.p1.model.Usuario;

/**
 * CellRender que permite mostrar el avatar del usuario, su nombre y su edad.
 * 
 * @author Alejandro Huertas Herrero 3ºB.
 */
@SuppressWarnings("serial")
public class userListCellRender extends UserListElement implements ListCellRenderer<Usuario>
{
    @Override
    public Component getListCellRendererComponent(JList<? extends Usuario> list, Usuario value, int index, boolean isSelected, boolean cellHasFocus)
    {
        if (value.getAvatar() != null)
            this.setIcon(new ImageIcon(value.getAvatar()));
        else
            this.setIcon(new ImageIcon("src/main/resources/huevo.jpg"));

        this.setName(value.getNombre());

        if (value.getFecha_nacimiento() != null)
            this.setAge(String.valueOf(value.calculateAge(value.getFecha_nacimiento())));
        else
            this.setAge("No hay datos de los ");

        this.setOpaque(true);

        if (isSelected)
            this.setBackground(Color.CYAN);
        else
            this.setBackground(Color.WHITE);

        return this;
    }
}