/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Maladie;
import com.esprit.services.ServiceMaladie;

/**
 *
 * @author ahmed
 */
public class AjoutMaladieForm extends Form{

    private TextField tfTitre;
    private TextField tfDescription;
    private TextField tfClassification_etiologique ; 
    private TextField tfClassification_fonctionelle; 
    private Button btnAjout;

    private Form previousForm;

    public AjoutMaladieForm(Form f) {
        super("Ajout", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }

    private void OnGui() {
        tfTitre = new TextField(null, "tire");
        tfDescription = new TextField(null, "description");
        tfClassification_etiologique =new TextField(null,"Classification_etiologique"); 
        tfClassification_fonctionelle = new TextField(null,"Classification_fonctionelle"); 
        btnAjout = new Button("Ajouter");
        this.addAll(tfTitre, tfDescription,tfClassification_etiologique,tfClassification_fonctionelle,btnAjout);
    }

    private void addActions() {
        btnAjout.addActionListener((evt) -> {
            if (tfTitre.getText().isEmpty() || tfDescription.getText().isEmpty() || tfClassification_etiologique.getText().isEmpty()|| tfClassification_fonctionelle.getText().isEmpty()) {
                Dialog.show("Alerte", "Veillez remplir tous les champs du maladie", "OK", null);
            } else {
                ServiceMaladie sp = new ServiceMaladie();
                if (sp.ajouter(new Maladie(tfTitre.getText(), tfDescription.getText(),tfClassification_etiologique.getText(),tfClassification_fonctionelle.getText()))) {
                    Dialog.show("SUCCESS", "Maladie ajoutée !", "OK", null);
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
