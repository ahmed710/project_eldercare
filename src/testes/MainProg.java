/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testes;
import entities.Commande;
import entities.Pharmacie;
import java.sql.Date;
import services.ServiceCommande;
import services.ServicePharmacie;

/**
 *
 * @author Wimbee018
 */
public class MainProg {
        public static void main(String[] args) {
        // TODO code application logic here
       ServiceCommande cm = new ServiceCommande();
       ServicePharmacie ph = new ServicePharmacie();
       //cm.ajouter(new Commande( 2, 3, "Gafsa", new Date(120,7,01), "etat"));
        //ph.ajouter(new Pharmacie("Gafsa", "Elamen", 2100000));
       //cm.modifier(new Commande(8, 2, 3, "Sfax", new Date(120,7,01), "etat valid"));
        //ph.modifier(new Pharmacie(6, "Pharmacie6", "Adresse_Pharmacie6", 123));
        //cp.modifier(new Commande(8, 2, 3, "Gafsa", new Date(120,7,01), "etat"));
        //cm.supprimer(new Commande(8));
        //ph.supprimer(new Pharmacie(6));   
        //cp.supprimer(new CommandePharmacie(1, 1, 0, "non dispo"));
        //System.out.println(ph.afficher());
        //System.out.println(cp.afficher());
        //System.out.println(cm.afficher());
        System.out.println(cm.getCommWithPhar());
        //cm.getCommWithPhar();
        }
    
}
