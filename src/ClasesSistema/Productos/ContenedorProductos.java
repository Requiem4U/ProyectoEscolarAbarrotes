/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema.Productos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author DIRECCION
 */
public class ContenedorProductos implements ContainerProductos,Serializable{
    
    private ArrayList<Producto> productos;
    private int posicionArray;

    public ContenedorProductos(ArrayList<Producto> articulos) {
        this.productos = articulos;
        this.posicionArray = 0;
    }

    @Override
    public IteratorStock getIterator() {
        return new RecorrerStock();
    }
    
    public int getPosicionArray() {
        return this.posicionArray;
    }

    public Producto getExistencia(){
        if ((this.productos.size()==0)||(posicionArray==this.productos.size())) {
            return null;
        } else {
            return this.productos.get(posicionArray);
        }
    }
    
    
    
    public class RecorrerStock implements IteratorStock{

        @Override
        public boolean hasNext() {
            return posicionArray<productos.size();
        }

        @Override
        public Producto next() {
            return productos.get(posicionArray++);
        }

        @Override
        public boolean Existencia(int id) {
            if(posicionArray<productos.size()){
                return !(productos.get(posicionArray).getID()==id);
            }else{
                return false;
            }
        }

        
    }
    
    
    
}
