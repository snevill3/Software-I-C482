package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Shawn
 */
public class InventoryManagementSystem extends Application {
    
    @Override
    public void start(Stage mainStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MainScreenView.fxml"));
        Scene scene = new Scene(root);
        Image appIcon = new Image("/main/inventory.png");
        mainStage.setTitle("Inventory Management System");
        mainStage.getIcons().add(appIcon);
        mainStage.setScene(scene);
        mainStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
