/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author DONIG
 */
public class JavaMail {

    public static void sendMail(String recepient,String reponse) throws MessagingException {
        System.out.println("sending email in progress");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myEmailAccount = "bilel.kasmi@esprit.tn";
        String myEmailPassword = "edjxrnmjwznosxvu";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmailAccount, myEmailPassword);
            }
            
        });

        //mail.smtp.auth
        //mail.smtp.starttls.enable
        //mail.smtp.host
        //mail.smtp.port 587
        
        Message message = prepareMessage( session, myEmailAccount, recepient,reponse);
        Transport.send(message);
        System.out.println("sucess mail sending");
    }

    private static Message prepareMessage(Session session, String myEmailAccount, String recepient,String reponse) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmailAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("ElderCare");
            message.setText(  "Votre rendez-vous pour le "+ reponse);
            return message;
        } catch (Exception ex) {
            System.out.println("Error sending email" + ex.getMessage());
        }
        return null;
    }

}

