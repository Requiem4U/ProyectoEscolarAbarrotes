/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema.Compras;

import ClasesSistema.Clientes.Cliente;

/**
 *
 * @author cmccl
 */
public class ConstructorCompra {
    
    public Compra Construir(Cliente cliente){
        
        CarritoDeCompras carrito = new CarritoDeCompras();
        Compra compra = new Compra();
        
        compra.setCliente(cliente);
        compra.setCarrito(carrito);
        
        return compra;
        
    }
    
    
}
