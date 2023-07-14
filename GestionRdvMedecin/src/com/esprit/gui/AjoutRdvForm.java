/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.esprit.entities.RendezVous;
import com.esprit.entities.User;
import com.esprit.services.ServiceRdv;
import com.esprit.services.ServiceUser;
import java.util.List;

/**
 *
 * @author DONIG
 */
public class AjoutRdvForm extends Form {

    private Picker date;
    private ComboBox<User> patients;
    private Button btnAjout;

    private Form previousForm;

    public AjoutRdvForm(Form f) {
        super("Ajout", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }

    private void OnGui() {
        date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        patients = new ComboBox<User>();
        ServiceUser su = new ServiceUser();
        List<User> medecinsList = su.afficher();

        // Ajouter les noms des médecins à la ComboBox
        for (User medecin : medecinsList) {
            patients.addItem(medecin);
        }

        btnAjout = new Button("Ajouter");
        this.addAll(date, patients, btnAjout);
    }

    private void addActions() {
        btnAjout.addActionListener((evt) -> {
            if (date.getText().isEmpty() || patients.getSelectedItem().toString().isEmpty()) {
                Dialog.show("Alerte", "Veillez remplir tous les champs", "OK", null);
            } else {
                ServiceRdv sr = new ServiceRdv();
                if (sr.ajouter(new RendezVous(date.getDate().toString(), patients.getSelectedItem().getNom()),date.getDate())) {
                    Dialog.show("SUCCESS", "Rendez-vous ajouté !", "OK", null);
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
