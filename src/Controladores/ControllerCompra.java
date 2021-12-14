/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesSistema.Clientes.Cliente;
import ClasesSistema.Clientes.ClientesRegistrados;
import ClasesSistema.Compras.Articulo;
import ClasesSistema.Compras.Compra;
import ClasesSistema.Compras.ConstructorArticulo;
import ClasesSistema.Compras.ConstructorCompra;
import ClasesSistema.Compras.DatosArticulo;
import ClasesSistema.GuardarArchivos;
import ClasesSistema.GuardarStock;
import ClasesSistema.Kilogramo;
import ClasesSistema.Productos.DatosProducto;
import ClasesSistema.Productos.Producto;
import ClasesSistema.Productos.Stock;
import ClasesSistema.Unidad;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cmccl
 */
public class ControllerCompra implements Initializable {

    @FXML
    private Button btn_Menu;
    @FXML
    private TextField txt_Cantidad;
    @FXML
    private TextField txt_Codigo;
    @FXML
    private Button btn_Agregar;
    @FXML
    private Button btn_Cobrar;
    @FXML
    private TableView<Articulo> tbl_Compras;
    @FXML
    private TableColumn col_Nombre_Art;
    @FXML
    private TableColumn col_Precio_Art;
    @FXML
    private TableColumn col_Cantidad_Art;
    @FXML
    private TableColumn col_SubTotal;
    @FXML
    private TextField txt_ID_Cliente;
    @FXML
    private TextField txt_Nom_Cliente;
    @FXML
    private Button btn_Buscar_Cliente;
    @FXML
    private Button btn_Remover;
    @FXML
    private Button btn_Cancelar;
    
    
    private ObservableList<Articulo> listaCompras;
    private Stock stock;
    private Stock stockAux;
    private ClientesRegistrados clientes;
    private Cliente cliente;
    private Compra compras;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.listaCompras = FXCollections.observableArrayList();
        this.tbl_Compras.setItems(listaCompras);
        
        this.col_Nombre_Art.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.col_Cantidad_Art.setCellValueFactory(new PropertyValueFactory("cant"));
        this.col_Precio_Art.setCellValueFactory(new PropertyValueFactory("precio"));
        this.col_SubTotal.setCellValueFactory(new PropertyValueFactory("subtotal"));
        
    }    

    
    @FXML
    private void AgreagarArticulo(ActionEvent event) {
        if (this.cliente!=null) {
            Producto producto = null;
            try {
                int id = Integer.parseInt(this.txt_Codigo.getText());
                producto = this.stock.getProducto(id);
                
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Aviso!");
                String aviso = "Ingrese un código valido";
                aviso += "\n\nRellene el campo 'Código del producto' correctaente";
                aviso += "\nDebe ingresar numeros sin puntos ni comas";
                alert.setContentText(aviso);
                alert.showAndWait();
            }
            
            if (producto!=null) {
                
                String nombreProd = producto.getNombre();
                float precioProd = producto.getPrecioPublico();

                DatosArticulo datos = new DatosArticulo();
                datos.setNombre(nombreProd);
                datos.setPrecio(precioProd);
                datos.setCodigo(producto.getID());
               
                Articulo articulo;
                ConstructorArticulo contruc = new ConstructorArticulo();
                if (producto.getFormaVenta().equalsIgnoreCase("Unidades")) {
                    articulo = contruc.ConstruirArtPorUnidades();
                }else {
                    articulo = contruc.ConstruirArtPorKilos();
                }
                articulo.setDatos(datos);
                float cantAux;
                try {
                    cantAux = Float.parseFloat(this.txt_Cantidad.getText());
                    float residuo = Float.parseFloat(producto.getExisT())-cantAux;
                    
                    if (residuo>=0) {
                        float subTotal;
                        DatosProducto dats = producto.getDatos();
                        Producto aux;
                        if (producto.getFormaVenta().equalsIgnoreCase("Unidades")) {
                            if ((cantAux % 1) == 0) {
                                Unidad cant = new Unidad();
                                cant.setCant((int) cantAux);
                                articulo.setCantidad(cant);
                                subTotal = cantAux * articulo.getDatos().getPrecio();
                                articulo.setSubtotal(subTotal);
                                
                                Unidad cant2 = new Unidad();
                                cant2.setCant((int)residuo);
                                aux = new Producto(dats, cant2);
                                this.stock.addProducto(producto, aux);
                                
                                this.txt_Cantidad.setText("");
                                this.txt_Codigo.setText("");
                                this.compras.addArticulo(articulo);
                                this.listaCompras.add(articulo);
                                this.tbl_Compras.refresh();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setHeaderText(null);
                                alert.setTitle("Aviso!");
                                String aviso = "Ingrese una cantidad valido.";
                                aviso += "\n\nEl producto se vende por unidades.";
                                aviso += "\nDebe ingresar numeros enteros, sin decimales.";
                                alert.setContentText(aviso);
                                alert.showAndWait();
                            }
                        } else {
                            Kilogramo cant = new Kilogramo();
                            cant.setCant(cantAux);
                            articulo.setCantidad(cant);
                            subTotal = cantAux * articulo.getDatos().getPrecio();
                            articulo.setSubtotal(subTotal);
                            
                            Kilogramo cant2 = new Kilogramo();
                            cant2.setCant(residuo);
                            aux = new Producto(dats, cant2);
                            this.stock.addProducto(producto, aux);
                                
                            this.compras.addArticulo(articulo);
                            this.listaCompras.add(articulo);
                            this.tbl_Compras.refresh();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Aviso!");
                        alert.setContentText("No hay existencias suficientes");
                        alert.showAndWait();
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("Aviso!");
                    String aviso = "Ingrese una cantidad valida.";
                    aviso += "\nDebe ingresar numeros sin espacios ni caracteres especiales.";
                    alert.setContentText(aviso);
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Aviso!");
                alert.setContentText("El producto no existe\n\nVerifique el código");
                alert.showAndWait();
            }
            
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Aviso!");
            String aviso = "Seleccione a un cliente.";
            aviso += "\n\nBusque a un cliente mediante su ID.";
            alert.setContentText(aviso);
            alert.showAndWait();
            
        }
        
    }

    @FXML
    private void RemoverArticulo(ActionEvent event) {
        try {
            int id = Integer.parseInt(this.txt_Codigo.getText());
            Articulo articulo=null;
            for (Articulo aux : this.listaCompras) {
                if(aux.getDatos().getCodigo() == id){
                    articulo = aux;
                    Producto prod = this.stock.getProducto(id);
                    DatosProducto datos = this.stock.getProducto(id).getDatos();
                    if(prod.getFormaVenta().equalsIgnoreCase("Unidades")){
                        Unidad cant = new Unidad();
                        int e = Integer.parseInt(prod.getExisT())+ Integer.parseInt(aux.getCant());
                        cant.setCant(e);
                        Producto nuevo = new Producto(datos, cant);
                        this.stock.addProducto(prod, nuevo);
                    }else{
                        Kilogramo cant = new Kilogramo();
                        float e = Float.parseFloat(prod.getExisT()) + Float.parseFloat(aux.getCant());
                        cant.setCant(e);
                        Producto nuevo = new Producto(datos, cant);
                        this.stock.addProducto(prod, nuevo);
                    }
                }
            }
            
            if (articulo != null) {
                this.compras.removeArticulo(articulo);
                this.listaCompras.remove(articulo);
                this.tbl_Compras.refresh();
                this.txt_Codigo.setText("");
                this.txt_Cantidad.setText("");
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Aviso!");
                alert.setContentText("El articulo aún no esta en la lista de compras");
                alert.showAndWait();
            }
            
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Aviso!");
            String aviso = "Ingrese un código valido";
            aviso += "\n\nRellene el campo 'Código del producto' correctaente";
            aviso += "\nDebe ingresar numeros sin puntos ni comas";
            alert.setContentText(aviso);
            alert.showAndWait();
        }
        
        
    }

    @FXML
    private void CancelarCompra(ActionEvent event) {
        int size = this.listaCompras.size();
        this.listaCompras.remove(0, size);
        this.tbl_Compras.refresh();
        this.compras = null;
        this.cliente = null;
        this.txt_ID_Cliente.setText("");
        this.txt_ID_Cliente.setEditable(true);
        this.txt_Nom_Cliente.setText("");
        
        this.stock = this.stockAux;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Exito");
        alert.setContentText("Compra cancelada");
        alert.showAndWait();
    }

    @FXML
    private void Cobrar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Cobro.fxml"));
            Parent root = loader.load();
            
            ControllerCobro control = loader.getController();
            control.Inicializar(this.compras);
            this.txt_Cantidad.setText("");
            this.txt_Codigo.setText("");
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            this.stockAux = this.stock;
            this.cliente = null;
            this.compras = null;
            this.txt_Cantidad.setText("");
            this.txt_Codigo.setText("");
            this.txt_ID_Cliente.setText("");
            this.txt_Nom_Cliente.setText("");
            int size = this.listaCompras.size();
            this.listaCompras.remove(0, size);
            this.tbl_Compras.refresh();
            GuardarStock save = new GuardarStock(this.stock);
            save.GuardarArchivo(GuardarArchivos.getRuta());
            save.crearCSV(GuardarArchivos.getRuta());
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }

    @FXML
    private void BuscarCliente(ActionEvent event) {
        if (this.txt_ID_Cliente.isEditable()) {
            try {
                int id = Integer.parseInt(this.txt_ID_Cliente.getText());
                this.cliente = this.clientes.getCliente(id);
                
                if (this.cliente != null) {
                    this.txt_ID_Cliente.setText(String.valueOf(id));
                    this.txt_ID_Cliente.setEditable(false);
                    this.txt_Nom_Cliente.setText(this.cliente.getNom()+" "+this.cliente.getAp());
                    ConstructorCompra construc = new ConstructorCompra();
                    this.compras = construc.Construir(this.cliente);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("Aviso!");
                    alert.setContentText("El cliente no existe");
                    alert.showAndWait();
                }
                
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Aviso!");
                alert.setContentText("Debe ingresar el ID del cliente primero");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Aviso!");
            String aviso = "Ya se ha ingresado un cliente";
            aviso += "\nCancele la compra actual para ingresar un nuevo cliente";
            alert.setContentText(aviso);
            alert.showAndWait();
        }
    }
    
    
    @FXML
    private void Salir(ActionEvent event) {
        if (this.compras != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Aviso!");
            alert.setContentText("Hay una compra en curso\n¿Desea cancelarla?");
            alert.showAndWait();
            this.stock = this.stockAux;
            Cerrar();
        }else{
            Cerrar();
        }
        
    }
    
    
    public void Inicializar(Stock stock, ClientesRegistrados clientes){
        this.clientes = clientes;
        this.stock = stock;
        this.stockAux = stock;
    }
    
    public void Cerrar(){
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Menu.fxml"));
            Parent root = loader.load();
            
            if (this.stock!=null) {
                ControllerMenu control = loader.getController();
                control.Inicializar(this.stockAux);
                control.Inicializar(this.clientes);
                
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
