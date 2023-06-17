/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import com.esprit.entities.Ordonnance;
import com.esprit.entities.medicament;
import com.esprit.gui.ServiceMedicament;
import com.esprit.gui.controller.ManageTraitementController;
import com.esprit.services.ServiceOdonnance;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Zouhour
 */
public class ManageOrdonnanceController implements Initializable {

    @FXML
    private TableColumn<?, ?> TC_ID_ordonnance;
    @FXML
    private TableColumn<?, ?> TC_nom_patient;
    @FXML
    private TableColumn<?, ?> TC_nom_medecin;
    @FXML
    private TableColumn<?, ?> TC_médicamnts;
    private TextField TF_nom_patient;
    private TextField TF_nom_medecin;
    
    @FXML
    private Button BT_supprimer;
    @FXML
    private TableColumn<?, Date> TC_date;
    @FXML
    private TableView<Ordonnance> TV_tableOrdonnance;
    @FXML
    private DatePicker DP_date;
    @FXML
    private Button BT_ajouter;
    @FXML
    private ComboBox<?> CMB_nom_medecin;
    @FXML
    private ComboBox<?> CMB_nom_patient;
    @FXML
    private ListView<String> LSTV_medicament;
    @FXML
    private TextField Display_medicament;
    @FXML
    private Button BTN_select_med;
    @FXML
    private Button BTN_clear;
    @FXML
    private Button BTN_selectNext;
    @FXML
    private Button BTN_traitement;
      private Stage primaryStage;
    
    /**
 * FXML Controller class
 *
 * @author Zouhour
 */

     @Override
public void initialize(URL url, ResourceBundle rb) {
            //modification avec doubleClick.
        tfaffichage.setEditable(true);
        nomcol.setCellFactory(TextFieldTableCell.forTableColumn());
        nomcol.setOnEditCommit(this::modifierNom);
        
        
        prenomcol.setCellFactory(TextFieldTableCell.forTableColumn());
        prenomcol.setOnEditCommit(this::modifierPrenom);

        poidscol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        poidscol.setOnEditCommit(this::modifierPoids);
        
        taillecol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        datecol.setOnEditCommit(this::modifierTaille);

       
    
    tableViewControl();
    ServiceMedicament serviceMedicament = new ServiceMedicament();
    ServiceOdonnance so = new ServiceOdonnance();
       
//    CMB_médicaments.getItems().addAll( serviceMedicament.getAllMedicaments());
    CMB_nom_medecin.getItems().addAll( so.getAlldocs());
    CMB_nom_patient.getItems().addAll( so.getAllpatient());
    
    
     LSTV_medicament.getItems().addAll( serviceMedicament.getAllMedicaments());
     LSTV_medicament.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
     LSTV_medicament.getSelectionModel().selectedItemProperty().addListener(this::selectionChanged);
//    
//    TV_tableOrdonnance.setRowFactory(tv -> {
//            TableRow<Ordonnance> row = new TableRow<>();
//            row.setOnMouseClicked(event -> {
//
//
//                if (event.getClickCount() == 2 && !row.isEmpty()) {
//                    Ordonnance selectedMaladie = row.getItem();
//                    int rowIndex = TV_tableOrdonnance.getSelectionModel().getSelectedIndex();
//                    int columnIndex = 0;
//                    String newValue = "New Value";
//
//                    updateOrdonnance(selectedMaladie, rowIndex, columnIndex, newValue);
//                }
//
//            });
//            return row;
//        });


//    List<Ordonnance> ordonnancesList = so.afficher();
//    System.out.println(ordonnancesList);
 
}
//private void updateOrdonnance(Ordonnance ordonnance, int rowIndex, int columnIndex, String newValue) {
//    TV_tableOrdonnance.getItems().get(rowIndex).setCellValueFactory(columnIndex, newValue);
//
//    ServiceOdonnance so = new ServiceOdonnance();
//    so.modifier(ordonnance);
//
//    TV_tableOrdonnance.refresh();
//    showInfoAlert("Maladie updated!");
//}
  private void selectionChanged(ObservableValue<? extends String> Observable, String oldVal, String newVal){
        ObservableList<String> selectedItems = LSTV_medicament.getSelectionModel().getSelectedItems();
        String getSelectedItem = (selectedItems.isEmpty())?"No Selected Item":selectedItems.toString();
        Display_medicament.setText(getSelectedItem);
    }
  
    @FXML
    private void supprimerOrdonnance(ActionEvent event) {
        
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Supprimer Ordonnance");
        confirmationAlert.setContentText("Voulez-vous vraiment supprimer l'ordonnance séléctionée ?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
        ServiceOdonnance so = new ServiceOdonnance();
        Ordonnance selectedOrdonnance = (Ordonnance) TV_tableOrdonnance.getSelectionModel().getSelectedItem();
        int selectedRow = TV_tableOrdonnance.getSelectionModel().getSelectedIndex();
        List<Ordonnance> ordonnances_list = so.afficher().stream().collect(Collectors.toList());
        so.supprimer(ordonnances_list.get(selectedRow));
        tableViewControl();
          showInfoAlert("Ordonnance supprimée!");
    }}
    
    
    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

//    private void commit_patient(TableColumn.CellEditEvent<Ordonnance, Integer> event) {
//        Ordonnance selectedOrdonnance = TV_tableOrdonnance.getSelectionModel().getSelectedItem();
//        selectedOrdonnance.setID_patient(event.getNewValue());
//        ServiceOdonnance so = new ServiceOdonnance();
//        so.modifier(selectedOrdonnance);
//    }

//    private void commit_medecin(TableColumn.CellEditEvent<Ordonnance, Integer> event) {
//        Ordonnance selectedOrdonnance = TV_tableOrdonnance.getSelectionModel().getSelectedItem();
//        selectedOrdonnance.setID_medecin(event.getNewValue());
//        ServiceOdonnance so = new ServiceOdonnance();
//        so.modifier(selectedOrdonnance);
//    }
          private void commit_patient(TableColumn.CellEditEvent<Ordonnance, String> event) {
        Ordonnance selectedOrdonnance = TV_tableOrdonnance.getSelectionModel().getSelectedItem();
        selectedOrdonnance.setID_medecin(event.getNewValue());

        ServiceOdonnance so = new ServiceOdonnance();
        so.modifier(selectedOrdonnance);
    }
    
      private void commit_medecin(TableColumn.CellEditEvent<Ordonnance, String> event) {
        Ordonnance selectedOrdonnance = TV_tableOrdonnance.getSelectionModel().getSelectedItem();
        selectedOrdonnance.setID_patient(event.getNewValue());

        ServiceOdonnance so = new ServiceOdonnance();
        so.modifier(selectedOrdonnance);
    }

      private void commit_date(TableColumn.CellEditEvent<Ordonnance, Date> event) {
        Ordonnance selectedOrdonnance = TV_tableOrdonnance.getSelectionModel().getSelectedItem();
        selectedOrdonnance.setDate(event.getNewValue());

        ServiceOdonnance so = new ServiceOdonnance();
        so.modifier(selectedOrdonnance);
    }
      
      private void sendMail(String recepteur,String sujet,Ordonnance ordonnance){
          
                String emetteur = "eldercareserviceofficiel@gmail.com";
                String host = "smtp.gmail.com";
                String mail = "eldercareserviceofficiel@gmail.com";
                String password = "zgsldmasvmnadrkh";

                Properties properties = new Properties();
                properties.setProperty("mail.smtp.host", host);
                properties.setProperty("mail.smtp.auth", "true");
                properties.setProperty("mail.smtp.starttls.enable", "true");
                Session session = Session.getDefaultInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mail, password);
                }
            });
            
             try {
                 
                int numOrdonnance = ordonnance.getID_ordonnance();
                String nomPatient = ordonnance.getID_patient();
                String nomMedecin = ordonnance.getID_medecin();
                String medicamentt = ordonnance.getID_medicament();
                Date date = (Date) ordonnance.getDate();
                
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(emetteur));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recepteur));
                message.setSubject(sujet);
                message.setText("Bonjour " +nomPatient+","+"le :"
                +date +"\n les médicaments que le médecin "+nomMedecin+ " vous a accordée sont :"+"\n "+medicamentt);
                Transport.send(message);
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
    }
 @FXML
    private void ajouterOrdonnance(ActionEvent event) {
     if (Display_medicament.getText().isEmpty()) {
        Display_medicament.setStyle("-fx-border-color: red;");
             Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir des medicaments de la liste.");
        alert.showAndWait();
        return;
    } else {
        Display_medicament.setStyle("-fx-border-color: none;");
    }

      if (CMB_nom_patient.getValue() == null) {
        CMB_nom_patient.setStyle("-fx-border-color: red;");
             Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir un patient de la liste.");
        alert.showAndWait();
        return;
    } else {
        CMB_nom_patient.setStyle("-fx-border-color: none;");
    }
          
      if (CMB_nom_medecin.getValue() == null) {
        CMB_nom_medecin.setStyle("-fx-border-color: red;");
             Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir votre nom de la liste.");
        alert.showAndWait();
        return;
    } else {
        CMB_nom_medecin.setStyle("-fx-border-color: none;");
    }
    String choixMedicaments = Display_medicament.getText();
    String medecin = (String) CMB_nom_patient.getValue();
    String patient = (String) CMB_nom_medecin.getValue();

    Date date = Date.valueOf(DP_date.getValue());

    ServiceOdonnance so = new ServiceOdonnance();
    Ordonnance o = new Ordonnance(choixMedicaments, patient, medecin, date, medecin, patient);
     so.ajouter(o);

    JOptionPane.showMessageDialog(null, "Ordonnance ajoutée !");
    tableViewControl();
        
    sendMail("zouhour.rouissi@esprit.tn","ordoonance",o);

    }
  
    private List<String> selectedMedicaments = new ArrayList<>();
    @FXML
    private void selectionner_medicament(ActionEvent event) {
        ObservableList<String> selectedItems = LSTV_medicament.getSelectionModel().getSelectedItems();
  // Clear the previous selections

        if (selectedItems.isEmpty()) {
            Display_medicament.setText("No Selected Item");
        } else {
            selectedMedicaments.addAll(selectedItems); // Add the newly selected medications to the list

            // Update the TextField with all the selected medications
            String displayText = String.join(", ", selectedMedicaments);
            Display_medicament.setText(displayText);
        }
    }

//    @FXML
//    private void selectionner_medicament(ActionEvent event) {
//        LSTV_medicament.getSelectionModel().getSelectedItem();
//    }

    @FXML
    private void ViderListe(ActionEvent event) {
        LSTV_medicament.getSelectionModel().clearSelection();
    }

    @FXML
    private void selectNext(ActionEvent event) {
        LSTV_medicament.getSelectionModel().selectNext();
    }

        private void tableViewControl() {
            
 
         
          if (CMB_nom_patient.getValue()!= null) {
            CMB_nom_patient.setStyle("-fx-border-color: red;");
        } else {
            CMB_nom_patient.setStyle("-fx-border-color: none;");
        }
            if (CMB_nom_medecin.getValue()!= null) {
            CMB_nom_medecin.setStyle("-fx-border-color: red;");
        } else {
            CMB_nom_medecin.setStyle("-fx-border-color: none;");
        }
            
        ServiceOdonnance so = new ServiceOdonnance();
        ObservableList<Ordonnance> liste = FXCollections.observableArrayList(so.afficher());
        TV_tableOrdonnance.setItems(liste);
        TC_ID_ordonnance.setCellValueFactory(new PropertyValueFactory<>("ID_ordonnance"));
        TC_nom_patient.setCellValueFactory(new PropertyValueFactory<>("ID_patient"));
        TC_nom_medecin.setCellValueFactory(new PropertyValueFactory<>("ID_medecin"));
        TC_médicamnts.setCellValueFactory(new PropertyValueFactory<>("ID_medicament"));
        TC_date.setCellValueFactory(new PropertyValueFactory<>("Date"));
    
        
    }
 

    @FXML
    private void Ajouter_traitement(ActionEvent event)throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("com.esprit.gui/ManageTraitement.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage patientStage = new Stage();
        patientStage.setScene(scene);
        patientStage.setTitle("ManageTraitement");
        patientStage.show();
        
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(".../gui/ManageTraitement.fxml"));
//        Parent root = loader.load();
//        ManageTraitementController Controller = loader.getController();
//        Controller.setPrimaryStage(primaryStage);
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//       
//        primaryStage.show();

   

    
    }
        
    }
       

 

    

