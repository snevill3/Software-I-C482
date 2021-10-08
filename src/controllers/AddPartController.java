package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import models.InHouse;
import static models.Inventory.addPart;
import models.Outsourced;

/**
 * FXML Controller class
 *
 * @author Shawn
 */
public class AddPartController implements Initializable {

    @FXML private RadioButton inHouseRadioButton;
    @FXML private RadioButton outSourcedRadioButton;
    @FXML private Label machineAndCompanyLabel;
    @FXML private TextField machineAndCompanyTextField;
    @FXML private TextField partNameTextField;
    @FXML private TextField inventoryTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField maxTextField;
    @FXML private TextField minTextField;
    @FXML private TextField partIDTextField;
    private ToggleGroup addPartToggleGroup;
    public static int partIDCount = 1;
    
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
     * This method will change label to MachineID if In-House or Company Name 
     * if out source
     */
    public void radioButtionChanged(){
        if(this.addPartToggleGroup.getSelectedToggle().equals(inHouseRadioButton)){
            machineAndCompanyLabel.setText("Machine ID");
            machineAndCompanyTextField.setPromptText("Machine ID");
        }
        if(this.addPartToggleGroup.getSelectedToggle().equals(outSourcedRadioButton)){
            machineAndCompanyLabel.setText("Company Name");
            machineAndCompanyTextField.setPromptText("Company Name");
        }
    }
    
    /**
     * 
     */
    public void saveButtonPushed(ActionEvent event) throws IOException{
        String partName = partNameTextField.getText();
        int stock = Integer.parseInt(inventoryTextField.getText());
        Double price = Double.parseDouble(priceTextField.getText());
        int max = Integer.parseInt(maxTextField.getText());
        int min = Integer.parseInt(minTextField.getText());
        
        if(stock < min || stock > max){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Inventory error!");
            alert.setContentText("Please ensure the inventory does not exceed maximum or fall below minimum.");
            alert.showAndWait();
        } else {
            if(this.addPartToggleGroup.getSelectedToggle().equals(inHouseRadioButton)){
            InHouse newPart = new InHouse(partIDCount, partName, price, stock, min, max, Integer.parseInt(machineAndCompanyTextField.getText()));
            addPart(newPart);
            }
            if(this.addPartToggleGroup.getSelectedToggle().equals(outSourcedRadioButton)){
                Outsourced newPart = new Outsourced(partIDCount, partName, price, stock, min, max, machineAndCompanyTextField.getText());
                addPart(newPart);
            }

            partIDCount++;
            // Return to main scene
            Parent mainSceneViewParent = FXMLLoader.load(getClass().getResource("/views/MainScreenView.fxml"));
            Scene mainViewScene = new Scene(mainSceneViewParent);
            //this line gets the Stage information
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
        // configure radio buttons
        addPartToggleGroup = new ToggleGroup();
        inHouseRadioButton.setToggleGroup(addPartToggleGroup);
        outSourcedRadioButton.setToggleGroup(addPartToggleGroup);
        addPartToggleGroup.selectToggle(inHouseRadioButton);
    }    
    
}
