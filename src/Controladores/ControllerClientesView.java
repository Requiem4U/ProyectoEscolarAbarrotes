/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesSistema.Busqueda;
import ClasesSistema.Clientes.Cliente;
import ClasesSistema.Clientes.ClientesRegistrados;
import ClasesSistema.GuardarArchivos;
import ClasesSistema.GuardarClientes;
import ClasesSistema.Productos.Stock;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cmccl
 */
public class ControllerClientesView implements Initializable {

    @FXML
    private TableView<Cliente> tbl_Clientes;
    @FXML
    private TableColumn col_Nombre;
    @FXML
    private TableColumn col_Apellido;
    @FXML
    private TableColumn col_Id;
    @FXML
    private TableColumn col_Direccion;
    @FXML
    private Button btn_Agregar;
    @FXML
    private Button btn_ModificarCliente;
    @FXML
    private Button btn_Menu;
    
    private ObservableList<Cliente> listaClientes;
    private ClientesRegistrados clientes;
    private Cliente clieneteSelec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        listaClientes = FXCollections.observableArrayList();
        this.tbl_Clientes.setItems(listaClientes);
        
        this.col_Nombre.setCellValueFactory(new PropertyValueFactory("nom"));
        this.col_Apellido.setCellValueFactory(new PropertyValueFactory("ap"));
        this.col_Id.setCellValueFactory(new PropertyValueFactory("IDCliente"));
        this.col_Direccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        
        
    }    

    @FXML
    private void AgregarCliente(ActionEvent event) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/RegistrarCliente.fxml"));
            Parent root = loader.load();
            
            ControllerRegistrarCliente control = loader.getController();
            control.Inicializar(this.clientes);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            Cliente cliente = control.getCliente();
            if (cliente!=null) {
                this.clientes.addCliente(cliente);
                this.listaClientes.add(cliente);
                this.tbl_Clientes.refresh();
                
                GuardarClientes save = new GuardarClientes();
                save.setRegistroClientes(this.clientes);
                save.GuardarArchivo(GuardarArchivos.getRuta());
                save.crearCSV(GuardarArchivos.getRuta());
            }
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }

    @FXML
    private void ModificarCliente(ActionEvent event) {
        
        this.clieneteSelec = this.getClienteSelec();
        if (this.clieneteSelec!=null) {
            try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/ModifCliente.fxml"));
                Parent root = loader.load();
                
                ControllerModifCliente control = loader.getController();
                control.Inicializar(this.clientes, this.clieneteSelec);
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                
                Cliente cliente = control.getClienteModif();
                if (cliente != null) {
                    this.clientes.modifCliente(this.clieneteSelec, cliente);
                    int pos = this.listaClientes.indexOf(this.clieneteSelec);
                    this.listaClientes.remove(pos);
                    this.listaClientes.add(pos,cliente);
                    this.tbl_Clientes.refresh();
                    
                    GuardarClientes save = new GuardarClientes();
                    save.setRegistroClientes(this.clientes);
                    save.GuardarArchivo(GuardarArchivos.getRuta());
                    save.crearCSV(GuardarArchivos.getRuta());
                }
                
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Primero debe seleccionar un cliente de la tabla");
            alert.showAndWait();
        }
    }

    @FXML
    private void Salir(ActionEvent event) {
        Cerrar();
    }
    
    public void Inicializar(ClientesRegistrados clientes){
        this.clientes = clientes;
        
        ArrayList<Cliente> lista = clientes.getListaClientes();
        for (Cliente client : lista) {
            this.listaClientes.add(client);
        }
    }
    
    private Cliente getClienteSelec(){
        return this.tbl_Clientes.getSelectionModel().getSelectedItem();
    }
    
    public void Cerrar(){
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Menu.fxml"));
            Parent root = loader.load();
            
            if (this.clientes!=null) {
                ControllerMenu control = loader.getController();
                control.Inicializar(this.clientes);
                
                Busqueda buscador = new Busqueda();
                Stock stock = buscador.leerStock(GuardarArchivos.getRuta());
                
                if (stock!=null) {
                    control.Inicializar(stock);
                }
            }
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            Stage myStage = (Stage) this.btn_Menu.getScene().getWindow();
            myStage.close();
            
        } catch (IOException e) {
        }
    }
    
    
}
