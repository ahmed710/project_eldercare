/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Services.UserService;
import entities.Patient;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import utils.DataSource;



/**
 *
 * @author iheb
 */
public class InscriptionPatientController implements Initializable{

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
    private Spinner<Double> tfPoidsSpinner;
    @FXML
    private Spinner<Integer> tftaillespinner;
    @FXML
    private PasswordField tfmotdepasse;
    
    private Double valueP;
    private int taille;
    private Connection cnx = DataSource.getinstance().getCnx();
    UserService us = new UserService();
    @FXML
    private PasswordField tfmotdepassconfirmation;
    @FXML
    private Label mdpconf1;
    @FXML
    private Label mdpconf2;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //togglegroup pour éviter le choix multiple
        ToggleGroup sexegrp = new ToggleGroup();
        sexegrp.getToggles().addAll(rbFemme,rbHomme);
        // le poids
        SpinnerValueFactory<Double> poidsSpinner = new SpinnerValueFactory.DoubleSpinnerValueFactory(40, 200, 70, 0.1);
        tfPoidsSpinner.setValueFactory(poidsSpinner);
        valueP=tfPoidsSpinner.getValue();
        tfPoidsSpinner.valueProperty().addListener(new ChangeListener<Double>() {
            @Override
            public void changed(ObservableValue<? extends Double> observable, Double oldValue, Double newValue) {
            tfPoidsSpinner.setValueFactory(poidsSpinner);
            valueP=tfPoidsSpinner.getValue();            
            }
        });

        // la taille
        SpinnerValueFactory<Integer> tailleSpinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(140, 250,170);
        tftaillespinner.setValueFactory(tailleSpinner);
        taille = tftaillespinner.getValue();
        tftaillespinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                tftaillespinner.setValueFactory(tailleSpinner);
                taille = tftaillespinner.getValue();            }
        });   
    mdpconf1.setVisible(false);
    mdpconf2.setVisible(false);
    }

    @FXML
    private void inscription(ActionEvent event) {
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String dateNaissance = datePicker.getValue() != null ? datePicker.getValue().toString() : "";
        String sexe = rbHomme.isSelected() ? "Homme" : "Femme";
        String adresse = tfAdresse.getText();
        String email = tfEmail.getText();
        float poids = valueP != null ? valueP.floatValue() : 0.0f;
        String motdepasse =tfmotdepasse.getText();
        String confirmationmotdepasse =tfmotdepassconfirmation.getText();
        


        
        if (prenom.isEmpty() || nom.isEmpty() || dateNaissance.isEmpty()|| sexe.isEmpty()|| adresse.isEmpty()|| email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        } else {
            
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
                us.ajouter(new Patient(nom, prenom, sexe, email, poids, taille, adresse, dateNaissance,motdepasse));
            }
    }
    
    
}

    @FXML
    private void insererphoto(ActionEvent event) {
    FileChooser choisirimage = new FileChooser();
    choisirimage.setTitle("Choisir une image");
    choisirimage.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg", "*.gif"));
    File selectedFile = choisirimage.showOpenDialog(new Stage());
    if (selectedFile != null) {
        // path de l'image a enregistrer
        String destinationDirectory = "C:/xampp/htdocs/PI/photo patient";
        // Generate a unique name for the image (optional)
        String uniqueName = generateUniqueName(selectedFile.getName());
        Path destinationPath = Paths.get(destinationDirectory, uniqueName);
        try {
            Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("image");
            alert.setHeaderText(null);
            alert.setContentText("Photo enregistrer avec succées");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("image");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier l'etat de votre photo");    
        }
    }
}

    private String generateUniqueName(String fileName) {

        return tfNom.getText()+"-"+tfPrenom.getText()+"-Patient.jpg";
    }


    
}
