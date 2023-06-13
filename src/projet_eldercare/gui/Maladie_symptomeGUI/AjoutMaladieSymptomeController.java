/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projet_eldercare.gui.Maladie_symptomeGUI;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import projet_eldercare.entites.Maladie;
import projet_eldercare.entites.Maladie_Symptome;
import projet_eldercare.entites.Symptome;
import projet_eldercare.gui.WelcomeGUI.WelcomeController;
import projet_eldercare.sevices.ServiceMaladie;
import projet_eldercare.sevices.ServiceSymptome;
import projet_eldercare.sevices.Service_Maladie_Symptome;

/**
 * FXML Controller class
 *
 * @author 21693
 */
public class AjoutMaladieSymptomeController implements Initializable {

    @FXML
    private TextField tauxCorresondance;
    private ObservableList<Maladie_Symptome> Maladie_Symptome_list;
    @FXML
    private TextField searchField;
    @FXML
    private TextField nomMaladie;
    @FXML
    private TextField nomSymptome;
    @FXML
    private TableColumn<Maladie_Symptome, String> idMaladieColumn;
    @FXML
    private TableColumn<Maladie_Symptome, String> idSymptomeColumn;
    @FXML
    private TableColumn<Maladie_Symptome, Integer> tauxCorrcolumn;
    @FXML
    private TableView<Maladie_Symptome> tableView;
    private Stage primaryStage;

    @FXML
    private TableView<Maladie> tableView1;
    @FXML
    private TableColumn<Maladie, String> titreColumn;

    private ObservableList<Maladie> maladiesList;

    @FXML
    private TableView<Symptome> tableView2;
    @FXML
    private TableColumn<Symptome, String> titreColumnSymptome;

    private ObservableList<Symptome> symptomesList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Maladie_Symptome_list = FXCollections.observableArrayList();

        tauxCorrcolumn.setCellValueFactory(new PropertyValueFactory<>("taux_de_correspondance"));
        tableView.setItems(Maladie_Symptome_list);

        Service_Maladie_Symptome sm = new Service_Maladie_Symptome();
        idMaladieColumn.setCellValueFactory(cellData -> {
            int idMaladie = cellData.getValue().getID_Maladie();
            String titreMaladie = sm.getTitreMaladieById(idMaladie);
            return new SimpleStringProperty(titreMaladie);
        });
        idSymptomeColumn.setCellValueFactory(cellData -> {
            int idSymptome = cellData.getValue().getID_Symptome();
            String titreSymptome = sm.getTitreSymptomeById(idSymptome);
            return new SimpleStringProperty(titreSymptome);
        });

        Maladie_Symptome_list.addAll(sm.afficher());

        maladiesList = FXCollections.observableArrayList();

        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tableView.setEditable(true);
        ServiceMaladie sm1 = new ServiceMaladie();
        maladiesList.addAll(sm1.afficher());
        tableView1.setItems(maladiesList);

        symptomesList = FXCollections.observableArrayList();
        titreColumnSymptome.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tableView2.setEditable(true);
        ServiceSymptome sm2 = new ServiceSymptome();
        tableView2.setItems(symptomesList);

        tableView1.setRowFactory(tv -> {
            TableRow<Maladie> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    Maladie selectedMaladie = row.getItem();
                    nomMaladie.setText(selectedMaladie.getTitre());
                }
            });
            return row;
        });

        tableView2.setRowFactory(tv -> {
            TableRow<Symptome> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    Symptome selectedSymptome = row.getItem();
                    nomSymptome.setText(selectedSymptome.getTitre());
                    
                }
            });
            return row;
        });

//        tableView.setRowFactory(tv -> {
//            TableRow<Maladie_Symptome> row = new TableRow<>();
//            row.setOnMouseClicked(event -> {
//                if (event.getClickCount() == 2 && !row.isEmpty()) {
//                    Maladie_Symptome selectedMaladieSymptome = row.getItem();
//                    updateMaladieSymptome(selectedMaladieSymptome);
//                }
//            });
//            return row;
//        });
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            String query = newValue.toLowerCase();

            if (query.isEmpty()) {
                // Show all maladies if the search query is empty
                tableView.setItems(Maladie_Symptome_list);
            } else {
                // Filter the maladies based on the search query
                ObservableList<Maladie_Symptome> filteredMaladiesSymptomes = FXCollections.observableArrayList();

                for (Maladie_Symptome maladie_Symptome : Maladie_Symptome_list) {
                    if (String.valueOf(maladie_Symptome.getID_Symptome()).contains(query)
                            || String.valueOf(maladie_Symptome.getID_Maladie()).contains(query)
                            || String.valueOf(maladie_Symptome.getTaux_de_correspondance()).contains(query)) {
                        filteredMaladiesSymptomes.add(maladie_Symptome);
                    }
                }

                tableView.setItems(filteredMaladiesSymptomes);
            }
        });

    }

    private void showConfirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void ajouterMaladieSymptome(ActionEvent event) {
        String maladieTitre = nomMaladie.getText();
        String symptomeTitre = nomSymptome.getText();
        float taux;
        taux = Float.valueOf(tauxCorresondance.getText());

        // Get the ID of the disease based on the given title
        ServiceMaladie smMaladie = new ServiceMaladie();
        int maladieId = smMaladie.getMaladieIdByTitre(maladieTitre);

        // Get the ID of the symptom based on the given title
        ServiceSymptome smSymptome = new ServiceSymptome();
        int symptomeId = smSymptome.getSymptomeIdByTitre(symptomeTitre);

        if (maladieId != -1 && symptomeId != -1) {
            Maladie_Symptome maladieSymptome = new Maladie_Symptome(maladieId, symptomeId, taux);

            Service_Maladie_Symptome sm = new Service_Maladie_Symptome();
            sm.ajouter(maladieSymptome);

            Maladie_Symptome_list.add(maladieSymptome);

            clearFields();

            showConfirmationAlert("Une ligne dans a table Maladie-Symptome est ajouter !");
        } else {
            showErrorAlert("Maladie ou Symptome non trouvé ");
        }
    }

    private void clearFields() {
        nomMaladie.clear();
        nomSymptome.clear();
        tauxCorresondance.clear();
    }

    @FXML
    private void deleteMaladie(ActionEvent event) {
        // Get the selected maladie from the table viewdele
        Maladie_Symptome selectedMaladieSymptome = tableView.getSelectionModel().getSelectedItem();
        if (selectedMaladieSymptome == null) {
            // No maladie selected, show an error message
            showErrorAlert("Please select a maladie_symptome to delete.");
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Delete MaladieSymptome");
        confirmationAlert.setContentText("Are you sure you want to delete the selected MaladieSymptome?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            Service_Maladie_Symptome sm = new Service_Maladie_Symptome();
            sm.supprimer(selectedMaladieSymptome);

            Maladie_Symptome_list.remove(selectedMaladieSymptome);

            showInfoAlert("Maladie deleted!");
        }
    }

    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../WelcomeGUI/Welcome1.fxml"));
        Parent root = loader.load();

        // Get the controller instance and set the primary stage
        WelcomeController welcomeController = loader.getController();
        welcomeController.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        String css = this.getClass().getResource("../WelcomeGUI/welcome.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
