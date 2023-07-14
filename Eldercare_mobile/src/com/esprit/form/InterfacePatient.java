/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.form;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Patient;

/**
 *
 * @author iheb
 */
public class InterfacePatient extends Form{
    private Label nomL;
    private Label prenomL;
    private Label date_de_naissanceL;
    private Label sexeL;
    private Label adresseL;
    private Label emailL;
    private Label poidsL;
    private Label tailleL;
    private Label numTelL;
    private Patient patient;
    public InterfacePatient(){
        super("Espace Patient",BoxLayout.y());
        onGui();
        addActions();
    }
    
    private void onGui(){
        patient=(Patient)Connecter.getUserConnected();
        nomL= new Label();
        prenomL= new Label();
        date_de_naissanceL= new Label();
        sexeL= new Label();
        adresseL= new Label();
        emailL= new Label();
        poidsL= new Label();
        tailleL= new Label();
        numTelL= new Label();
        nomL.setText(patient.getNom());
        prenomL.setText(patient.getPrenom());
        date_de_naissanceL.setText(patient.getDate_de_naissance());
        sexeL.setText(patient.getSexe());
        adresseL.setText(patient.getAdresse());
        emailL.setText(patient.getEmail());
        poidsL.setText(String.valueOf(patient.getPoids()));
        tailleL.setText(String.valueOf(patient.getTaille()));
        numTelL.setText(String.valueOf(patient.getNum_tel()));
        this.addAll(nomL,prenomL,date_de_naissanceL,sexeL,adresseL,emailL,poidsL,tailleL,numTelL);
    }
    private void addActions(){
        this.getToolbar().addCommandToLeftBar("Déconnecter", null, (evt) -> {
            new LoginForm().show();
            Connecter.setUserConnected(null);
        });
    }
        
}
