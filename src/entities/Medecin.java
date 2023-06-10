/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author iheb
 */
public class Medecin extends User{
    private String specialite;
    private String adresse;

    public Medecin(int id, String nom, String prenom, String sexe, String email, String specialite,String adresse,String motdepasse ) {
        super(id, nom, prenom, sexe, email, motdepasse);
        this.specialite = specialite;
        this.adresse=adresse;
    }

    public Medecin( String nom, String prenom, String sexe, String email, String specialite,String adresse,String motdepasse) {
        super(nom, prenom, sexe, email,motdepasse);
        this.specialite = specialite;
        this.adresse=adresse;
    }
    public Medecin( String nom, String prenom, String sexe, String email, String specialite,String adresse) {
        super(nom, prenom, sexe, email);
        this.specialite = specialite;
        this.adresse=adresse;
    }


    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Medecin{" +super.toString()+ ", specialite=" + specialite + ", adresse=" + adresse + '}'+ '\n';
    }


    
}
