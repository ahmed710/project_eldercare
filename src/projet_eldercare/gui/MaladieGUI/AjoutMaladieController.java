/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projet_eldercare.gui.MaladieGUI;

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
import javafx.scene.control.DialogPane;
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
import projet_eldercare.utils.Classification_etiologique;
import projet_eldercare.utils.Classification_fonctionelle;
import projet_eldercare.sevices.ServiceMaladie;
import projet_eldercare.sevices.ServicePDF;
import projet_eldercare.sevices.Service_Maladie_Symptome;

/**
 * FXML Controller class
 *
 * @author 21693
 */
public class AjoutMaladieController implements Initializable {

    @FXML
    private Label myLabel1;
    @FXML
    private Label myLabel2;
    @FXML
    private TextField titre;
    @FXML
    private TextArea area;
    @FXML
    private ComboBox<Classification_etiologique> myChoiceBox1;
    @FXML
    private ComboBox<Classification_fonctionelle> myChoiceBox2;
    @FXML
    private TableView<Maladie> tableView;
    @FXML
    private TableColumn<Maladie, String> titreColumn;
    @FXML
    private TableColumn<Maladie, String> descriptionColmn;
    @FXML
    private TableColumn<Maladie, String> classification_etiologiqueColumn;
    @FXML
    private TableColumn<Maladie, String> classification_fonctionelleColumn;
  
    private ObservableList<Maladie> maladiesList;
    @FXML
    private TextField searchField;
    private Stage primaryStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        maladiesList = FXCollections.observableArrayList();

        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionColmn.setCellValueFactory(new PropertyValueFactory<>("description"));
        classification_etiologiqueColumn.setCellValueFactory(new PropertyValueFactory<>("classification_etiologique"));
        classification_fonctionelleColumn.setCellValueFactory(new PropertyValueFactory<>("classification_fonctionelle"));

        titreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColmn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Enable editing for the table view
        tableView.setEditable(true);

        myChoiceBox1.setItems(FXCollections.observableArrayList(Classification_etiologique.values()));
        myChoiceBox2.setItems(FXCollections.observableArrayList(Classification_fonctionelle.values()));

        ServiceMaladie sm = new ServiceMaladie();
        maladiesList.addAll(sm.afficher());

        tableView.setItems(maladiesList);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            String query = newValue.toLowerCase();

            if (query.isEmpty()) {
                // Show all maladies if the search query is empty
                tableView.setItems(maladiesList);
            } else {
                // Filter the maladies based on the search query
                ObservableList<Maladie> filteredMaladies = FXCollections.observableArrayList();

                for (Maladie maladie : maladiesList) {
                    if (maladie.getTitre().toLowerCase().contains(query)
                            || maladie.getDescription().toLowerCase().contains(query)
                            || maladie.getClassification_etiologique().toLowerCase().contains(query)
                            || maladie.getClassification_fonctionelle().toLowerCase().contains(query)) {
                        filteredMaladies.add(maladie);
                    }
                }

                tableView.setItems(filteredMaladies);
            }
        });

        tableView.setRowFactory(tv -> {
            TableRow<Maladie> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    handleMaladieClick(event);
                }

                if (event.getClickCount() == 3 && !row.isEmpty()) {
                    Maladie selectedMaladie = row.getItem();
                    updateMaladie(selectedMaladie);

                }
                  if (event.getClickCount() == 2 && !row.isEmpty()) {
                 Maladie selectedMaladie = row.getItem();
                handlePrintClick(selectedMaladie);
            }
            });
            return row;
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
    private void ajoutMaladie(ActionEvent event) {
        String selectedEtio = myChoiceBox1.getSelectionModel().getSelectedItem().toString();
        String selectedFonc = myChoiceBox2.getSelectionModel().getSelectedItem().toString();

        if (selectedEtio != null && selectedFonc != null) {
            try {

                ServiceMaladie sm = new ServiceMaladie();
                Maladie maladie = new Maladie(
                        titre.getText(),
                        area.getText(),
                        selectedEtio,
                        selectedFonc);
                sm.ajouter(maladie);

                maladiesList.add(maladie);

                showInfoAlert("Maladie ajoutée !");
            } catch (IllegalArgumentException ex) {

                showErrorAlert("Invalid classification value!");
            }

            titre.clear();
            area.clear();
        }
    }

    @FXML
    private void deleteMaladie(ActionEvent event) {
        // Get the selected maladie from the table viewdele
        Maladie selectedMaladie = tableView.getSelectionModel().getSelectedItem();
        if (selectedMaladie == null) {
            // No maladie selected, show an error message
            showErrorAlert("Please select a maladie to delete.");
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Delete Maladie");
        confirmationAlert.setContentText("Are you sure you want to delete the selected maladie?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            ServiceMaladie sm = new ServiceMaladie();
            sm.supprimer(selectedMaladie);

            maladiesList.remove(selectedMaladie);

            showInfoAlert("Maladie deleted!");
        }
    }

    private void updateMaladie(Maladie maladie) {
        TextInputDialog dialog = new TextInputDialog(maladie.getTitre());
        dialog.setTitle("Update Maladie");
        dialog.setHeaderText("Enter the updated details:");
        dialog.setContentText("Title:");
        Optional<String> updatedTitle = dialog.showAndWait();

        if (updatedTitle.isPresent()) {
            maladie.setTitre(updatedTitle.get());
            ServiceMaladie sm = new ServiceMaladie();
            sm.modifier(maladie);

            tableView.refresh();
            showInfoAlert("Maladie updated!");
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
private void handleMaladieClick(MouseEvent event) {
    if (event.getClickCount() == 3) {
        Maladie selectedMaladie = tableView.getSelectionModel().getSelectedItem();
        if (selectedMaladie != null) {
            int diseaseId = selectedMaladie.getID_Maladie();

            // Retrieve the symptoms related to the selected disease
            Service_Maladie_Symptome sms = new Service_Maladie_Symptome();
            List<Symptome> relatedSymptoms = sms.getRelatedSymptomes(diseaseId);

            if (!relatedSymptoms.isEmpty()) {
                // Display the related symptoms
                StringBuilder symptomsText = new StringBuilder();
                for (Symptome symptom : relatedSymptoms) {
                    symptomsText.append(symptom.getTitre()).append("\n");
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Related Symptoms");
                alert.setHeaderText("Les Symptômes en relation avec la maladie: " + selectedMaladie.getTitre());
                alert.setContentText(symptomsText.toString());

                // Remove the icon from the alert dialog
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);

                // Apply custom styles to the alert dialog
                dialogPane.getStylesheets().add(getClass().getResource("alertStyles.css").toExternalForm());
                dialogPane.getStyleClass().add("custom-alert");

                // Set the preferred width and height of the dialog pane to match the pane window
                dialogPane.setPrefWidth(tableView.getScene().getWindow().getWidth());
                dialogPane.setPrefHeight(tableView.getScene().getWindow().getHeight());

                alert.showAndWait();
            } else {
                // No related symptoms found
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Related Symptoms");
                alert.setHeaderText("Aucun symptôme en relation avec la maladie: " + selectedMaladie.getTitre());

                // Remove the icon from the alert dialog
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);

                // Apply custom styles to the alert dialog
                dialogPane.getStylesheets().add(getClass().getResource("alertStyles.css").toExternalForm());
                dialogPane.getStyleClass().add("custom-alert");

                // Set the preferred width and height of the dialog pane to match the pane window
                dialogPane.setPrefWidth(tableView.getScene().getWindow().getWidth());
                dialogPane.setPrefHeight(tableView.getScene().getWindow().getHeight());

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
    
@FXML
private void handlePrintClick(Maladie selectedMaladie) {
    if (selectedMaladie != null) {
        ServicePDF.printPDF(selectedMaladie);
    }
}

}
