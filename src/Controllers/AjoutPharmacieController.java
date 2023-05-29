/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Entities.Pharmacie;
import Services.ServicePharmacie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AjoutPharmacieController {


    @FXML
    private TextField tfadressepharmacie;

    @FXML
    private TextField tfnompharmacie;

    @FXML
    private TextField tftelephone;

    @FXML
    private TableView<?> taffichage;

    @FXML
    private TableColumn<?, ?> idcol;

    @FXML
    private TableColumn<?, ?> adressecol;

    @FXML
    private TableColumn<?, ?> nomcol;

    @FXML
    private TableColumn<?, ?> telephonecol;
    @FXML
    private void ajouterpharmacie(ActionEvent event) {
        String adressePhramacie = tfadressepharmacie.getText();
        String nomPharmacie = tfnompharmacie.getText();
        String telephone = tftelephone.getText();
        ServicePharmacie ph = new ServicePharmacie();
        ph.ajouter(new Pharmacie(adressePhramacie, nomPharmacie, telephone));

    }
    @FXML
    private void modifierpharmacie(ActionEvent event) {

    }
    @FXML
    private void supprimerpharmacie(ActionEvent event) {

    }

}
