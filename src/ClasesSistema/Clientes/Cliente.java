/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema.Clientes;

import java.io.Serializable;

/**
 *
 * @author DIRECCION
 */
public class Cliente implements Serializable{
    private int IDCliente;
    private Persona persona;
    private Direccion address;
    private String nom, ap, direccion;
    
    
    
    public Cliente(Persona persona, Direccion address){
        this.address = address;
        this.persona = persona;
        
        this.nom = persona.getNombre();
        this.ap = persona.getApellidoP();
        this.direccion = address.getDireccionCompleta();
    }
    
    public int getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(int IDCliente) {
        this.IDCliente = IDCliente;
    }

    public Persona getPersona() {
        return persona;
    }

    public Direccion getAddress() {
        return address;
    }

    public String getNom() {
        return nom;
    }

    public String getAp() {
        return ap;
    }

    public String getDireccion() {
        return direccion;
    }
    
}
