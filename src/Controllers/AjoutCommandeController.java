/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Entities.Commande;
import Entities.Pharmacie;
import Services.ServiceCommande;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import utils.DataSource;
public class AjoutCommandeController implements Initializable{
    @FXML
    private TextField tfadresse;

    @FXML
    private DatePicker dpdateCommande;

    @FXML
    private TextField tfetat;

    @FXML
    private TableView<Commande> tfaffichage;

    @FXML
    private TableColumn<?, ?> idpharmaciecol;

    @FXML
    private TableColumn<Commande, String> datecol;

    @FXML
    private TableColumn<Commande, String> etatcol;

    @FXML
    private TableColumn<Commande, String> adressecol;
    ServiceCommande cm = new ServiceCommande();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        tfaffichage.setEditable(true);
        datecol.setCellFactory(TextFieldTableCell.forTableColumn());
        datecol.setOnEditCommit(this::modifierDate);
        adressecol.setCellFactory(TextFieldTableCell.forTableColumn());
        adressecol.setOnEditCommit(this::modifierAdresse);
        etatcol.setCellFactory(TextFieldTableCell.forTableColumn());
        etatcol.setOnEditCommit(this::modifierEtat);
        tableview();


    }
    @FXML
    public void ajouterCommande(ActionEvent event) {
        String adresse = tfadresse.getText();
        String dateCommande = dpdateCommande.getValue().toString();
        String etat = tfetat.getText();
        ServiceCommande cm = new ServiceCommande();
        cm.ajouter(new Commande(adresse, dateCommande, etat)); 
    }
    
       @FXML
    public void modifiercommande(ActionEvent event) {

    }

    @FXML
<<<<<<< HEAD
    public void supprimercommande(ActionEvent event) {
        ServiceCommande cm = new ServiceCommande();
        Commande selectedUser = (Commande) tfaffichage.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            cm.supprimer(selectedUser);
        }
        //pour actualiser le tableview
        tableview();

    }
 
=======
    void supprimercommande(ActionEvent event) {

    }
>>>>>>> 31f974c54240be487c3ac3d4531901a0cfda097f

    private void tableview() {
        
        ObservableList<Commande> commandeObs = FXCollections.observableArrayList(cm.afficher());
        //l'insertion de la liste dans table view 
        tfaffichage.setItems(commandeObs);
        adressecol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date_commande"));
        etatcol.setCellValueFactory(new PropertyValueFactory<>("etat"));
    }

    @FXML
    private void modifierDate(CellEditEvent event) {
        Commande selectedUser = (Commande) tfaffichage.getSelectionModel().getSelectedItem();
        selectedUser.setDate_commande(event.getNewValue().toString());
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce patient?");
        confirmationDialog.initOwner(tfadresse.getScene().getWindow());
        confirmationDialog.showAndWait();

        if (confirmationDialog.getResult() == ButtonType.OK)
            cm.modifier(selectedUser);
        else 
            tableview();
    }

@FXML
private void modifierEtat(CellEditEvent event) {
    Commande selectedCommande = tfaffichage.getSelectionModel().getSelectedItem();
    String newEtat = event.getNewValue().toString();

    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationDialog.setTitle("Confirmation");
    confirmationDialog.setHeaderText("Confirmation");
    confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce patient?");
    confirmationDialog.initOwner(tfetat.getScene().getWindow());
    confirmationDialog.showAndWait();

    if (confirmationDialog.getResult() == ButtonType.OK) {
        selectedCommande.setEtat(newEtat);
        cm.modifier(selectedCommande);
    } else {
        tableview();
    }
}


@FXML
private void modifierAdresse(CellEditEvent event) {
    Commande selectedCommande = (Commande) tfaffichage.getSelectionModel().getSelectedItem();
    String newAdresse = event.getNewValue().toString();

    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationDialog.setTitle("Confirmation");
    confirmationDialog.setHeaderText("Confirmation");
    confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce patient?");
    confirmationDialog.initOwner(tfadresse.getScene().getWindow());
    confirmationDialog.showAndWait();

    if (confirmationDialog.getResult() == ButtonType.OK) {
        selectedCommande.setAdresse(newAdresse);
        cm.modifier(selectedCommande);
    } else {
        tableview();
    }
}

    

}
