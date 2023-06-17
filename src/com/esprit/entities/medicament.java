/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

import com.esprit.services.Medicament;
import java.util.List;

/**
 *
 * @author Zouhour
 */

public class medicament {
    private int idMedicament;
    private String nom;
    private float prix;
    private String description;
    private String categorie;

    public medicament(int idMedicament, String nom, float prix, String description, String categorie) {
        this.idMedicament = idMedicament;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.categorie = categorie;
    }

    public medicament(String nom, float prix, String description, String categorie) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.categorie = categorie;
    }

    public medicament(int idMedicament) {
        this.idMedicament = idMedicament;
    }

    public medicament(int idMedicament, float prix) {
        this.idMedicament = idMedicament;
        this.prix = prix;
    }

    public medicament(String string, float aFloat, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIdMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(int idMedicament) {
        this.idMedicament = idMedicament;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Medicament{" + " nom=" + nom + ", prix=" + prix + ", description=" + description + ", categorie=" + categorie + '}';
    }}

   
   

