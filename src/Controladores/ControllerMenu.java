/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesSistema.Clientes.ClientesRegistrados;
import ClasesSistema.Productos.Stock;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cmccl
 */
public class ControllerMenu implements Initializable {

    @FXML
    private Button btn_RegistrarCliente;
    @FXML
    private Button btn_RegistrarArticulo;
    @FXML
    private Button btn_Compra;
    
    private Stock stock;
    private ClientesRegistrados clientes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RegistrarCliente(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/ClientesView.fxml"));
            Parent root = loader.load();
            
            ControllerClientesView control = loader.getController();
            
            if (this.clientes!=null) {
                control.Inicializar(this.clientes);
            }else{
                this.clientes = new ClientesRegistrados();
                control.Inicializar(this.clientes);
            }
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> control.Cerrar());
            
            Stage myStage = (Stage) this.btn_RegistrarCliente.getScene().getWindow();
            myStage.close();
            
        } catch (IOException e) {
        }
    }

    @FXML
    private void RegistrarArticulo(ActionEvent event) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/RegistroArt.fxml"));
            Parent root = loader.load();
            
            ControllerRegistroArt control = loader.getController();
            
            if (this.stock!=null) {
                control.Inicializar(this.stock);   
            }else{
                this.stock = new Stock();
                control.Inicializar(this.stock);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> control.Cerrar());
            
            Stage myStage = (Stage) this.btn_RegistrarArticulo.getScene().getWindow();
            myStage.close();
            
        } catch (IOException e) {
        }
    }

    @FXML
    private void RealizarCompra(ActionEvent event) {
        
        if ((this.stock!=null)&&(this.clientes!=null)) {
            try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Compra.fxml"));
                Parent root = loader.load();
                
                ControllerCompra control = loader.getController();
                control.Inicializar(this.stock, this.clientes);
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                stage.setOnCloseRequest(e -> control.Cerrar());
                
                Stage myStage = (Stage) this.btn_RegistrarArticulo.getScene().getWindow();
                myStage.close();
                
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }else if (this.stock==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No existen articulos registrados en el Stock\n\nPrimero registre productos en el Stock");
            alert.showAndWait();
        }else if (this.clientes==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No existen clientes registrados\n\nPrimero registre a un cliente");
            alert.showAndWait();
        }
    }
    
    
    public void Inicializar(Stock stock){
        this.stock = stock;
    }
    
    public void Inicializar(ClientesRegistrados clientes){
        this.clientes = clientes;
    }
    
}
