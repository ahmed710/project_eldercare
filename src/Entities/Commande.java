/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Wimbee018
 */
public class Commande {
    private int ID_commande;
    private int ID_pharmacie;
    private String adresse;
    private String nom_pharmacie;
    private Date date_commande;
    private String etat;

    public Commande() {
    }

    public Commande(int ID_pharmacie, String adresse, String nom_pharmacie, Date date_commande, String etat) {
        this.ID_pharmacie = ID_pharmacie;
        this.adresse = adresse;
        this.nom_pharmacie = nom_pharmacie;
        this.date_commande = date_commande;
        this.etat = etat;
    }

    public Commande(int ID_commande, int ID_pharmacie, String adresse, String nom_pharmacie, Date date_commande, String etat) {
        this.ID_commande = ID_commande;
        this.ID_pharmacie = ID_pharmacie;
        this.adresse = adresse;
        this.nom_pharmacie = nom_pharmacie;
        this.date_commande = date_commande;
        this.etat = etat;
    }
    
    public Commande(int ID_commande, int ID_pharmacie, String adresse, Date date_commande, String etat) {
        this.ID_commande = ID_commande;
        this.ID_pharmacie = ID_pharmacie;
        this.adresse = adresse;
        this.date_commande = date_commande;
        this.etat = etat;
    }

    public Commande(int ID_commande) {
        this.ID_commande = ID_commande;
    }

    public Commande(int ID_pharmacie,  String adresse, Date date_commande, String etat) {
        this.ID_pharmacie = ID_pharmacie;
        this.adresse = adresse;
        this.date_commande = date_commande;
        this.etat = etat;
    }

    public String getNom_pharmacie() {
        return nom_pharmacie;
    }

    public void setNom_pharmacie(String nom_pharmacie) {
        this.nom_pharmacie = nom_pharmacie;
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


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Commande{" + "ID_commande=" + ID_commande + ", ID_pharmacie=" + ID_pharmacie + ", adresse=" + adresse + ", nom_pharmacie=" + nom_pharmacie + ", date_commande=" + date_commande + ", etat=" + etat + '}';
    }

    
}
