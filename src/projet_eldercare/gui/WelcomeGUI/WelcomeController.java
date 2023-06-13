package projet_eldercare.gui.WelcomeGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import projet_eldercare.gui.MaladieGUI.AjoutMaladieController;
import projet_eldercare.gui.Maladie_symptomeGUI.AjoutMaladieSymptomeController;
import projet_eldercare.gui.SymptomeGUI.AjoutSymptomeController;

public class WelcomeController implements Initializable {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void ajouterMaladieButtonClicked(ActionEvent event) throws IOException {
        // Route to AjoutMaladie.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MaladieGUI/AjoutMaladie.fxml"));
        Parent root = loader.load();

        // Get the controller instance and set the primary stage
        AjoutMaladieController ajoutMaladieController = loader.getController();
        ajoutMaladieController.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        String css = this.getClass().getResource("../MaladieGUI/ajoutmaladie.fxml.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void ajouterSymptomeButtonClicked(ActionEvent event) throws IOException {
        // Route to AjoutSymptome.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../SymptomeGUI/AjoutSymptome.fxml"));
        Parent root = loader.load();

        // Get the controller instance and set the primary stage
        AjoutSymptomeController ajoutSymptomeController = loader.getController();
        ajoutSymptomeController.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        String css = this.getClass().getResource("../SymptomeGUI/AjoutSymptome.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void ajouterMaladieSymptomeButtonClicked(ActionEvent event) throws IOException {
        // Route to AjoutMaladieSymptome.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Maladie_symptomeGUI/AjoutMaladieSymptome.fxml"));
        Parent root = loader.load();

        // Get the controller instance and set the primary stage
        AjoutMaladieSymptomeController ajoutMaladieSymptomeController = loader.getController();
        ajoutMaladieSymptomeController.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        String css = this.getClass().getResource("../Maladie_symptomeGUI/ajoutmaladiesymptome.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Button Clicked");
        alert.setHeaderText(null);
        alert.setContentText("You clicked on: " + message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
