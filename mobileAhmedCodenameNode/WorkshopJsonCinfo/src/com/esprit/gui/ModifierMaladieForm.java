/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Maladie;
import com.esprit.services.ServiceMaladie;
import com.esprit.utils.Classification_etiologique;
import com.esprit.utils.Classification_fonctionelle;

/**
 *
 * @author 21693
 */
public class ModifierMaladieForm extends Form{
     private TextField tfTitre;
    private TextField tfDescription;
    private Button btnModifier;
     private ComboBox<Classification_etiologique> cmbClassificationEtiologique;
    private ComboBox<Classification_fonctionelle> cmbClassificationFonctionelle;
    private Form previousForm;
    private Maladie maladie;

    public ModifierMaladieForm(Form f, Maladie maladie) {
        super("Maladie Details", BoxLayout.y());
        previousForm = f;
        this.maladie = maladie;
        initializeComponents();
        addActions();
    }

    private void initializeComponents() {

        tfTitre = new TextField(maladie.getTitre());
        tfDescription = new TextField(maladie.getDescription());
        cmbClassificationEtiologique = new ComboBox<>(Classification_etiologique.values());
        cmbClassificationFonctionelle = new ComboBox<>(Classification_fonctionelle.values());
        add(new Label("Titre:"));
        add(tfTitre);
        add(new Label("Description:"));
        add(tfDescription);
        add(new Label("Classification Étiologique:"));
        add(cmbClassificationEtiologique);
        add(new Label("Classification Fonctionelle:"));
        add(cmbClassificationFonctionelle);
        btnModifier = new Button("Modifier");
        add(btnModifier);}
        private void addActions() {
        getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
            revalidate(); 
        });

        btnModifier.addActionListener((evt) -> {
            maladie.setTitre(tfTitre.getText());
            maladie.setDescription(tfDescription.getText());
             maladie.setClassification_etiologique(String.valueOf(cmbClassificationEtiologique.getSelectedItem()));
            maladie.setClassification_fonctionelle(String.valueOf(cmbClassificationFonctionelle.getSelectedItem()));
            ServiceMaladie serviceMaladie = new ServiceMaladie();
            serviceMaladie.modifier(maladie);
            modifyDisease(maladie);
            System.out.println(maladie.toString());
            revalidate(); 
        });
    }

    private void modifyDisease(Maladie maladie) {
        Dialog.show("Success", "Disease modified successfully", "OK", null);
    }
}
