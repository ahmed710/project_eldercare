/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javax.swing.JOptionPane;
import com.esprit.services.ServiceOdonnance;
import com.esprit.entities.Ordonnance;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.sql.Date;
import static java.sql.Date.valueOf;
import javafx.scene.control.ChoiceBox;
import com.esprit.entities.Ordonnance;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;






/**
 * FXML Controller class
 *
 * @author Zouhour
 */
public class AjouterOrdonnanceController implements Initializable {
    @FXML
    private TextField TF_ID_patient;
    @FXML
    private TextField TF_ID_medecin;
    @FXML
    private DatePicker TF_Date;
    private ComboBox<?> CHB_ID_medicament;
    @FXML
    private Button BT_valider;
    @FXML
    private ComboBox<String> CB_ID_medicament;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> itemList = Arrays.asList("Dolipranne", "Panadol", "Glygophage", "antaphéne");
        CB_ID_medicament.getItems().addAll(itemList);
    }
    
//    private void ajouterOrdonnance(ActionEvent event) throws IOException {
//        ServiceOdonnance so = new ServiceOdonnance();
//        
//        //CHB_ID_medicament.getAccessibleText()mte3 liste 
//        so.ajouter(new Ordonnance( CHB_ID_medicament.getText() ,
//                TF_ID_patient.getText(),TF_ID_medecin.getText()
//                ,valueOf(TF_Date.getValue())));
//     
//        JOptionPane.showMessageDialog(null, "Personne ajoutée !");

    
    @FXML
    private void ajouterOrdonnance(ActionEvent event) throws IOException {
    ServiceOdonnance so = new ServiceOdonnance();
    Ordonnance ordonnance = new Ordonnance();
    
    String selectedItem = (String) CHB_ID_medicament.getSelectionModel().getSelectedItem();
    ordonnance.getID_medicament().add(selectedItem);

    ordonnance.setID_patient(Integer.parseInt(TF_ID_patient.getText()));
    ordonnance.setID_medecin(Integer.parseInt(TF_ID_medecin.getText()));
    ordonnance.setDate(valueOf(TF_Date.getValue()));

    so.ajouter(ordonnance);

    JOptionPane.showMessageDialog(null, "Ordonnance ajoutée !");
    
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsOrdonnance.fxml"));
        Parent root = loader.load();
        TF_ID_patient.getScene().setRoot(root);
        
        DetailsOrdonnanceController dpc = loader.getController();
        dpc.setLB_ID_patient(Integer.parseInt(TF_ID_patient.getText()));
        dpc.setLB_ID_médecin(Integer.parseInt(TF_ID_medecin.getText()));
     
}

    
    
    
    
    
}
