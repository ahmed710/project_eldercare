/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author iheb
 */
public class Patient extends User{
    private float poids;
    private int taille;
    private String adresse;
    private String date_de_naissance; 

    public Patient( int id, String nom, String prenom, String sexe, String email, float poids, int taille,String adresse,String date_de_naissance,String motdepasse) {
        super(id, nom, prenom, sexe, email,motdepasse);
        this.poids = poids;
        this.taille = taille;
        this.adresse=adresse;
        this.date_de_naissance=date_de_naissance;
    }


    public Patient( String nom, String prenom, String sexe, String email, float poids, int taille,String adresse,String date_de_naissance,String motdepasse) {
        super(nom, prenom, sexe, email,motdepasse);
        this.poids = poids;
        this.taille = taille;
        this.adresse=adresse;
        this.date_de_naissance=date_de_naissance;
    }
    public Patient( String nom, String prenom, String sexe, String email, float poids, int taille,String adresse,String date_de_naissance) {
        super(nom, prenom, sexe, email);
        this.poids = poids;
        this.taille = taille;
        this.adresse=adresse;
        this.date_de_naissance=date_de_naissance;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    @Override
    public String toString() {
        return "Patient{" +super.toString() +"poids=" + poids + ", taille=" + taille + ", adresse=" + adresse + ", date_de_naissance=" + date_de_naissance + '}'+'\n';
    }
    
    


    
    
    
    }
    
    
    

