
package com.esprit.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.esprit.entities.Ordonnance;
import com.esprit.services.ServiceOdonnance;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class AjouterOrdonnanceController implements Initializable {

    @FXML
    private TextField TF_ID_patient;
    @FXML
    private TextField TF_ID_medecin;
    @FXML
    private DatePicker TF_Date;
    @FXML
    private Button BT_valider;
    @FXML
    private ListView<String> LST_ID_medicaments;
    @FXML
    private TableColumn<?, Integer> TV_nom_patient;
    @FXML
    private TableColumn<?, ?> TV_nom_medecin;
    @FXML
    private TableColumn<?, ?> TV_date;
    @FXML
    private TableView<?> TV_ID;
    @FXML
    private Button BT_supprimer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> itemList = Arrays.asList("k15", "Dolipranne", "Panadol", "Glygophage", "antaphéne");
        LST_ID_medicaments.getItems().addAll(itemList); //a rendre dynamique 
        ServiceOdonnance so = new ServiceOdonnance();
        List<Ordonnance> ordonnances_list = so.afficher().stream().collect(Collectors.toList());
        System.out.println(ordonnances_list);
       
        TV_ID.setEditable(true);
        TV_nom_patient.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TV_nom_patient.setOnEditCommit(this::commit_patient);
 
         tableViewConrol();
    }
@FXML
private void ajouterOrdonnance(ActionEvent event) throws IOException {

    //methode kima service affiher medicament 
    ServiceOdonnance so = new ServiceOdonnance();
    Ordonnance ordonnance = new Ordonnance(60, 70, Date.valueOf("2018-05-27"));

    ObservableList<String> items = LST_ID_medicaments.getItems();

    for (String item : items) {
        if (LST_ID_medicaments.getSelectionModel().isSelected(items.indexOf(item))) {
            ordonnance.getID_medicament().add(item);
        }
    }

    ordonnance.setID_patient(Integer.parseInt(TF_ID_patient.getText()));
    ordonnance.setID_medecin(Integer.parseInt(TF_ID_medecin.getText()));
    ordonnance.setDate(Date.valueOf(TF_Date.getValue()));

    so.ajouter(ordonnance);

    JOptionPane.showMessageDialog(null, "Ordonnance ajoutée !");
    tableViewConrol();
   
}

    private void tableViewConrol(){
    
        
       ServiceOdonnance so = new ServiceOdonnance();
   
    List<Ordonnance> ordonnances_list= new ArrayList() ;

    ordonnances_list=so.afficher().stream().collect(Collectors.toList());
    ObservableList liste = FXCollections.observableArrayList(ordonnances_list);
    TV_ID.setItems(liste);
    TV_nom_patient.setCellValueFactory(new PropertyValueFactory<>("ID_patient"));
    TV_nom_medecin.setCellValueFactory(new PropertyValueFactory<>("ID_medecin"));
    TV_date.setCellValueFactory(new PropertyValueFactory<>("Date"));
  
}

    @FXML
    private void supprimerOrdonnance(ActionEvent event) {
        ServiceOdonnance so =  new ServiceOdonnance();
        Ordonnance selectedOrdonnance = (Ordonnance) TV_ID.getSelectionModel().getSelectedItem();
        int selectedRow = TV_ID.getSelectionModel().getSelectedIndex(); 
        List<Ordonnance> ordonnances_list= new ArrayList() ;
        ordonnances_list=so.afficher().stream().collect(Collectors.toList());
        so.supprimer(ordonnances_list.get(selectedRow));
        tableViewConrol();
     }

    @FXML
    private void commit_patient(CellEditEvent event) {
                Ordonnance selectedOrdonnance = (Ordonnance) TV_ID.getSelectionModel().getSelectedItem();
                selectedOrdonnance.setID_patient((int) event.getNewValue());
                ServiceOdonnance so =  new ServiceOdonnance();
                so.modifier(selectedOrdonnance);

    }

    @FXML
    private void commit_medecin() {
        
    }

    @FXML
    private void commit_date() {
    }


}
