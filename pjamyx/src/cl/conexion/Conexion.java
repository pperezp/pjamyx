/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.conexion;

import cl.util.Archivo;
import cl.util.Ruta;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import xml.analizador.dom.DOM;

/**
 *
 * @author Pato Pérez
 */
public class Conexion {
    private DatosConexion datos;
    
    public Conexion(DatosConexion datos) throws MalformedURLException{
        this.datos = datos;
    }
    
    public Resultado executeQuery(String query) throws MalformedURLException, IOException, ParserConfigurationException, SAXException{
        URL url = new URL("http://"+datos.getServer()+"/pjamyx/consulta.php"
                + "?q="+query.replaceAll(" ", "_")+"&bd="+datos.getBaseDeDatos()+
                "&user="+datos.getUser()+"&pass="+datos.getPass()+"&executeQuery=1");
        URLConnection uc = url.openConnection();
        uc.connect();
        StringBuilder contenido;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()))) {
            String linea;
            contenido = new StringBuilder();
            while ((linea = in.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        }
        if(!new File(Ruta.CARPETA_TEMP).exists()){
            new File(Ruta.CARPETA_TEMP).mkdir();
            
        }
        Archivo arXML = new Archivo(Ruta.ARCHIVO_TEMP);
        arXML.escribirEnAchivo(contenido.toString(), true, false);
        
        Resultado r = new Resultado(DOM.procesarArchivoXMLDom(arXML));
        return r;
    }

    public boolean execute(String query) throws MalformedURLException, IOException, ParserConfigurationException, SAXException{
        URL url = new URL("http://"+datos.getServer()+"/pjamyx/consulta.php"
                + "?q="+query.replaceAll(" ", "_")+"&bd="+datos.getBaseDeDatos()+
                "&user="+datos.getUser()+"&pass="+datos.getPass()+"&executeQuery=0");
        URLConnection uc = url.openConnection();
        uc.connect();
        StringBuilder contenido;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()))) {
            String linea;
            contenido = new StringBuilder();
            while ((linea = in.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        }
        
        System.out.println(contenido);
        
        return Boolean.getBoolean(contenido.toString());
    }
}
