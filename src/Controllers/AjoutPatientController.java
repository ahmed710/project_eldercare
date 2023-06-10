package Controllers;

import Services.UserService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import entities.Patient;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import utils.DataSource;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;
import javafx.util.StringConverter;


public class AjoutPatientController implements Initializable{
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private DatePicker datePicker;
    @FXML
    private RadioButton rbHomme;
    @FXML
    private RadioButton rbFemme;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfEmail;
    @FXML
    private Spinner<Double> tfPoidsSpinner;
    @FXML
    private Spinner<Integer> tftaillespinner;
    @FXML
    private TableView<User> tfaffichage;
    @FXML
    private TableColumn<Patient, String> nomcol;
    @FXML
    private TableColumn<Patient, String> prenomcol;
    @FXML
    private TableColumn<Patient, String> sexecol;
    @FXML
    private TableColumn<Patient, String> emailcol;
    @FXML
    private TableColumn<Patient, String> adressecol;
    @FXML
    private TableColumn<Patient, String> datecol;
    @FXML
    private TableColumn<Patient, Float> poidscol;
    @FXML
    private TableColumn<Patient, Integer> taillecol;
    private Double valueP;
    private float poids;
    private int taille;
    private Connection cnx = DataSource.getinstance().getCnx();
    UserService us = new UserService();
    @FXML
    private PasswordField tfmotdepasse;
    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //togglegroup pour éviter le choix multiple
        ToggleGroup sexegrp = new ToggleGroup();
        sexegrp.getToggles().addAll(rbFemme,rbHomme);
        // le poids
        SpinnerValueFactory<Double> poidsSpinner = new SpinnerValueFactory.DoubleSpinnerValueFactory(40, 200, 70, 0.1);
        tfPoidsSpinner.setValueFactory(poidsSpinner);
        valueP=tfPoidsSpinner.getValue();
        tfPoidsSpinner.valueProperty().addListener(new ChangeListener<Double>() {
            @Override
            public void changed(ObservableValue<? extends Double> observable, Double oldValue, Double newValue) {
            tfPoidsSpinner.setValueFactory(poidsSpinner);
            valueP=tfPoidsSpinner.getValue();            }
        });

        // la taille
        SpinnerValueFactory<Integer> tailleSpinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(140, 250,170);
        tftaillespinner.setValueFactory(tailleSpinner);
        taille = tftaillespinner.getValue();
        tftaillespinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                tftaillespinner.setValueFactory(tailleSpinner);
                taille = tftaillespinner.getValue();            }
        });
        //modification avec doubleClick.
        tfaffichage.setEditable(true);
        nomcol.setCellFactory(TextFieldTableCell.forTableColumn());
        nomcol.setOnEditCommit(this::modifierNom);
        
        prenomcol.setCellFactory(TextFieldTableCell.forTableColumn());
        prenomcol.setOnEditCommit(this::modifierPrenom);
        
        sexecol.setCellFactory(TextFieldTableCell.forTableColumn());
        sexecol.setOnEditCommit(this::modifierSexe);
        
        emailcol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailcol.setOnEditCommit(this::modifierEmail);
        
        adressecol.setCellFactory(TextFieldTableCell.forTableColumn());
        adressecol.setOnEditCommit(this::modifierAdresse);
        
        datecol.setCellFactory(TextFieldTableCell.forTableColumn());
        datecol.setOnEditCommit(this::modifierDate);

        poidscol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        poidscol.setOnEditCommit(this::modifierPoids);
        
        taillecol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        datecol.setOnEditCommit(this::modifierTaille);

        
        
        //boutton supprimer dans une colonne
        
        TableColumn<User, Void> supBtn = new TableColumn("Suprimer");
        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
        @Override
        public TableCell<User, Void> call(final TableColumn<User, Void> param) {
            final TableCell<User, Void> cell = new TableCell<User, Void>() {

                private final Button btn = new Button("Suprimer");

                {
                    btn.setOnAction((ActionEvent event) -> {
                         Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de modifier medecin");
                            alert.setHeaderText("Confirmation de modifier medeci");
                            alert.setContentText("Êtes-vous sûr?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                   User p = getTableView().getItems().get(getIndex());
                                    us.supprimer(p);
                                    //actualiser
                                    tableview();
                            }

                    });
                }



                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {

                        setGraphic(btn);
                    }
                }
            };
            return cell;
        }
    };

    supBtn.setCellFactory(cellFactory);

    tfaffichage.getColumns().add(supBtn);


    
    
    

    //actualiser
    tableview();
    }
    @FXML
    private void ajouterPatient(ActionEvent event) throws IOException{
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String dateNaissance = datePicker.getValue() != null ? datePicker.getValue().toString() : "";
        String sexe = rbHomme.isSelected() ? "Homme" : "Femme";
        String adresse = tfAdresse.getText();
        String email = tfEmail.getText();
        float poids = valueP != null ? valueP.floatValue() : 0.0f;
        String motdepasse =tfmotdepasse.getText();


        
        if (prenom.isEmpty() || nom.isEmpty() || dateNaissance.isEmpty()|| sexe.isEmpty()|| adresse.isEmpty()|| email.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        } else {
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation");
            confirmationDialog.setHeaderText("Confirmation");
            confirmationDialog.setContentText("Êtes-vous sûr de vouloir ajouter ce patient?");
            confirmationDialog.initOwner(tfNom.getScene().getWindow());
            confirmationDialog.showAndWait();

                if (confirmationDialog.getResult() == ButtonType.OK) {
                us.ajouter(new Patient(nom, prenom, sexe, email, poids, taille, adresse, dateNaissance,motdepasse));
                // Réinitialiser les champs après l'ajout de patient
                tfNom.setText("");
                tfPrenom.setText("");
                datePicker.setValue(null);
                rbHomme.setSelected(false);
                rbFemme.setSelected(false);
                tfAdresse.setText("");
                tfEmail.setText("");
                tfmotdepasse.setText("");

                tableview();
        }
    }
        tableview();
    }


    @FXML
    private void supprimerPatient(ActionEvent event) {
        User selectedUser = tfaffichage.getSelectionModel().getSelectedItem();
        // set the row of selected item in selectedRow variable
        int selectedRow = tfaffichage.getSelectionModel().getSelectedIndex();
        // creation une liste qui contient tous les patients
        List<User> userList= new ArrayList();
        List<User> patientlist= new ArrayList();
        userList=us.afficher().stream().collect(Collectors.toList());
        for(User p : userList){
            if(p instanceof  Patient)
                patientlist.add( p);
        }
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir supprimer ce patient?");
        confirmationDialog.initOwner(tfNom.getScene().getWindow());
        confirmationDialog.showAndWait();

        if (confirmationDialog.getResult() == ButtonType.OK && selectedUser != null) 
            us.supprimer(patientlist.get(selectedRow));      

//        //pour actualiser le tableview
        tableview();

    }
    private void supprimerdeTableView(){

    }
    private void tableview() {
        
        
        //pour filtrer objet user et laisser que les patients dans une liste 
        List<User> userList= new ArrayList();
        List<User> patientlist= new ArrayList();
        userList=us.afficher().stream().collect(Collectors.toList());
        for(User p : userList){
            if(p instanceof  Patient)
                patientlist.add( p);
        }
        
        //l'insertion de la liste dans table view 
        ObservableList<User> patientobservList = FXCollections.observableArrayList(patientlist);
        tfaffichage.setItems(patientobservList);
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexecol.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        adressecol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date_de_naissance"));
        poidscol.setCellValueFactory(new PropertyValueFactory<>("poids"));
        taillecol.setCellValueFactory(new PropertyValueFactory<>("taille"));
        
        
        TableColumn<User, User> remove = new TableColumn<>("supprimer");
        remove.setMinWidth(40);
        remove.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        remove.setCellFactory(param -> new TableCell<User, User>() {
            private final Button deleteButton = new Button("supprimer");

            @Override
            protected void updateItem(User person, boolean empty) {
                super.updateItem(person, empty);

                if (person == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> us.supprimer(person));
            }
        }); 
        
        FilteredList<User> userfiltreded = new FilteredList<> (patientobservList, c -> true);
        searchBar.textProperty().addListener((Observable,oldValue,newValue) -> {
        userfiltreded.setPredicate(u -> {
        if (newValue == null || newValue.isEmpty()){
            return true;}
        String lowerc = newValue.toLowerCase();
        if( u.getNom().toLowerCase().contains(lowerc)){
            return true;}
        if( u.getPrenom().toLowerCase().contains(lowerc)){
            return true;}

        else
            return false;});
        SortedList<User> sortedCours = new SortedList<>(userfiltreded);
        sortedCours.comparatorProperty().bind(tfaffichage.comparatorProperty());
        tfaffichage.setItems(sortedCours);
    });
        
    }
    @FXML
    void modifierNom(CellEditEvent event) {
        Patient selectedUser = (Patient) tfaffichage.getSelectionModel().getSelectedItem();
        selectedUser.setNom(event.getNewValue().toString());
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce patient?");
        confirmationDialog.initOwner(tfNom.getScene().getWindow());
        confirmationDialog.showAndWait();

        if (confirmationDialog.getResult() == ButtonType.OK)
            us.modifier(selectedUser);
        else 
            tableview();
    }
    
    @FXML
    void modifierPrenom(CellEditEvent event) {
        Patient selectedUser = (Patient) tfaffichage.getSelectionModel().getSelectedItem();
        selectedUser.setPrenom(event.getNewValue().toString());
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce patient?");
        confirmationDialog.initOwner(tfNom.getScene().getWindow());
        confirmationDialog.showAndWait();

        if (confirmationDialog.getResult() == ButtonType.OK)
            us.modifier(selectedUser);
        else 
            tableview();
    }
    
    @FXML
    void modifierAdresse(CellEditEvent event) {
        Patient selectedUser = (Patient) tfaffichage.getSelectionModel().getSelectedItem();
        selectedUser.setAdresse(event.getNewValue().toString());
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce patient?");
        confirmationDialog.initOwner(tfNom.getScene().getWindow());
        confirmationDialog.showAndWait();

        if (confirmationDialog.getResult() == ButtonType.OK)
            us.modifier(selectedUser);
        else 
            tableview();
    }

    @FXML
    void modifierDate(CellEditEvent event) {
        Patient selectedUser = (Patient) tfaffichage.getSelectionModel().getSelectedItem();
        selectedUser.setDate_de_naissance(datePicker.getValue().toString());
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce patient?");
        confirmationDialog.initOwner(tfNom.getScene().getWindow());
        confirmationDialog.showAndWait();

        if (confirmationDialog.getResult() == ButtonType.OK)
            us.modifier(selectedUser);
        else 
            tableview();

    }
    // date modification problème
    @FXML
    void modifierEmail(CellEditEvent event) {
        Patient selectedUser = (Patient) tfaffichage.getSelectionModel().getSelectedItem();
        selectedUser.setEmail(event.getNewValue().toString());
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce patient?");
        confirmationDialog.initOwner(tfNom.getScene().getWindow());
        confirmationDialog.showAndWait();

        if (confirmationDialog.getResult() == ButtonType.OK)
            us.modifier(selectedUser);
        else 
            tableview();
    }

    @FXML
    void modifierPoids(CellEditEvent event) {
        Patient selectedUser = (Patient) tfaffichage.getSelectionModel().getSelectedItem();
        selectedUser.setPoids((float) event.getNewValue());
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce patient?");
        confirmationDialog.initOwner(tfNom.getScene().getWindow());
        confirmationDialog.showAndWait();

        if (confirmationDialog.getResult() == ButtonType.OK)
            us.modifier(selectedUser);
        else 
            tableview();
    }


    @FXML
    void modifierSexe(CellEditEvent event) {
        Patient selectedUser = (Patient) tfaffichage.getSelectionModel().getSelectedItem();
        selectedUser.setSexe(event.getNewValue().toString());
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce patient?");
        confirmationDialog.initOwner(tfNom.getScene().getWindow());
        confirmationDialog.showAndWait();

        if (confirmationDialog.getResult() == ButtonType.OK)
            us.modifier(selectedUser);
        else 
            tableview();
    }

    @FXML
    void modifierTaille(CellEditEvent event) {
        Patient selectedUser = (Patient) tfaffichage.getSelectionModel().getSelectedItem();
        selectedUser.setTaille((int) event.getNewValue());
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir modifier ce patient?");
        confirmationDialog.initOwner(tfNom.getScene().getWindow());
        confirmationDialog.showAndWait();

        if (confirmationDialog.getResult() == ButtonType.OK)
            us.modifier(selectedUser);

        else 
            tableview();
    }

      
    private void suppBouton(){

    }

    @FXML
    private void chercher(ActionEvent event) {
        List<User> userList= new ArrayList();
        List<User> patientlist= new ArrayList();
        userList=us.afficher().stream().collect(Collectors.toList());
        for(User p : userList){
            if(p instanceof  Patient)
                patientlist.add( p);
        }
        
        //l'insertion de la liste dans table view 
        ObservableList<User> patientobservList = FXCollections.observableArrayList(patientlist);
        FilteredList<User> userfiltered = new FilteredList<>(patientobservList,b ->true);
        searchBar.textProperty().addListener((observable,oldValue,newValue) -> {
            userfiltered.setPredicate(new Predicate<User>() {
                @Override
                public boolean test(User t) {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String LowerCaseFilter = newValue.toLowerCase();
                    if(t.getNom().toLowerCase().indexOf(LowerCaseFilter)!=-1)
                        return true;
                    return false;
                }
            });
        });
        SortedList<User> sortedUser = new SortedList<>(userfiltered);
        sortedUser.comparatorProperty().bind(tfaffichage.comparatorProperty());
        tfaffichage.setItems(sortedUser);
    }
}
