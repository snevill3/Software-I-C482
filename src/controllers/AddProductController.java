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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Inventory;
import static models.Inventory.addProduct;
import static models.Inventory.lookupPart;
import models.Part;
import models.Product;

/**
 * FXML Controller class
 *
 * @author Shawn
 */
public class AddProductController implements Initializable {

    //These items are for product TextFields
    @FXML private TextField productNameTextField;
    @FXML private TextField productInventoryTextField;
    @FXML private TextField productPriceTextField;
    @FXML private TextField productMaxTextField;
    @FXML private TextField productMinTextField;
    
    //these items are for the available parts TableView
    @FXML private TableView<Part> availPartsTableView;
    @FXML private TableColumn<Part, Integer> availPartIDColumn;
    @FXML private TableColumn<Part, String> availPartNameColumn;
    @FXML private TableColumn<Part, Integer> availPartInventoryColumn;
    @FXML private TableColumn<Part, Double> availPartPriceColumn;
    @FXML private TextField partSearchTextField;
    
    //these items are for the comprised parts TableView
    @FXML private TableView<Part> compPartsTableView;
    @FXML private TableColumn<Part, Integer> compPartIDColumn;
    @FXML private TableColumn<Part, String> compPartNameColumn;
    @FXML private TableColumn<Part, Integer> compPartInventoryColumn;
    @FXML private TableColumn<Part, Double> compPartPriceColumn;
    private ObservableList<Part> productParts = FXCollections.observableArrayList();
    
    public static int productIDCount = 1;
    
    /**
     * This method searches available parts by partName or PartID
     */
    public void searchPartButtonPushed(){
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
                availPartsTableView.setItems(tempParts);
            }
        } else if(lookupPart(userSearch).isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Search Error");
                    alert.setHeaderText("Part not found!");
                    alert.setContentText("Search term does not match any known parts.");
                    alert.showAndWait();
                } else {

                    availPartsTableView.setItems(lookupPart(userSearch));
                }
    }
    
    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * This method will add selected available part to list of parts 
     * that make up the product
     */
    public void addPartButtonPushed(ActionEvent event){
        if (availPartsTableView.getSelectionModel().getSelectedItem() == null) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No part selected!");
            alert.setContentText("Please ensure a part is selected to be added.");
            alert.showAndWait();
        } else {
            Part selectedPart = availPartsTableView.getSelectionModel().getSelectedItem();
            productParts.add(selectedPart);
            compPartsTableView.setItems(productParts);
        }
    }
    
    /**
     * This method will remove selected part from the list of parts 
     * that make up the product
     */
    public void deletePartButtonPushed(ActionEvent event){
        if (compPartsTableView.getSelectionModel().getSelectedItem() == null) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No part selected!");
            alert.setContentText("Please ensure a part is selected to be removed from product.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Confirm Delete");
            alert.setContentText("Are you sure you want to remove this part?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Part selectedPart = compPartsTableView.getSelectionModel().getSelectedItem();
                productParts.remove(selectedPart);
                compPartsTableView.setItems(productParts);
            }
        }
    }
    
    /**
     * This method will save product to list of products in inventory
     */
    public void saveProductButtonPushed(ActionEvent event) throws IOException{
        String productName = productNameTextField.getText();
        int productStock = Integer.parseInt(productInventoryTextField.getText());
        Double productPrice = Double.parseDouble(productPriceTextField.getText());
        int max = Integer.parseInt(productMaxTextField.getText());
        int min = Integer.parseInt(productMinTextField.getText());
        
        if(productStock < min || productStock > max){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Inventory error!");
            alert.setContentText("Please ensure the inventory does not exceed maximum or fall below minimum.");
            alert.showAndWait();
        } else{
            Product newProduct = new Product(productIDCount, productName, productPrice, productStock, min, max);
            addProduct(newProduct);
            newProduct.associatedParts = productParts;
            productIDCount++;
            //return to main scene
            Parent mainSceneViewParent = FXMLLoader.load(getClass().getResource("/views/MainScreenView.fxml"));
            Scene mainViewScene = new Scene(mainSceneViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(mainViewScene);
            window.show();
        }
        
    }
    
    /**
     * This method returns to the main scene when the user pushes the
     * cancel button
     */
    public void cancelButtonPushed(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancelation");
        alert.setHeaderText("Are you sure you want to cancel?");
        alert.setContentText("Information not saved will be lost!");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
            Parent mainSceneViewParent = FXMLLoader.load(getClass().getResource("/views/MainScreenView.fxml"));
            Scene mainViewScene = new Scene(mainSceneViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(mainViewScene);
            window.show();
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //populate available parts TableView
        availPartIDColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPart_id()).asObject());
        availPartNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        availPartInventoryColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        availPartPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        availPartsTableView.setItems(Inventory.getAllParts());
        //populate product parts TableView
        compPartIDColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPart_id()).asObject());
        compPartNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        compPartInventoryColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        compPartPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        compPartsTableView.setItems(productParts);
    }    
    
}
