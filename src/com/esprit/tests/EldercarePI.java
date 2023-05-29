/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.esprit.tests;
import com.esprit.entities.Traitement;
import com.esprit.entities.Ordonnance;
import com.esprit.services.ServiceOdonnance;
import com.esprit.services.ServiceTraitement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zouhour
 */
public class EldercarePI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//
  
//    Ordonnance ordonance1 = new Ordonnance("k15", 50, 80, Date.valueOf("2023-01-31");
    ServiceOdonnance so1 = new ServiceOdonnance();
    so1.afficher();
    List med = new ArrayList();
    med.add("k15");
//    so1.ajouter(new Ordonnance( med ,50, 80, Date.valueOf("2023-01-31")));
//    so1.ajouter(new Ordonnance("k15", 50, 80, Date.valueOf("2016-08-05")));
   
    System.out.println(so1.afficher());   
    so1.supprimer(new Ordonnance( 23,med,50, 80, Date.valueOf("2023-01-31")));
    
      
     /********************************************************************************/ 
      
    Traitement T1 = new Traitement();
    ServiceTraitement sT1 = new ServiceTraitement();
    
    System.out.println(sT1.afficher());
    
    }
    
}
