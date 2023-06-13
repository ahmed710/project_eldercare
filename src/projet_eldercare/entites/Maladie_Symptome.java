/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_eldercare.entites;

/**
 *
 * @author 21693
 */
public class Maladie_Symptome {
         private int ID_Symptome ;  
         private int ID_Maladie ;
         private float taux_de_correspondance ; 

    public Maladie_Symptome(int ID_Symptome, int ID_Maladie, float taux_de_correspondance) {
        this.ID_Symptome = ID_Symptome;
        this.ID_Maladie = ID_Maladie;
        this.taux_de_correspondance = taux_de_correspondance;
    }

    public Maladie_Symptome() {
    }

    public int getID_Symptome() {
        return ID_Symptome;
    }

    public void setID_Symptome(int ID_Symptome) {
        this.ID_Symptome = ID_Symptome;
    }

    public int getID_Maladie() {
        return ID_Maladie;
    }

    public void setID_Maladie(int ID_Maladie) {
        this.ID_Maladie = ID_Maladie;
    }

    public float getTaux_de_correspondance() {
        return taux_de_correspondance;
    }

    public void setTaux_de_correspondance(float taux_de_correspondance) {
        this.taux_de_correspondance = taux_de_correspondance;
    }

    public Maladie getMaladie() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Symptome getSymptome() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  
    
}
