/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.form;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author iheb
 */
public class ChoixInscription extends Form{
    
    private Form previousForm;
    
    
    private Button patientB ;
    private Button medecinB ; 
    private Image patientLogo = null;
    private Image medecinLogo = null;
    
    
    public ChoixInscription(Form f){
        super("inscription", BoxLayout.y());
        previousForm = f;
        onGui();
        addActions();
    }
    
    private void onGui(){
        try {
            patientLogo = Image.createImage("/patient.png");
            medecinLogo = Image.createImage("/doctor.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        patientB = new Button(patientLogo);
        patientB.getAllStyles().setMargin(300, 30, 10, 10);
        medecinB = new Button(medecinLogo);
        medecinB.getAllStyles().setMargin(80, 30, 10, 10);
        this.addAll(patientB,medecinB);
    }
    private void addActions(){
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
        patientB.addActionListener(eventpatient->{
            new InscriptionPatientForm(this).show();
        });
        medecinB.addActionListener(eventpatient->{
            new InscriptionMedecinForm(this).show();
        });
    }
}
