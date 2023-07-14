/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

/**
 *
 * @author iheb
 */
public class Patient extends User{
    private float poids;
    private int taille;
    private String date_de_naissance; 
    private String role;


    public Patient(String nom, String prenom, String sexe, String email, String motdepasse, String etat, int num_tel, float poids, int taille, String adresse, String date_de_naissance, String imageUrl) {
        super(nom,prenom,email,motdepasse,etat,num_tel,sexe,adresse);
        this.date_de_naissance = date_de_naissance;
        this.role="patient";
        this.poids=poids;
        this.taille=taille;
    }

    public Patient(int id, String nom, String prenom, String sexe, String email, String motdepasse, String etat, int num_tel, float poids, int taille, String adresse, String date_de_naissance, String imageUrl) {
        super(id,nom,prenom,email,motdepasse,etat,num_tel,sexe,adresse);
        this.poids = poids;
        this.taille = taille;
        this.date_de_naissance = date_de_naissance;
        this.role="patient";

    }

    public Patient(String nom, String prenom, String sexe, String email, String motdepasse, String etat, int num_tel, float poids, int taille, String adresse, String date_de_naissance) {
        super(nom,prenom,email,motdepasse,etat,num_tel,sexe,adresse);
        this.poids = poids;
        this.taille = taille;
        this.date_de_naissance = date_de_naissance;
        this.role="patient";

    }

    public Patient(int id,String nom, String prenom, String sexe, String email, String motdepasse, String etat, int num_tel, float poids, int taille, String adresse, String date_de_naissance) {
        super(id,nom,prenom,email,motdepasse,etat,num_tel,sexe,adresse);
        this.date_de_naissance = date_de_naissance;
        this.role="patient";
        this.poids=poids;
        this.taille=taille;

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

    public String getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Patient{"+super.toString() + "poids=" + poids + ", taille=" + taille + ", date_de_naissance=" + date_de_naissance + ", role=" + role + '}';
    }
    
}
