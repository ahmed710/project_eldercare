/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Wimbee018
 */
public class Pharmacie {
    
    private int ID_pharmacie;
    private String nom_pharmacie;
    private String adresse_pha;
    private String telephone;

    public Pharmacie(String nom_pharmacie, String adresse_pha, String telephone) {
        this.nom_pharmacie = nom_pharmacie;
        this.adresse_pha = adresse_pha;
        this.telephone = telephone;
    }

    public Pharmacie(int ID_pharmacie, String nom_pharmacie, String adresse_pha, String telephone) {
        this.ID_pharmacie = ID_pharmacie;
        this.nom_pharmacie = nom_pharmacie;
        this.adresse_pha = adresse_pha;
        this.telephone = telephone;
    }

    public Pharmacie(String nom_pharmacie) {
                this.nom_pharmacie = nom_pharmacie;
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

    public String getAdresse_pha() {
        return adresse_pha;
    }

    public void setAdresse_pha(String adresse_pha) {
        this.adresse_pha = adresse_pha;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Pharmacie{" + "ID_pharmacie=" + ID_pharmacie + ", nom_pharmacie=" + nom_pharmacie + ", adresse_pha=" + adresse_pha + ", telephone=" + telephone + '}';
    }
    
    
}
