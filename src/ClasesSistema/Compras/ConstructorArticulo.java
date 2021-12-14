/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema.Compras;

import ClasesSistema.Kilogramo;
import ClasesSistema.Unidad;

/**
 *
 * @author cmccl
 */
public class ConstructorArticulo {
    
    public Articulo ConstruirArtPorUnidades(){
        Unidad unidad = new Unidad();
        DatosArticulo datos = new DatosArticulo();
        Articulo articulo= new Articulo();
        
        articulo.setCantidad(unidad);
        articulo.setDatos(datos);
        
        return articulo;
    }
    
    public Articulo ConstruirArtPorKilos(){
        
        Kilogramo kilo = new Kilogramo();
        DatosArticulo datos = new DatosArticulo();
        Articulo articulo = new Articulo();
        
        articulo.setCantidad(kilo);
        articulo.setDatos(datos);
        
        return articulo;
    }
    
}
