/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import entities.Pharmacie;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import services.ServicePharmacie;

/**
 *
 * @author Wimbee018
 */
public class AjoutPharmacieController implements Initializable{

    @FXML
    private TableColumn<Pharmacie, String> adressecol;

    @FXML
    private TableColumn<Pharmacie, String> nomcol;

    @FXML
    private TableColumn<Pharmacie, String> telephonecol;
    


    @FXML
    private TextField tfadresse;

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tftelephone;

    @FXML
    private TableView<Pharmacie> tvafficher;

    @FXML
    void ajouterpharmacie(ActionEvent event) {
        String telephone = tftelephone.getText();
        String adresse = tfadresse.getText();
        String nom = tfnom.getText();
        ServicePharmacie ph = new ServicePharmacie();
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Errer de saisir");
            alert.setHeaderText(null);
        if (nom == null || nom.isEmpty()) {
            alert.setContentText("Nom de Pharmacie est obligatoir");
            alert.showAndWait();
        }
        
        else if (ph.getIdPharByNom(nom)!=-1 ) {            
                alert.setContentText("Nom de Pharmacie est deja existe");
                alert.showAndWait();
        } 
        
        else if (adresse == null || adresse.isEmpty()) {            
                alert.setContentText("L'adresse est obligatoir");
                alert.showAndWait();
        } 
        
        else if (telephone == null || telephone.isEmpty()) {            
                alert.setContentText("telephone est obligatoir");
                alert.showAndWait();
        } 
        
        else {
            ph.ajouter(new Pharmacie(nom,adresse,telephone));      
        tableview();
        VidF();
        }
        
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {    
        tvafficher.setEditable(true);
        nomcol.setCellFactory(TextFieldTableCell.forTableColumn());
        nomcol.setOnEditCommit(this::modNom);
        telephonecol.setCellFactory(TextFieldTableCell.forTableColumn());
        telephonecol.setOnEditCommit(this::modTele);
        adressecol.setCellFactory(TextFieldTableCell.forTableColumn());
        adressecol.setOnEditCommit(this::modAdd);
        tableview();
    } 


    @FXML
    void supprimerpharmacie(ActionEvent event) {
        Pharmacie selectedUser = (Pharmacie) tvafficher.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            ph.supprimer(selectedUser);
        }
        //pour actualiser le tableview
        tableview();

    }
          ServicePharmacie ph = new ServicePharmacie();

    private void tableview() {
          ServicePharmacie ph = new ServicePharmacie();
         ObservableList<Pharmacie> pharmacie = FXCollections.observableArrayList(ph.afficher());
        tvafficher.setItems(pharmacie);
        adressecol.setCellValueFactory(new PropertyValueFactory<>("adresse_pha"));
        telephonecol.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom_pharmacie"));
    }

    @FXML
    private void modNom(TableColumn.CellEditEvent event) {
        Pharmacie selectedPharmacie = (Pharmacie) tvafficher.getSelectionModel().getSelectedItem();
    String newNom = event.getNewValue().toString();

    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationDialog.setTitle("Confirmation");
    confirmationDialog.setHeaderText("Confirmation");
    confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce nom?");
    confirmationDialog.initOwner(tfnom.getScene().getWindow());
    confirmationDialog.showAndWait();

    if (confirmationDialog.getResult() == ButtonType.OK) {
        selectedPharmacie.setNom_pharmacie(newNom);
        ph.modifier(selectedPharmacie);
    } else {
        tableview();
    }
    }

    @FXML
    private void modAdd(TableColumn.CellEditEvent event) {
            Pharmacie selectedPharmacie = (Pharmacie) tvafficher.getSelectionModel().getSelectedItem();
    String newAdd = event.getNewValue().toString();

    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationDialog.setTitle("Confirmation");
    confirmationDialog.setHeaderText("Confirmation");
    confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier cet adresse?");
    confirmationDialog.initOwner(tfadresse.getScene().getWindow());
    confirmationDialog.showAndWait();

    if (confirmationDialog.getResult() == ButtonType.OK) {
        selectedPharmacie.setAdresse_pha(newAdd);
        ph.modifier(selectedPharmacie);
    } else {
        tableview();
    }
    }

    @FXML
    private void modTele(TableColumn.CellEditEvent event) {
            Pharmacie selectedPharmacie = (Pharmacie) tvafficher.getSelectionModel().getSelectedItem();
    String newTele = event.getNewValue().toString();

    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationDialog.setTitle("Confirmation");
    confirmationDialog.setHeaderText("Confirmation");
    confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce Telephone?");
    confirmationDialog.initOwner(tftelephone.getScene().getWindow());
    confirmationDialog.showAndWait();

    if (confirmationDialog.getResult() == ButtonType.OK) {
        selectedPharmacie.setTelephone(newTele);
        ph.modifier(selectedPharmacie);
    } else {
        tableview();
    }
        
    }

    private void VidF() {
        tfadresse.setText("");
        tfnom.setText("");
        tftelephone.setText("");
    }
    @FXML
    private void viderFields(ActionEvent event) {
        VidF();        
    }

    
    
}


