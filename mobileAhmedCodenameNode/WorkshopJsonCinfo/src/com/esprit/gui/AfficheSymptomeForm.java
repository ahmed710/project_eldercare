/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Maladie;
import com.esprit.entities.Symptome;
import com.esprit.services.ServiceMaladie;
import com.esprit.services.ServiceSymptome;

/**
 *
 * @author 21693
 */
public class AfficheSymptomeForm extends Form{
    

    private Form previousForm;
    private Button btnSupprimer ; 
    private Button btnShow ; 

    public AfficheSymptomeForm(Form f) {
        super("Affichage", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }

   private void OnGui() {
    ServiceSymptome serviceSymptome = new ServiceSymptome();
    for (Symptome symptome : serviceSymptome.afficher()) {
        Container container = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label symptomeLabel = new Label(symptome.getTitre());
        btnSupprimer  = new Button("Supprimer");
        btnShow = new Button("Show") ;
        System.out.println(symptomeLabel);
        container.add(symptomeLabel);
        container.add(btnShow);
        container.add(btnSupprimer);
        
        btnShow.addActionListener((evt) -> {
                new OneSymptomeForm(this, symptome).show();
            });
        
        btnSupprimer.addActionListener((evt) -> {
                serviceSymptome.supprimer(symptome);
                removeComponent(container);
                System.out.println(symptome.getTitre());
                revalidate(); 
            new AfficheSymptomeForm(this).show();
        
        });
        add(container);
    }
}


    private void addActions() {
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
    }
}
