/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;
import java.sql.Date;

/**
 *
 * @author houcem
 */


public class Commande {
    
    private String adresse;
    private String date_commande;
    private int ID_commande;
    private int ID_pharmacie;
    private String etat;

    public Commande(String adresse, String date_commande, int ID_commande, int ID_pharmacie, String etat) {
        this.adresse = adresse;
        this.date_commande = date_commande;
        this.ID_commande = ID_commande;
        this.ID_pharmacie = ID_pharmacie;
        this.etat=etat;
    }
    public Commande(String adresse, String date_commande, String etat) {
        this.adresse = adresse;
        this.date_commande = date_commande;
        this.etat = etat;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(String date_commande) {
        this.date_commande = date_commande;
    }

    public int getID_commande() {
        return ID_commande;
    }

    public void setID_commande(int ID_commande) {
        this.ID_commande = ID_commande;
    }

    public int getID_pharmacie() {
        return ID_pharmacie;
    }

    public void setID_pharmacie(int ID_pharmacie) {
        this.ID_pharmacie = ID_pharmacie;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Commande{" + "adresse=" + adresse + ", date_commande=" + date_commande + ", ID_commande=" + ID_commande + ", ID_pharmacie=" + ID_pharmacie + ", etat=" + etat + '}';
    }


}
