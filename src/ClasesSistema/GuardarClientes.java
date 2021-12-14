/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema;

import ClasesSistema.Clientes.ClientesRegistrados;
import ClasesSistema.Clientes.Cliente;
import com.csvreader.CsvWriter;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author cmccl
 */
public class GuardarClientes extends GuardarArchivos{
    
    private ClientesRegistrados registroClientes;

    @Override
    public void GuardarArchivo(String ruta) throws IOException {
        try (FileOutputStream file = new FileOutputStream(ruta+File.separator+"recover"+File.separator+"RegistroDeClientes.bin")) {
            ObjectOutputStream obj = new ObjectOutputStream(file);
            obj.writeObject(this.registroClientes);
            obj.close();
        }
    }
    
    @Override
    public void crearCSV(String ruta) throws IOException{
        
        ArrayList<Cliente> lista = this.registroClientes.getListaClientes();
        CsvWriter csvWriter = new CsvWriter(ruta+File.separator+"ListaClientes.csv");
        
        String[] tCeldas = {"ID","Nombre","Apellido","Direccion"};
        csvWriter.writeRecord(tCeldas);
        String[] espCeldas = {" ", " ", " ", " "};
        csvWriter.writeRecord(espCeldas);
        
        for (Cliente cliente : lista) {
            String[] datos = {String.valueOf(cliente.getIDCliente()),cliente.getNom(),cliente.getAp(),cliente.getDireccion()};
            csvWriter.writeRecord(datos);
        }
        csvWriter.close();
    }
    
    public void setRegistroClientes(ClientesRegistrados registro){
        this.registroClientes = registro;
    }

    
}
