/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author iheb
 */
public class ChoixInscriptionController {



    @FXML
    private void inscriptionMedecin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/InscriptionMedecin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage patientStage = new Stage();
        patientStage.setScene(scene);
        patientStage.setTitle("eldercare");
        patientStage.show();
    }

    @FXML
    private void inscriptionPatient(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/InscriptionPatient.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage patientStage = new Stage();
        patientStage.setScene(scene);
        patientStage.setTitle("eldercare");
        patientStage.show();
    }
    
}
