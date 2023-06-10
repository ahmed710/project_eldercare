/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Zouhour
 */
public class DetailsOrdonnanceController implements Initializable {

    @FXML
    private Label LB_ID_patient;
    @FXML
    private Label LB_ID_médecin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    public Label getLB_ID_patient() {
        return LB_ID_patient;
    }

    public void setLB_ID_patient(int LB_ID_patient) {
        LB_ID_patient = LB_ID_patient;
    }

    public Label getLB_ID_médecin() {
        return LB_ID_médecin;
    }

    public void setLB_ID_médecin(int LB_ID_médecin) {
        LB_ID_médecin = LB_ID_médecin;
    }
    
}
