/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author iheb
 */
public class choixUtilisateurController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }
    @FXML
    private void patientClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/GestionPatient.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage patientStage = new Stage();
        patientStage.setScene(scene);
        patientStage.setTitle("eldercare");
        patientStage.show();
    
    }
    @FXML
    private void medecinClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/GestionMedecin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage patientStage = new Stage();
        patientStage.setScene(scene);
        patientStage.setTitle("eldercare");
        patientStage.show();
        
        
    }

    @FXML
    private void moderatorClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/GestionModerateur.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage patientStage = new Stage();
        patientStage.setScene(scene);
        patientStage.setTitle("eldercare");
        patientStage.show();
    }
}
