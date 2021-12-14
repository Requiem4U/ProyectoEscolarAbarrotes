/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema.Productos;

/**
 *
 * @author DIRECCION
 */
public interface IteratorStock {
    
    public boolean hasNext();
    public Producto next();
    public boolean Existencia(int id);
    
}
