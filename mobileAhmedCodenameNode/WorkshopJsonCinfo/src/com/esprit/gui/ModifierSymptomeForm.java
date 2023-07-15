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
import com.esprit.entities.Symptome;
import com.esprit.services.ServiceMaladie;
import com.esprit.services.ServiceSymptome;
import com.esprit.utils.Classification_etiologique;
import com.esprit.utils.Classification_fonctionelle;
import com.esprit.utils.Organe;

/**
 *
 * @author 21693
 */
public class ModifierSymptomeForm extends Form {

    private TextField tfTitre;
    private TextField tfDescription;
    private TextField tfFrequenceParJour;
    private TextField tfFrequenceParSemaine;
    private TextField tfDuree;
    private Button btnModifier;
    private ComboBox<Organe> cmbOrgane;
    private Form previousForm;
    private Symptome symptome;

    public ModifierSymptomeForm(Form f, Symptome symptome) {
        super("Maladie Details", BoxLayout.y());
        previousForm = f;
        this.symptome = symptome;
        initializeComponents();
        addActions();
    }

    private void initializeComponents() {

        tfTitre = new TextField(symptome.getTitre());
        tfDescription = new TextField(symptome.getDescription());
        tfFrequenceParJour = new TextField(symptome.getFrequenceParJour());
        tfFrequenceParSemaine = new TextField(symptome.getFrequenceParSemaine());
        tfDuree = new TextField(String.valueOf(symptome.getDuree()));
        cmbOrgane = new ComboBox<>(Organe.values());
        add(new Label("Titre:"));
        add(tfTitre);
        add(new Label("Description:"));
        add(tfDescription);
        add(new Label("Frequence Par Jour"));
        add(tfFrequenceParJour);
        add(new Label("Frequence Par Semaine"));
        add(tfFrequenceParSemaine);
        add(new Label("Duree"));
        add(tfDuree);
        add(new Label("Organe"));
        add(cmbOrgane);
        btnModifier = new Button("Modifier");
        add(btnModifier);
    }

    private void addActions() {
        getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
            revalidate();
        });

        btnModifier.addActionListener((evt) -> {
            symptome.setTitre(tfTitre.getText());
            symptome.setDescription(tfDescription.getText());
            symptome.setFrequenceParJour(Integer.parseInt(tfFrequenceParJour.getText()));
            symptome.setFrequenceParSemaine(Integer.parseInt(tfFrequenceParSemaine.getText()));
            symptome.setDuree(Integer.parseInt(tfDuree.getText()));
            symptome.setOrgane(cmbOrgane.getSelectedItem().toString());
            ServiceSymptome SserviceSymptome = new ServiceSymptome();
            SserviceSymptome.modifier(symptome);
            modifyDisease(symptome);
            System.out.println(symptome.toString());
            revalidate();
        });
    }

    private void modifyDisease(Symptome symptome) {
        Dialog.show("Success", "Symptome modifiée avec succée", "OK", null);
    }
}
