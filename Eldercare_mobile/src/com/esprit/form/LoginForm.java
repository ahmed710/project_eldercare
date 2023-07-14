/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.form;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.Services.*;
import com.esprit.entities.Medecin;
import com.esprit.entities.Patient;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author iheb
 */
public class LoginForm extends Form{
    
    private TextField emailTF;
    private TextField passwordTF;
    private Button connecterB;
    private Button mot_de_passe_oublierB ;
    private Button inscriptionB ;
    private Button Test ;
    
    ServicePatient sp = new ServicePatient();
    ServiceMedecin sm = new ServiceMedecin();
    
    public LoginForm(){
        super("Login",BoxLayout.y());
        OnGui();
        addActions();
    }
    
    private void OnGui(){
        emailTF = new TextField("", "Email");
        passwordTF = new TextField("", "mot de passe", 16, TextField.PASSWORD);
        connecterB = new Button("se connecter");
        mot_de_passe_oublierB = new Button("mot de passe oublier");
        inscriptionB = new Button("inscription");
        Test = new Button("test d'affichage");
        this.addAll(emailTF,passwordTF,connecterB,mot_de_passe_oublierB,inscriptionB,Test);
    }
    
    private void addActions(){
        connecterB.addActionListener(event->{
            String email = emailTF.getText();
            String password = passwordTF.getText();
            if(isAccountExists(email, password)){
                if(Connecter.getUserConnected() instanceof Patient){
                    new InterfacePatient().show();
                }
            }else
                Dialog.show("Compte", "Ce compte n'existe pas veuillez verifier votre email ou mot de passe.", "OK",null);
        });
        mot_de_passe_oublierB.addActionListener(even ->{
            new MotdePasseOublierForm(this).show();
        });
        inscriptionB.addActionListener(eve ->{
            new ChoixInscription(this).show();
        });
        Test.addActionListener(eve ->{
            new affichagepatient(this).show();
        });
    }
    private boolean isEmailExists(String email){     
        return sm.afficherEmails().contains(email)||sp.afficherEmails().contains(email);
    }
    private boolean isAccountExists(String email,String mdp) {
        for (Medecin m : sm.afficher()){
            if (m.getEmail().equals(email) && BCrypt.checkpw(mdp,m.getMotdepasse())){
                Connecter.setUserConnected(m);
                return true;
            }
        }
        for(Patient p : sp.afficher()){
            if (p.getEmail().equals(email) && BCrypt.checkpw( mdp,p.getMotdepasse())){
                Connecter.setUserConnected(p);
                return true;
            }
            }
        return false;
    }
    public boolean verifierMotDePasse(String motDePasseNormale, String motDePasseHache){
        return BCrypt.checkpw(motDePasseNormale, motDePasseHache);
    }

}
