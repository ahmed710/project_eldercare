/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;


/**
 *
 * @author DONIG
 */
public class Medicament {
    private int idMedicament;
    private String nom;
    private float prix;
    private String description;
    private String categorie;

    public Medicament() {
    }

    public Medicament(int idMedicament, String nom, float prix, String description, String categorie) {
        this.idMedicament = idMedicament;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.categorie = categorie;
    }

    public Medicament(String nom, float prix, String description, String categorie) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.categorie = categorie;
    }

    public Medicament(int idMedicament) {
        this.idMedicament = idMedicament;
    }

    public Medicament(int idMedicament, float prix) {
        this.idMedicament = idMedicament;
        this.prix = prix;
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

//    @Override
//    public String toString() {
//        return "Medicament{" + " nom=" + nom + ", prix=" + prix + ", description=" + description + ", categorie=" + categorie + '}';
//    }

    @Override
    public String toString() {
        return   nom ;
    }

   
   

   
   
    
    
    
}
