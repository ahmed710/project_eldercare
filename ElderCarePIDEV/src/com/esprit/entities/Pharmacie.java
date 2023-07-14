/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.ID_pharmacie;
        hash = 11 * hash + Objects.hashCode(this.nom_pharmacie);
        hash = 11 * hash + Objects.hashCode(this.adresse_pha);
        hash = 11 * hash + Objects.hashCode(this.telephone);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pharmacie other = (Pharmacie) obj;
        if (this.ID_pharmacie != other.ID_pharmacie) {
            return false;
        }
        if (!Objects.equals(this.nom_pharmacie, other.nom_pharmacie)) {
            return false;
        }
        if (!Objects.equals(this.adresse_pha, other.adresse_pha)) {
            return false;
        }
        return Objects.equals(this.telephone, other.telephone);
    }
    
    
    
}
