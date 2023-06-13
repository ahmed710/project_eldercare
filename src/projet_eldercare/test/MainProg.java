/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_eldercare.test;

import projet_eldercare.entites.*;
import projet_eldercare.sevices.*;

/**
 *
 * @author 21693
 */
public class MainProg {
    public static void main(String[] args) {

//        ServiceMaladie m1 = new ServiceMaladie();
//        m1.ajouter(new Maladie(4, "cida", "maladi pour", "Maladies_dyscrasiques", "Dysfonctionnements_cellulaires"));
//        m1.modifier(new Maladie(1, "cida1", "maladi pour", "Maladies_infectieuses","Dysfonctionnements_cellulaires"));
//        m1.supprimer(new Maladie(1, "cida1", "maladi pour", "Maladies_dyscrasiques", "Dysfonctionnements_corporel"));
//        m1.ajouter(new Maladie(5, "cida", "maladi pour", "Maladies_dyscrasiques", "Dysfonctionnements_corporel"));
//        System.out.println(m1.afficher());

        ServiceSymptome ss = new ServiceSymptome();
        System.out.println(ss.afficher());
//        ServiceMaladie sm = new ServiceMaladie();
//        System.out.println(sm.getMaladieIdByTitre("cida"));

//        Service_Maladie_Symptome ms = new Service_Maladie_Symptome();
//        ms.ajouter(new Maladie_Symptome(1, 1, 0.0f));
//        System.out.println(ms.getRelatedSymptomes(7)); 
        Service_Maladie_Symptome ms = new Service_Maladie_Symptome();
//        ms.ajouter(new Maladie_Symptome(1, 1, 0.0f));
        System.out.println(ms.getRelatedMaladies(5));
    } 
}
