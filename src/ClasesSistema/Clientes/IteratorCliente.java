/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema.Clientes;

/**
 *
 * @author DIRECCION
 */
public interface IteratorCliente {
    
    public boolean hasNext();
    public Cliente next();
    public boolean sinRegistrar(Cliente cliente);
    public boolean sinRegistrar(int id);
    
    
}
