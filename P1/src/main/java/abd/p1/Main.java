package abd.p1;

import abd.p1.controller.loginController;
import abd.p1.controller.mainWindowController;
import abd.p1.model.Usuario;
import abd.p1.view.Login;
import abd.p1.view.MainWindow;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Random;

import javax.swing.UIManager;
import javax.swing.UIManager.*;
import javax.swing.UnsupportedLookAndFeelException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Clase main que se encarga de crear el SessionFactory y acceder a la base
 * de datos con un usario creado que solo tiene persmisos para realizar
 * consultas y actualizaciones de la base de datos. Luego se crean todos los
 * componentes del programa.
 *
 * @author Alejandro Huertas Herrero 3ºB.
 *
 */
public class Main
{
    public static void main(String[] args)
    {
        try (SessionFactory factory = buildSessionFactory())
        {
            setLookAndFeel();

            loginController controller = new loginController(factory);
            Login l = new Login(null, true, controller);
            l.setVisible(true);
            l.setModal(true);

            Usuario conectedUser = controller.getConectedUser();

            if (conectedUser != null)
            {
                newCordinates(conectedUser);
                
                try(Session session = factory.openSession())
                {
                    Transaction tr = session.beginTransaction();
                    conectedUser = (Usuario)session.merge(conectedUser);
                    tr.commit();
                }
                
                MainWindow mw = new MainWindow(null, true,  new mainWindowController(factory, conectedUser));
                mw.setVisible(true);
                mw.setModal(true);
            }

            System.exit(0);
        }
    }
    
    //--------------------------FUNCIONES PRIVADAS--------------------------//

    /**
     * @return sessionFactory creado para poder conectarse con la base de datos
     */
    private static SessionFactory buildSessionFactory()
    {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try
        {
            return new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
            return null;
        }
    }

    /**
     * Función que cambia la apariencia de los componentes visuales usando nimbus.
     */
    private static void setLookAndFeel()
    {
        try
        {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
        }
    }

    /**
     * Función que calcula unas coordenadas aleatorias para el usuario que se conecta.
     * @param user usuario conectado en la app.
     */
    private static void newCordinates(Usuario user)
    {
        Random randomA = new Random(), randomB = new Random();

        double randA = randomA.nextDouble(), randB = randomB.nextDouble();

        double scaledA = 40 + randA * 1.2, scaledB = 3 + randB * 1.5;

        double radiansA = (scaledA * 2 * 3.14) / 360, randiansB = (scaledB * 2 * 3.14) / 360;

        user.setLatitud(radiansA);
        user.setLongitud(randiansB);
    }
}