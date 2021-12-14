/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema.Compras;

import ClasesSistema.Kilogramo;
import ClasesSistema.Unidad;
import java.util.ArrayList;

/**
 *
 * @author DIRECCION
 */
public class CarritoDeCompras {
    private ArrayList<Articulo> ListaCompras;
    private float importe;
    
    public CarritoDeCompras(){
        this.ListaCompras=new ArrayList();
    }
    
    public void addArticulo( Articulo art){
        this.ListaCompras.add(art);
    }
    
    public void removeArticulo(Articulo art){
        this.ListaCompras.remove(art);
    }
    
    public float getImporte(){
        float aux=0;
        for (Articulo art : ListaCompras) {
            aux += art.getSubtotal();
        }
        
        this.importe = aux;
        return this.importe;
    }

    public ArrayList<Articulo> getListaCompras() {
        return ListaCompras;
    }
    
}
