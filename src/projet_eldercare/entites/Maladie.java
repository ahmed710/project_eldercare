/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_eldercare.entites;

import projet_eldercare.utils.Classification_etiologique;
import projet_eldercare.utils.Classification_fonctionelle;

/**
 *
 * @author 21693
 */
public class Maladie {
    private int ID_Maladie ; 
    private String titre ; 
    private String description  ;
    private String classification_etiologique ;  
    private String classification_fonctionelle; 

    public Maladie(int ID_Maladie, String titre, String description, String classification_etiologique, String classification_fonctionelle) {
        this.ID_Maladie = ID_Maladie;
        this.titre = titre;
        this.description = description;
        this.classification_etiologique = classification_etiologique;
        this.classification_fonctionelle = classification_fonctionelle;
    }

    public Maladie(int ID_Maladie, String titre) {
        this.ID_Maladie = ID_Maladie;
        this.titre = titre;
    }

    public Maladie(String titre, String description, String classification_etiologique, String classification_fonctionelle) {
        this.titre = titre;
        this.description = description;
        this.classification_etiologique = classification_etiologique;
        this.classification_fonctionelle = classification_fonctionelle;
    }
    

    public Maladie() {
    }

    public int getID_Maladie() {
        return ID_Maladie;
    }

    public void setID_Maladie(int ID_Maladie) {
        this.ID_Maladie = ID_Maladie;
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

    public String getClassification_etiologique() {
        return classification_etiologique;
    }

    public void setClassification_etiologique(String classification_etiologique) {
        this.classification_etiologique = classification_etiologique;
    }

    public String getClassification_fonctionelle() {
        return classification_fonctionelle;
    }

    public void setClassification_fonctionelle(String classification_fonctionelle) {
        this.classification_fonctionelle = classification_fonctionelle;
    }

    @Override
    public String toString() {
        return "Maladie{" + "ID_Maladie=" + ID_Maladie + ", titre=" + titre + ", description=" + description + ", classification_etiologique=" + classification_etiologique + ", classification_fonctionelle=" + classification_fonctionelle + '}';
    }

  
    
    
    
}

