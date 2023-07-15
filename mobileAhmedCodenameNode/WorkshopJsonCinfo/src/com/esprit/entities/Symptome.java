/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

import com.esprit.utils.Organe;

/**
 *
 * @author ahmed
 */



public class Symptome {
    private int ID_Symptome ;
    private String titre ; 
    private String description ; 
    private int frequenceParJour ; 
    private int frequenceParSemaine ; 
    private float duree ; 
    private String organe ; 

    public Symptome(int ID_Symptome, String titre, String description, int frequenceParJour, int frequenceParSemaine, float duree, String organe) {
        this.ID_Symptome = ID_Symptome;
        this.titre = titre;
        this.description = description;
        this.frequenceParJour = frequenceParJour;
        this.frequenceParSemaine = frequenceParSemaine;
        this.duree = duree;
        this.organe = organe;
    }

    public Symptome() {
    }

    public Symptome(String titre, String description, int frequenceParJour, int frequenceParSemaine, float duree, String organe) {
        this.titre = titre;
        this.description = description;
        this.frequenceParJour = frequenceParJour;
        this.frequenceParSemaine = frequenceParSemaine;
        this.duree = duree;
        this.organe = organe;
    }

    public Symptome(int ID_Symptome, String titre) {
        this.ID_Symptome= ID_Symptome ; 
       this.titre = titre;
    }


    public int getID_Symptome() {
        return ID_Symptome;
    }

    public void setID_Symptome(int ID_Symptome) {
        this.ID_Symptome = ID_Symptome;
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

    public int getFrequenceParJour() {
        return frequenceParJour;
    }

    public void setFrequenceParJour(int frequenceParJour) {
        this.frequenceParJour = frequenceParJour;
    }

    public int getFrequenceParSemaine() {
        return frequenceParSemaine;
    }

    public void setFrequenceParSemaine(int frequenceParSemaine) {
        this.frequenceParSemaine = frequenceParSemaine;
    }

    public float getDuree() {
        return duree;
    }

    public void setDuree(float duree) {
        this.duree = duree;
    }

    public String getOrgane() {
        return organe;
    }

    public void setOrgane(String organe) {
        this.organe = organe;
    }

    @Override
    public String toString() {
        return "Symptome{" + "ID_Symptome=" + ID_Symptome + ", titre=" + titre + ", description=" + description + ", frequenceParJour=" + frequenceParJour + ", frequenceParSemaine=" + frequenceParSemaine + ", duree=" + duree + ", organe=" + organe + '}';
    }

   

}
