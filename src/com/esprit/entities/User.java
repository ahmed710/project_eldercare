/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

/**
 *
 * @author iheb
 */
public abstract class User {
    private int id;
    private String nom;
    private String prenom;
    private String sexe;
    private String email;
    private String motdepasse;
    private String etat;
    private int num_tel;

    public User(int id, String nom, String prenom, String sexe, String email,String motdepasse,String etat,int num_tel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.email = email;
        this.motdepasse=motdepasse;
        this.etat=etat;
        this.num_tel=num_tel;
    }

    public User(String nom, String prenom, String sexe, String email,String motdepasse,String etat,int num_tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.email = email;
        this.motdepasse=motdepasse;
        this.etat=etat;
        this.num_tel=num_tel;
    }
    public User(String nom, String prenom, String sexe, String email,String etat,int num_tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.email = email;
        this.etat=etat;
        this.num_tel=num_tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", email=" + email + ", motdepasse=" + motdepasse + ", etat=" + etat + ", num_tel=" + num_tel + '}';
    }



}
