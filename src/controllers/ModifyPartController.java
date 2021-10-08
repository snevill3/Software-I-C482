package controllers;

import static controllers.MainScreenController.modifyPart;
import static controllers.MainScreenController.modifyPartIndex;
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
import static models.Inventory.getAllParts;
import static models.Inventory.updatePart;
import models.Outsourced;

/**
 * FXML Controller class
 *
 * @author Shawn
 */
public class ModifyPartController implements Initializable {

    @FXML private RadioButton inHouseRadioButton;
    @FXML private RadioButton outsourcedRadioButton;
    @FXML private TextField modIDTextField;
    @FXML private TextField modNameTextField;
    @FXML private TextField modInventoryTextField;
    @FXML private TextField modPriceTextField;
    @FXML private TextField modMaxTextField;
    @FXML private TextField modMinTextField;
    @FXML private Label machineAndCompanyLabel;
    @FXML private TextField machineAndCompanyTextField;
    private ToggleGroup modPartToggleGroup;
    
    
    /**
     * This method will change label to MachineID if In-House or Company Name 
     * if out source
     */
    public void radioButtionChanged(){
        if(this.modPartToggleGroup.getSelectedToggle().equals(inHouseRadioButton)){
            machineAndCompanyLabel.setText("Machine ID");
            machineAndCompanyTextField.setPromptText("Machine ID");
            machineAndCompanyTextField.setText("");
        }
        if(this.modPartToggleGroup.getSelectedToggle().equals(outsourcedRadioButton)){
            machineAndCompanyLabel.setText("Company Name");
            machineAndCompanyTextField.setPromptText("Company Name");
            machineAndCompanyTextField.setText("");
        }
    }
    
    /**
     * updates Part TableView when save button is pushed
     */
    public void saveButtonPushed(ActionEvent event) throws IOException{
        String partName = modNameTextField.getText();
        int stock = Integer.parseInt(modInventoryTextField.getText());
        Double price = Double.parseDouble(modPriceTextField.getText());
        int max = Integer.parseInt(modMaxTextField.getText());
        int min = Integer.parseInt(modMinTextField.getText());
        
        if(stock < min || stock > max){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Inventory error!");
            alert.setContentText("Please ensure the inventory does not exceed maximum or fall below minimum.");
            alert.showAndWait();
        } else {
            if(this.modPartToggleGroup.getSelectedToggle().equals(inHouseRadioButton)){
            InHouse updatedPart = new InHouse(modifyPartIndex, partName, price, stock, min, max, Integer.parseInt(machineAndCompanyTextField.getText()));
            updatePart(modifyPartIndex, updatedPart);
            }
            if(this.modPartToggleGroup.getSelectedToggle().equals(outsourcedRadioButton)){
                Outsourced updatedPart = new Outsourced(modifyPartIndex, partName, price, stock, min, max, machineAndCompanyTextField.getText());
                updatePart(modifyPartIndex, updatedPart);
            }
            // Return to main scene
            Parent mainSceneViewParent = FXMLLoader.load(getClass().getResource("/views/MainScreenView.fxml"));
            Scene mainViewScene = new Scene(mainSceneViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(mainViewScene);
            window.show();
            }
        
    }
    
    /**
     * Changes Scene back to main scene when cancel button is pushed
     */
    public void canceltButtonPushed(ActionEvent event)throws IOException{
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
        // configure radio buttons
        modPartToggleGroup = new ToggleGroup();
        inHouseRadioButton.setToggleGroup(modPartToggleGroup);
        outsourcedRadioButton.setToggleGroup(modPartToggleGroup);
        
        // Set TextField to item selected from TableView
        modIDTextField.setText("Auto Gen: " + Integer.toString(modifyPart.getPart_id()));
        modNameTextField.setText(modifyPart.getName());
        modInventoryTextField.setText(Integer.toString(modifyPart.getStock()));
        modPriceTextField.setText(Double.toString(modifyPart.getPrice()));
        modMaxTextField.setText(Integer.toString(modifyPart.getMax()));
        modMinTextField.setText(Integer.toString(modifyPart.getMin()));
        //Check Part for Inhouse or Outsourced
        if (modifyPart instanceof InHouse) {
            machineAndCompanyLabel.setText("Machine ID");
            machineAndCompanyTextField.setText(Integer.toString(((InHouse) getAllParts().get(modifyPartIndex)).getMachine_id()));
            inHouseRadioButton.setSelected(true);
        } else {
            machineAndCompanyLabel.setText("Company Name");
            machineAndCompanyTextField.setText(((Outsourced) getAllParts().get(modifyPartIndex)).getCompanyName());
            outsourcedRadioButton.setSelected(true);
        }
    }    
    
}
