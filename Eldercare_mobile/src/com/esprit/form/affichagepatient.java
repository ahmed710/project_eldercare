/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.form;

import com.esprit.Services.*;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Medecin;
import com.esprit.entities.Patient;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iheb
 */
public class affichagepatient extends Form{
    private Form previousForm;
    private Patient patient;
    private Medecin medecin;
    ServicePatient sp = new ServicePatient();
    ServiceMedecin sm = new ServiceMedecin();
    private Medecin m ;
    public affichagepatient(Form f) {
        super("Affichage", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }

    private void OnGui() {
        this.add(new SpanLabel(new ServicePatient().afficher().toString()));
        this.add(new SpanLabel(new ServiceMedecin().afficherEmails().toString()));
        this.add(new SpanLabel(new ServicePatient().afficherEmails().toString()));
        if(isAccountExists("narutouzu@gmail.com","123456789")){
        this.add(new SpanLabel(String.valueOf(isAccountExists("DADA@gmail.com","123456789"))));
            if(Connecter.getUserConnected() instanceof Patient){
                this.add(new SpanLabel("patient"));
            }
            else if(Connecter.getUserConnected() instanceof Medecin){
                this.add(new SpanLabel("medecin"));
            }
            else
                this.add(new SpanLabel("banney"));
//            System.out.println(Connecter.getUserConnected().toString());
        }
         else
            this.add(new SpanLabel("l3asba"));
        
            this.add(new SpanLabel(String.valueOf(sp.afficherEmails().contains("khalfallahiheb@gmail.com"))));
            this.add(new SpanLabel(String.valueOf(isEmailExists("khalfallahiheb@gmail.com"))));

    }

    private void addActions() {
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
    }
    private boolean isNumTelExists(int numtel) {
        return sp.afficherNumTel().contains(numtel);         
    }
    private boolean isAccountExists(String email,String mdp) {
        for (Medecin m : sm.afficher()){
            if (m.getEmail().equals(email) && m.getMotdepasse().equals(mdp)){
                Connecter.setUserConnected(m);
                return true;
            }
        }
        for(Patient p : sp.afficher()){
            if (p.getEmail().equals(email) && p.getMotdepasse().equals(mdp)){
                Connecter.setUserConnected(p);
                return true;
            }
            }
        return false;
    }
    private boolean isEmailExists(String email) {
        return (sm.afficherEmails().contains(email)||sp.afficherEmails().contains(email));
    }

}