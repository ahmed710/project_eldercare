/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author iheb
 */
public class Moderateur extends User{
    
    

    public Moderateur(int id, String nom, String prenom, String sexe, String email,String motdepasse) {
        super(id, nom, prenom, sexe, email,motdepasse);
    }

    public Moderateur(String nom, String prenom, String sexe, String email,String motdepasse) {
        super(nom, prenom, sexe, email,motdepasse);
    }
    public Moderateur(String nom, String prenom, String sexe, String email) {
        super(nom, prenom, sexe, email);
    }



    @Override
    public String toString() {
        return "Moderateur{"+super.toString() + '}'+ '\n';
    }
    
    
}
