/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_eldercare.test;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projet_eldercare.gui.WelcomeGUI.WelcomeController;

/**
 *
 * @author 21693
 */
public class TestGUI extends Application{
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/WelcomeGUI/Welcome1.fxml"));
        Parent root = loader.load();
        
        WelcomeController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        
        Scene scene = new Scene(root) ; 
        String css = this.getClass().getResource("../gui/WelcomeGUI/welcome.css").toExternalForm() ; 
        scene.getStylesheets().add(css); 
        primaryStage.setScene(scene);
        primaryStage.setTitle("ELDERCARE");
        primaryStage.show(); 

  
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
