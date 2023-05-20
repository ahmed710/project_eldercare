/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_eldercare.test;

import projet_eldercare.entites.Maladie;
import projet_eldercare.sevices.ServiceMaladie;

/**
 *
 * @author 21693
 */
public class MainProg {
       public static void main(String[] args) {

        ServiceMaladie m1 = new ServiceMaladie();
        m1.ajouter(new Maladie("1","cida","maladi pour","maladie de foie ", "moyenne"));
        m1.modifier(new Maladie("1","cida1","maladi pour","maladie de foie ", "moyenne"));
        m1.supprimer(new Maladie("1","cida1","maladi pour","maladie de foie ", "moyenne"));
        m1.ajouter(new Maladie("3","cida","maladi pour","maladie de foie ", "moyenne"));
        System.out.println(m1.afficher());

        
    } 
}
