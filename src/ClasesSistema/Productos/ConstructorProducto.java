/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema.Productos;

import ClasesSistema.Kilogramo;
import ClasesSistema.Unidad;
import java.io.Serializable;

/**
 *
 * @author cmccl
 */
public class ConstructorProducto implements Serializable{
    
    public Producto ConstruirProducto(DatosProducto datos,Unidad cant){
        
        Producto producto = new Producto(datos,cant);
        
        return producto;
    }
    
    public Producto ConstruirProducto(DatosProducto datos,Kilogramo cant){
        Producto producto = new Producto(datos,cant);
        
        return producto;
    }
    
    
}
