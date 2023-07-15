/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Maladie;
import com.esprit.entities.Symptome;

/**
 *
 * @author 21693
 */
public class OneSymptomeForm extends Form{
    
    private TextField tfTitre;
    private TextField tfDescription;
    private Button btnModifier;
    private Form previousForm;
    private Symptome symptome;

    public OneSymptomeForm(Form f, Symptome symptome) {
        super("Symptome Details", BoxLayout.y());
        previousForm = f;
        this.symptome = symptome;
        OnGui(symptome);
        addActions();
    }

    private void OnGui(Symptome symptome) {
        Label titleLabel = new Label("Titre: " + symptome.getTitre());
        Label descriptionLabel = new Label("Description: " + symptome.getDescription());
        Label frequenceParJour = new Label("Classification frequence Par Jour: " + symptome.getFrequenceParJour());
        Label frequenceParSemaine = new Label("Classification frequence Par Semaine: " + symptome.getFrequenceParSemaine());
        Label duree = new Label("duree: " + symptome.getDuree());
        Label organe =new Label("Organe : "+symptome.getOrgane()); 
        btnModifier = new Button("modifier");
        this.addAll(titleLabel, descriptionLabel, frequenceParJour, frequenceParSemaine,duree,organe, btnModifier);
    }

    private void addActions() {
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
        btnModifier.addActionListener((evt) -> {
            new ModifierSymptomeForm(this, symptome).show();
        }) ;
    }
}
