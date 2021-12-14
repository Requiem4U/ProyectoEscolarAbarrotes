/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema.Compras;

import ClasesSistema.Kilogramo;
import ClasesSistema.Unidad;


public class Articulo {
    
    private DatosArticulo Datos;
    private Object cantidad;
    private float subtotal, precio;
    private String nombre, codigo, cant;

    
    
    public void setDatos(DatosArticulo Datos) {
        this.Datos = Datos;
        this.nombre = Datos.getNombre();
        this.codigo = String.valueOf(Datos.getCodigo());
        this.precio = Datos.getPrecio();
    }

    public void setCantidad(Unidad cantidad) {
        this.cantidad = cantidad;
        this.cant = String.valueOf(cantidad.getCant());
    }
    
    public void setCantidad(Kilogramo cantidad) {
        this.cantidad = cantidad;
        this.cant = String.valueOf(cantidad.getCant());
    }
    
    public DatosArticulo getDatos() {
        return Datos;
    }

    public Object getCantidad(){
        return this.cantidad;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCant() {
        return cant;
    }
    
    
    
}
