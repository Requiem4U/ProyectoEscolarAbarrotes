/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema;

import ClasesSistema.Compras.Compra;

/**
 *
 * @author cmccl
 */
public class Ticket {
    
    private Compra compras;
    private FechaHora fechaHora;

    public Ticket(Compra compras) {
        this.compras = compras;
        this.fechaHora = new FechaHora();
    }


    public Compra getCompras() {
        return compras;
    }

    public FechaHora getFechaHora() {
        return fechaHora;
    }
    
}
