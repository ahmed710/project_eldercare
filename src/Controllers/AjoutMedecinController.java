package Controllers;

import Services.UserService;
import entities.Medecin;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import utils.DataSource;

public class AjoutMedecinController implements Initializable{

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
    private TableView<User> tfaffichage;

    private TableColumn<Medecin, Integer> idcol;

    @FXML
    private TableColumn<Medecin, String> nomcol;

    @FXML
    private TableColumn<Medecin, String> prenomcol;

    @FXML
    private TableColumn<Medecin, String> sexecol;

    @FXML
    private TableColumn<Medecin, String> emailcol;

    @FXML
    private TableColumn<Medecin, String> adressecol;

    @FXML
    private TableColumn<Medecin, String> colspec;
    Connection cnx = DataSource.getinstance().getCnx();
    UserService us = new UserService();
    @FXML
    private ComboBox<String> cbspecialite;
    private String[] specList={"chirurgie plastique","ophtalmologie","dermatologie","cardiovasculaire","reconstructrice et esthétique","Anesthésie-réanimation","Radiologie et imagerie médicale","Neurologie","Rhumatologie","Pédiatrie","Médecine générale","Psychiatrie"};
    @FXML
    private PasswordField tfmotdepasse;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //modification avec doubleClick.
        tfaffichage.setEditable(true);
        nomcol.setCellFactory(TextFieldTableCell.forTableColumn());
        nomcol.setOnEditCommit(this::modifierNom);
        
        prenomcol.setCellFactory(TextFieldTableCell.forTableColumn());
        prenomcol.setOnEditCommit(this::modifierPrenom);
        
        sexecol.setCellFactory(TextFieldTableCell.forTableColumn());
        sexecol.setOnEditCommit(this::modifierSexe);
        
        emailcol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailcol.setOnEditCommit(this::modifierEmail);
        
        adressecol.setCellFactory(TextFieldTableCell.forTableColumn());
        adressecol.setOnEditCommit(this::modifierAdresse);
        
        colspec.setCellFactory(TextFieldTableCell.forTableColumn());
        colspec.setOnEditCommit(this::modifierSpecialité);
        
        cbspecialite.getItems().addAll(specList);
        
        tableview();
    }
    
    @FXML
    void ajouterMedecin(ActionEvent event) {
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String sexe = rbHomme.isSelected() ? "Homme" : "Femme";
        String adresse = tfAdresse.getText();
        String email = tfEmail.getText();
        String specialite = cbspecialite.getValue();
        String motdepasse =tfmotdepasse.getText();

        if (prenom.isEmpty() || nom.isEmpty() || sexe.isEmpty()|| adresse.isEmpty()|| email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        }

        
        us.ajouter(new Medecin(nom, prenom, sexe, email, specialite, adresse,motdepasse));
        
        
        
        // Réinitialiser les champs après l'ajout de patient
        tfNom.setText("");
        tfPrenom.setText("");
        datePicker.setValue(null);
        rbHomme.setSelected(false);
        rbFemme.setSelected(false);
        tfAdresse.setText("");
        tfEmail.setText("");
        tfmotdepasse.setText("");
        tableview();

    }
    
    void modifierMedecin(ActionEvent event) {
        
    }

    @FXML
    void supprimerMedecin(ActionEvent event) {
        User selectedUser = tfaffichage.getSelectionModel().getSelectedItem();
        // set the row of selected item in selectedRow variable
        int selectedRow = tfaffichage.getSelectionModel().getSelectedIndex();
        // creation une liste qui contient tous les patients
        List<User> userList= new ArrayList();
        List<User> medecinList= new ArrayList();
        userList=us.afficher().stream().collect(Collectors.toList());
        for(User m : userList){
            if(m instanceof  Medecin)
                medecinList.add( m);
        }
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir supprimer ce patient?");
        confirmationDialog.initOwner(tfNom.getScene().getWindow());
        confirmationDialog.showAndWait();

        if (confirmationDialog.getResult() == ButtonType.OK && selectedUser != null) 
            us.supprimer(medecinList.get(selectedRow)); 
        //pour actualiser le tableview
        tableview();
    }
    private void tableview() {
        //pour filtrer objet user et laisser que les patients dans une liste 
        List<User> userList= new ArrayList();
        List<User> medecinlist= new ArrayList();
        userList=us.afficher().stream().collect(Collectors.toList());
        for(User p : userList){
            if(p instanceof  Medecin)
                medecinlist.add( p);
        }

        //l'insertion de la liste dans table view 
        ObservableList<User> medecinobservList = FXCollections.observableArrayList(medecinlist);
        tfaffichage.setItems(medecinobservList);
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexecol.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        adressecol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colspec.setCellValueFactory(new PropertyValueFactory<>("specialite"));
  
    }

    @FXML
    private void modifierNom(CellEditEvent event) {
        Medecin selectedUser = (Medecin) tfaffichage.getSelectionModel().getSelectedItem();
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
    private void modifierPrenom(CellEditEvent event) {
        Medecin selectedUser = (Medecin) tfaffichage.getSelectionModel().getSelectedItem();
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

    private void modifierSexe(CellEditEvent event) {
    
        Medecin selectedUser = (Medecin) tfaffichage.getSelectionModel().getSelectedItem();
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
            tableview();}
    

    @FXML
    private void modifierEmail(CellEditEvent event) {
        Medecin selectedUser = (Medecin) tfaffichage.getSelectionModel().getSelectedItem();
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

    @FXML
    private void modifierAdresse(CellEditEvent event) {
        Medecin selectedUser = (Medecin) tfaffichage.getSelectionModel().getSelectedItem();
        selectedUser.setAdresse(event.getNewValue().toString());
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
    private void modifierSpecialité(CellEditEvent event) {
        Medecin selectedUser = (Medecin) tfaffichage.getSelectionModel().getSelectedItem();
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

    private void tfsexe(CellEditEvent event) {
        Patient selectedUser = (Patient) tfaffichage.getSelectionModel().getSelectedItem();
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

 

}
