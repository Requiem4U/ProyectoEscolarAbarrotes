/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema.Productos;

import ClasesSistema.Kilogramo;
import ClasesSistema.Unidad;
import java.io.Serializable;
import java.util.ArrayList;



/**
 *
 * @author DIRECCION
 */
public class Stock implements Serializable{
    
    private ArrayList<Producto> listaProductos;

    public Stock() {
        this.listaProductos = new ArrayList();
    }
    
    
    public void addProducto(Producto producto){
        this.listaProductos.add(producto);
    }
    
    public void addProducto(Producto prodOriginal, Producto prodModif){
        ContenedorProductos cont = new ContenedorProductos(this.listaProductos);
        
        for (IteratorStock iter = cont.getIterator(); iter.Existencia(prodOriginal.getID());){
            Producto prod = iter.next();
        }
        
        int pos = cont.getPosicionArray();
        this.listaProductos.remove(pos);
        this.listaProductos.add(pos, prodModif);
    }
    
    public ArrayList<Producto> getListaProductos(){
        return this.listaProductos;
    }
    
    public int getUnidadesExistencia(Producto producto){
        ContenedorProductos cont = new ContenedorProductos(this.listaProductos);
        
        for (IteratorStock iter = cont.getIterator(); iter.Existencia(producto.getID());){
            Producto prod = iter.next();
        }
        
        Unidad unidades = (Unidad) cont.getExistencia().getExistencias();
        return unidades.getCant();
    }
    
    public float getKilosExistencia(Producto producto){
        ContenedorProductos cont = new ContenedorProductos(this.listaProductos);
        
        for (IteratorStock iter = cont.getIterator(); iter.Existencia(producto.getID());){
            Producto prod = iter.next();
        }
        
        Kilogramo kilos = (Kilogramo) cont.getExistencia().getExistencias();
        return kilos.getCant();
    }
    
    public Producto getProducto(int id){
        ContenedorProductos cont = new ContenedorProductos(this.listaProductos);
        
        for (IteratorStock iter = cont.getIterator(); iter.Existencia(id);){
            Producto prod = iter.next();
        }
        
        return cont.getExistencia();
        
    }
}
