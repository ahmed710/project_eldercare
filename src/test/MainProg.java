/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import Entities.Commande;
import Services.ServiceCommande;
import Services.ServicePharmacie;

/**
 *
 * @author houcem
 */
public class MainProg {
        public static void main(String[] args) {
        // TODO code application logic here
        ServiceCommande cm = new ServiceCommande();
        ServicePharmacie ph = new ServicePharmacie();
        cm.modifier(new Commande("smaaa", "1997-08-09", 1, 2, "annulé"));
        // ph.ajouter(new Pharmacie("Gafsa", "Elamen", 2142));
        //cp.ajouter(new CommandePharmacie(12, "dispo"));
        //cm.modifier(new Commande("Bizerte",null,10,2,"En cours"));
        //ph.modifier(new Pharmacie("fqjhfuqh", 1, "kahloun", 0));
        //cp.modifier(new CommandePharmacie(1, 1, 0, "non dispo"));
        //cm.supprimer(new Commande("sdhufhsjd",null, 1, 0, 0, 0));
        //ph.supprimer(new Pharmacie("fqjhfuqh", 1, "kahloun", 0));   
        //cp.supprimer(new CommandePharmacie(1, 1, 0, "non dispo"));
        //System.out.println(ph.afficher());
        //System.out.println(cp.afficher());
        System.out.println(cm.afficher());
        }
    
}
