/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesSistema.Clientes.Cliente;
import ClasesSistema.Clientes.ClientesRegistrados;
import ClasesSistema.Clientes.Direccion;
import ClasesSistema.Clientes.Persona;
import java.net.URL;
import java.util.ResourceBundle;
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
public class ControllerRegistrarCliente implements Initializable {

    @FXML
    private TextField txt_Nombre;
    @FXML
    private TextField txt_Apellido;
    @FXML
    private TextField txt_IdCliente;
    @FXML
    private TextField txt_Dir_Calle;
    @FXML
    private TextField txt_Dir_Numero;
    @FXML
    private TextField txt_Dir_Colonia;
    @FXML
    private TextField txt_Dir_CP;
    @FXML
    private TextField txt_Dir_Ciudad;
    @FXML
    private TextField txt_Dir_Estado;
    @FXML
    private Button btn_Registrar;
    @FXML
    private Button btn_Regresar;
    @FXML
    private TextField txt_Telefono;
    
    private Cliente cliente=null;
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
            String nombre = this.txt_Nombre.getText();
            String apellido = this.txt_Apellido.getText();
            int telefono = Integer.parseInt(this.txt_Telefono.getText());
            int id = Integer.parseInt(this.txt_IdCliente.getText());
            int calle = Integer.parseInt(this.txt_Dir_Calle.getText());
            int numero = Integer.parseInt(this.txt_Dir_Numero.getText());
            int cp = Integer.parseInt(this.txt_Dir_CP.getText());
            String colonia = this.txt_Dir_Colonia.getText();
            String ciudad = this.txt_Dir_Ciudad.getText();
            String estado = this.txt_Dir_Estado.getText();
            
            Cliente aux = this.clientes.getCliente(id);
            if (aux == null) {
                
                Direccion address = new Direccion();
                address.setCalle(calle);
                address.setNumero(numero);
                address.setColonia(colonia);
                address.setCiudad(ciudad);
                address.setEstado(estado);
                address.setCP(cp);
                address.setTelefono(telefono);
                
                Persona persona = new Persona();
                persona.setNombre(nombre);
                persona.setApellidoP(apellido);
                
                this.cliente = new Cliente(persona, address);
                this.cliente.setIDCliente(id);
                
                Stage myStage = (Stage) this.btn_Registrar.getScene().getWindow();
                myStage.close();
                
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                String aviso = "El identificador proporcionado ya pertenece a otro cliente";
                alert.setContentText(aviso);
                alert.showAndWait();
            }
            
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            String aviso = "Error en el ingreso de los datos...\nVerifique su correcta escritura.";
            aviso += "\n\n☼ Rellene todos los campos antes de registrar al cliente";
            aviso += "\n☼ La calle, el CP y el ID del cliente, deben ser valores numericos";
            aviso += "\n☼ El ID debe tener exactamente 8 digitos";
            alert.setContentText(aviso);
            alert.showAndWait();
        }
        
    }

    @FXML
    private void Salir(ActionEvent event) {
        Stage myStage = (Stage) this.btn_Regresar.getScene().getWindow();
        myStage.close();
    }
    
    public void Inicializar(ClientesRegistrados clientes){
        this.clientes = clientes;
    }
    
    public Cliente getCliente(){
        return this.cliente;
    }
    
}
