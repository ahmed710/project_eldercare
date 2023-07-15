/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
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
public class AjoutSymtomeForm extends Form{
    
    private TextField tfTitre;
    private TextField tfDescription;
    private TextField tfFrequenceParJour ; 
    private TextField tfFrequenceParSemaine; 
    private TextField tfDuree ; 
    private TextField tfOrgane;
    private Button btnAjout;
    private Form previousForm;
    private ComboBox<Organe> cmbOrgane;

    public AjoutSymtomeForm(Form f) {
        super("Ajout", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }

    private void OnGui() {
        tfTitre = new TextField(null, "tire");
        tfDescription = new TextField(null, "description");
        tfFrequenceParJour =new TextField(null,"Frequence Par Jour"); 
        tfFrequenceParSemaine = new TextField(null,"Frequence Par Semaine"); 
        tfDuree =new TextField(null,"Duree"); 
        cmbOrgane =new ComboBox(Organe.values()) ; 
        btnAjout = new Button("Ajouter");
        this.addAll(tfTitre, tfDescription,tfFrequenceParJour,tfFrequenceParSemaine,tfDuree,cmbOrgane,btnAjout);
    }

    private void addActions() {
        btnAjout.addActionListener((evt) -> {
            if (tfTitre.getText().isEmpty() || tfDescription.getText().isEmpty() || tfFrequenceParJour.getText().isEmpty()|| tfFrequenceParSemaine.getText().isEmpty() ||  cmbOrgane.getSelectedItem()==null || tfDuree.getText().isEmpty()) {
                Dialog.show("Alerte", "Veillez remplir tous les champs du Symptome", "OK", null);
            } else {
                ServiceSymptome sp = new ServiceSymptome();
                if (sp.ajouter(new Symptome(tfTitre.getText(), tfDescription.getText(),Integer.parseInt(tfFrequenceParJour.getText()),Integer.parseInt(tfFrequenceParSemaine.getText()),Integer.parseInt(tfDuree.getText()),String.valueOf(cmbOrgane.getSelectedItem())))) {
                    Dialog.show("SUCCESS", "Symptome ajoutée !", "OK", null);
                } else {
                    Dialog.show("ERROR", "Erreur serveur", "OK", null);
                }
            }
        });

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
    }
}
