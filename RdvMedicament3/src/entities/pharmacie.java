/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author DONIG
 */
public class pharmacie {
     private int ID_pharmacie;
    private String nom_pharmacie;
    private String adresse_pha;
         private int telephone;

    public pharmacie() {
    }

    public pharmacie(int ID_pharmacie, String nom_pharmacie, String adresse_pha, int telephone) {
        this.ID_pharmacie = ID_pharmacie;
        this.nom_pharmacie = nom_pharmacie;
        this.adresse_pha = adresse_pha;
        this.telephone = telephone;
    }

    public pharmacie(String nom_pharmacie, String adresse_pha, int telephone) {
        this.nom_pharmacie = nom_pharmacie;
        this.adresse_pha = adresse_pha;
        this.telephone = telephone;
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

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "pharmacie{" + "nom_pharmacie=" + nom_pharmacie + ", adresse_pha=" + adresse_pha + ", telephone=" + telephone + '}';
    }

    
}
