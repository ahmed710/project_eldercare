package Controllers;

import static Controllers.Connecter.getUserConnected;
import com.esprit.entities.Ordonnance;
import com.esprit.entities.User;
import com.esprit.services.ServiceOdonnance;
import com.esprit.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

//yesta3melha patient bsh ilawj 3al medecin
public class Medecin_searchController implements Initializable {

    @FXML
    private TableView<Ordonnance> TV_search;
    @FXML
    private TableColumn<Ordonnance, String> TC_nom_medecin;
    @FXML
    private TableColumn<Ordonnance, String> TC_nom_patient;
    @FXML
    private TableColumn<Ordonnance, Integer> TC_num_ordonnance;
    @FXML
    private TableColumn<Ordonnance, String> TC_date;
    @FXML
    private TableColumn<Ordonnance, String> TC_medicaments;
    @FXML
    private TextField search_bar;

    private Connection connection;

    ObservableList<Ordonnance> patientSearch = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeColumns();
        loadData();
        setupSearchListener();
    }

    private void initializeColumns() {
        TC_num_ordonnance.setCellValueFactory(new PropertyValueFactory<>("ID_ordonnace"));
        TC_nom_patient.setCellValueFactory(new PropertyValueFactory<>("ID_patient"));
        TC_nom_medecin.setCellValueFactory(new PropertyValueFactory<>("ID_medecin"));
        TC_medicaments.setCellValueFactory(new PropertyValueFactory<>("ID_medicament"));
        TC_date.setCellValueFactory(new PropertyValueFactory<>("Date"));
    }

    private void loadData() {
        // Get the ID  current connected user
        User currentUserId = getUserConnected();

        ServiceOdonnance so = new ServiceOdonnance();
        ObservableList<Ordonnance> ordonnanceList = FXCollections.observableArrayList();
        ordonnanceList.addAll(so.afficher());

        // Filter  ordonnances  current user ID
        ObservableList<Ordonnance> filteredOrdonnances = FXCollections.observableArrayList();
        for (Ordonnance ordonnance : ordonnanceList) {
            String UserName = currentUserId.getNom();
            String UserLastName = currentUserId.getPrenom();
            String UserFullName = UserName + " " + UserLastName;

            if (ordonnance.getID_patient().equals(UserFullName)) {
                filteredOrdonnances.add(ordonnance);
            }
        }

        TV_search.setItems(filteredOrdonnances);
    }

    private void setupSearchListener() {
        ObservableList<Ordonnance> ordonnanceList = TV_search.getItems();

        FilteredList<Ordonnance> filteredOrdonnances = new FilteredList<>(ordonnanceList, b -> true);

        search_bar.textProperty().addListener((observable, oldValue, newValue) -> {
            String query = newValue.toLowerCase();

            filteredOrdonnances.setPredicate(ordonnance -> {
                if (query.isEmpty()) {
                    return true;
                }

                String medecinID = ordonnance.getID_medicament().toLowerCase();

                return medecinID.contains(query);
            });
        });

        SortedList<Ordonnance> sortedData = new SortedList<>(filteredOrdonnances);
        sortedData.comparatorProperty().bind(TV_search.comparatorProperty());
        TV_search.setItems(sortedData);
    }
}
