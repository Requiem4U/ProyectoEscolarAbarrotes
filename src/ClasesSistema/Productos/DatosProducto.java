/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema.Productos;

import java.io.Serializable;

/**
 *
 * @author DIRECCION
 */
public class DatosProducto implements Serializable{
    
    private int ID;
    private String nombre;
    private float precioPublico;
    private float precioProveedor;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecioPublico(float precioPublico) {
        this.precioPublico = precioPublico;
    }

    public void setPrecioProveedor(float precioProveedor) {
        this.precioProveedor = precioProveedor;
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
    
}
