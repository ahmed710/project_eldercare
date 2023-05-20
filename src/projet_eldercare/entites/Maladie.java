/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_eldercare.entites;

/**
 *
 * @author 21693
 */
public class Maladie {
    private String id_maladie ; 
    private String titre ; 
    private String description  ;
    private String categorie ; 
    private String situation ; 

    public Maladie(String id_maladie, String titre, String description, String categorie, String situation) {
        this.id_maladie = id_maladie;
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.situation = situation;
    }

    public Maladie() {
    }

    public String getId_maladie() {
        return id_maladie;
    }

    public void setId_maladie(String id_maladie) {
        this.id_maladie = id_maladie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    @Override
    public String toString() {
        return "Maladie{" + "id_maladie=" + id_maladie + ", titre=" + titre + ", description=" + description + ", categorie=" + categorie + ", situation=" + situation + '}';
    }


    
    
}

