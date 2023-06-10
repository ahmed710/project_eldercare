/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Services.UserService;
import entities.Medecin;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author iheb
 */
public class InscriptionMedecinController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private DatePicker datePicker;
    @FXML
    private RadioButton rbHomme;
    @FXML
    private RadioButton rbFemme;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfEmail;
    @FXML
    private ComboBox<String> cbspecialite;
    @FXML
    private PasswordField tfmotdepasse;
    
    private String[] specList={"chirurgie plastique","ophtalmologie","dermatologie","cardiovasculaire","reconstructrice et esthétique","Anesthésie-réanimation","Radiologie et imagerie médicale","Neurologie","Rhumatologie","Pédiatrie","Médecine générale","Psychiatrie"};
    Connection cnx = DataSource.getinstance().getCnx();
    UserService us = new UserService();
    @FXML
    private PasswordField tfmotdepassconfirmation;
    @FXML
    private Label mdpconf1;
    @FXML
    private Label mdpconf2;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mdpconf1.setVisible(false);
        mdpconf2.setVisible(false);    }    

    @FXML
    private void inscription(ActionEvent event) {
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String sexe = rbHomme.isSelected() ? "Homme" : "Femme";
        String adresse = tfAdresse.getText();
        String email = tfEmail.getText();
        String specialite = cbspecialite.getValue();
        String motdepasse =tfmotdepasse.getText();
        String confirmationmotdepasse =tfmotdepassconfirmation.getText();


        if (prenom.isEmpty() || nom.isEmpty() || sexe.isEmpty()|| adresse.isEmpty()|| email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        }
        else{
            if (!(motdepasse.equals(confirmationmotdepasse))) {
                mdpconf1.setVisible(true);
                mdpconf2.setVisible(true);
            }
            else{
                Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationDialog.setTitle("Confirmation");
                confirmationDialog.setHeaderText("Confirmation");
                confirmationDialog.setContentText("Êtes-vous sûr de vouloir ajouter ce patient?");
                confirmationDialog.initOwner(tfNom.getScene().getWindow());
                confirmationDialog.showAndWait();
                mdpconf1.setVisible(false);
                mdpconf2.setVisible(false);
                if (confirmationDialog.getResult() == ButtonType.OK)
                    us.ajouter(new Medecin(nom, prenom, sexe, email, specialite, adresse,motdepasse));
            }
        }
        

    
    }
}
    
