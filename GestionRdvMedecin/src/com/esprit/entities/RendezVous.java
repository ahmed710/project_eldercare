/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

/**
 *
 * @author DONIG
 */
public class RendezVous {

    private int idR;
    private String date;
    private String patient;
    private String medecin;
    private String etat;

    public RendezVous(int idR, String date, String patient, String medecin, String etat) {
        this.idR = idR;
        this.date = date;
        this.patient = patient;
        this.medecin = medecin;
        this.etat = etat;
    }

    public RendezVous(String date, String patient, String medecin, String etat) {
        this.date = date;
        this.patient = patient;
        this.medecin = medecin;
        this.etat = etat;
    }

    public RendezVous(int idR, String etat) {
        this.idR = idR;
        this.etat = etat;
    }

    public RendezVous(int idR) {
        this.idR = idR;
    }

    public RendezVous(String date, String patient, String etat) {
        this.date = date;
        this.patient = patient;
        this.etat = etat;
    }

    public RendezVous(String date, String medecin) {
        this.date = date;
        this.medecin = medecin;
    }


    
    
    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getMedecin() {
        return medecin;
    }

    public void setMedecin(String medecin) {
        this.medecin = medecin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

   
    @Override
    public String toString() {
        return " *Votre Rendez-vous du (" + date + ")"
                + " avec '" + patient + "' "
                + "est: " + etat + "\n";
    }

}
