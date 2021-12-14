/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema.Clientes;

import java.io.Serializable;

/**
 *
 * @author cmccl
 */
public class Direccion implements Serializable{
    private int calle;
    private int numero;
    private String colonia;
    private int CP;
    private String ciudad;
    private String estado;
    private int telefono;

    public Direccion() {
        this.calle = 0;
        this.numero = 0;
        this.colonia = "";
        this.CP = 0;
        this.ciudad = "";
        this.estado = "";
        this.telefono = 0;
    }

    public void setCalle(int calle) {
        this.calle = calle;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setCP(int CP) {
        this.CP = CP;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getCalle() {
        return calle;
    }

    public int getNumero() {
        return numero;
    }

    public String getColonia() {
        return colonia;
    }

    public int getCP() {
        return CP;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getDireccionCompleta(){
        String direC = " Calle "+String.valueOf(this.calle)+" ";
        direC += "N "+String.valueOf(this.numero)+"   ";
        direC +=  "Col. "+ this.colonia+", "+this.ciudad+", "+this.estado;
        direC += ", CP" + String.valueOf(this.CP);
        direC += "      Tel. " + String.valueOf(this.telefono);

        return direC;
    }

}
