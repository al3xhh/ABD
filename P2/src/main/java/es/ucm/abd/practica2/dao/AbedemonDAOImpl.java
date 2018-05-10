package es.ucm.abd.practica2.dao;

import es.ucm.abd.practica2.model.Abedemon;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import org.w3c.dom.Element;

/**
 * Implementación concreta del DAO que hace llamadas a eXist.
 * 
 * @author Manuel Montenegro (mmontene@ucm.es)
 */
public class AbedemonDAOImpl implements AbedemonDAO {

    private final XQDataSource ds;

    public AbedemonDAOImpl(XQDataSource ds) {
        this.ds = ds;
    }

    /**
     * Obtiene los tipos de especies disponibles en la BD.
     * 
     * @return Lista de tipos de especies (sin duplicados)
     */
    @Override
    public List<String> getTypes() 
    {
    	InputStream is = getClass().getResourceAsStream("Abedemon1.xquery");
    	XQConnection con;
    	XQPreparedExpression exp;
    	List<String> types = new ArrayList<>();
    	
		try 
		{
			con = ds.getConnection();
			exp = con.prepareExpression(is);
			XQResultSequence rs = exp.executeQuery();
			
			while(rs.next())
				types.add(rs.getItemAsString(null));
		} 
		catch (XQException e) 
		{
			e.printStackTrace();
		}
    	
        return types;
    }

    /**
     * Obtiene las especies de abedemon de un determinado.
     * 
     * @param type Tipo a buscar
     * @return Especies encontradas del tipo pasado como parámetro.
     */
    @Override
    public List<Abedemon> getAbedemonsOf(String type) 
    {
    	InputStream is = getClass().getResourceAsStream("Abedemon2.xquery");
    	XQConnection con;
    	XQPreparedExpression exp;
    	List<Abedemon> abedemons = new ArrayList<>();
    	
		try 
		{
			con = ds.getConnection();
			exp = con.prepareExpression(is);
			exp.bindObject(new QName("tipo"), type, null);
			XQResultSequence rs = exp.executeQuery();
						
			while(rs.next())
			{		
				Element e = (Element)rs.getNode();
				int nAtaques = Integer.parseInt(e.getAttribute("num-ataques"));
				String nombre = e.getAttribute("nombre"), id = e.getAttribute("id");
				
				abedemons.add(new Abedemon(id, nombre, nAtaques));
			}
		} 
		catch (XQException e) 
		{
			e.printStackTrace();
		}
		
        return abedemons;
    }

    /**
     * Obtiene la descripción de una especie de abedemon.
     * 
     * @param id ID de la especie a describir
     * @return Código XHTML con la descripción de la especie
     */
    @Override
    public String getAbedemonDescription(String id) 
    {
    	InputStream is = getClass().getResourceAsStream("Abedemon3.xquery");
    	XQConnection con;
    	XQPreparedExpression exp;
    	String abedemon = null;
    	
		try 
		{
			con = ds.getConnection();
			exp = con.prepareExpression(is);
			exp.bindObject(new QName("id"), id, null);
			XQResultSequence rs = exp.executeQuery();
			
			rs.next();
			abedemon = rs.getItemAsString(null);
		} 
		catch (XQException e) 
		{
			e.printStackTrace();
		}
		
        return abedemon;
    }

    /**
     * Obtiene el daño máximo recibido por un abedemon de una especie dada al
     * ser atacado por otro.
     * 
     * @param idAttacker ID de la especie del atacante.
     * @param idReceiver ID de la especie que recibe el daño.
     * @return Máximo daño que puede infligir el atacante.
     */
    @Override
    public Integer getDamage(String idAttacker, String idReceiver) 
    {
    	InputStream is = getClass().getResourceAsStream("Abedemon4.xquery");
    	XQConnection con;
    	XQPreparedExpression exp;
    	int damage = 0;
    	
		try 
		{
			con = ds.getConnection();
			exp = con.prepareExpression(is);
			exp.bindObject(new QName("yoId"), idAttacker, null);
			exp.bindObject(new QName("adversarioId"), idReceiver, null);
			XQResultSequence rs = exp.executeQuery();
			
			rs.next();
			damage = Integer.parseInt(rs.getItemAsString(null));
		} 
		catch (XQException e) 
		{
			e.printStackTrace();
		}
		
        return damage;
    }
}