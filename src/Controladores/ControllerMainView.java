/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesSistema.Busqueda;
import ClasesSistema.Clientes.ClientesRegistrados;
import ClasesSistema.GuardarArchivos;
import ClasesSistema.Productos.Stock;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cmccl
 */
public class ControllerMainView implements Initializable {

    @FXML
    private Button btn_Inicio;
    
    private Stock stock;
    private ClientesRegistrados clientes;
    private final String ruta = GuardarArchivos.getRuta();

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }

    @FXML
    private void Iniciar(ActionEvent event) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Menu.fxml"));
            Parent root = loader.load();
            
            Busqueda buscador = new Busqueda();
            if(buscador.BuscarCarpetas(this.ruta)){
                Stock stock1 = buscador.leerStock(this.ruta);
                ClientesRegistrados cl = buscador.leerClientesRegistrados(this.ruta);
                
                ControllerMenu control = loader.getController();
                if (stock1!=null) {
                    control.Inicializar(stock1);
                }
                if (cl!=null) {
                    control.Inicializar(cl);
                }
            }else{
                buscador.crerCarpeta(GuardarArchivos.getRuta());
                buscador.crerCarpeta(GuardarArchivos.getRuta()+File.separator+"recover");
                buscador.crerCarpeta(GuardarArchivos.getRuta()+File.separator+"Tickets");
            }
        
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            Stage myStage = (Stage) this.btn_Inicio.getScene().getWindow();
            myStage.close();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        
    }
    
    
}
