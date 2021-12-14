/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author DIRECCION
 */
public abstract class GuardarArchivos {
    
    
    public abstract void GuardarArchivo(String ruta) throws IOException;
    public abstract void crearCSV(String ruta) throws IOException;
    
    public static String getRuta(){
        return System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"AbarrotesTizimin";
    }
    
}
