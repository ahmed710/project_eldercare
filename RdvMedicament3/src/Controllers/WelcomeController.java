/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Controllers.AjoutMedicamentController;
import Controllers.AjoutRdvController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DONIG
 */
public class WelcomeController implements Initializable {
    
    private Stage primaryStage;
    
    public void setPrimaryStage(Stage primaryStage){
        
        this.primaryStage= primaryStage;
    }

    @FXML
    private Button idMedicament;
    @FXML
    private Button idRdv;
    @FXML
    private Button idStock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void accederMedicament(ActionEvent event)throws IOException{
        
       Parent root2 = FXMLLoader .load(getClass().getResource("../gui/AjoutMedicament.fxml"));
    Stage window = (Stage) idMedicament.getScene().getWindow();
    window.setScene(new Scene(root2));

    }

    @FXML
    private void accederRdv(ActionEvent event) throws IOException {
     
    Parent root2 = FXMLLoader .load(getClass().getResource("../gui/AjoutRdv.fxml"));
    Stage window = (Stage) idRdv.getScene().getWindow();
    window.setScene(new Scene(root2));

    }

    @FXML
    private void accederStock(ActionEvent event) throws IOException {
           Parent root2 = FXMLLoader .load(getClass().getResource("../gui/AjoutStock.fxml"));

      Stage window = (Stage) idStock.getScene().getWindow();
    window.setScene(new Scene(root2));

    }
    
}
