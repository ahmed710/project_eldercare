/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entities.Commande;
import entities.Pharmacie;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ServiceCommande;
import services.ServicePDF;
import services.ServicePharmacie;
import utlis.DataSource;

/**
 * FXML Controller class
 *
 * @author Wimbee018
 */
public class AjoutCommandeController implements Initializable {
    private Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private ComboBox<String> nomcombo;
    @FXML
    private ComboBox<String> comboetat;
    @FXML
    private TextField tfadresse;
    @FXML
    private TableView<Commande> tvafficher;
    private TableColumn<Pharmacie, String> nomphacol;
    @FXML
    private TableColumn<Commande, String> datecol;
    @FXML
    private TableColumn<Commande, String> adressecol;
    @FXML
    private TableColumn<Commande, String>etatcol;
    private TableView<Pharmacie> tvnomphar;
    @FXML
    private TableColumn<Commande, String> Pharcol;
    /**
     * Initializes the controller class.
     */
    private String[] etats = {"En cours de préparation", "En cours de livraison", "Livré", "Annulé"};
    
       ServicePharmacie ph = new ServicePharmacie();
    /**
     * Initializes the controller class.
     */
    private List<String> Phars = ph.getNomPhar();
    @FXML
    private TextField day;
    @FXML
    private TextField month;
    @FXML
    private TextField year;
    ServiceCommande cm = new ServiceCommande();
    @FXML
    private Button modifButtom;
    @FXML
    private Button suppButtom;
    
    
    
    
    public void setEtat(String message) {
        ObservableList<String> e = null;
        e.add(message);
        this.comboetat.setItems(e);
    }
    public void setAdresse(String message) {
        this.tfadresse.setText(message);
    }
    public void setday(String message) {
        this.day.setText(message);
    }
    public void setMonth(String message) {
        this.month.setText(message);
    }
    public void setYear(String message) {
        this.year.setText(message);
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboboxEtat();
        comboboxPhar();
        modifButtom.setDisable(true);
        suppButtom.setDisable(true);
        tableview();
        //tableview2();
        // TODO
    }    
    public void comboboxEtat() {
        List<String> list = new ArrayList<>(); 
        for (String data : etats) {
            list.add(data);
        }
        ObservableList dataList = FXCollections.observableArrayList(list);
        comboetat.setItems(dataList);
        comboetat.setValue("En cours de préparation");
        //comboetat.getSelectionModel().isEmpty()
        //etat.getSelectionModel().getSelectedItem()
    }
    
    public void comboboxPhar() {
        List<String> list = new ArrayList<>(); 
        for (String data : Phars) {
            list.add(data);
        }
        ObservableList dataList = FXCollections.observableArrayList(list);
        nomcombo.setItems(dataList);
        nomcombo.setValue(list.get(0));
        //comboetat.getSelectionModel().isEmpty()
        //etat.getSelectionModel().getSelectedItem()
    }

        private void tableview() {
         ServiceCommande cm = new ServiceCommande();
        //ObservableList<Commande> commande = FXCollections.observableArrayList(cm.afficher());
        //ObservableList<Pharmacie> getnompha = FXCollections.observableArrayList(cm.getNom());
        ObservableList<Commande> commande = FXCollections.observableArrayList(cm.getCommWithPhar());
        tvafficher.setItems(commande);
        //tvnomphar.setItems(getnompha);
        adressecol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date_commande"));
        etatcol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        Pharcol.setCellValueFactory(new PropertyValueFactory<>("nom_pharmacie"));
    }
        /*private void tableview2() {
         ServiceCommande cm = new ServiceCommande();
        ObservableList<Pharmacie> getnompha = FXCollections.observableArrayList(cm.getNom());
        tvnomphar.setItems(getnompha);
        nomphacol.setCellValueFactory(new PropertyValueFactory<>("nom_pharmacie"));
    }*/

    @FXML
    private void AjouterCommonde(ActionEvent event) {
    String nomPhar = nomcombo.getSelectionModel().getSelectedItem();
    String etat = comboetat.getSelectionModel().getSelectedItem();
    String adresse = tfadresse.getText();
    String dayText = day.getText();
    String monthText = month.getText();
    String yearText = year.getText();

    ServiceCommande cm = new ServiceCommande();
    ServicePharmacie ph = new ServicePharmacie();

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);

    if (nomPhar.isEmpty() || nomPhar == null) {
        alert.setContentText("Le nom de la pharmacie est obligatoire.");
        alert.showAndWait();
    } else if (etat.isEmpty() || etat == null) {
        alert.setContentText("L'état est obligatoire.");
        alert.showAndWait();
    } else if (adresse.isEmpty() || adresse == null) {
        alert.setContentText("L'adresse est obligatoire.");
        alert.showAndWait();
    } else if (dayText.isEmpty() || monthText.isEmpty() || yearText.isEmpty()) {
        alert.setContentText("Veuillez entrer une date valide.");
        alert.showAndWait();
    } else {
        try {
            int d = Integer.parseInt(dayText);
            int m = Integer.parseInt(monthText);
            m = m - 1;
            int y = Integer.parseInt(yearText);
            y -= 1900;

            if (d < 1 || d > 31 || m < 0 || m > 11 || y < 0) {
                alert.setContentText("Date de commande invalide.");
                alert.showAndWait();
            } else {
                cm.ajouter(new Commande(ph.getIdPharByNom(nomPhar), adresse, nomPhar, new Date(y, m, d), etat));
                tableview();
            }
        } catch (NumberFormatException e) {
            alert.setContentText("Veuillez entrer une date valide.");
            alert.showAndWait();
        }
    }
}


    @FXML
    private void SupprimerCommande(ActionEvent event) {
            Commande selectedCommande = (Commande) tvafficher.getSelectionModel().getSelectedItem();
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationDialog.setTitle("Confirmation");
    confirmationDialog.setHeaderText("Confirmation");
    confirmationDialog.setContentText("Êtes-vous sûr de supprimer cette commande?");
    confirmationDialog.initOwner(tfadresse.getScene().getWindow());
    confirmationDialog.showAndWait();
    if (confirmationDialog.getResult() == ButtonType.OK) {        
        if (selectedCommande != null) {
            cm.supprimer(selectedCommande);
        }
        suppButtom.setDisable(true);        
        modifButtom.setDisable(true);        
        
        
    }
    VidF();
        //pour actualiser le tableview
        tableview();

    }

    @FXML
    private void ImprimerCommande(ActionEvent event) {
        Commande selectedCommande = (Commande) tvafficher.getSelectionModel().getSelectedItem();
        ServicePDF pdf = new ServicePDF();
        pdf.printPDF(selectedCommande);
        
        // Show information window
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Impression réussie");
    alert.setHeaderText(null);
    alert.setContentText("La commande a été imprimée avec succès.");
    alert.showAndWait();
        
        // clear all fields
        VidF();
    }

    @FXML
    private void MofComm(ActionEvent event) {
        Commande selectedCommande = (Commande) tvafficher.getSelectionModel().getSelectedItem();
        
        String nomPhar = nomcombo.getSelectionModel().getSelectedItem();
        String etat = comboetat.getSelectionModel().getSelectedItem();
        String adresse = tfadresse.getText();
        int d = Integer.parseInt(day.getText());
        int m = Integer.parseInt(month.getText());
        m = m-1;
        int y = Integer.parseInt(year.getText());
        y-=1900;
        
        ServiceCommande cm = new ServiceCommande();
        ServicePharmacie ph= new ServicePharmacie();
        
        Commande c = new Commande();
        c.setAdresse(adresse);
        c.setDate_commande(new Date(y,m,d));
        c.setEtat(etat);
        c.setID_commande(selectedCommande.getID_commande());
        c.setID_pharmacie(ph.getIdPharByNom(nomPhar));
        c.setNom_pharmacie(nomPhar);
        cm.modifier(c);
        
        // clear all fields
        VidF();
                
        modifButtom.setDisable(true);        
        suppButtom.setDisable(true);
        
        tableview();
    }

    @FXML
    private void prime_selected(MouseEvent event) {
        /*int index = tableau_prime_2.getSelectionModel().getSelectedIndex();
        prime h = tableau_prime_2.getSelectionModel().getSelectedItem();
        id_prime_textfield_2.setText(id_prime_colonne_2.getCellData(index).toString());
        type_prime_textfield_2.setText(type_prime_colonne_2.getCellData(index));
        valeur_prime_textfield_2.setText(valeur_prime_colonne_2.getCellData(index).toString());*/
        
        
        int index = tvafficher.getSelectionModel().getSelectedIndex();
        Commande selectedCommande = (Commande) tvafficher.getSelectionModel().getSelectedItem();
        
        // Etat Field
        comboetat.setValue(selectedCommande.getEtat());
        
        // Pharmacie Field        
        nomcombo.setValue(selectedCommande.getNom_pharmacie());
        
        // Address Field        
        tfadresse.setText(adressecol.getCellData(index));
        
        // Date Field
        DateFormat dfdny = new SimpleDateFormat("yyyy");
        String Sny = dfdny.format(selectedCommande.getDate_commande());
        year.setText(Sny);
        
        DateFormat dfdnm = new SimpleDateFormat("MM");
        String Snm = dfdnm.format(selectedCommande.getDate_commande());
        month.setText(Snm);
        
        DateFormat dfdnj = new SimpleDateFormat("dd");
        String Snj = dfdnj.format(selectedCommande.getDate_commande());
        day.setText(Snj);
        
        modifButtom.setDisable(false);
        suppButtom.setDisable(false);
        
    }    

    private void VidF() {
    nomcombo.setValue("");
        comboetat.setValue("");
        setAdresse("");
        setMonth("");
        setYear("");
        setday("");}
    @FXML
    private void viderFields(ActionEvent event) {
        VidF();        
    }
}
