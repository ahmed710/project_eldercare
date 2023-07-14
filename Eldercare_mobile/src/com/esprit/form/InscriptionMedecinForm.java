/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.form;

import com.codename1.components.OnOffSwitch;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.Services.ServicePatient;
import com.esprit.entities.Medecin;
import com.esprit.Services.ServiceMedecin;
import java.util.Date;

/**
 *
 * @author iheb
 */
public class InscriptionMedecinForm extends Form{
    
    private Form previousForm;

    private TextField nomTF;
    private TextField prenomTF;
    private TextField adresseTF;
    private TextField emailTF;
    private TextField passwordTF;
    private TextField confirmpassword;
    private Container genderContainer;
    private Label hommeL;
    private OnOffSwitch onOff;
    private Label femmeL;
    private TextField telNumTF;
    private TextField specialite;
    private Button inscrit;
    private ComboBox<List<String>> specCB;
    private String[] specList={"chirurgie plastique","ophtalmologie","dermatologie","cardiovasculaire","reconstructrice et esthétique","Anesthésie-réanimation","Radiologie et imagerie médicale","Neurologie","Rhumatologie","Pédiatrie","Médecine générale","Psychiatrie"};
    private Validator valid;

    ServiceMedecin sm = new ServiceMedecin();
    ServicePatient sp = new ServicePatient();
    public InscriptionMedecinForm(Form f){
        super("S’inscrire comme patient", BoxLayout.y());
        previousForm = f;
        onGui();
        addActions();
    }
    private void onGui(){
        nomTF = new TextField("", "Nom");
        prenomTF = new TextField("", "Prenom");
        adresseTF = new TextField("", "Adresse");
        emailTF = new TextField("", "Email");
        passwordTF = new TextField("", "mot de passe", 16, TextField.PASSWORD);
        confirmpassword = new TextField("", "confirmer mot de passe", 16, TextField.PASSWORD);
        genderContainer = new Container(BoxLayout.xCenter());
        hommeL = new Label("Homme");
        onOff = new OnOffSwitch();
        femmeL = new Label("femme");
        genderContainer.addAll(hommeL,onOff,femmeL);       
        telNumTF = new TextField("", "numéro de téléphone", 16, TextField.NUMERIC);
        specCB=new ComboBox(specList);
        inscrit = new Button("S'inscrire");
        this.addAll(nomTF,prenomTF,adresseTF,emailTF,passwordTF,confirmpassword,genderContainer,telNumTF,specCB,inscrit);
    }
    private void addActions(){
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
            valid = new Validator();

            valid.addConstraint(emailTF, RegexConstraint.validEmail("Veuillez saisir une adresse email valide"));
            valid.addConstraint(passwordTF, new LengthConstraint(8, "Le mot de passe doit contenir au moins 8 caractères"));
            valid.addConstraint(telNumTF, new LengthConstraint(8, "Le numéro de téléphone doit contenir 8 chiffres"));
            inscrit.addActionListener(event->{
            if(valid.isValid()){
                String nom = nomTF.getText();
                String prenom = prenomTF.getText();
                String adresse = adresseTF.getText();
                String sexe ;
                if(onOff.isValue())
                    sexe="Femme";
                else
                    sexe="Homme";
                String numtel_text=telNumTF.getText().toString();
                int numtel = Integer.parseInt(numtel_text);
                String email = emailTF.getText();
                String motdepasse = confirmpassword.getText();
                int specialite_row = specCB.getSelectedIndex();
                String specialite =specList[specialite_row];
                if(nom.isEmpty()||prenom.isEmpty()||adresse.isEmpty()||telNumTF.getText().isEmpty()||email.isEmpty()||passwordTF.getText().isEmpty()||confirmpassword.getText().isEmpty()||specialite.isEmpty()){
                    Dialog.show("Champs vides", "Veuillez remplir tous les champs.", "OK",null);
                }else if(!(confirmpassword.getText().equals(passwordTF.getText())))
                    Dialog.show("mot de passe", "votre mot de passe n'est pas confirmé", "OK",null);
                else if(isEmailExists(email))
                    Dialog.show("Email", "ce email existe déja.", "OK",null);
                else if(isNumTelExists(numtel))
                    Dialog.show("Numéro de téléphone", "Numéro de téléphone existe déja.", "OK",null);
                else{
                    if (sm.ajouter(new Medecin(nom, prenom, sexe, email, motdepasse, "activé", numtel, specialite, adresse))) {
                        Dialog.show("SUCCESS", "Personne ajoutée !", "OK", null);
                    } else {
                        Dialog.show("ERROR", "Erreur serveur", "OK", null);
                    }
                }
            }
        });
    }
    private boolean isEmailExists(String email) {
        return (sm.afficherEmails().contains(email)||sp.afficherEmails().contains(email));
    }

    private boolean isNumTelExists(int numtel) {
        return sp.afficherNumTel().contains(numtel) || sm.afficherNumTel().contains(numtel);
    }

}
