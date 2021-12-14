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
 * @author cmccl
 */
public class ContenedorClientes implements ContainerCliente, Serializable{
    
    private ArrayList<Cliente> listaClientes;
    private int posicionArray;
    
    
    ContenedorClientes(ArrayList<Cliente> lista){
        this.listaClientes = lista;
        this.posicionArray = 0;
    }

    @Override
    public IteratorCliente getIterator() {
        return new RecorrerCliente();
    }

    public int getPosicionArray() {
        return posicionArray;
    }
    
    public Cliente getCliente(){
        if ((this.listaClientes.size()==0)||(this.posicionArray==this.listaClientes.size())) {
            return null;
        } else {
            return this.listaClientes.get(posicionArray);
        }
    }

    public class RecorrerCliente implements IteratorCliente{

        @Override
        public boolean hasNext() {
            return posicionArray<listaClientes.size();
        }

        @Override
        public Cliente next() {
            return listaClientes.get(posicionArray++);
        }

        @Override
        public boolean sinRegistrar(Cliente cliente) {
            if(posicionArray<listaClientes.size()){
                return !listaClientes.get(posicionArray).equals(cliente);
            }else{
                return false;
            }
        }

        @Override
        public boolean sinRegistrar(int id) {
            if(posicionArray<listaClientes.size()){
                return !(listaClientes.get(posicionArray).getIDCliente()==id);
            }else{
                return false;
            }
        }
        
    }
    
    
}
