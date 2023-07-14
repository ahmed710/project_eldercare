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
public class Medicament {
    public int ID_medicament;
    public int ID_Pharmacie;
    public String nom_medicament;
    public float prix;
    public String categorie;

    public Medicament() {
    }

    public Medicament(int ID_medicament, int ID_Pharmacie, String nom_medicament, float prix, String categorie) {
        this.ID_medicament = ID_medicament;
        this.ID_Pharmacie = ID_Pharmacie;
        this.nom_medicament = nom_medicament;
        this.prix = prix;
        this.categorie = categorie;
    }

    public Medicament(int ID_Pharmacie, String nom_medicament, float prix, String categorie) {
        this.ID_Pharmacie = ID_Pharmacie;
        this.nom_medicament = nom_medicament;
        this.prix = prix;
        this.categorie = categorie;
    }

    public int getID_medicament() {
        return ID_medicament;
    }

    public void setID_medicament(int ID_medicament) {
        this.ID_medicament = ID_medicament;
    }

    public int getID_Pharmacie() {
        return ID_Pharmacie;
    }

    public void setID_Pharmacie(int ID_Pharmacie) {
        this.ID_Pharmacie = ID_Pharmacie;
    }

    public String getNom_medicament() {
        return nom_medicament;
    }

    public void setNom_medicament(String nom_medicament) {
        this.nom_medicament = nom_medicament;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Medicament{" + "ID_medicament=" + ID_medicament + ", ID_Pharmacie=" + ID_Pharmacie + ", nom_medicament=" + nom_medicament + ", prix=" + prix + ", categorie=" + categorie + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.ID_medicament;
        hash = 47 * hash + this.ID_Pharmacie;
        hash = 47 * hash + Objects.hashCode(this.nom_medicament);
        hash = 47 * hash + Float.floatToIntBits(this.prix);
        hash = 47 * hash + Objects.hashCode(this.categorie);
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
        final Medicament other = (Medicament) obj;
        if (this.ID_medicament != other.ID_medicament) {
            return false;
        }
        if (this.ID_Pharmacie != other.ID_Pharmacie) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.nom_medicament, other.nom_medicament)) {
            return false;
        }
        return Objects.equals(this.categorie, other.categorie);
    }
    
    
    
}
