/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema.Clientes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author DIRECCION
 */
public class ClientesRegistrados implements Serializable{
   
    private ArrayList<Cliente> listaClientes;
    
    public ClientesRegistrados(){
        this.listaClientes = new ArrayList();
    }
   
    public void addCliente(Cliente cliente){
        this.listaClientes.add(cliente);
    }
    
    public void modifCliente(Cliente cOriginal, Cliente cModif){
        ContenedorClientes cont = new ContenedorClientes(this.listaClientes);
        
        for(IteratorCliente iter=cont.getIterator();iter.sinRegistrar(cOriginal);){
            Cliente cl = iter.next();
        }
        
        int pos = cont.getPosicionArray();
        this.listaClientes.remove(pos);
        this.listaClientes.add(pos, cModif);
    }
    
    public ArrayList<Cliente> getListaClientes() {
        return this.listaClientes;
    }
    
    public boolean ClienteYaRegistrado(Cliente cliente){
        
        ContenedorClientes cont = new ContenedorClientes(this.listaClientes);
        
        for(IteratorCliente iter=cont.getIterator();iter.sinRegistrar(cliente);){
            Cliente cl = iter.next();
            if(cliente.getNom().equalsIgnoreCase(cl.getNom())&&cliente.getAp().equalsIgnoreCase(cl.getAp())){
                return true;
            }
        }
        
        return cont.getIterator().hasNext();
    }
    
    public Cliente getCliente(int id){
        
        ContenedorClientes cont = new ContenedorClientes(this.listaClientes);
        
        for(IteratorCliente iter=cont.getIterator();iter.sinRegistrar(id);){
            Cliente cl = iter.next();
            
        }
        
        return cont.getCliente();
    }
    
}
