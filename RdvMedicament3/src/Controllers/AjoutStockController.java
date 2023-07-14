/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import entities.Medicament;
import entities.RendezVous;
import entities.Stock;
import services.ServiceRendezVous;
import services.ServiceStock;
import services.servicepharmacie;
import utils.DataSource;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;


/**
 * FXML Controller class
 *
 * @author DONIG
 */
public class AjoutStockController implements Initializable {
    
     private Stage primaryStage;
    
    @FXML
    private ComboBox<String> lvMed;
    @FXML
    private ComboBox<String> lvPh;
    @FXML
    private TextField tfQuantite;
//           private listdatastock listdataS = new listdatastock();
//                      private listdatamedicament listdataM = new listdatamedicament();
 //private ServiceStock sr = ServiceStock.getInstance();

    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_supprim;
    @FXML
    private Button btn_back;
    @FXML
    private ListView<Stock> lv;
    @FXML
    private Button btn_modif;
    @FXML
    private Button btn_rech;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
         //    ServiceStock sr = ServiceStock.getInstance();
ServiceStock sr = ServiceStock.getInstance();
 sr.loadMedicaments();
 lvMed.setItems(sr.getMedicationNames());
 
 //*****
 servicepharmacie sp = servicepharmacie.getInstance();
 sp.loadpharmacies();
 lvPh.setItems(sp.getpharmacies());
        //sr.loadMedicaments(); 
       // lvMed.setItems(listdataM.getMedicationNames());
       ServiceStock sss = ServiceStock.getInstance();
       sss.loadStocks();

                 lv.setItems(sss.getstock()); 
                 

                       lv.setOnMouseClicked((MouseEvent event)->{
            

        Stock current = lv.getSelectionModel().getSelectedItem();
       
         current.getIdStock();

     lvMed.setValue(String.valueOf(current.getIdMedicament()));
lvPh.setValue(String.valueOf(current.getIdPharmacie()));
         tfQuantite.setText(Integer.toString(current.getQuantite()));


//*************************************************************************************************************************************
    });
                 
    }
   
 


    @FXML
    private void ajouterStock(javafx.event.ActionEvent event) throws IOException {
         if (Saisi() == true)
        {
        ServiceStock sr = ServiceStock.getInstance();
        sr.ajouter(new Stock(lvPh.getValue(),
                lvMed.getValue(),
                  Integer.parseInt(tfQuantite.getText())));
        JOptionPane.showMessageDialog(null, "Stock ajouté !");
    Parent root2 = FXMLLoader .load(getClass().getResource("../gui/AjoutStock.fxml"));
    Stage window = (Stage) btn_ajouter.getScene().getWindow();
    window.setScene(new Scene(root2));
    }}

    
    @FXML
    private void supprimerStock(javafx.event.ActionEvent event) {
         ServiceStock ss = ServiceStock.getInstance();
        
        List<Stock> list = new ArrayList<>();
        list=ss.afficher().stream().collect(Collectors.toList());
         Stock selectedItem2 = lv.getSelectionModel().getSelectedItem();
        Alert ConfirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        ConfirmationDialog.setTitle("confirmation de suppression");
        ConfirmationDialog.setHeaderText("confirmation");
        ConfirmationDialog.setContentText("voulez vous supprimer ce stock ?");
        ConfirmationDialog.showAndWait();
if(ConfirmationDialog.getResult()==ButtonType.OK){
        lv.getItems().remove(selectedItem2);
   ss.supprimer(selectedItem2);  }
    
   
    }
    
     @FXML
    private void backButtonClicked(javafx.event.ActionEvent event) throws IOException {
          Parent root2 = FXMLLoader .load(getClass().getResource("../gui/Welcome.fxml"));
    Stage window = (Stage) btn_back.getScene().getWindow();
    window.setScene(new Scene(root2));
    }

    @FXML
    private void modifier(javafx.event.ActionEvent event) throws IOException {
         if (Saisi() == true)
        {
         Stock selectedItem2 = lv.getSelectionModel().getSelectedItem();
            Stock p = new Stock();

            p.setIdStock(selectedItem2.getIdStock());


    
        p.setIdPharmacie(lvPh.getValue());
p.setIdMedicament(lvMed.getValue());
             p.setQuantite(Integer.parseInt(tfQuantite.getText()));

            ServiceStock pdao = ServiceStock.getInstance();
            pdao.modifier(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Stock modifié !");
        alert.show();

//reload
Parent root2 = FXMLLoader .load(getClass().getResource("../gui/AjoutStock.fxml"));
    Stage window = (Stage) btn_modif.getScene().getWindow();
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

        if ( tfQuantite.getText().isEmpty() ) {
            Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

          

          
      
           
           
        }
        return true;
         
    }

    @FXML
   private void go_rech(javafx.event.ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader .load(getClass().getResource("../gui/recherchestock.fxml"));
    Stage window = (Stage) btn_rech.getScene().getWindow();
    window.setScene(new Scene(root2));
    }
    
    
    
}

