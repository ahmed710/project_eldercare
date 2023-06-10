/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Services.UserService;
import entities.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author iheb
 */
public class CodeMotDePasseOublierController implements Initializable{

    @FXML
    private TextField tfEmail;
    @FXML
    private Label Emailprob;
    @FXML
    private TextField tfCode;
    @FXML
    private Label codeProb;
    UserService us = new UserService();
    User Cc;
    Timer timer = new Timer();
    TimerTask disable = new TimerTask() {
        @Override
        public void run() {
            tfEmail.setDisable(true);
            attend.setVisible(true);
            Benvoyercode.setDisable(true);
        }
    };
    private int code;
    List<User> listUser = new ArrayList(us.afficher());
    @FXML
    private Button Benvoyercode;
    @FXML
    private Label attend;
    @FXML
    private PasswordField nouvellemdp;
    @FXML
    private PasswordField confirmernmdp;
    @FXML
    private Label mdpProb;
    @FXML
    private Button Bconfimer;
    @FXML
    private Button BconfirmerCode;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Emailprob.setVisible(false);
        codeProb.setVisible(false);
        attend.setVisible(false);
        mdpProb.setVisible(false);
        nouvellemdp.setDisable(true);
        confirmernmdp.setDisable(true);
        Bconfimer.setDisable(true);
    }

    @FXML
    private void envoyerCode(ActionEvent event) {
        User e = null;
        for(User u : listUser){
            if(u.getEmail().equals(tfEmail.getText()))
                e=u;
        }
        if (e!=null) {
            //start the timer
            timer.schedule(disable, 2000);
            codeProb.setVisible(false);
            Emailprob.setVisible(false);
            //la creation d'un code aléatoire
            Random random = new Random();
            code = random.nextInt(9000) + 1000;
            System.out.println(code);
            envoyerMail(code, e.getNom(),e.getPrenom(), e.getEmail());
            Cc=e;
        }
        else
            Emailprob.setVisible(true);
    }

    @FXML
    private void confirmercode(ActionEvent event) {
        int confirmationcode= Integer.parseInt(tfCode.getText());
        if(confirmationcode==code){
            codeProb.setDisable(false);
            nouvellemdp.setDisable(false);
            confirmernmdp.setDisable(false);
            Bconfimer.setDisable(false);

        }
        else
            codeProb.setVisible(true);
    }
    
    
    
    
    //methode pour envoyer le mail de code
    public void envoyerMail(int code,String nom,String prenom,String recepteur){
                    
            
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
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recepteur));
                message.setSubject("code de réinitialisation de mot de passe de votre compte Eldercare");
                message.setText("Hello dear "+nom+" "+prenom+"\n"+"votre code :"+code);
                Transport.send(message);
                            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Email");
            alert.setHeaderText(null);
            alert.setContentText("E-mail contient code de réinitialisation de mot de passe de votre compte à l'adresse "+recepteur);
            alert.showAndWait();
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
    }

    @FXML
    private void confirmerchangement(ActionEvent event) throws IOException {
        if (nouvellemdp.getText().equals(confirmernmdp.getText())){
            if (Cc instanceof Patient){
                Cc.setMotdepasse(nouvellemdp.getText());
                us.modifier(Cc);
            }
            if (Cc instanceof Medecin){
                Cc.setMotdepasse(nouvellemdp.getText());
                us.modifier(Cc);
            }
            if(Cc instanceof Moderateur){
                Cc.setMotdepasse(nouvellemdp.getText());
                us.modifier(Cc);
            }
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/InterfacePatient.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        Stage patientStage = new Stage();
//        patientStage.setScene(scene);
//        patientStage.setTitle("eldercare");
        }
        else
            mdpProb.setVisible(true);
    }

    

}
