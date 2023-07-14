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
 * @author DONIG
 */
public class HomeForm extends Form{
    private Button btnAdd;
    private Button btnShow;

    public HomeForm() {
        super("Gestion des Rendez-vous", BoxLayout.y());
        OnGui();
        addActions();
    }
    
    private void OnGui() {
        btnAdd = new Button("Prendre Rendez-vous");
        btnShow = new Button("Consulter mes Rendez-vous");
        this.addAll(new Label("Choisissez une option :"), btnAdd, btnShow);
    }
    
    private void addActions() {
        btnAdd.addActionListener((evt) -> {
            new AjoutRdvForm(this).show();
        });
        btnShow.addActionListener((evt) -> {
            new AfficheRdvForm(this).show();
        });
    }
    
}
