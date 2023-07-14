/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

/**
 *
 * @author iheb
 */
public class Medecin extends User{

    private String specialite;
    private String role;


    public Medecin(String nom, String prenom, String sexe, String email, String motdepasse, String etat, int num_tel, String specialite, String adresse) {
        super(nom,prenom,email,motdepasse,etat,num_tel,sexe,adresse);
        this.specialite = specialite;
        this.role="médecin";

    }

    public Medecin(int id, String nom, String prenom, String sexe, String email, String motdepasse, String etat, int num_tel, String specialite, String adresse) {
        super(id,nom,prenom,email,motdepasse,etat,num_tel,sexe,adresse);
        this.specialite = specialite;
        this.role="médecin";

    }

    public Medecin(String nom, String prenom, String sexe, String email, String motdepasse, String etat, int num_tel, String specialite, String adresse, String imageUrl) {
        super(nom,prenom,email,motdepasse,etat,num_tel,sexe,adresse,imageUrl);
        this.specialite = specialite;
        this.role="médecin";
    }

    public Medecin(int id, String nom, String prenom, String sexe, String email, String motdepasse, String etat, int num_tel, String specialite, String adresse, String imageUrl) {
        super(id,nom,prenom,email,motdepasse,etat,num_tel,sexe,adresse,imageUrl);
        this.specialite = specialite;
        this.role="médecin";
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Medecin{"+super.toString() + "specialite=" + specialite + ", role=" + role + '}';
    }




    
}
