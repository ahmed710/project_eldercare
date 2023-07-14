/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;


import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author DONIG
 */
public class RendezVous {
    private int idR;
    private Date date;
    private String idPatient;
    private String idMedecin;
    private String etat;

    public RendezVous(int idR, Date date, String idPatient, String idMedecin, String etat) {
        this.idR = idR;
        this.date = date;
        this.idPatient = idPatient;
        this.idMedecin = idMedecin;
        this.etat = etat;
    }

    public RendezVous() {
    }

    public RendezVous(Date date, String idPatient, String idMedecin, String etat) {
        this.date = date;
        this.idPatient = idPatient;
        this.idMedecin = idMedecin;
        this.etat = etat;
    }

    public RendezVous(int idR, String etat) {
        this.idR = idR;
        this.etat = etat;
    }

    public RendezVous(int idR) {
        this.idR = idR;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(String idMedecin) {
        this.idMedecin = idMedecin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return  etat+" : Mr/Mme ' "+idPatient+" '"+" rendrait visite chez Docteur ' "+idMedecin+" '"+ " le "+"("+date+")";
    }

    

   
}
