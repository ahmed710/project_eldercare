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
         private String idSymptome ;  
         private String id_maladie ;

    public Maladie_Symptome(String idSymptome, String id_maladie) {
        this.idSymptome = idSymptome;
        this.id_maladie = id_maladie;
    }

    public Maladie_Symptome() {
    }

    public String getIdSymptome() {
        return idSymptome;
    }

    public void setIdSymptome(String idSymptome) {
        this.idSymptome = idSymptome;
    }

    public String getId_maladie() {
        return id_maladie;
    }

    public void setId_maladie(String id_maladie) {
        this.id_maladie = id_maladie;
    }
    
    
}
