/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Zouhour
 */
import java.util.List;

public class Ordonnance {
    private int ID_ordonnance;
    protected String ID_medicament; // Changed from String to List<String>
    private String ID_patient;
    private String ID_medecin;
    private Date date;
<<<<<<< Updated upstream
    
  
       public List<String> getID_medicament() {
        if (ID_medicament == null) {
            ID_medicament = new ArrayList<String>(); // Initialize the list if it's null
        }
=======
    private String nom_prenomMedecin;
    private String nom_prenomPatient;

    public String getNom_prenomMedecin() {
        return nom_prenomMedecin;
    }

    public void setNom_prenomMedecin(String nom_prenomMedecin) {
        this.nom_prenomMedecin = nom_prenomMedecin;
    }

    public String getNom_prenomPatient() {
        return nom_prenomPatient;
    }

    public void setNom_prenomPatient(String nom_prenomPatient) {
        this.nom_prenomPatient = nom_prenomPatient;
    }

    public String getID_medicament() {
>>>>>>> Stashed changes
        return ID_medicament;
    }

    public void setID_medicament(String ID_medicament) {
        this.ID_medicament = ID_medicament;
    }

    public String getID_patient() {
        return ID_patient;
    }

    public void setID_patient(String ID_patient) {
        this.ID_patient = ID_patient;
    }

    public String getID_medecin() {
        return ID_medecin;
    }

    public void setID_medecin(String ID_medecin) {
        this.ID_medecin = ID_medecin;
    }
    
  
    public int getID_ordonnance() {
        return ID_ordonnance;
    }
    
    public void setID_ordonnance(int ID_ordonnance) {
        this.ID_ordonnance = ID_ordonnance;
    }
    

    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    public Ordonnance() {
    }

    public Ordonnance(int ID_ordonnance, String ID_medicament, String ID_patient, String ID_medecin, Date date, String nom_prenomMedecin, String nom_prenomPatient) {
        this.ID_ordonnance = ID_ordonnance;
        this.ID_medicament = ID_medicament;
        this.ID_patient = ID_patient;
        this.ID_medecin = ID_medecin;
        this.date = date;
        this.nom_prenomMedecin = nom_prenomMedecin;
        this.nom_prenomPatient = nom_prenomPatient;
    }

    public Ordonnance(String ID_medicament,  Date date, String nom_prenomMedecin, String nom_prenomPatient) {
        this.ID_medicament = ID_medicament;

        this.date = date;
        this.nom_prenomMedecin = nom_prenomMedecin;
        this.nom_prenomPatient = nom_prenomPatient;
    }

    public Ordonnance(String ID_medicament, String ID_patient, String ID_medecin, Date date, String nom_prenomMedecin, String nom_prenomPatient) {
        this.ID_medicament = ID_medicament;
        this.ID_patient = ID_patient;
        this.ID_medecin = ID_medecin;
        this.date = date;
        this.nom_prenomMedecin = nom_prenomMedecin;
        this.nom_prenomPatient = nom_prenomPatient;
    }

<<<<<<< Updated upstream
    public Ordonnance() {
    }

    public Ordonnance(int ID_ordonnance, List<String> ID_medicament, int ID_patient, int ID_medecin, Date date) {
        this.ID_ordonnance = ID_ordonnance;
        this.ID_medicament = ID_medicament;
        this.ID_patient = ID_patient;
        this.ID_medecin = ID_medecin;
        this.date = date;
    }
=======

>>>>>>> Stashed changes
    
    
    
    @Override
    public String toString() {
        return "Ordonnance{" +
                "ID_ordonnance=" + ID_ordonnance +
                ", ID_medicament=" + ID_medicament +
                ", ID_patient=" + ID_patient +
                ", ID_medecin=" + ID_medecin +
                ", date=" + date +
                '}';
    }

  
    
    
    
}