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
import com.esprit.entities.Personne;
import com.esprit.services.ServicePersonne;

/**
 *
 * @author abdel
 */
public class AjoutPersonneForm extends Form {

    private TextField tfNom;
    private TextField tfPrenom;
    private Button btnAjout;

    private Form previousForm;

    public AjoutPersonneForm(Form f) {
        super("Ajout", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }

    private void OnGui() {
        tfNom = new TextField(null, "Nom");
        tfPrenom = new TextField(null, "Prénom");
        btnAjout = new Button("Ajouter");
        this.addAll(tfNom, tfPrenom, btnAjout);
    }

    private void addActions() {
        btnAjout.addActionListener((evt) -> {
            if (tfNom.getText().isEmpty() || tfPrenom.getText().isEmpty()) {
                Dialog.show("Alerte", "Veillez remplir tous les champs", "OK", null);
            } else {
                ServicePersonne sp = new ServicePersonne();
                if (sp.ajouter(new Personne(tfNom.getText(), tfPrenom.getText()))) {
                    Dialog.show("SUCCESS", "Personne ajoutée !", "OK", null);
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
