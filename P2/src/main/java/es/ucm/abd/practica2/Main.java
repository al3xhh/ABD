package es.ucm.abd.practica2;

import es.ucm.abd.practica2.controller.FightController;
import es.ucm.abd.practica2.controller.SearchPanelController;
import es.ucm.abd.practica2.dao.AbedemonDAO;
import es.ucm.abd.practica2.dao.AbedemonDAOImpl;
import es.ucm.abd.practica2.model.Abedemon;
import es.ucm.abd.practica2.view.MainFrame;
import java.util.Arrays;
import java.util.List;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import net.xqj.exist.ExistXQDataSource;

/**
 * Clase principal de la aplicación
 *
 * @author Manuel Montenegro (mmontene@ucm.es)
 */
public class Main {
    
    public static void main(String[] args) throws XQException {
        XQDataSource ds = createDataSource();

        // Obtenemos los tipos de especies para rellenar los JComboBox
        AbedemonDAO dao = new AbedemonDAOImpl(ds);
        List<String> types = dao.getTypes();
        if (types == null) {
            types = Arrays.asList();
        }
        List<Abedemon> abedemons = Arrays.asList();

        // Creamos controladores para cada uno de los paneles, y para el botón
        // de lucha de la ventana principal
        SearchPanelController ctrl1 = new SearchPanelController(types, abedemons, dao);
        SearchPanelController ctrl2 = new SearchPanelController(types, abedemons, dao);
        FightController fight = new FightController(dao);
        MainFrame mainFrame = new MainFrame(ctrl1, ctrl2, fight);
        mainFrame.setVisible(true);
    }

    private static XQDataSource createDataSource() throws XQException {
        XQDataSource ds = new ExistXQDataSource();
        ds.setProperty("serverName", "localhost");
        ds.setProperty("port", "80");
        ds.setProperty("user", "admin");
        ds.setProperty("password", "alex");
        return ds;
    }

}
