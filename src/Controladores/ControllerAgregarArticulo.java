/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesSistema.Kilogramo;
import ClasesSistema.Productos.ConstructorProducto;
import ClasesSistema.Productos.DatosProducto;
import ClasesSistema.Productos.Producto;
import ClasesSistema.Productos.Stock;
import ClasesSistema.Unidad;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cmccl
 */
public class ControllerAgregarArticulo implements Initializable {

    @FXML
    private TextField txt_NomArticulo;
    @FXML
    private TextField txt_IdArticulo;
    @FXML
    private TextField txt_PrecioPublico;
    @FXML
    private TextField txt_PrecioProveedor;
    @FXML
    private TextField txt_ExistenciasT;
    @FXML
    private Button btn_Agregar;
    @FXML
    private Button btn_Regresar;
    @FXML
    private RadioButton selec_Unidades;
    @FXML
    private RadioButton selec_Kilos;
    
    
    private Stock stock;
    private Producto producto = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ToggleGroup tg = new ToggleGroup();
        this.selec_Unidades.setToggleGroup(tg);
        this.selec_Kilos.setToggleGroup(tg);
    }    

    @FXML
    private void AgregarArticulo(ActionEvent event) {
        
        try {
            String nom = this.txt_NomArticulo.getText();
            int id = Integer.parseInt(this.txt_IdArticulo.getText());
            float precioPublico = Float.parseFloat(this.txt_PrecioPublico.getText());
            float precioProveedor = Float.parseFloat(this.txt_PrecioProveedor.getText());
            float existencias = Float.parseFloat(this.txt_ExistenciasT.getText());
            
            Producto aux = this.stock.getProducto(id);
            if (aux == null) {
                DatosProducto datos = new DatosProducto();
                datos.setID(id);
                datos.setNombre(nom);
                datos.setPrecioProveedor(precioProveedor);
                datos.setPrecioPublico(precioPublico);
                
                if (this.selec_Unidades.isSelected()) {
                    if ((existencias % 1) == 0) {
                        int exis = (int) existencias;
                        Unidad cant = new Unidad();
                        cant.setCant(exis);
                        ConstructorProducto construc = new ConstructorProducto();
                        this.producto = construc.ConstruirProducto(datos, cant);
                        
                        Stage myStage = (Stage) this.btn_Agregar.getScene().getWindow();
                        myStage.close();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Aviso");
                        String aviso = "La cantidad de existencias ingresada no coinside con la forma de venta";
                        aviso += "\n\nIngrese cantidades enteras, sin punto";
                        aviso += "\nO cambie la forma de venta a 'Kilogramos'";
                        alert.setContentText(aviso);
                        alert.showAndWait();
                    }
                } else if(this.selec_Kilos.isSelected()){
                    Kilogramo cant = new Kilogramo();
                    cant.setCant(existencias);
                    ConstructorProducto construc = new ConstructorProducto();
                    this.producto = construc.ConstruirProducto(datos, cant);
                    
                    Stage myStage = (Stage) this.btn_Agregar.getScene().getWindow();
                    myStage.close();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Aviso");
                    alert.setContentText("Seleccione una forma de venta");
                    alert.showAndWait();
                } 
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Aviso");
                alert.setContentText("El producto ya fue registrado\nEl coidgo ya pertenece a un producto");
                alert.showAndWait();
            }
            
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            String aviso = "Error en el ingreso de los valores...\nVerifique su correcta escritura.";
            aviso += "\n\n☼ Rellene todos los campos antes de registrar el articulo";
            aviso += "\n☼ Precios, existencias y el codigo del producto, deben ser valores numericos";
            aviso += "\n☼ El codigo debe tener exactamente 8 digitos";
            alert.setContentText(aviso);
            alert.showAndWait();
        }
        
    }

    @FXML
    private void Salir(ActionEvent event) {
        Stage myStage = (Stage) this.btn_Regresar.getScene().getWindow();
        myStage.close();
    }
    
    public void Inicializar(Stock stock){
        if (stock != null) {
            this.stock = stock;
        } else {
            this.stock = new Stock();
        }
    }
    
    public Producto getProducto(){
        return  this.producto;
    }
    
    
}
