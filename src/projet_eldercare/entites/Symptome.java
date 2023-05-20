/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_eldercare.entites;

/**
 *
 * @author 21693
 */
public class Symptome {
    private String idSymptome ;
    private String titre ; 
    private String description ; 
    private int frequence ; 
    private int duree ; 
    private String organe ; 

    public Symptome(String idSymptome, String titre, String description, int frequence, int duree, String organe) {
        this.idSymptome = idSymptome;
        this.titre = titre;
        this.description = description;
        this.frequence = frequence;
        this.duree = duree;
        this.organe = organe;
    }

    @Override
    public String toString() {
        return "Symptome{" + "idSymptome=" + idSymptome + ", titre=" + titre + ", description=" + description + ", frequence=" + frequence + ", duree=" + duree + ", organe=" + organe + '}';
    }

    public Symptome() {
    }

    public String getIdSymptome() {
        return idSymptome;
    }

    public void setIdSymptome(String ID_Symptome) {
        this.idSymptome = ID_Symptome;
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

    public int getFrequence() {
        return frequence;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getOrgane() {
        return organe;
    }

    public void setOrgane(String organe) {
        this.organe = organe;
    }


}
