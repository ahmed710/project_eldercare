/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Enities;

/**
 *
 * @author houcem
 */
public class Pharmacie {
    
    private String adresse_pha;
    private int ID_pharmacie;
    private String nom_pharmacie;
    private String telephone;

    public Pharmacie(String adresse_pha, int ID_pharmacie, String nom_pharmacie, String telephone) {
        this.adresse_pha = adresse_pha;
        this.ID_pharmacie = ID_pharmacie;
        this.nom_pharmacie = nom_pharmacie;
        this.telephone = telephone;
    }

    public String getAdresse_pha() {
        return adresse_pha;
    }

    public void setAdresse_pha(String adresse_pha) {
        this.adresse_pha = adresse_pha;
    }

    public int getID_pharmacie() {
        return ID_pharmacie;
    }

    public void setID_pharmacie(int ID_pharmacie) {
        this.ID_pharmacie = ID_pharmacie;
    }

    public String getNom_pharmacie() {
        return nom_pharmacie;
    }

    public void setNom_pharmacie(String nom_pharmacie) {
        this.nom_pharmacie = nom_pharmacie;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Pharmacie{" +
                "adresse_pha='" + adresse_pha + '\'' +
                ", ID_pharmacie=" + ID_pharmacie +
                ", nom_pharmacie='" + nom_pharmacie + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}

