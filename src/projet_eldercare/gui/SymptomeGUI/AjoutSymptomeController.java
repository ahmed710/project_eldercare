/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projet_eldercare.gui.SymptomeGUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projet_eldercare.entites.Maladie;
import projet_eldercare.entites.Symptome;
import projet_eldercare.gui.WelcomeGUI.WelcomeController;
import projet_eldercare.utils.Organe;
import projet_eldercare.sevices.ServiceSymptome;
import projet_eldercare.sevices.Service_Maladie_Symptome;

public class AjoutSymptomeController implements Initializable {

    @FXML
    private Label titreLabel;
    @FXML
    private TextField titre;
    @FXML
    private Label descriptionLabel;
    @FXML
    private TextArea area;
    @FXML
    private Label jourLabel;
    @FXML
    private TextField jour;
    @FXML
    private Label semaineLabel;
    @FXML
    private TextField semaine;
    @FXML
    private Label titre1Column;
    @FXML
    private TextField duree;
    @FXML
    private Label myLabel211;
    @FXML
    private ComboBox<Organe> organeComboBox;
    @FXML
    private TableView<Symptome> tableView;
    @FXML
    private TableColumn<Symptome, String> titreColumn;
    @FXML
    private TableColumn<Symptome, String> descriptionColmn;
    @FXML
    private TableColumn<Symptome, Integer> parjourColumn;
    @FXML
    private TableColumn<Symptome, Integer> parsemaineColumn;
    @FXML
    private TableColumn<Symptome, Float> dureeColumn;
    @FXML
    private TableColumn<Symptome, String> organeColumn1;

    private ObservableList<Symptome> symptomesList;

    @FXML
    private TextField searchField;
    private Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        symptomesList = FXCollections.observableArrayList();
        titreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColmn.setCellFactory(TextFieldTableCell.forTableColumn());
        ServiceSymptome sm2 = new ServiceSymptome();
        symptomesList.addAll(sm2.afficher());

        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionColmn.setCellValueFactory(new PropertyValueFactory<>("description"));
        parjourColumn.setCellValueFactory(new PropertyValueFactory<>("frequenceParJour"));
        parsemaineColumn.setCellValueFactory(new PropertyValueFactory<>("frequenceParSemaine"));
        dureeColumn.setCellValueFactory(new PropertyValueFactory<>("duree"));
        organeColumn1.setCellValueFactory(new PropertyValueFactory<>("organe"));

        tableView.setItems(symptomesList);

        tableView.setRowFactory(tv -> {
            TableRow<Symptome> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()){
                    handleSymptomeClick(event);
                }
                    
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Symptome selectedSymptome = row.getItem();
                    updateSymptome(selectedSymptome);
                     
                }
            });
            return row;
        });


        

        organeComboBox.setItems(FXCollections.observableArrayList(Organe.values()));

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            String query = newValue.toLowerCase();

            if (query.isEmpty()) {
                // Show all maladies if the search query is empty
                tableView.setItems(symptomesList);
            } else {
                // Filter the maladies based on the search query
                ObservableList<Symptome> filteredSymptomes = FXCollections.observableArrayList();

                for (Symptome symptome : symptomesList) {
                    if (symptome.getTitre().toLowerCase().contains(query)
                            || symptome.getDescription().toLowerCase().contains(query)
                            || String.valueOf(symptome.getFrequenceParJour()).contains(query)
                            || String.valueOf(symptome.getFrequenceParSemaine()).contains(query)) {
                        filteredSymptomes.add(symptome);
                    }
                }

                tableView.setItems(filteredSymptomes);
            }
        });
    }

    @FXML
    private void ajoutSymptome(ActionEvent event) {
        String title = titre.getText();
        String description = area.getText();
        int perDay = Integer.parseInt(jour.getText());
        int perWeek = Integer.parseInt(semaine.getText());
        int duration = Integer.parseInt(duree.getText());
        String selectedOrgane = organeComboBox.getSelectionModel().getSelectedItem().toString();

        Symptome symptome = new Symptome(title, description, perDay, perWeek, duration, selectedOrgane);
        ServiceSymptome sm = new ServiceSymptome();
        sm.ajouter(symptome);

        symptomesList.add(symptome);

        clearFields();

        showConfirmationAlert("Symptome added!");
    }

    private void clearFields() {
        titre.clear();
        area.clear();
        jour.clear();
        semaine.clear();
        duree.clear();
        organeComboBox.getSelectionModel().clearSelection();
    }

    private void showConfirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void deleteSymtome(ActionEvent event) {
        // Get the selected symptome from the table view
        Symptome selectedSymptome = tableView.getSelectionModel().getSelectedItem();
        if (selectedSymptome == null) {
            // No symptome selected, show an error message
            showErrorAlert("Please select a symptome to delete.");
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Delete Symptome");
        confirmationAlert.setContentText("Are you sure you want to delete the selected symptome?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            ServiceSymptome sm = new ServiceSymptome();
            sm.supprimer(selectedSymptome);

            symptomesList.remove(selectedSymptome);

            showInfoAlert("Symptome deleted!");
        }
    }

    private void showErrorAlert(String please_select_a_symptome_to_delete) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void showInfoAlert(String symptome_deleted) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void updateSymptome(Symptome symptome) {
        TextInputDialog dialog = new TextInputDialog(symptome.getTitre());
        dialog.setTitle("Update Symptome");
        dialog.setHeaderText("Enter the updated details:");
        dialog.setContentText("Title:");
        Optional<String> updatedTitle = dialog.showAndWait();

        if (updatedTitle.isPresent()) {
            symptome.setTitre(updatedTitle.get());
            ServiceSymptome ss = new ServiceSymptome();
            ss.modifier(symptome);

            tableView.refresh();
            showInfoAlert("Symptome updated!");
        }
    }
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
private void handleSymptomeClick(MouseEvent event) {
    if (event.getClickCount() == 1) {
        Symptome selectedMaladie = tableView.getSelectionModel().getSelectedItem();
        if (selectedMaladie != null) {
            int diseaseId = selectedMaladie.getID_Symptome();
            
            // Retrieve the symptoms related to the selected disease
            Service_Maladie_Symptome sms = new Service_Maladie_Symptome();
            List<Maladie> relatedSymptoms = sms.getRelatedMaladies(diseaseId)  ; 
            
            if (!relatedSymptoms.isEmpty()) {
                // Display the related symptoms
                StringBuilder symptomsText = new StringBuilder();
                for (Maladie maladie : relatedSymptoms) {
                    symptomsText.append(maladie.getTitre()).append("\n");
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Les maladies en relation");
                alert.setHeaderText(" Les maladies en relation avec la Symptom:  " + selectedMaladie.getTitre());
                alert.setContentText(symptomsText.toString());
                alert.showAndWait();
            } else {
                // No related symptoms found
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Related Symptoms");
                alert.setHeaderText("Aucune maladies en relation avec la Symptom : " + selectedMaladie.getTitre());
                alert.showAndWait();
            }
        }
    }
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
