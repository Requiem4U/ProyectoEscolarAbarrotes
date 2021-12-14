/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema.Compras;

import ClasesSistema.Clientes.Cliente;
import java.util.ArrayList;

/**
 *
 * @author DIRECCION
 */
public class Compra {
    private Cliente cliente;
    private CarritoDeCompras carrito;
    private float pagoCliente;
    private float cambio;
    
    
    public void addArticulo(Articulo art){
        carrito.addArticulo(art);
    }
    
    public void removeArticulo(Articulo art){
        carrito.removeArticulo(art);
    }

    public float getImporte() {
        return carrito.getImporte();
    }

    public ArrayList<Articulo> getListaCompra() {
        return carrito.getListaCompras();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCarrito(CarritoDeCompras carrito) {
        this.carrito = carrito;
    }

    public float getPagoCliente() {
        return pagoCliente;
    }

    public void setPagoCliente(float pagoCliente) {
        this.pagoCliente = pagoCliente;
    }

    public float getCambio() {
        return cambio;
    }

    public void setCambio(float cambio) {
        this.cambio = cambio;
    }
    
    
}
