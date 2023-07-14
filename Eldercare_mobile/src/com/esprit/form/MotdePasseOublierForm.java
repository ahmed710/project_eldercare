/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.form;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import javafx.scene.text.Text;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.esprit.Services.*;
import com.esprit.entities.*;
import java.util.Random;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.util.Properties;
import javax.mail.*;




/**
 *
 * @author iheb
 */
public class MotdePasseOublierForm extends Form{
    private Form previousform;
    
    private TextField emailTF;
    private Button envoyerCodeB;
    private TextField codeTF;
    private Button validerB;
    private TextField motdepasseTF;
    private TextField confirmermotdepasseTF;
    private Button changerB;
    private Container info;
    private Validator validEmail;
    private Validator ValidCode;
    private Validator ValidMdp;
    private Label codeLabel;
    private int code;
    private User user;
    private Message message;
    private Patient p;
    private Medecin m;

    ServiceMedecin sm = new ServiceMedecin();
    ServicePatient sp = new ServicePatient();
    
    
    
    public MotdePasseOublierForm(Form f){
        super("Mot de passe Oublier",BoxLayout.y());
        previousform=f;
        
        onGui();
        addActions();
    }
    private void onGui(){
        emailTF = new TextField("","Email");
        envoyerCodeB = new Button("Envoyer Code");
        codeTF = new TextField("", "Code");
        validerB = new Button("Valider Code");
        motdepasseTF = new TextField("", "Nouvelle Mot de passe", 16, TextField.PASSWORD);
        confirmermotdepasseTF = new TextField("", "Nouvelle Mot de passe", 16, TextField.PASSWORD);
        changerB = new Button("Changer la mot de passe");
        info = new Container(BoxLayout.y());
        validEmail = new Validator();
        ValidCode = new Validator();
        ValidMdp = new Validator();
        codeLabel = new Label();
        validEmail.addConstraint(emailTF, RegexConstraint.validEmail("Veuillez saisir une adresse email valide"));
        ValidCode.addConstraint(codeTF, new LengthConstraint(4, "Le mot de passe doit contenir au moins 8 caractères"));
        ValidMdp.addConstraint(motdepasseTF, new LengthConstraint(8, "Le numéro de téléphone doit contenir 8 chiffres"));
        Random random = new Random();
        code = random.nextInt(9000) + 1000;
        codeLabel.setText(String.valueOf(code));
        this.addAll(info);
        motdepasseTF.setEditable(false);
        confirmermotdepasseTF.setEditable(false);
        changerB.setEnabled(false);
        info.addAll(emailTF,envoyerCodeB,codeTF,validerB,motdepasseTF,confirmermotdepasseTF,changerB,codeLabel);
        
        
    }
    private void addActions(){
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousform.showBack();
        });   
        envoyerCodeB.addActionListener(event->{
            String email = emailTF.getText();
            for(User u:sp.afficher()){
                if(u.getEmail().equals(email))
                    user =u;
            }
            for(User u:sm.afficher()){
                if(u.getEmail().equals(email))
                    user =u;
            }

            if(validEmail.isValid()){
                if(isEmailExists(email)){                    
                    envoyerMail(email,"code de réinitialisation de mot de passe de votre compte Eldercare","Hello dear "+user.getNom()+" "+user.getPrenom()+"\n"+"votre code :"+code);
                    Dialog.show("Email"," E-mail contient un code de confirmation de votre compte a envoyée  à l'adresse "+user.getEmail());
                    

                }
                else
                    Dialog.show("Email", "ce email n'existe pas.", "OK",null);
            }
        
        });
        validerB.addActionListener(event->{
            if(ValidCode.isValid()){
              if(code==Integer.parseInt(codeTF.getText())){
                    motdepasseTF.setEditable(true);
                    confirmermotdepasseTF.setEditable(true);
                    changerB.setEnabled(true);

              }
              else 
                  Dialog.show("Code", "Veuillez vérifier votre code s'il vous plaît");
                  
            }
        
        });
        changerB.addActionListener(event->{
            String mpd1 = motdepasseTF.getText();
            String mpd2 = confirmermotdepasseTF.getText();
            if(mpd1.equals(mpd2)){

                if (user instanceof Patient){
                    p = (Patient)user;
                    p.setMotdepasse(mpd2);
                    sp.modifier(p);
                }
                if (user instanceof Medecin){
                    m = (Medecin)user;
                    m.setMotdepasse(mpd2);
                    sm.modifier(m);
                    
                }
            }   
            else 
                Dialog.show("mot de passe", "votre mot de passe n'est pas confirmé", "OK",null);
                
        });
    }
    //methode pour envoyer le mail de code
    public void envoyerMail(String recepteur,String sujet,String contenue){

            String emetteur = "eldercareserviceofficiel@gmail.com";
            String host = "smtp.gmail.com";
            String mail = "eldercareserviceofficiel@gmail.com";
            String password = "zgsldmasvmnadrkh";

            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host", host);
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mail, password);
                }
            });

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(emetteur));
                message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(recepteur));
                message.setSubject(sujet);
                message.setText(contenue);
                Transport.send(message);
//                Dialog.show("Email"," E-mail contient un code de confirmation de votre compte a envoyée  à l'adresse "+recepteur);
            } catch (MessagingException ex) {
                ex.printStackTrace();
//                Dialog.show("Email","Echec d'envoie de l'Email.");

            }
    }
    
    private boolean isEmailExists(String email){     
        return sm.afficherEmails().contains(email)||sp.afficherEmails().contains(email);
    }

}
