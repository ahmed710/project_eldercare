/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author abdel
 */
public class HomeForm extends Form {
    
    private Button btnAddMaladie;
    private Button btnShowMaladie;
    private Button btnAddSymptome ;
    private Button btnShowSymptome;


    public HomeForm() {
        super("Home", BoxLayout.y());
        OnGui();
        addActions();
    }
    
    private void OnGui() {
        btnAddMaladie = new Button("Ajouter Maladie");
        btnShowMaladie = new Button("Afficher Maladie");
        btnAddSymptome = new Button("Ajouter Symptome") ;
        btnShowSymptome = new Button("Afficher Symptome"); 
        
        this.addAll(new Label("Choisissez une option :"), btnAddMaladie, btnShowMaladie,btnAddSymptome,btnShowSymptome);
    }
    
    private void addActions() {
        btnAddMaladie.addActionListener((evt) -> {
            new AjoutMaladieForm(this).show();
        });
        btnShowMaladie.addActionListener((evt) -> {
            new AfficheMaladieForm(this).show();

        });
        btnAddSymptome.addActionListener((evt) -> {
            new AjoutSymtomeForm(this).show();
        });
        btnShowSymptome.addActionListener((evt)->{
            new AfficheSymptomeForm(this).show();
        }); 

        
    }
    
}
    