/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.sql.Date;

/**
 *
 * @author MSI
 */
public class user_1 {
    private int ID_user;
    private String nom;
    private String prenom;
    private String role;
    private String sexe;
     private String email;
     private String adresse;
    private Date date_de_naissance;
    private float poids;
    private int taille;
         private String specialite;
    private String motdepasse;

    public user_1() {
    }

    public user_1(int ID_user, String nom, String prenom, String role, String sexe, String email, String adresse, Date date_de_naissance, float poids, int taille, String specialite, String motdepasse) {
        this.ID_user = ID_user;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.sexe = sexe;
        this.email = email;
        this.adresse = adresse;
        this.date_de_naissance = date_de_naissance;
        this.poids = poids;
        this.taille = taille;
        this.specialite = specialite;
        this.motdepasse = motdepasse;
    }

    public user_1(String nom, String prenom, String role, String sexe, String email, String adresse, Date date_de_naissance, float poids, int taille, String specialite, String motdepasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.sexe = sexe;
        this.email = email;
        this.adresse = adresse;
        this.date_de_naissance = date_de_naissance;
        this.poids = poids;
        this.taille = taille;
        this.specialite = specialite;
        this.motdepasse = motdepasse;
    }

    public int getID_user() {
        return ID_user;
    }

    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(Date date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    @Override
    public String toString() {
        return "user{" + "nom=" + nom + ", prenom=" + prenom + ", role=" + role + ", sexe=" + sexe + ", email=" + email + ", adresse=" + adresse + ", date_de_naissance=" + date_de_naissance + ", poids=" + poids + ", taille=" + taille + ", specialite=" + specialite + ", motdepasse=" + motdepasse + '}';
    }
    
}
