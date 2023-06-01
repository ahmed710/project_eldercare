/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Entities.Pharmacie;
import Services.ServicePharmacie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class AjoutPharmacieController {


    @FXML
    private TextField tfadressepharmacie;

    @FXML
    private TextField tfnompharmacie;

    @FXML
    private TextField tftelephone;

    @FXML
    private TableView<Pharmacie> taffichage;

    @FXML
    private TableColumn<?, ?> idcol;

    @FXML
    private TableColumn<Pharmacie, String> adressecol;

    @FXML
    private TableColumn<Pharmacie, String>nomcol;

    @FXML
    private TableColumn<Pharmacie, String> telephonecol;
    ServicePharmacie ph = new ServicePharmacie();
    @FXML
    private void ajouterpharmacie(ActionEvent event) {
        String adressePhramacie = tfadressepharmacie.getText();
        String nomPharmacie = tfnompharmacie.getText();
        String telephone = tftelephone.getText();
        ServicePharmacie ph = new ServicePharmacie();
        ph.ajouter(new Pharmacie(adressePhramacie, nomPharmacie, telephone));
        tableview();
    }

    private void tableview() {
        
        ObservableList<Pharmacie> pharmacieObs = FXCollections.observableArrayList(ph.afficher());
        //l'insertion de la liste dans table view 
        taffichage.setItems(pharmacieObs);
        adressecol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom_pharmacie"));
        telephonecol.setCellValueFactory(new PropertyValueFactory<>("etat"));
 
        
    }
    @FXML
    private void modifierpharmacie(ActionEvent event) {

    }
    @FXML
    private void supprimerpharmacie(ActionEvent event) {

    }
    

}
