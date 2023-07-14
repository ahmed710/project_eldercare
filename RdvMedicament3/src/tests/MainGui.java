/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package tests;
import Controllers.WelcomeController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 *
 * @author DONIG
 */
public class MainGui extends Application {
    
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Welcome.fxml"));
        Parent root = loader.load();
        WelcomeController c = loader.getController() ;
        c.setPrimaryStage(primaryStage);
        
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("../gui/welcome.css").toExternalForm() ;
        scene.getStylesheets().add(css) ; 
        primaryStage.setScene(scene);
        primaryStage.setTitle("Eldercare");
        primaryStage.show();
    }
 public static void main(String[] args) {
        launch(args);
        
    }
    
}
