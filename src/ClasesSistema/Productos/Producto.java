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
 * @author DIRECCION
 */
public class Producto implements  Serializable{

    
    private Object Existencias;
    private DatosProducto Datos;
    private int ID;
    private String nombre;
    private float precioPublico;
    private float precioProveedor;
    private String exisT, formaVenta;
    
    
    public Producto(DatosProducto datos, Kilogramo cant) {
        this.Datos = datos;
        this.Existencias = cant;
        
        this.ID = datos.getID();
        this.nombre = datos.getNombre();
        this.precioProveedor = datos.getPrecioProveedor();
        this.precioPublico = datos.getPrecioPublico();
        this.exisT = String.valueOf(cant.getCant());
        this.formaVenta = "Kilogramos";
    }
    
    public Producto(DatosProducto datos, Unidad cant) {
        this.Datos = datos;
        this.Existencias = cant;
        
        this.ID = datos.getID();
        this.nombre = datos.getNombre();
        this.precioProveedor = datos.getPrecioProveedor();
        this.precioPublico = datos.getPrecioPublico();
        this.exisT = String.valueOf(cant.getCant());
        this.formaVenta = "Unidades";
    }

    public Object getExistencias() {
        return Existencias;
    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecioPublico() {
        return precioPublico;
    }

    public float getPrecioProveedor() {
        return precioProveedor;
    }

    public DatosProducto getDatos() {
        return Datos;
    }

    public String getExisT() {
        return exisT;
    }

    public String getFormaVenta() {
        return formaVenta;
    }
    
}
