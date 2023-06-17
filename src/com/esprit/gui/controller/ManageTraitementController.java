/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.gui.controller;

import com.esprit.entities.Ordonnance;
import com.esprit.entities.Traitement;
import com.esprit.gui.ServiceMedicament;
import com.esprit.services.ServiceOdonnance;
import com.esprit.services.ServiceTraitement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 * ManageTraitementController
 */
public class ManageTraitementController implements Initializable {

    @FXML
    private TableView<Traitement> TV_Traitements;
    @FXML
    private TableColumn<Traitement, String> TC_medicaments_traitement;
    @FXML
    private TableColumn<Traitement, Integer> TC_nbrPrises_Traitement;
    @FXML
    private TableColumn<Traitement, String> TC_desc_traitement;
    private TableColumn<Traitement, Integer> TC_durée_Traitement;
    @FXML
    private TableColumn<Traitement, Integer> TC_Ordonnance_Traitement;
    private TextField TF_OrdonnanceID_Traitement;
    @FXML
    private TextField TF_nbr_prises;
    @FXML
    private TextField TF_descTraitement;
    @FXML
    private DatePicker DateDébuut_Traitement;
    @FXML
    private DatePicker DateFin_Traitement;
    @FXML
    private Button BT_enregistrer_Traitement;
    @FXML
    private Button BT_supprimer_Traitement;
    private TextField TF_medicamentID_Traitement;
    @FXML
    private TableColumn<?, ?> TC_date_debut;
    @FXML
    private TableColumn<?, ?> TC_date_fin;
    @FXML
    private ComboBox<?> CMB_ordonnaance;
    @FXML
    private ComboBox<?> CMB_médicaments;
    @FXML
    private Button BTN_back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceTraitement T = new ServiceTraitement();
       
         ServiceMedicament serviceMedicament = new ServiceMedicament();
    
        ServiceOdonnance so = new ServiceOdonnance();
        CMB_médicaments.getItems().addAll( serviceMedicament.getAllMedicaments());
        CMB_ordonnaance.getItems().addAll( so.getAllOrdonnances());
        

    TC_nbrPrises_Traitement.setCellValueFactory(new PropertyValueFactory<>("nbr_prises"));
    TC_nbrPrises_Traitement.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    TC_nbrPrises_Traitement.setOnEditCommit(this::editNbrPrises);
//        
//        TV_Traitements.setCellFactory(TextFieldTableCell.forTableColumn());
//        TV_Traitements.setOnEditCommit(this::editMedicaments);
        
            tableViewControl();
        
      }
    
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
      private void tableViewControl (){
          
           ServiceTraitement T = new ServiceTraitement();
           List<Traitement> traitements_list = T.afficher();
           
        TC_medicaments_traitement.setCellValueFactory(new PropertyValueFactory<>("ID_medicament"));
        TC_nbrPrises_Traitement.setCellValueFactory(new PropertyValueFactory<>("nbr_prises"));
        TC_desc_traitement.setCellValueFactory(new PropertyValueFactory<>("description"));
        TC_date_debut.setCellValueFactory(new PropertyValueFactory<>("Date_Debut"));
        TC_date_fin.setCellValueFactory(new PropertyValueFactory<>("Date_Fin"));
        TC_Ordonnance_Traitement.setCellValueFactory(new PropertyValueFactory<>("ID_ordonnance"));

        TV_Traitements.setItems(FXCollections.observableArrayList(traitements_list));
    }
private TextField textField;

void onKeyTyped(KeyEvent event) {
        if (textField.getText().isEmpty()) {
            textField.setStyle("-fx-border-color: red;");
        } else {
            textField.setStyle("-fx-border-color: none;");
        }
    }


//@FXML
//private void ajouterTraitement(ActionEvent event) {
//    
//    if (CMB_ordonnaance.getValue() == null) {
//        CMB_ordonnaance.setStyle("-fx-border-color: red;");
//         Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Erreur de saisie");
//        alert.setHeaderText(null);
//        alert.setContentText("Veuillez choisir une ordonnance.");
//        alert.showAndWait();
//        return;
//    } else {
//        CMB_ordonnaance.setStyle("-fx-border-color: none;");
//    }
//    
// 
//    String description = TF_descTraitement.getText();
//    
//    if (description.length() > 25){
//        TF_descTraitement.setStyle("-fx-border-color: red;");
//             Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Erreur de saisie");
//        alert.setHeaderText(null);
//        alert.setContentText("Vous avez depasser la limite de saisie pour le champs description.");
//        alert.showAndWait();
//        return;
//    } else {
//        TF_descTraitement.setStyle("-fx-border-color: none;");
//    }
//    
//    if (description.isEmpty() ) {
//        TF_descTraitement.setStyle("-fx-border-color: red;");
//             Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Erreur de saisie");
//        alert.setHeaderText(null);
//        alert.setContentText("Veuillez remplir le champs description.");
//        alert.showAndWait();
//        return;
//    } else {
//        TF_descTraitement.setStyle("-fx-border-color: none;");
//    }
//    
// 
//    if (CMB_médicaments.getValue() == null) {
//        CMB_médicaments.setStyle("-fx-border-color: red;");
//             Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Erreur de saisie");
//        alert.setHeaderText(null);
//        alert.setContentText("Veuillez choisir un medicament de la liste.");
//        alert.showAndWait();
//        return;
//    } else {
//        CMB_médicaments.setStyle("-fx-border-color: none;");
//    }
//
//    if (CMB_ordonnaance.getValue() == null || description.isEmpty() || description.length() > 25 || CMB_médicaments.getValue() == null) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Erreur de saisie");
//        alert.setHeaderText(null);
//        alert.setContentText("Veuillez remplir tous les champs correctement.");
//        alert.showAndWait();
//        return; // Exit the method if there are invalid fields
//    }
//    
//   
//    Traitement traitement = new Traitement();
//    traitement.setID_ordonnance((int) CMB_ordonnaance.getValue());
//    traitement.setID_medicament((String) CMB_médicaments.getValue());
//    traitement.setNbr_prises(Integer.parseInt(TF_nbr_prises.getText()));
//    traitement.setDescription(description);
//    traitement.setDate_Debut(Date.valueOf(DateDébuut_Traitement.getValue()));
//    traitement.setDate_Fin(Date.valueOf(DateFin_Traitement.getValue()));
//
//    
//    ServiceTraitement T = new ServiceTraitement();
//    T.ajouter(traitement);
//
//  
//    JOptionPane.showMessageDialog(null, "Traitement ajouté !");
//    initialize(null, null); // Refresh  table view
//}


@FXML
private void ajouterTraitement(ActionEvent event) {
    if (CMB_ordonnaance.getValue() == null) {
        CMB_ordonnaance.setStyle("-fx-border-color: red;");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir une ordonnance.");
        alert.showAndWait();
        return;
    } else {
        CMB_ordonnaance.setStyle("-fx-border-color: none;");
    }

    String description = TF_descTraitement.getText();

    if (description.length() > 25) {
        TF_descTraitement.setStyle("-fx-border-color: red;");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Vous avez dépassé la limite de saisie pour le champ description.");
        alert.showAndWait();
        return;
    } else {
        TF_descTraitement.setStyle("-fx-border-color: none;");
    }

    if (description.isEmpty()) {
        TF_descTraitement.setStyle("-fx-border-color: red;");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir le champ description.");
        alert.showAndWait();
        return;
    } else {
        TF_descTraitement.setStyle("-fx-border-color: none;");
    }

    if (CMB_médicaments.getValue() == null) {
        CMB_médicaments.setStyle("-fx-border-color: red;");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir un médicament de la liste.");
        alert.showAndWait();
        return;
    } else {
        CMB_médicaments.setStyle("-fx-border-color: none;");
    }

    // Validate the "nombre de prises" field
    String nbrPrisesText = TF_nbr_prises.getText();
    if (nbrPrisesText.isEmpty()) {
        TF_nbr_prises.setStyle("-fx-border-color: red;");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez entrer un nombre de prises.");
        alert.showAndWait();
        return;
    } else {
        // Check if the input is a valid integer
        try {
            int nbrPrises = Integer.parseInt(nbrPrisesText);
            TF_nbr_prises.setStyle("-fx-border-color: none;");
            // The input is a valid integer, proceed with the rest of the code
        } catch (NumberFormatException e) {
            TF_nbr_prises.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un nombre entier pour le nombre de prises.");
            alert.showAndWait();
            return;
        }
    }

    Traitement traitement = new Traitement();
    traitement.setID_ordonnance((int) CMB_ordonnaance.getValue());
    traitement.setID_medicament((String) CMB_médicaments.getValue());
    traitement.setNbr_prises(Integer.parseInt(TF_nbr_prises.getText()));
    traitement.setDescription(description);
    traitement.setDate_Debut(Date.valueOf(DateDébuut_Traitement.getValue()));
    traitement.setDate_Fin(Date.valueOf(DateFin_Traitement.getValue()));

    ServiceTraitement T = new ServiceTraitement();
    T.ajouter(traitement);

    JOptionPane.showMessageDialog(null, "Traitement ajouté !");
    initialize(null, null); // Refresh  table view
}


    private void commit_nbrPrises(TableColumn.CellEditEvent<Traitement, Integer> event) {
        Traitement selectedTraitement = TV_Traitements.getSelectionModel().getSelectedItem();
        if (selectedTraitement != null) {
            selectedTraitement.setNbrPrises(event.getNewValue());
            ServiceTraitement st = new ServiceTraitement();
            st.modifier(selectedTraitement);
        }
    }

    private void commit_descTraitement(TableColumn.CellEditEvent<Traitement, String> event) {
        Traitement selectedTraitement = TV_Traitements.getSelectionModel().getSelectedItem();
        if (selectedTraitement != null) {
            selectedTraitement.setDescription(event.getNewValue());
            ServiceTraitement st = new ServiceTraitement();
            st.modifier(selectedTraitement);
        }
    }
        private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
 
        @FXML
  private void supprimerTraitement(ActionEvent event) {
        
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Supprimer Traitement");
        confirmationAlert.setContentText("Voulez-vous vraiment supprimer le Traitement séléctionée ?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
        ServiceOdonnance so = new ServiceOdonnance();
        Traitement selectedTraitement = TV_Traitements.getSelectionModel().getSelectedItem();
        int selectedRow = TV_Traitements.getSelectionModel().getSelectedIndex();
        List<Ordonnance> ordonnances_list = so.afficher().stream().collect(Collectors.toList());
        so.supprimer(ordonnances_list.get(selectedRow));
        tableViewControl();
          showInfoAlert("Traitement supprimée!");
    }}
  
private void editNbrPrises(TableColumn.CellEditEvent<Traitement, Integer> event) {
    Traitement selectedTraitement = event.getRowValue();
    int newNbrPrises = event.getNewValue();
    
    // Update the selectedTraitement object with the new value
    selectedTraitement.setNbr_prises(newNbrPrises);
    
    // Call the update method to save the changes to the database
    ServiceTraitement serviceTraitement = new ServiceTraitement();
    serviceTraitement.modifier(selectedTraitement);
}
    @FXML
    private void retourVersOrdonnance(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ManageOrdonnance.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage patientStage = new Stage();
        patientStage.setScene(scene);
        patientStage.setTitle("ManageTraitement");
        patientStage.show();
    }
}
