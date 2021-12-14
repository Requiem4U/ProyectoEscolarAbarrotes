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
public class ControllerModifArticulo implements Initializable {

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
    private Button btn_Modificar;
    @FXML
    private Button btn_Regresar;
    @FXML
    private RadioButton selec_Unidades;
    @FXML
    private RadioButton selec_Kilos;
    
    
    private Producto prodSelec;
    private Producto prodModif;
    private Stock stock;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ToggleGroup tg = new ToggleGroup();
        this.selec_Kilos.setToggleGroup(tg);
        this.selec_Unidades.setToggleGroup(tg);
        
    }    

    @FXML
    private void ModificarArticulo(ActionEvent event) {
        
        try {
            String nom = this.txt_NomArticulo.getText();
            int id = this.prodSelec.getID();
            float precioPublico = Float.parseFloat(this.txt_PrecioPublico.getText());
            float precioProveedor = Float.parseFloat(this.txt_PrecioProveedor.getText());
            float existencias = Float.parseFloat(this.txt_ExistenciasT.getText());
            
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
                        this.prodModif = construc.ConstruirProducto(datos, cant);
                        
                        Stage myStage = (Stage) this.btn_Modificar.getScene().getWindow();
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
                    this.prodModif = construc.ConstruirProducto(datos, cant);
                    
                    Stage myStage = (Stage) this.btn_Modificar.getScene().getWindow();
                    myStage.close();
                }

            Stage myStage = (Stage) this.btn_Modificar.getScene().getWindow();
            myStage.close();
            
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
    
    public void Inicializar(Stock stock, Producto prodSelec){
        this.prodSelec = prodSelec;
        this.stock = stock;
        
        this.txt_ExistenciasT.setText(prodSelec.getExisT());
        this.txt_IdArticulo.setText(String.valueOf(prodSelec.getID()));
        this.txt_NomArticulo.setText(prodSelec.getNombre());
        this.txt_PrecioProveedor.setText(String.valueOf(prodSelec.getPrecioProveedor()));
        this.txt_PrecioPublico.setText(String.valueOf(prodSelec.getPrecioPublico()));
        
        if(prodSelec.getFormaVenta().equalsIgnoreCase("Unidades")){
            this.selec_Unidades.setSelected(true);
        }else{
            this.selec_Kilos.setSelected(true);
        }
        
        
    }
    
    public Producto getProductoModif(){
        return  this.prodModif;
    }
    
}
