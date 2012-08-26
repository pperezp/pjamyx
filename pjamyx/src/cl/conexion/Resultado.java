/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.conexion;


import java.util.List;
import xml.analizador.dom.modelo.Tag;

/**
 *
 * @author Pato Pérez
 */
public class Resultado {
    private List<Tag> registros;
    private int indice; 
    
    public Resultado(Tag raiz){
        registros = raiz.getTagsHijos();
        indice = 0;
    }
    
    public boolean next(){
        return indice++ < registros.size();
    }
    
    public String getString(String nombreColumna){
        Tag reg = registros.get(indice-1);
        for(Tag col : reg.getTagsHijos()){
            if(col.getNombre().equalsIgnoreCase(nombreColumna)){
                return ("".equals(col.getContenido())?null:col.getContenido());
            }
        }
        return null;
    }
    
    public String getString(int index){
        Tag reg = registros.get(indice-1);
        int cont = 0;
        for(Tag col : reg.getTagsHijos()){
            if(cont == index){
                return ("".equals(col.getContenido())?null:col.getContenido());
            }
            cont++;
        }
        return null;
    }
}
