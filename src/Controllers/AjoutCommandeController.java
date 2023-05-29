/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Entities.Commande;
import Entities.Pharmacie;
import Services.ServiceCommande;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import utils.DataSource;
public class AjoutCommandeController {
    @FXML
    private TextField tfadresse;

    @FXML
    private DatePicker dpdateCommande;

    @FXML
    private TextField tfetat;

    @FXML
    private TableView<?> tfaffichage;

    @FXML
    private TableColumn<?, ?> idcol;

    @FXML
    private TableColumn<?, ?> idpharmaciecol;

    @FXML
    private TableColumn<?, ?> datecol;

    @FXML
    private TableColumn<?, ?> etatcol;

    @FXML
    private TableColumn<?, ?> adressecol;
    @FXML
    private void ajouterCommande(ActionEvent event) {
        String adresse = tfadresse.getText();
        String dateCommande = dpdateCommande.getValue().toString();
        String etat = tfetat.getText();
        ServiceCommande cm = new ServiceCommande();
        cm.ajouter(new Commande(adresse, dateCommande, etat)); 
    }
    
       @FXML
    void modifiercommande(ActionEvent event) {

    }

    @FXML
    void supprimercommande(ActionEvent event) {
        ServiceCommande cm = new ServiceCommande();
        Commande selectedUser = (Commande) tfaffichage.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            cm.supprimer(selectedUser);
        }
        //pour actualiser le tableview
        tableview();

    }
 
    
    
    
    
    
    
    
    private void tableview() {
        
        ServiceCommande cm = new ServiceCommande();
        
        //pour filtrer objet user et laisser que les patients dans une liste 
        List<Commande> commandeList= new ArrayList();
        commandeList=cm.afficher().stream().collect(Collectors.toList());
        for(Commande c : commandeList){
                commandeList.add( c);
        }
        
        //l'insertion de la liste dans table view 

        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        adressecol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        etatcol.setCellValueFactory(new PropertyValueFactory<>("etat"));
    }

}
