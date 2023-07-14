/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import entities.Medicament;
import entities.RendezVous;
import services.ServiceMedicament;
import services.ServiceRendezVous;
import services.serviceuser;
import utils.DataSource;
import utils.JavaMail;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javax.mail.MessagingException;


/**
 * FXML Controller class
 *
 * @author DONIG
 */
public class AjoutRdvController implements Initializable {

    @FXML
    private DatePicker dpDate;
    @FXML
    private ComboBox<String> tfIdP;
    @FXML
    private ComboBox<String> tfIdM;
    @FXML
    private ChoiceBox<String> cbEtat;
    private String[] etat={"Confirmé","Annulé","En attente"};
    @FXML
    private ListView<RendezVous> lv;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supp;
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_stat;
    @FXML
    private DatePicker dd;
    @FXML
    private DatePicker df;
    @FXML
    private Button btn_r;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceuser sm = serviceuser.getInstance();
        sm.loaduesrsmedecin();
        tfIdM.setItems(sm.getUsersmedecins());
        serviceuser su = serviceuser.getInstance();
        su.loaduesrspatient();
        tfIdP.setItems(su.getUserspatients()); 
        cbEtat.getItems().addAll(etat);
        ServiceRendezVous sr = ServiceRendezVous.getInstance();
        sr.loadRdvs();
        lv.setItems(sr.getRdvs()); 
        lv.setOnMouseClicked((MouseEvent event)->{
         java.sql.Date currentDate = new java.sql.Date( System.currentTimeMillis() );
         RendezVous current = lv.getSelectionModel().getSelectedItem();
         current.getIdR();
         LocalDate localDate = dpDate.getValue();
         dpDate.setValue(localDate);
         tfIdP.setValue(String.valueOf(current.getIdPatient()));
         tfIdM.setValue(String.valueOf(current.getIdMedecin()));
         cbEtat.setValue(current.getEtat());
    });
    } 
    @FXML
    private void ajouterRdv(ActionEvent event) throws IOException {
        ServiceRendezVous sr = ServiceRendezVous.getInstance();
        sr.ajouter(new RendezVous(
        Date.valueOf(dpDate.getValue()),
        tfIdP.getValue(),
        tfIdM.getValue(),
        cbEtat.getSelectionModel().getSelectedItem()
     ));
     //********mailling************
      try {
                JavaMail.sendMail("bilel.kasmi@esprit.tn",dpDate.getValue()+" est "+cbEtat.getValue());
            } catch (MessagingException ex) {
                System.out.println("error while sending the email" + ex.getMessage());
            }
     

        JOptionPane.showMessageDialog(null, "Rendez-vous ajouté !");
      
         tfIdP.setValue(null);
         tfIdM.setValue(null);
         cbEtat.setValue(""); 
        
        //reload
  Parent root2 = FXMLLoader .load(getClass().getResource("../gui/AjoutRdv.fxml"));
    Stage window = (Stage) btn_ajouter.getScene().getWindow();
    window.setScene(new Scene(root2));

    }

    @FXML
    private void supprimer(ActionEvent event) {
        	 ServiceRendezVous pdao = ServiceRendezVous.getInstance();
     RendezVous selectedItem2 = lv.getSelectionModel().getSelectedItem();
  
    lv.getItems().remove(selectedItem2);
    pdao.supprimer(selectedItem2);  
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setHeaderText(null);
    alert.setContentText("Rendez-vous suprimé !");
    alert.show();
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
         if (Saisi() == true)
        {
		 RendezVous current = lv.getSelectionModel().getSelectedItem();
            RendezVous p = new RendezVous();
            p.setIdR(current.getIdR());
            LocalDate localDate = dpDate.getValue();
     Date date = Date.valueOf(localDate);
    p.setDate(date);
        p.setIdPatient(tfIdP.getValue());
p.setIdMedecin(tfIdM.getValue());

             p.setEtat(cbEtat.getSelectionModel().getSelectedItem());
            ServiceRendezVous pdao = ServiceRendezVous.getInstance();
            pdao.modifier(p);
               try {
                JavaMail.sendMail("bilel.kasmi@esprit.tn",dpDate.getValue()+" est "+cbEtat.getValue());
            } catch (MessagingException ex) {
                System.out.println("error while sending the email" + ex.getMessage());
            }
     
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Rendez-vous modifié !");
        alert.show();

//reload
Parent root2 = FXMLLoader .load(getClass().getResource("../gui/AjoutRdv.fxml"));
    Stage window = (Stage) btn_modifier.getScene().getWindow();
    window.setScene(new Scene(root2));
   
        }	
        
    }

   
      public static void Alert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    
 private boolean Saisi() {  

        if (  tfIdP.getValue()== null || tfIdM.getValue()== null||  cbEtat.getValue() == null) {
            Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Veuillez bien remplir tous les champs !");
            return false;
        } 
           
        
        return true;
         
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader .load(getClass().getResource("../gui/Welcome.fxml"));
    Stage window = (Stage) btn_retour.getScene().getWindow();
    window.setScene(new Scene(root2));
    }

    @FXML
    private void go_stat(ActionEvent event) throws IOException {
           Parent root2 = FXMLLoader .load(getClass().getResource("../gui/statisque.fxml"));
    Stage window = (Stage) btn_stat.getScene().getWindow();
    window.setScene(new Scene(root2));
    }

    @FXML
    private void Filtrer(ActionEvent event) {
    ObservableList<RendezVous> eventsList = FXCollections.observableArrayList();
    ServiceRendezVous pdao = ServiceRendezVous.getInstance();
    java.sql.Date startDate = java.sql.Date.valueOf(dd.getValue());
    java.sql.Date endDate = java.sql.Date.valueOf(df.getValue());
    List<RendezVous> filteredEvents = pdao.filtrerParDate(startDate, endDate);
    eventsList.clear();
    eventsList.addAll(filteredEvents);
    lv.setItems(eventsList);
    }

    @FXML
    private void rafraichir(ActionEvent event) throws IOException {
          Parent root2 = FXMLLoader .load(getClass().getResource("../gui/AjoutRdv.fxml"));
    Stage window = (Stage) btn_r.getScene().getWindow();
    window.setScene(new Scene(root2));
    }
    
    
}
