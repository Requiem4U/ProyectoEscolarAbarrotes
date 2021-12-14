/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema;

import ClasesSistema.Clientes.ClientesRegistrados;
import ClasesSistema.Productos.Stock;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DIRECCION
 */
public class Busqueda {
    
    public boolean BuscarCarpetas(String ruta){
        
        File direc = new  File(ruta);
        
        return direc.exists();
        
    }
    
    public void crerCarpeta(String ruta){
        String rutaDirec = ruta;
        File file = new File(ruta);
        file.mkdir();
        
    }
    
    public Stock leerStock(String ruta){
        Stock stock = null;
        try {
            FileInputStream file = new FileInputStream(ruta+File.separator+"recover"+File.separator+"Stock.bin");
            ObjectInputStream obj = new ObjectInputStream(file);
            
            try {
                stock = (Stock) obj.readObject();
            } catch (ClassNotFoundException ex) {
                stock = null;
            }
            obj.close();
            file.close();
            return stock;
        } catch (IOException ex) {
            return stock;
        }
        
    }
    
    public ClientesRegistrados leerClientesRegistrados(String ruta){
        
        ClientesRegistrados contenedor = null;
        try {
            FileInputStream file = new FileInputStream(ruta+File.separator+"recover"+File.separator+"RegistroDeClientes.bin");
            ObjectInputStream obj = new ObjectInputStream(file);
            
            try {
                contenedor = (ClientesRegistrados) obj.readObject();
            } catch (ClassNotFoundException ex) {
                contenedor = null;
            }
            obj.close();
            file.close();
            return contenedor;
        } catch (IOException ex) {
            return contenedor;
        }
    }
}
