/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

import java.util.Date;

/**
 *
 * @author Zouhour
 */
public class Traitement {
    private int ID_traitement;
    private Date Date_Debut;
    private Date Date_Fin;
    private String description;
    private String ID_medicament;
    private int ID_ordonnance;
//    private int PrisesMatin;
//    private int PrisesMidi;
//    private int PrisesSoir;
    private int nbr_prises ; 

    public int getID_traitement() {
        return ID_traitement;
    }

    public void setID_traitement(int ID_traitement) {
        this.ID_traitement = ID_traitement;
    }

    public Date getDate_Debut() {
        return Date_Debut;
    }

    public void setDate_Debut(Date Date_Debut) {
        this.Date_Debut = Date_Debut;
    }

    public Date getDate_Fin() {
        return Date_Fin;
    }

    public void setDate_Fin(Date Date_Fin) {
        this.Date_Fin = Date_Fin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getID_medicament() {
        return ID_medicament;
    }

    public void setID_medicament(String ID_medicament) {
        this.ID_medicament = ID_medicament;
    }

    public int getID_ordonnance() {
        return ID_ordonnance;
    }

    public void setID_ordonnance(int ID_ordonnance) {
        this.ID_ordonnance = ID_ordonnance;
    }

    public int getNbr_prises() {
        return nbr_prises;
    }

    public void setNbr_prises(int nbr_prises) {
        this.nbr_prises = nbr_prises;
    }


    /*maghir ID*/
    public Traitement(Date Date_Debut, Date Date_Fin, String description, String ID_medicament
            , int ID_ordonnance, int nbr_prises) {
      
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
        this.description = description;
        this.ID_medicament = ID_medicament;
        this.ID_ordonnance = ID_ordonnance;
        this.nbr_prises = nbr_prises ;
    }
    /*bel ID*/
    public Traitement(int ID_traitement, Date Date_Debut, Date Date_Fin, String description,
            String ID_medicament, int ID_ordonnance, int nbr_prises) {
        this.ID_traitement = ID_traitement;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
        this.description = description;
        /*this.ID_medicament = ID_medicament;
        this.ID_ordonnance = ID_ordonnance;*/
        this.nbr_prises = nbr_prises ;

    }

   

    public Traitement() {
    }

    

    @Override
    public String toString() {
        return "Traitement{" + "ID_traitement=" + ID_traitement + ", Date_Debut=" + Date_Debut + ", Date_Fin=" + Date_Fin + ", description=" + description + ", ID_medicament=" + ID_medicament + ", ID_ordonnance=" + ID_ordonnance + ", nbr_prises=" + nbr_prises + '}';
    }


  
    
}
