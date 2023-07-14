/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import entities.Medicament;
import services.ServiceMedicament;
import services.ServiceStock;
import utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.FloatStringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author DONIG
 */
public class AjoutMedicamentController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextArea tfDescription;
    @FXML
    private ChoiceBox<String> cbCat;
   
    //   private listdatamedicament listdata = new listdatamedicament();
    
    

    private String[] cat ={"Dermatologie",
"Diététique Nutrition",
"Endocrinologie",
"Hématologie",
"Immunologie Allergologie",
"Troubles Métaboliques",
"Neurologie",
"Ophtalmologie",
"Parasitologie",
"Pneumologie",
"Psychiatrie",
"Rhumatologie",
"Stomatologie",
"Urologie",
"Vaccins",
"Immunoglobulines",
"Sérothérapie",
"Cancérologie",
"AnesthésiquesLocaux"};
    @FXML
    private TableView<Medicament> tftableview;
   // private TableColumn<Medicament, String> tfNomcol;
     @FXML
    private TableColumn<Medicament, String> tfNomcol;
    @FXML
    private TableColumn<Medicament, Float> tfPrixcol;
    @FXML
    private TableColumn<Medicament, String> tfdesccol;
    @FXML
    private TableColumn<Medicament, String> tfcategcol;
    private Stage primaryStage;
    @FXML
    private Button btn_modif;
    @FXML
    private Button btn_back;
    @FXML
    private TextField btn_search;
    @FXML
    private Button btn_raf;

    public AjoutMedicamentController() {
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbCat.getItems().addAll(cat);
        tableviewControle();
        tftableview.setEditable( true);
        tfNomcol.setCellFactory(TextFieldTableCell.forTableColumn());
    //    tfNomcol.setOnEditCommit(this::modifierNom);
        tfPrixcol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
      //  tfPrixcol.setOnEditCommit(this::modifierPrix);
        tfdesccol.setCellFactory(TextFieldTableCell.forTableColumn());
      //  tfdesccol.setOnEditCommit(this::modifierDescription);
        tfcategcol.setCellFactory(TextFieldTableCell.forTableColumn());
      //  tfcategcol.setOnEditCommit(this::modifierCategorie);
       
      
      
//      
//      //search
//         btn_search.textProperty().addListener((observable, oldValue, newValue) -> {
//    ServiceMedicament pdao = ServiceMedicament.getInstance();
//    List<Medicament> r = pdao.rechercher(newValue);
//    ObservableList<Medicament> nommedic = FXCollections.observableArrayList(r);
////   listviewP.setStyle("-fx-font-family: Arial; -fx-font-size: 14px; -fx-font-weight: bold;");
//   lv.setItems(nommedic);
//  
//lv.setCellFactory(param -> new ListCell<Medicament>() {
//    @Override
//    protected void updateItem(Medicament item, boolean empty) {
//        super.updateItem(item, empty);
//        if (empty || item == null) {
//            setText(null);
//        } else {
//            setText(item.toString()); // or any other method that returns a string representation of the Reclamation object
//         //   setFont(Font.font("Arial", FontWeight.BOLD, 24));
//        }
//    }
//});
//
//});
         
        
    

    
      
      
        
         //listview
 tftableview.setOnMouseClicked((MouseEvent event)->{
            

        Medicament current = tftableview.getSelectionModel().getSelectedItem();
        // txt_id.setText(Integer.toString(current.getId()));
       
         current.getIdMedicament();
          int idMedicament = current.getIdMedicament();
        System.out.println("ID du médicament : " + idMedicament);
         current.getNom();
        current.getPrix();
         current.getDescription();
        current.getCategorie();
//System.out.println(current+"eeeee");
        tfNom.setText(current.getNom());
tfPrix.setText(Float.toString(current.getPrix()));
//tfNom.setText(current.getNom());
        tfDescription.setText(current.getDescription());
              cbCat.setValue(current.getCategorie());


          
      
//*************************************************************************************************************************************
    });
        
        ServiceStock sr = ServiceStock.getInstance();
 sr.loadMedicaments();
        //listview
//        lv.setItems(sr.getMedicationNamess()); 
//        
//         lv.setCellFactory(param -> new ListCell<Medicament>() {
//        @Override
//        protected void updateItem(Medicament item, boolean empty) {
//            super.updateItem(item, empty);
//            if (empty || item == null) {
//                setText(null);
//            } else {
//                setText(item.getNom());
//            }
//        }
//    });
//        
//        
// lv.setOnMouseClicked((MouseEvent event)->{
//            
//
//        Medicament current = lv.getSelectionModel().getSelectedItem();
//        // txt_id.setText(Integer.toString(current.getId()));
//       
//         current.getIdMedicament();
////         System.out.println(current+"   id");
////
////         current.getNom();
////         current.getPrix();
////         current.getDescription();
////         current.getCategorie();
//
//        tfNom.setText(current.getNom());
//      
////*************************************************************************************************************************************
//    });
        
    }  
    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage=primaryStage ; 
    }
    
    @FXML
     private void ajouterMedicament(ActionEvent event) throws IOException {
          if (Saisi() == true)
        {
ServiceMedicament m1 = ServiceMedicament.getInstance();
        m1.ajouter(new Medicament(tfNom.getText(),
                Float.parseFloat(tfPrix.getText()) ,tfDescription.getText(),
                cbCat.getValue()));
        JOptionPane.showMessageDialog(null, "Medicament ajouté !");
        tableviewControle();
        }
    }
    private void tableviewControle(){
        Connection cnx = DataSource.getInstance().getCnx();
ServiceMedicament sm = ServiceMedicament.getInstance();
        List<Medicament> list = new ArrayList<>();
        list=sm.displayAll().stream().collect(Collectors.toList());
        //System.out.println(list);
        ObservableList medlist = FXCollections.observableArrayList(list);
        //System.out.println(medlist);
        tftableview.setItems(medlist);
        tfNomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tfPrixcol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tfdesccol.setCellValueFactory(new PropertyValueFactory<>("description"));
        tfcategcol.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        
    }


    @FXML
private void supprimerligne(ActionEvent event) {
ServiceMedicament sm = ServiceMedicament.getInstance();
    Medicament selectedMed = tftableview.getSelectionModel().getSelectedItem();

   
     
  
    tftableview.getItems().remove(selectedMed);
      sm.supprimer(selectedMed);  
        }
    






    
    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {

Parent root3 = FXMLLoader .load(getClass().getResource("../gui/Welcome.fxml"));
    Stage window = (Stage) btn_back.getScene().getWindow();
    window.setScene(new Scene(root3));
    }
    
    
    
    //controle de saisie
         public static void Alert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    
 private boolean Saisi() {  

        if ( tfNom.getText().isEmpty() || tfPrix.getText().isEmpty() || tfDescription.getText().isEmpty()||  cbCat.getValue() == null) {
            Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

          

          
          if (!Pattern.matches("[A-Za-z]*", tfNom.getText())) {
                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le nom de medicament ! ");
                return false;
            }
           
           
        }
        return true;
         
    }
 
//   tfNom.setText(current.getNom());
//tfPrix.setText(Float.toString(current.getPrix()));
//tfNom.setText(current.getNom());
//        tfDescription.setText(current.getDescription());
//              cbCat.setValue(current.getCategorie());
 

    @FXML
    private void modifier(ActionEvent event) throws IOException {
         if (Saisi() == true)
        {
		 Medicament current = tftableview.getSelectionModel().getSelectedItem();
                  int idcurrent = tftableview.getSelectionModel().getSelectedItem().getIdMedicament();
                    System.out.println("idddd"+idcurrent);
            Medicament p = new Medicament();
//            p.setId(Integer.parseInt(txt_id.getText()));
            p.setIdMedicament(current.getIdMedicament());
            System.out.println("idddd"+current.getIdMedicament());
//            p.setNom(txt_nom.getText());
  //          p.setPrenom(txt_prenom.getText());
            p.setNom(tfNom.getText());
p.setPrix(Float.parseFloat(tfPrix.getText()));
             p.setDescription(tfDescription.getText());
             p.setCategorie(cbCat.getSelectionModel().getSelectedItem());
            ServiceMedicament pdao = ServiceMedicament.getInstance();
            pdao.modifier(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Medicament modifié !");
        alert.show();

//reload
Parent root2 = FXMLLoader .load(getClass().getResource("..gui/AjoutMedicament.fxml"));
    Stage window = (Stage) btn_modif.getScene().getWindow();
    window.setScene(new Scene(root2));
 
        }	
    }

    @FXML
    private void rafraichir(ActionEvent event) throws IOException {
        Parent root3 = FXMLLoader .load(getClass().getResource("../gui/AjoutMedicament.fxml"));
    Stage window = (Stage) btn_raf.getScene().getWindow();
    window.setScene(new Scene(root3));
    }

    
    
    
    
    
    
    
    
    
    
}
    
    
    
    



      
