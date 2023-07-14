/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.form;

import com.codename1.components.OnOffSwitch;
import com.codename1.io.File;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.Constraint;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.Services.ServiceMedecin;
import com.esprit.Services.ServicePatient;
import java.io.IOException;

import com.esprit.Services.ServicePatient;
import com.esprit.entities.Patient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author iheb
 */
public class InscriptionPatientForm extends Form {
    
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
    private Picker datepicker;
    private TextField telNumTF;
    private Container tailleContainner;
    private Container poidsContainer;
    private Image plusNum;
    private Image minusNum;
    private Label tailleL;
    private Label poidsL;
    private Button plusB;
    private Button plusPB;
    private Button minusB;
    private Button minusPB;
    private Button inscrit;
    private int taille;
    private int poids;
    private String poidsText;
    private String tailleText;
    private Validator valid;
    
    private Form previousForm;
    ServicePatient sp = new ServicePatient();
    ServiceMedecin sm = new ServiceMedecin();
    
    public InscriptionPatientForm(Form f){
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
        datepicker = new Picker();
        datepicker.setType(Display.PICKER_TYPE_DATE);
        telNumTF = new TextField("", "numéro de téléphone", 16, TextField.NUMERIC);
        //taille container
        tailleContainner = new Container(BoxLayout.xCenter());
        plusNum = null;
        minusNum = null;
        try {
            plusNum = Image.createImage("/add.png");
            minusNum = Image.createImage("/minus.png");
            minusNum.scaled(10, 20);
        } catch (IOException ex) {
            ex. printStackTrace();
        }
        tailleText = "170";
        plusB = new Button();
        plusB.setIcon(plusNum.scaled(150, 150));
        tailleL = new Label(tailleText+" cm");
        tailleL.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        minusB = new Button();
        minusB.setIcon(minusNum.scaled(150, 150));
        tailleContainner.addAll(plusB,tailleL,minusB);
        //poids container
        poidsContainer = new Container(BoxLayout.xCenter());
        poidsText="70";
        plusPB = new Button();
        poidsL = new Label(poidsText+" Kg");
        poidsL.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        minusPB = new Button();
        plusPB.setIcon(plusNum.scaled(150, 150));
        minusPB.setIcon(minusNum.scaled(150, 150));
        poidsContainer.addAll(plusPB,poidsL,minusPB);
        inscrit = new Button("S'inscrire");
        emailTF.setConstraint(TextField.URL);
        poids=Integer.parseInt(poidsText);
        taille= Integer.parseInt(tailleText);
        this.addAll(nomTF,prenomTF,adresseTF,emailTF,passwordTF,
                confirmpassword,genderContainer,
                    datepicker,telNumTF,tailleContainner,poidsContainer,
                   inscrit);
    }
    private void addActions(){
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
        //boutton plus taille
            plusB.addActionListener(eventplus-> {
                taille+=1;
                tailleL.setText(String.valueOf(taille)+ "cm");
            });
            //boutton moins taille
            minusB.addActionListener(eventmoins-> {
                taille-=1;
                tailleL.setText(String.valueOf(taille)+ "cm");
            });
            //boutton plus poids
            plusPB.addActionListener(eventPplus->{
                poids+=1;
                poidsL.setText(String.valueOf(poids)+ "Kg");
                System.out.println(poids);
            });
            //boutton moins poids
            minusPB.addActionListener(eventPminus->{
                poids-=1;
                poidsL.setText(String.valueOf(poids)+ "Kg");
                System.out.println(poids);
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
                
                Date date=datepicker.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date_de_naissance = sdf.format(date);
                String numtel_text=telNumTF.getText().toString();
                int numtel = Integer.parseInt(numtel_text);
                String email = emailTF.getText();                
                String motdepasse = confirmpassword.getText();
                if(nom.isEmpty()||prenom.isEmpty()||adresse.isEmpty()||telNumTF.getText().isEmpty()||email.isEmpty()||passwordTF.getText().isEmpty()||confirmpassword.getText().isEmpty()){
                    Dialog.show("Champs vides", "Veuillez remplir tous les champs.", "OK",null);
                }else if(!(confirmpassword.getText().equals(passwordTF.getText())))
                    Dialog.show("mot de passe", "votre mot de passe n'est pas confirmé", "OK",null);
                else if(isEmailExists(email))
                    Dialog.show("Email", "ce email existe déja.", "OK",null);
                else if(isNumTelExists(numtel))
                    Dialog.show("Numéro de téléphone", "Numéro de téléphone existe déja.", "OK",null);
                else{
                    if(sp.ajouter(new Patient(nom, prenom, sexe, email, motdepasse, "active", numtel, poids, taille, adresse, date_de_naissance))) {
                        for(Patient p : sp.afficher()){
                            if(p.getEmail().equals(email)){
                                Connecter.setUserConnected(p);
                            }
                        }
                        Dialog.show("SUCCESS", "Personne ajoutée !", "OK", null);
                        new InterfacePatient().show();
                    } else {
                        Dialog.show("ERROR", "Erreur serveur", "OK", null);
                    }
                    }
                }
            });
    }
    private boolean isEmailExists(String email){     
        return sm.afficherEmails().contains(email)||sp.afficherEmails().contains(email);
    }
    private boolean isNumTelExists(int numtel) {
        return sp.afficherNumTel().contains(numtel)||sp.afficherNumTel().contains(numtel);         
    }






}
