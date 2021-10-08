package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Inventory;
import static models.Inventory.deletePart;
import static models.Inventory.deleteProduct;
import static models.Inventory.getAllParts;
import static models.Inventory.getAllProducts;
import static models.Inventory.lookupPart;
import static models.Inventory.lookupProduct;
import models.Part;
import models.Product;

/**
 *
 * @author Shawn
 */
public class MainScreenController implements Initializable {
    
    //these items are for the Parts TableView
    @FXML private TableView<Part> partsTableView;
    @FXML private TableColumn<Part, Integer> partIDColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> partInventoryColumn;
    @FXML private TableColumn<Part, Double> partPriceColumn;
    @FXML private TextField partSearchTextField;
    
    //these items are for the Products TableView
    @FXML private TableView<Product> productsTableView;
    @FXML private TableColumn<Product, Integer> productIDColumn;
    @FXML private TableColumn<Product, String> productNameColumn;
    @FXML private TableColumn<Product, Integer> productInventoryColumn;
    @FXML private TableColumn<Product, Double> productPriceColumn;
    @FXML private TextField productSearchTextField;
    
    public static Part modifyPart;
    public static Product modifyProduct;
    public static int modifyPartIndex;
    public static int modifyProductIndex;
    
    /**
     * Searches Parts in inventory by matching name or ID values
     */
    public void searchPartsButtonPushed(ActionEvent event){
        String userSearch = partSearchTextField.getText();
        ObservableList<Part> tempParts = FXCollections.observableArrayList();
        
        if(isInteger(userSearch)){
            int searchID = Integer.parseInt(userSearch);
            
            if(lookupPart(searchID) == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search Error");
                alert.setHeaderText("Part not found!");
                alert.setContentText("Search term does not match any known parts.");
                alert.showAndWait();
            } else{
                System.out.println(lookupPart(searchID).getName());
                tempParts.add(lookupPart(searchID));
                partsTableView.setItems(tempParts);
            }
        } else if(lookupPart(userSearch).isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Search Error");
                    alert.setHeaderText("Part not found!");
                    alert.setContentText("Search term does not match any known parts.");
                    alert.showAndWait();
                } else {

                    partsTableView.setItems(lookupPart(userSearch));
                }
        
    }
    
    /**
     * Searches products in inventory by matching name or ID values
     */
    public void searchProductsButtonPushed(ActionEvent event){
        String userSearch = productSearchTextField.getText();
        ObservableList<Product> tempProducts = FXCollections.observableArrayList();
        
        if(isInteger(userSearch)){
            int searchID = Integer.parseInt(userSearch);
            
            if(lookupProduct(searchID) == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search Error");
                alert.setHeaderText("Product not found!");
                alert.setContentText("Search term does not match any known products.");
            } else{
                System.out.println(lookupProduct(searchID).getName());
                tempProducts.add(lookupProduct(searchID));
                productsTableView.setItems(tempProducts);
            }
        } else if(lookupProduct(userSearch).isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Search Error");
                    alert.setHeaderText("Product not found!");
                    alert.setContentText("Search term does not match any known products.");
                } else {

                    productsTableView.setItems(lookupProduct(userSearch));
                }
        
    }
    
    /**
     * This method checks if string can be parsed into an integer
     */
    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Changes scene to AddPart Scene when the Add button is pushed.
     */
    public void addPartButtonPushed(ActionEvent event)throws IOException{
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/views/AddPartView.fxml"));
        //create new scene
        Scene addPartViewScene = new Scene(addPartParent);
        //this line gets the Stage information and casts it as a stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartViewScene);
        window.show();
    }
    
    /**
     * Changes scene to ModifyPart Scene when the Modify button is pushed
     */
    public void modifyPartButtonPushed(ActionEvent event)throws IOException{
        if (partsTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No part selected!");
            alert.setContentText("Please ensure a part is selected to be modified.");
            alert.showAndWait();
        } else {
            modifyPart = partsTableView.getSelectionModel().getSelectedItem();
            modifyPartIndex = getAllParts().indexOf(modifyPart);
            //load ModifyPart scene
            Parent addPartParent = FXMLLoader.load(getClass().getResource("/views/ModifyPartView.fxml"));
            Scene modifyPartViewScene = new Scene(addPartParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(modifyPartViewScene);
            window.show();
        }
    }
    
    /**
     * Changes scene to the AddProduct Scene when the add product button is pushed
     */
    public void addProductButtonPushed(ActionEvent event) throws IOException{
        Parent addProductParent = FXMLLoader.load(getClass().getResource("/views/AddProductView.fxml"));
        Scene addProductViewScene = new Scene(addProductParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductViewScene);
        window.show();
    }
    
    /**
     * Changes scene to the ModifyProduct scene when the modify button is pushed
     */
    public void modifyProductButtonPushed(ActionEvent event) throws IOException{
        if (productsTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No product selected!");
            alert.setContentText("Please ensure a product is selected to be modified.");
            alert.showAndWait();
        } else {
            modifyProduct = productsTableView.getSelectionModel().getSelectedItem();
            modifyProductIndex = getAllProducts().indexOf(modifyProduct);
            //change scene to ModifyParts scene
            Parent modifyProductParent = FXMLLoader.load(getClass().getResource("/views/ModifyProductView.fxml"));
            Scene modifyProductScene = new Scene(modifyProductParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(modifyProductScene);
            window.show();
        }
    }
    
    /**
     * deletes the part that is selected in the TableView
     */
    public void deletePartButtonPushed(ActionEvent event){
        if (partsTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No part selected!");
            alert.setContentText("Please ensure a part is selected to be deleted.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Confirm Delete");
            alert.setContentText("Are you sure you want to delete this part?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Part part = partsTableView.getSelectionModel().getSelectedItem();
                deletePart(part);
                partsTableView.setItems(getAllParts());
            }
        }   
    }
    
    /**
     * deletes the part that is selected in the TableView
     */
    public void deleteProductButtonPushed(ActionEvent event){
        if (productsTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No product selected!");
            alert.setContentText("Please ensure a product is selected to be deleted.");
            alert.showAndWait();
        } else {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirm Delete");
                alert.setHeaderText("Confirm Delete");
                alert.setContentText("Are you sure you want to delete this product?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
                    deleteProduct(selectedProduct);
                    productsTableView.setItems(getAllProducts());
                }
        }   
    }
    
    /**
     * This will close the program when the Exit button is pushed
     */
    public void exitButtonPushed(ActionEvent event) throws IOException{
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();
    }
    
    /**
     * This method initializes stuff when scene is loaded.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Parts TableView
        partIDColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPart_id()).asObject());
        partNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        partInventoryColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        partPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        partsTableView.setItems(Inventory.getAllParts());
        // Products TableView
        productIDColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProduct_id()).asObject());
        productNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        productInventoryColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        productPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        productsTableView.setItems(Inventory.getAllProducts());
    }    
    
}
