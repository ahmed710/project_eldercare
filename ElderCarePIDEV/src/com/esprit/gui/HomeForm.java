/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Wimbee018
 */
public class HomeForm extends Form {
    
    private Button btnAddCommanden;
    private Button btnShoCommande;
    private Button btnModifyCommande;

    public HomeForm() {
        super("Commande", BoxLayout.y());
        OnGui();
        addActions();
    }
    
    private void OnGui() {
        btnAddCommanden = new Button("Ajouter");
        btnModifyCommande = new Button("Modifier");
        btnShoCommande = new Button("Afficher");
        this.addAll(new Label("Choisissez une option :"), btnAddCommanden, btnShoCommande);
    }
    
    private void addActions() {
        btnAddCommanden.addActionListener((evt) -> {
            new AjoutCommandeForm(this).show();
        });
        btnShoCommande.addActionListener((evt) -> {
            new AfficheCommandeForm(this).show();
        });
    }
    
}
