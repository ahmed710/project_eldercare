//package com.esprit.gui.controller;
//
//import com.esprit.entities.Ordonnance;
//import com.esprit.entities.medicament;
//import com.esprit.gui.ServiceMedicament;
//import com.esprit.services.Medicament;
//import com.esprit.services.ServiceOdonnance;
//import com.esprit.utils.DataSource;
//import static java.awt.PageAttributes.MediaType.A;
//
//import java.io.IOException;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.ResourceBundle;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.DatePicker;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.control.cell.TextFieldTableCell;
//import static javafx.scene.input.KeyCode.A;
//import static javafx.scene.input.KeyCode.R;
//import javafx.util.converter.IntegerStringConverter;
//
//import javax.swing.JOptionPane;
//
//public class ManageOrdonnanceControllerTest implements Initializable {
//
//    @FXML
//    private TextField TF_ID_patient;
//    @FXML
//    private TextField TF_ID_medecin;
//    @FXML
//    private DatePicker TF_Date;
//    @FXML
//    private Button BT_valider;
//    @FXML
//    private TableColumn<?, Integer> TV_nom_patient;
//    @FXML
//    private TableColumn<?, Integer> TV_nom_medecin;
//    @FXML
//    private TableColumn<?, Date> TV_date;
//    @FXML
//    private TableView<Ordonnance> TV_ID;
//    @FXML
//    private Button BT_supprimer;
//    @FXML
//    private ComboBox<String> CHB_meds;
//    @FXML
//    private ListView<?> LST_ID_medicaments;
//
//   @Override
//public void initialize(URL url, ResourceBundle rb) {
//    ServiceMedicament serviceMedicament = new ServiceMedicament();
//
//    List<medicament> medicamentsList = serviceMedicament.getAllMedicaments();
//    List<String> medicamentsNamesList = medicamentsList.stream()
//            .map(medicament::getNom)
//            .collect(Collectors.toList());
//
//    CHB_meds.getItems().addAll(medicamentsNamesList);
//
//    ServiceOdonnance so = new ServiceOdonnance();
//
//    List<Ordonnance> ordonnancesList = so.afficher();
//    System.out.println(ordonnancesList);
//
//    tableViewControl();
//}
//
//    public void ajouterOrdonnance(Ordonnance o) throws IOException {
//        
//        List<String> medicamentsChoisis = new ArrayList<>();
//        String choixMedicaments = CHB_meds.getSelectionModel().getSelectedItem();
//         medicamentsChoisis.addAll(0, medicamentsChoisis);
//
//        ServiceOdonnance so = new ServiceOdonnance();
//        Ordonnance ordonnance = new Ordonnance(60, 70, Date.valueOf("2018-05-27"));
//
//        ordonnance.setID_patient(Integer.parseInt(TF_ID_patient.getText()));
//        ordonnance.setID_medecin(Integer.parseInt(TF_ID_medecin.getText()));
//        ordonnance.setDate(Date.valueOf(TF_Date.getValue()));
//
//        so.ajouter(ordonnance);
//
//        JOptionPane.showMessageDialog(null, "Ordonnance ajoutée !");
//        tableViewControl();
//    }
//     @FXML
//    private void supprimerOrdonnance(ActionEvent event) {
//        ServiceOdonnance so = new ServiceOdonnance();
//        Ordonnance selectedOrdonnance = TV_ID.getSelectionModel().getSelectedItem();
//        int selectedRow = TV_ID.getSelectionModel().getSelectedIndex();
//        List<Ordonnance> ordonnances_list = so.afficher().stream().collect(Collectors.toList());
//        so.supprimer(ordonnances_list.get(selectedRow));
//        tableViewControl();
//    }
//
//    
//        private void tableViewControl() {
//        ServiceOdonnance so = new ServiceOdonnance();
//        List<Ordonnance> ordonnances_list = so.afficher().stream().collect(Collectors.toList());
//        ObservableList<Ordonnance> liste = FXCollections.observableArrayList(ordonnances_list);
//        TV_ID.setItems(liste);
//        TV_nom_patient.setCellValueFactory(new PropertyValueFactory<>("ID_patient"));
//        TV_nom_medecin.setCellValueFactory(new PropertyValueFactory<>("ID_medecin"));
//        TV_date.setCellValueFactory(new PropertyValueFactory<>("Date"));
//    }
//    private void commit_patient(TableColumn.CellEditEvent<Ordonnance, Integer> event) {
//        Ordonnance selectedOrdonnance = TV_ID.getSelectionModel().getSelectedItem();
//        selectedOrdonnance.setID_patient(event.getNewValue());
//        ServiceOdonnance so = new ServiceOdonnance();
//        so.modifier(selectedOrdonnance);
//    }
//
//   
//      
//      
//       
////
////    @FXML
////    private void Rechercher(String titre) throws SQLException {
////        ServiceOdonnance so = new ServiceOdonnance();
////        String titreRecherche = so.rechercherParTitre(titre).().trim();
////
////        // Vérifiez que le champ de recherche n'est pas vide
////        if (titreRecherche.isEmpty()) {
////            Alert alert = new Alert(Alert.AlertType.ERROR);
////            alert.setTitle("Erreur");
////            alert.setContentText("Veuillez saisir un titre pour effectuer la recherche");
////            alert.showAndWait();
////            return;
////        }
////
////        // Effectuez la recherche dans la base de données
////        List<Ordonnance> OrdonnanceList = so.rechercherParTitre(titreRecherche);
////
////        // Afficher les résultats dans la table
////        ObservableList<Ordonnance> observableList = FXCollections.observableArrayList(OrdonnanceList);
////        TV_ID.setItems(observableList);
////    }
//
//    @FXML
//    private void ajouterOrdonnance(ActionEvent event) {
//    }
//
//    @FXML
//    private void commit_patient(TableColumn.CellEditEvent<S, T> event) {
//    }
//
//    private void commit_medecin(TableColumn.CellEditEvent<Ordonnance, Integer> event) {
//        Ordonnance selectedOrdonnance = TV_ID.getSelectionModel().getSelectedItem();
//        selectedOrdonnance.setID_medecin(event.getNewValue());
//        ServiceOdonnance so = new ServiceOdonnance();
//        so.modifier(selectedOrdonnance);
//    }
//
//    private void commit_date(TableColumn.CellEditEvent<Ordonnance, Date> event) {
//        Ordonnance selectedOrdonnance = TV_ID.getSelectionModel().getSelectedItem();
//        selectedOrdonnance.setDate(event.getNewValue());
//
//        ServiceOdonnance so = new ServiceOdonnance();
//        so.modifier(selectedOrdonnance);
//    }
//
//    @FXML
//    private void commit_medecin(TableColumn.CellEditEvent<S, T> event) {
//    }
//
//    @FXML
//    private void commit_date(TableColumn.CellEditEvent<S, T> event) {
//    }
//
//  
//
// 
//
//}
