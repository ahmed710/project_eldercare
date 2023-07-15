/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Maladie;
import com.esprit.entities.Personne;
import com.esprit.services.ServicePersonne;

/**
 *
 * @author 21693
 */
public class OneMaladieForm extends Form {

    private TextField tfTitre;
    private TextField tfDescription;
    private Button btnModifier;
    private Form previousForm;
    private Maladie maladie;

    public OneMaladieForm(Form f, Maladie maladie) {
        super("Maladie Details", BoxLayout.y());
        previousForm = f;
        this.maladie = maladie;
        OnGui(maladie);
        addActions();
    }

    private void OnGui(Maladie maladie) {
        Label titleLabel = new Label("Titre: " + maladie.getTitre());
        Label descriptionLabel = new Label("Description: " + maladie.getDescription());
        Label classificationEtiologiqueLabel = new Label("Classification Etiologique: " + maladie.getClassification_etiologique());
        Label classificationFonctionelleLabel = new Label("Classification Fonctionelle: " + maladie.getClassification_fonctionelle());
        btnModifier = new Button("modifier");
        this.addAll(titleLabel, descriptionLabel, classificationEtiologiqueLabel, classificationFonctionelleLabel, btnModifier);
    }

    private void addActions() {
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
        btnModifier.addActionListener((evt) -> {
            new ModifierMaladieForm(this, maladie).show();
        }) ;
    }

}
