/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Maladie;
import com.esprit.services.ServiceMaladie;

/**
 *
 * @author ahmed
 */
public class AfficheMaladieForm extends Form{


    private Form previousForm;
    private Button btnSupprimer ; 
    private Button btnShow ; 

    public AfficheMaladieForm(Form f) {
        super("Affichage", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }

   private void OnGui() {
    ServiceMaladie serviceMaladie = new ServiceMaladie();
    for (Maladie maladie : serviceMaladie.afficher()) {
        Container container = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label maladieLabel = new Label(maladie.getTitre().toString());
        btnSupprimer  = new Button("Supprimer");
        btnShow = new Button("Show") ;
        System.out.println(maladieLabel);
        container.add(maladieLabel);
        container.add(btnShow);
        container.add(btnSupprimer);
        
        btnShow.addActionListener((evt) -> {
                new OneMaladieForm(this, maladie).show();
            });
        
        btnSupprimer.addActionListener((evt) -> {
                serviceMaladie.supprimer(maladie);
                removeComponent(container);
                System.out.println(maladie.getTitre());
                revalidate(); 
            new AfficheMaladieForm(this).show();
        
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
