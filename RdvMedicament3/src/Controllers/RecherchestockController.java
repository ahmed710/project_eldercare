/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Stock;
import services.ServiceStock;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DONIG
 */
public class RecherchestockController implements Initializable {

    @FXML
    private TextField txt_m;
    @FXML
    private TextField txt_p;
    @FXML
    private Button btn_back;
    @FXML
    private ListView<Stock> lv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 ServiceStock sss = ServiceStock.getInstance();
       sss.loadStocks();

                 lv.setItems(sss.getstock()); 
                 
       //******************************************
       txt_m.textProperty().addListener((observable, oldValue, newValue) -> {
    ServiceStock pdao = ServiceStock.getInstance();
    List<Stock> r = pdao.recherchermedic(newValue);
    ObservableList<Stock> stockList = FXCollections.observableArrayList(r);
//   listviewP.setStyle("-fx-font-family: Arial; -fx-font-size: 14px; -fx-font-weight: bold;");
   lv.setItems(stockList);


lv.setCellFactory(param -> new ListCell<Stock>() {
    @Override
    protected void updateItem(Stock item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
        } else {
            setText(item.toString()); // or any other method that returns a string representation of the Reclamation object
         //   setFont(Font.font("Arial", FontWeight.BOLD, 24));
        }
    }
});

  });           
       
   //******************************************
       txt_p.textProperty().addListener((observable, oldValue, newValue) -> {
    ServiceStock pdao = ServiceStock.getInstance();
    List<Stock> r = pdao.rechercherpharm(newValue);
    ObservableList<Stock> stockList = FXCollections.observableArrayList(r);
//   listviewP.setStyle("-fx-font-family: Arial; -fx-font-size: 14px; -fx-font-weight: bold;");
   lv.setItems(stockList);


lv.setCellFactory(param -> new ListCell<Stock>() {
    @Override
    protected void updateItem(Stock item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
        } else {
            setText(item.toString()); // or any other method that returns a string representation of the Reclamation object
         //   setFont(Font.font("Arial", FontWeight.BOLD, 24));
        }
    }
});

  });        
      
       
       
       
    }    

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader .load(getClass().getResource("../gui/AjoutStock.fxml"));
    Stage window = (Stage) btn_back.getScene().getWindow();
    window.setScene(new Scene(root2));
    }
    
}
