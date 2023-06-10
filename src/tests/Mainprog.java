/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tests;

import Services.UserService;
import entities.Medecin;
import entities.Moderateur;
import entities.Patient;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author iheb
 */
public class Mainprog {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        UserService us = new UserService();
        //us.ajouter(new Patient("iheb", "khalfallah", "homme ", "khallfallah@gmail.com", 85.0f,184, "cité monji slim thala"));
        //us.ajouter(new Patient("iheb", "khalfallah", "homme", "faof,kef", 85.0f,184,"thala"));
//        us.ajouter(new Moderateur("ahmed", "bakha", "homme", "bakha@gm"));
//        us.ajouter(new Moderateur("monem", "benaissa", "homme", "bakha@gm"));
//        us.supprimer(new Moderateur(9,"monem","benaissa","homme", "bakha@gm"));
//        us.modifier(new Moderateur(7, "ahmed", "boukhari", "homme", "iaokakzdnjaz"));
//        us.ajouter(new Medecin("iheb", "khalfallah", "homme", "khalfalnjad", "ajodiazjd,", "ianad"));
//        us.modifier(new Patient(6,"iheb", "khlifa", "homme", "khalfallaiheb@esprit", 85.0f, 184, "cité"));
//        us.modifier(new Medecin(5,"hamma", "ali", "homme", "zonzon", "généraliste", "darhom"));
//        us.supprimer(new Medecin(5,"hamma", "ali", "homme", "zonzon", "généraliste", "darhom"));
//        us.supprimer(new Medecin(5,"hamma", "ali", "homme", "zonzon", "généraliste", "darhom"));  
          //us.ajouter(new Patient("ahmed", "mohsen", "homme", "iqjdiqjd", 90.0f, 170, "zouhall", "1997-08-09"));
        System.out.println(us.afficher());

        
    }
    
}


