//package com.esprit.utils;
//
//import com.mysql.cj.Session;
//import java.util.Properties;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
// 
//public class GMailer {
//        private String username = "zouhour.rouissi@esprit.tn";
//        private String password = "221SFT3576";
//        
//public void envoyer() {
//
//    // Etape 1 : Création de la session
//    
//String emetteur = "eldercareserviceofficiel@gmail.com";
//            String host = "smtp.gmail.com";
//            String mail = "eldercareserviceofficiel@gmail.com";
//            String password = "zgsldmasvmnadrkh";
//
//            Properties properties = new Properties();
//            properties.setProperty("mail.smtp.host", host);
//            properties.setProperty("mail.smtp.auth", "true");
//            properties.setProperty("mail.smtp.starttls.enable", "true");
//            Session session =  Session. ; 
//            
//            });
//    try {
//        
//    // Etape 2 : Création de l'objet Message
//    
//
//        Message message = new MimeMessage((MimeMessage) session);
//        message.setFrom(new InternetAddress("votre_mail@gmail.com"));
//        message.setRecipients(Message.RecipientType.TO,
//        InternetAddress.parse("destinataire@gmail.com"));
//        message.setSubject("Test email");
//        message.setText("Bonjour, ce message est un test ...");
//    
//    
//    // Etape 3 : Envoyer le message
//    
//        Transport.send(message);
//        System.out.println("Message_envoye");
//        } catch (MessagingException e) {
//        throw new RuntimeException(e);
//        }}}
//
