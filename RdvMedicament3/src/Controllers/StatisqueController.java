/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import services.ServiceRendezVous;
import services.ServiceStock;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author DONIG
 */
public class StatisqueController implements Initializable {

    @FXML
    private PieChart piechart;
    @FXML
    private Button btn_retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
        
       ServiceRendezVous pdao = ServiceRendezVous.getInstance();
 HashMap<String, Integer> counts = new HashMap<>();
        pdao.afficher().stream()
                .forEach(p -> counts.put(p.getEtat(), counts.getOrDefault(p.getEtat(), 0) + 1));
        piechart.getData().clear();
          
piechart.setLegendVisible(false); // hide the legend
piechart.setLabelsVisible(true); // show labels for each slice
piechart.setLabelLineLength(10); // adjust the length of the label lines
piechart.setStartAngle(180); // adjust the starting angle of the pie chart
piechart.setPrefSize(800, 500); // adjust the width and height of the chart
piechart.setAnimated(true);
  counts.forEach((role, count) -> piechart.getData().add(new PieChart.Data(role+" : "+count, count)));
//*************************************************
Timeline timeline = new Timeline();
DoubleProperty startAngleProperty = new SimpleDoubleProperty(180);
piechart.startAngleProperty().bind(startAngleProperty);
timeline.getKeyFrames().addAll(
    new KeyFrame(Duration.ZERO, new KeyValue(startAngleProperty, 180)),
    new KeyFrame(Duration.seconds(3.0), new KeyValue(startAngleProperty, 0))
);
timeline.play(); 

for (PieChart.Data slice : piechart.getData()) {
    Node node = slice.getNode();
    node.setOnMouseEntered(event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), node);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    });
    node.setOnMouseExited(event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), node);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.play();
    });
}
        
        
        
        
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
          Parent root2 = FXMLLoader .load(getClass().getResource("../gui/AjoutRdv.fxml"));
    Stage window = (Stage) btn_retour.getScene().getWindow();
    window.setScene(new Scene(root2));
    }
    
}
