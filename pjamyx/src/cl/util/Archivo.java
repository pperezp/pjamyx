/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.util;

import java.io.*;

/**
 *
 * @author Fabiola
 */
public class Archivo extends File {

    public Archivo(String rutaArchivo) {
        super(rutaArchivo);
    }

    public void escribirEnAchivo(String texto, boolean sobreEscribir, boolean archivoSoloLectura) throws IOException {
//        this.mkdir();
        this.createNewFile();
        if (archivoSoloLectura) {
            this.setReadOnly();
        }
        PrintWriter archivoWriter;
        archivoWriter = new PrintWriter(new FileWriter(this, !sobreEscribir));
        archivoWriter.println(texto);
        if (archivoWriter != null) {
            archivoWriter.close();
        }
    }

    public long getFileSize() {
        if (this.isFile()) {
            return this.length();
        }
        return 0;
    }
    
    public String getArchivoComoString() throws IOException {
        StringBuilder texto = new StringBuilder();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {
            fis = new FileInputStream(this);
            isr = new InputStreamReader(fis);
            reader = new BufferedReader(isr);
            String linea;
            while ((linea = reader.readLine()) != null) {
                texto.append(linea).append("\n");
            }
        }finally {
            reader.close();
            isr.close();
            fis.close();
            return texto.toString();
        }
        
        
//        StringBuilder texto = new StringBuilder();
//        Scanner entrada = new Scanner(this);
//        
//        while(entrada.hasNext()){
//            texto.append(entrada.nextLine()).append("\n");
//        }
//        return texto.toString();
    }
    
//    public static void main(String[] args){
//        try {
//            Date d = new Date();
//            long time = d.getTime();
//            Archivo a = new Archivo("manifest.mf");
//            System.out.println(a.getArchivoComoString());
//            d = new Date();
//            long time1 = d.getTime();
//            System.out.println(time1-time);
//        } catch (IOException ex) {
//            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
