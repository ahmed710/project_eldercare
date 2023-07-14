/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.DateTimeSpinner;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.esprit.entities.Commande;
import com.esprit.entities.Pharmacie;
import com.esprit.services.IServicePharmacie;
import com.esprit.services.ServiceCommande;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AjoutCommandeForm extends Form {

    private TextField tfAdresse;
    private ComboBox<String> lnompha; // Updated to ComboBox<String>
    private Picker date_p;
    private ComboBox<String> letat; // Updated to ComboBox<String>
    private Button btnAjout;
    private Form previousForm;
    private IServicePharmacie sp = new IServicePharmacie();

    public AjoutCommandeForm(Form f) {
        super("Ajout", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }

private void OnGui() {
    List<String> pha = sp.getNomPhar();
    lnompha = new ComboBox<>(pha.toArray(new String[0])); // Convert List to an array of String
    tfAdresse = new TextField("", "Adresse");
    date_p = new Picker();
    date_p.setType(Display.PICKER_TYPE_DATE);
    letat = new ComboBox<>("En cours de préparation", "En cours de livraison", "Livré", "Annulé");
    btnAjout = new Button("Ajouter");
    this.addAll(lnompha, tfAdresse, date_p, letat,btnAjout);
}


    private void addActions() {
        btnAjout.addActionListener((evt) -> {
            if (lnompha.getSelectedItem() == null || tfAdresse.getText().isEmpty() || String.valueOf(date_p).isEmpty() || letat.getSelectedItem() == null) {
                Dialog.show("Alerte", "Veuillez remplir tous les champs", "OK", null);
            } else {
                ServiceCommande sm = new ServiceCommande();
                Date date =date_p.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String cmDate = sdf.format(date);
                int id = pharmacieById(lnompha.getSelectedItem());
                Commande commande = new Commande(id, tfAdresse.getText(), lnompha.getSelectedItem(), String.valueOf(date), letat.getSelectedItem());
                if (sm.ajouter(commande)) {
                    Dialog.show("SUCCESS", "Commande ajoutée !", "OK", null);
                } else {
                    Dialog.show("ERROR", "Erreur serveur", "OK", null);
                }
            }
        });

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
    }
    private int pharmacieById(String nompharmacie){
        for(Pharmacie p :  sp.afficher()){
            if(p.getNom_pharmacie().equals(nompharmacie)){
                return p.getID_pharmacie();
            }
        }
        return -1;
    }
}

