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
import java.util.List;

public class Ordonnance {
    private int ID_ordonnance;
    protected List<String> ID_medicament; // Changed from String to List<String>
    private int ID_patient;
    private int ID_medecin;
    private Date date;
    
  
    public List<String> getID_medicament() {
        return ID_medicament;
    }
    
    public void setID_medicament(List<String> ID_medicament) {
        this.ID_medicament = ID_medicament;
    }
    
    public int getID_ordonnance() {
        return ID_ordonnance;
    }
    
    public void setID_ordonnance(int ID_ordonnance) {
        this.ID_ordonnance = ID_ordonnance;
    }
    
    public int getID_patient() {
        return ID_patient;
    }
    
    public void setID_patient(int ID_patient) {
        this.ID_patient = ID_patient;
    }
    
    public int getID_medecin() {
        return ID_medecin;
    }
    
    public void setID_medecin(int ID_medecin) {
        this.ID_medecin = ID_medecin;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    public Ordonnance(List<String> ID_medicament, int ID_patient, int ID_medecin, Date date) {
        this.ID_medicament = ID_medicament;
        this.ID_patient = ID_patient;
        this.ID_medecin = ID_medecin;
        this.date = date;
    }

    public Ordonnance(int ID_patient, int ID_medecin, Date date) {
        this.ID_patient = ID_patient;
        this.ID_medecin = ID_medecin;
        this.date = date;
    }

    public Ordonnance() {
    }
    
    
    
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