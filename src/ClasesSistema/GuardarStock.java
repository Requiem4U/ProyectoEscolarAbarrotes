/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema;

import ClasesSistema.Productos.Producto;
import ClasesSistema.Productos.Stock;
import com.csvreader.CsvWriter;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author DIRECCION
 */
public class GuardarStock extends GuardarArchivos{
    
    private Stock stock;

    public GuardarStock(Stock stock) {
        this.stock = stock;
    }
    

    @Override
    public void GuardarArchivo(String ruta) throws IOException {
        try (FileOutputStream file = new FileOutputStream(ruta+File.separator+"recover"+File.separator+"Stock.bin")) {
            ObjectOutputStream obj = new ObjectOutputStream(file);
            obj.writeObject(this.stock);
            obj.close();
        }
    }
    
    @Override
    public void crearCSV(String ruta) throws IOException{
        
        ArrayList<Producto> lista = this.stock.getListaProductos();
        CsvWriter csvWriter = new CsvWriter(ruta+File.separator+"ListaProductos.csv");
        
        String[] tCeldas = {"Nombre del producto", "Codigo", "Precio al publico", "Precio del probeedor", "Forma de venta", "Existencias"};
        csvWriter.writeRecord(tCeldas);
        String[] tCeldasV = {"", "", "", "", "", ""};
        csvWriter.writeRecord(tCeldasV);
        csvWriter.setEscapeMode(1);
        
        for (Producto p : lista) {
            String[] datos = {p.getNombre(), String.valueOf(p.getID()), String.valueOf(p.getPrecioPublico()), String.valueOf(p.getPrecioProveedor()), p.getFormaVenta(),p.getExisT()};
            csvWriter.writeRecord(datos);
        }
        
        csvWriter.close();
    }
    
    
}
