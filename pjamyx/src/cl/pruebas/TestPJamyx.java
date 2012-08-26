/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pruebas;

import cl.conexion.Conexion;
import cl.conexion.DatosConexion;
import cl.conexion.Resultado;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Pato Pérez
 */
public class TestPJamyx {
    
    public static void main(String[] a){
        try {
            Conexion c = new Conexion(new DatosConexion() {

                @Override
                public String getServer() {
                    return "localhost";
                }

                @Override
                public String getUser() {
                    return "root";
                }

                @Override
                public String getPass() {
                    return "";
                }

                @Override
                public String getBaseDeDatos() {
                    return "prueba";
                }
            });
//            c.execute("insert into cliente values('44-4','nombre4','123');");
            c.execute("update cliente set nombre = 'Nombre 4' where rut = '44-4'");
            Resultado rs = c.executeQuery("select * from cliente");
            
            while(rs.next()){
                System.out.println(rs.getString("rut"));
                System.out.println(rs.getString("nombre"));
                System.out.println(rs.getString("fono"));
            }
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(TestPJamyx.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
