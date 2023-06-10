/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Services.UserService;
import entities.Moderateur;
import entities.Patient;
import entities.User;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import utils.DataSource;

/**
 *
 * @author iheb
 */
public class AjoutModerateurController implements Initializable{

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private RadioButton rbHomme;
    @FXML
    private RadioButton rbFemme;
    @FXML
    private TableColumn<Moderateur, String> nomcol;
    @FXML
    private TableColumn<Moderateur, String> prenomcol;
    @FXML
    private TableColumn<Moderateur, String> sexecol;
    @FXML
    private TableColumn<Moderateur, String> emailcol;
    
    private Connection cnx = DataSource.getinstance().getCnx();
    UserService us = new UserService();
    @FXML
    private TableView<User> tfaffichage;
    @FXML
    private PasswordField tfmotdepasse;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfaffichage.setEditable(true);
        nomcol.setCellFactory(TextFieldTableCell.forTableColumn());
        nomcol.setOnEditCommit(this::modifierNom);
        
        prenomcol.setCellFactory(TextFieldTableCell.forTableColumn());
        prenomcol.setOnEditCommit(this::modifierPrenom);
        
        sexecol.setCellFactory(TextFieldTableCell.forTableColumn());
        sexecol.setOnEditCommit(this::modifierSexe);
        
        emailcol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailcol.setOnEditCommit(this::modifierEmail);
        tableview();


    }
    @FXML
    private void AjouterModerateur(ActionEvent event) {
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String sexe = rbHomme.isSelected() ? "Homme" : "Femme";
        String email = tfEmail.getText();
        String motdepasse =tfmotdepasse.getText();


        
        if (prenom.isEmpty() || nom.isEmpty() ||  sexe.isEmpty()|| email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        } else {
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation");
            confirmationDialog.setHeaderText("Confirmation");
            confirmationDialog.setContentText("Êtes-vous sûr de vouloir ajouter ce patient?");
            confirmationDialog.initOwner(tfNom.getScene().getWindow());
            confirmationDialog.showAndWait();

                if (confirmationDialog.getResult() == ButtonType.OK) {
                us.ajouter(new Moderateur(nom, prenom, sexe, email,motdepasse));
                // Réinitialiser les champs après l'ajout de patient
                tfNom.setText("");
                tfPrenom.setText("");
                rbHomme.setSelected(false);
                rbFemme.setSelected(false);
                tfEmail.setText("");
                tfmotdepasse.setText("");
                tableview();
        }

    }

                

        tfNom.setText("");
        tfPrenom.setText("");
        rbHomme.setSelected(false);
        rbFemme.setSelected(false);
        tfEmail.setText("");
        tableview();
    }

    @FXML
    private void SupprimerModerateur(ActionEvent event) {
        User selectedUser = tfaffichage.getSelectionModel().getSelectedItem();
        // set the row of selected item in selectedRow variable
        int selectedRow = tfaffichage.getSelectionModel().getSelectedIndex();
        // creation une liste qui contient tous les patients
        List<User> userList= new ArrayList();
        List<User> moderateurlist= new ArrayList();
        userList=us.afficher().stream().collect(Collectors.toList());
        for(User m : userList){
            if(m instanceof  Moderateur)
                moderateurlist.add( m);
        }
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir supprimer ce patient?");
        confirmationDialog.initOwner(tfNom.getScene().getWindow());
        confirmationDialog.showAndWait();

        if (confirmationDialog.getResult() == ButtonType.OK && selectedUser != null) 
            us.supprimer(moderateurlist.get(selectedRow));      

//        //pour actualiser le tableview
        tableview();
    }

    @FXML
    private void modifierNom(TableColumn.CellEditEvent event) {
        Moderateur selectedUser =(Moderateur) tfaffichage.getSelectionModel().getSelectedItem();
        selectedUser.setNom(event.getNewValue().toString());
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce patient?");
        confirmationDialog.initOwner(tfNom.getScene().getWindow());
        confirmationDialog.showAndWait();

        if (confirmationDialog.getResult() == ButtonType.OK)
            us.modifier(selectedUser);
        else 
            tableview();
    }

    @FXML
    private void modifierPrenom(TableColumn.CellEditEvent event) {
        Moderateur selectedUser =(Moderateur) tfaffichage.getSelectionModel().getSelectedItem();
        selectedUser.setPrenom(event.getNewValue().toString());
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce patient?");
        confirmationDialog.initOwner(tfNom.getScene().getWindow());
        confirmationDialog.showAndWait();

        if (confirmationDialog.getResult() == ButtonType.OK)
            us.modifier(selectedUser);
        else 
            tableview();
    }

    @FXML
    private void modifierSexe(TableColumn.CellEditEvent event) {
        Moderateur selectedUser =(Moderateur) tfaffichage.getSelectionModel().getSelectedItem();
        selectedUser.setSexe(event.getNewValue().toString());
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce patient?");
        confirmationDialog.initOwner(tfNom.getScene().getWindow());
        confirmationDialog.showAndWait();

        if (confirmationDialog.getResult() == ButtonType.OK)
            us.modifier(selectedUser);
        else 
            tableview();
    }

    @FXML
    private void modifierEmail(TableColumn.CellEditEvent event) {
        Moderateur selectedUser =(Moderateur) tfaffichage.getSelectionModel().getSelectedItem();
        selectedUser.setEmail(event.getNewValue().toString());
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce patient?");
        confirmationDialog.initOwner(tfNom.getScene().getWindow());
        confirmationDialog.showAndWait();

        if (confirmationDialog.getResult() == ButtonType.OK)
            us.modifier(selectedUser);
        else 
            tableview();
    }
    private void tableview(){
                
        //pour filtrer objet user et laisser que les patients dans une liste 
        List<User> userList= new ArrayList();
        List<User> moderateurlist= new ArrayList();
        userList=us.afficher().stream().collect(Collectors.toList());
        for(User p : userList){
            if(p instanceof  Moderateur)
                moderateurlist.add( p);
        }
        
        //l'insertion de la liste dans table view 
        ObservableList<User> moderateurobservList = FXCollections.observableArrayList(moderateurlist);
        tfaffichage.setItems(moderateurobservList);
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexecol.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }


    
}
