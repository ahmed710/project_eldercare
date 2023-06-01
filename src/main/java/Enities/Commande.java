/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Enities;
import java.util.Date;

/**
 *
 * @author houcem
 */


public class Commande {
    
    private String adresse;
    private Date date_commande;
    private int ID_commande;
    private int ID_ordonnance;
    private int ID_pharmacie;
    private double prix_totale;

    public Commande(String adresse, Date date_commande, int ID_commande, int ID_ordonnance, int ID_pharmacie, double prix_totale) {
        this.adresse = adresse;
        this.date_commande = date_commande;
        this.ID_commande = ID_commande;
        this.ID_ordonnance = ID_ordonnance;
        this.ID_pharmacie = ID_pharmacie;
        this.prix_totale = prix_totale;
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

    public int getID_commande() {
        return ID_commande;
    }

    public void setID_commande(int ID_commande) {
        this.ID_commande = ID_commande;
    }

    public int getID_ordonnance() {
        return ID_ordonnance;
    }

    public void setID_ordonnance(int ID_ordonnance) {
        this.ID_ordonnance = ID_ordonnance;
    }

    public int getID_pharmacie() {
        return ID_pharmacie;
    }

    public void setID_pharmacie(int ID_pharmacie) {
        this.ID_pharmacie = ID_pharmacie;
    }

    public double getPrix_totale() {
        return prix_totale;
    }

    public void setPrix_totale(double prix_totale) {
        this.prix_totale = prix_totale;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "adresse='" + adresse + '\'' +
                ", date_commande=" + date_commande +
                ", ID_commande=" + ID_commande +
                ", ID_ordonnance=" + ID_ordonnance +
                ", ID_pharmacie=" + ID_pharmacie +
                ", prix_totale=" + prix_totale +
                '}';
    }
}
