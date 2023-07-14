/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.services.ServiceRdv;

/**
 *
 * @author DONIG
 */
public class AfficheRdvForm extends Form{
    private Form previousForm;

    public AfficheRdvForm(Form f) {
        super("Affichage", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }

    private void OnGui() {
        this.add(new SpanLabel(new ServiceRdv().afficher().toString()));
    }

    private void addActions() {
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
    }
}
