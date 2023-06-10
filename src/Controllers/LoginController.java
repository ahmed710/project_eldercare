/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import org.mindrot.jbcrypt.BCrypt;
import Services.UserService;
import entities.Medecin;
import entities.Moderateur;
import entities.Patient;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author iheb
 */
public class LoginController implements Initializable{

    @FXML
    private TextField tfEmaillogin;
    @FXML
    private TextField tfMotdepasselogin;
    UserService us = new UserService();
    List<User> listUser = new ArrayList(us.afficher());
    @FXML
    private RadioButton patient;
    @FXML
    private RadioButton medecin;
    @FXML
    private Label mailmdpProb;  
    @FXML
    private CheckBox affichermotdepasse;
    @FXML
    private TextField affichagemotdepasse;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup selectiongrp = new ToggleGroup();
        selectiongrp.getToggles().addAll(patient,medecin);
        mailmdpProb.setVisible(false);
        //affichage mot de passe avec checkbutton
        affichagemotdepasse.setVisible(false);
    affichermotdepasse.setOnAction(event -> {
        if (affichermotdepasse.isSelected()) {
            affichagemotdepasse.setText(tfMotdepasselogin.getText());
            affichagemotdepasse.setVisible(true);
            tfMotdepasselogin.setVisible(false);
        } else {
            tfMotdepasselogin.setText(affichagemotdepasse.getText());
            tfMotdepasselogin.setVisible(true);
            affichagemotdepasse.setVisible(false);

        }
    });
        
    }
    @FXML
    private void seConnecter(ActionEvent event) throws IOException {
        

        String email =tfEmaillogin.getText();
        String motDePasse = tfMotdepasselogin.getText();
        User e = null;
        if(!(email.isEmpty() || motDePasse.isEmpty())){
            for(User u : listUser){
                if(u.getEmail().equals(email) && verifierMotDePasse(motDePasse,u.getMotdepasse()))
                    e=u;
            }
            if(e==null){
                mailmdpProb.setVisible(true);
            }
            if(e instanceof Patient){
                System.out.println("patient");
                mailmdpProb.setVisible(false);
                tfMotdepasselogin.setText("");

                }

            if(e instanceof Medecin){
                System.out.println("medecin");
                mailmdpProb.setVisible(false);
                tfMotdepasselogin.setText("");


            }

            if(e instanceof Moderateur){
                System.out.println("moderateur");
                tfMotdepasselogin.setText("");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/choixUtilisateur.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage patientStage = new Stage();
                patientStage.setScene(scene);
                patientStage.setTitle("eldercare");
                patientStage.show();
            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        }
            
    }        
            

    @FXML
    private void inscription(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ChoixInscription.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage patientStage = new Stage();
        patientStage.setScene(scene);
        patientStage.setTitle("eldercare");
        patientStage.show();

    }

    @FXML
    private void motDePasseOublier(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/CodeMotDePasseOublier.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage patientStage = new Stage();
        patientStage.setScene(scene);
        patientStage.setTitle("eldercare");
        patientStage.show();
    }
    
    public boolean verifierMotDePasse(String motDePasseNormale, String motDePasseHache){
        return BCrypt.checkpw(motDePasseNormale, motDePasseHache);
    }

    
}


