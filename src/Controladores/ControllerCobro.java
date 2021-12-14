/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesSistema.Compras.Compra;
import ClasesSistema.GuardarArchivos;
import ClasesSistema.ImprimirTickets;
import ClasesSistema.Ticket;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cmccl
 */
public class ControllerCobro implements Initializable {

    @FXML
    private Button btn_Cobrar;
    @FXML
    private TextField txt_Cambio;
    @FXML
    private TextField txt_Importe;
    @FXML
    private TextField txt_Cant_Recivida;
    @FXML
    private Button btn_Imprimir;
    
    
    private Compra compras;
    private float importe;
    private float cambio ;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.txt_Importe.setText(String.valueOf(this.importe));
        this.btn_Imprimir.setDisable(true);
        this.btn_Cobrar.setDisable(false);
    }    

    @FXML
    private void Cobrar(ActionEvent event) {
        try {
            float cantRecibida = Float.parseFloat(this.txt_Cant_Recivida.getText());
            this.cambio = cantRecibida - this.importe;
            this.compras.setCambio(cambio);
            this.compras.setPagoCliente(cantRecibida);
            this.txt_Cambio.setText(String.valueOf(cambio));
            this.btn_Imprimir.setDisable(false);
            this.btn_Cobrar.setDisable(true);
            
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Aviso!");
            String aviso = "Ingrese una cantidad valida.";
            aviso += "\nDebe ingresar numeros sin espacios ni caracteres especiales.";
            alert.setContentText(aviso);
            alert.showAndWait();
        }
        
    }

    @FXML
    private void ImprimirTicket(ActionEvent event) {
        try {
            Ticket ticket = new Ticket(this.compras);
            ImprimirTickets imprimir = new ImprimirTickets();
            imprimir.setTicket(ticket);
            imprimir.Imprimir(GuardarArchivos.getRuta()+File.separator+"Tickets");
            
            Stage myStage = (Stage) this.btn_Imprimir.getScene().getWindow();
            myStage.close();
            
        } catch (IOException | DocumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void Inicializar(Compra compras){
        this.compras = compras;
        float a = compras.getImporte();
        this.importe  = a;
    }

}
