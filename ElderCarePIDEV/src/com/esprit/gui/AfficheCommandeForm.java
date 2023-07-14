/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.esprit.entities.Commande;
import com.esprit.entities.Pharmacie;
import com.esprit.services.IServicePharmacie;
import com.esprit.services.ServiceCommande;
import java.util.List;

/**
 *
 * @author Wimbee018
 */

public class AfficheCommandeForm extends Form {
    private Form previousForm;

    public AfficheCommandeForm(Form f) {
        super("Affichage");
        previousForm = f;
        OnGui();
        addActions();
    }

    private void OnGui() {
        List<Commande> commandes = new ServiceCommande().afficher();
        List<Pharmacie> pharmacies = new IServicePharmacie().afficher();

        Container container = new Container(new TableLayout(commandes.size() + 1, 3)); // +1 for header row
        container.add(new Label("Adresse"));
        container.add(new Label("Date"));
        container.add(new Label("Etat"));
        for (Commande commande : commandes ) {
            container.add(new Label(commande.getAdresse()));
            container.add(new Label(commande.getDate_commande()));
            container.add(new Label(commande.getEtat()));
        }

        add(container);
    }

    private void addActions() {
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
    }
}

