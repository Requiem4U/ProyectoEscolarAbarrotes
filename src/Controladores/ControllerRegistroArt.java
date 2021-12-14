/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesSistema.Busqueda;
import ClasesSistema.Clientes.ClientesRegistrados;
import ClasesSistema.GuardarArchivos;
import ClasesSistema.GuardarStock;
import ClasesSistema.Productos.Producto;
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
public class ControllerRegistroArt implements Initializable {

    @FXML
    private TableView<Producto> tbl_Productos;
    @FXML
    private TableColumn col_Art_Nombre;
    @FXML
    private TableColumn col_Art_Precio_Publico;
    @FXML
    private TableColumn col_Art_Precio_Proveedor;
    @FXML
    private TableColumn col_Art_Existencias;
    @FXML
    private Button btn_Agregar;
    @FXML
    private Button btn_Modificar;
    @FXML
    private Button btn_Menu;
    @FXML
    private TableColumn col_FormaVenta;
    @FXML
    private TableColumn col_Codigo;
    
    
    private Producto prodSelec;
    private Stock stock;
    private ObservableList<Producto> productos = null;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        productos = FXCollections.observableArrayList();
        this.tbl_Productos.setItems(productos);
        
        this.col_Art_Existencias.setCellValueFactory(new PropertyValueFactory("exisT"));
        this.col_Codigo.setCellValueFactory(new PropertyValueFactory("ID"));
        this.col_Art_Nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.col_Art_Precio_Proveedor.setCellValueFactory(new PropertyValueFactory("precioProveedor"));
        this.col_Art_Precio_Publico.setCellValueFactory(new PropertyValueFactory("precioPublico"));
        this.col_FormaVenta.setCellValueFactory(new PropertyValueFactory("formaVenta"));
        
        
    }    


    @FXML
    private void AgregarProducto(ActionEvent event) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/AgregarArticulo.fxml"));
            Parent root = loader.load();
            
            ControllerAgregarArticulo control = loader.getController();
            control.Inicializar(this.stock);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            Producto producto = control.getProducto();
            if(producto != null){
                this.stock.addProducto(producto);
                this.productos.add(producto);
                this.tbl_Productos.refresh();
                
                GuardarStock save = new GuardarStock(this.stock);
                save.GuardarArchivo(GuardarArchivos.getRuta());
                save.crearCSV(GuardarArchivos.getRuta());
            }
            
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }

    @FXML
    private void ModificarProducto(ActionEvent event) {
        this.prodSelec = this.getProdSelec();
        if (this.prodSelec!=null) {
            try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/ModifArticulo.fxml"));
                Parent root = loader.load();
                
                ControllerModifArticulo control = loader.getController();
                control.Inicializar(this.stock, this.prodSelec);
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                
                Producto prodModif = control.getProductoModif();
                if (prodModif != null) {
                    this.stock.addProducto(this.prodSelec,prodModif);
                    int pos = this.productos.indexOf(this.prodSelec);
                    this.productos.remove(pos);
                    this.productos.add(pos, prodModif);
                    this.tbl_Productos.refresh();
                    
                    GuardarStock save = new GuardarStock(this.stock);
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
            alert.setContentText("Primero debe seleccionar un producto de la tabla");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void Salir(ActionEvent event) {
        Cerrar();
    }
    
    public void Inicializar(Stock stock){
        this.stock = stock;
        ArrayList<Producto> lista = stock.getListaProductos();
        
        for (Producto prod : lista) {
            this.productos.add(prod);
        }
    }
    
    private Producto getProdSelec(){
        return this.tbl_Productos.getSelectionModel().getSelectedItem();
    }
    
    public void Cerrar(){
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Menu.fxml"));
            Parent root = loader.load();
            
            if (this.stock!=null) {
                ControllerMenu control = loader.getController();
                control.Inicializar(this.stock);
                
                Busqueda buscador = new Busqueda();
                ClientesRegistrados cl = buscador.leerClientesRegistrados(GuardarArchivos.getRuta());
                
                if (cl!=null) {
                    control.Inicializar(cl);
                }
                
            }
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            Stage myStage = (Stage) this.btn_Menu.getScene().getWindow();
            myStage.close();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
