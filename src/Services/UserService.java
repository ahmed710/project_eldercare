/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import org.mindrot.jbcrypt.BCrypt;
import entities.Medecin;
import entities.Moderateur;
import entities.Patient;
import entities.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author iheb
 */
public class UserService implements IService<User>{

    private Connection cnx = DataSource.getinstance().getCnx();
    public void ajouter(User u) {
        try {
            if (u instanceof Patient){
                String req = "INSERT INTO user(nom,prenom,role,sexe,email,adresse,date_de_naissance,poids,taille,motdepasse) VALUES (?,?,?,?,?,?,?,?,?,?);";
                PreparedStatement pst = cnx.prepareStatement(req);
                Patient p = (Patient) u;
                pst.setString(1,p.getNom());
                pst.setString(2,p.getPrenom());
                pst.setString(3,"patient");
                pst.setString(4,p.getSexe());
                pst.setString(5,p.getEmail());
                pst.setString(6,p.getAdresse());
                pst.setDate(7,Date.valueOf(p.getDate_de_naissance()));
                pst.setFloat(8,p.getPoids());
                pst.setInt(9,p.getTaille());
                pst.setString(10, BCrypt.hashpw(p.getMotdepasse(), BCrypt.gensalt()));
                pst.executeUpdate(); 
                System.out.println("Le Patient est ajoutée avec succés");
            }
            if(u instanceof Medecin){
                String req = "INSERT INTO user(nom,prenom,role,sexe,email,adresse,specialite,motdepasse) VALUES (?,?,?,?,?,?,?,?);";
                PreparedStatement pst = cnx.prepareStatement(req);
                Medecin m = (Medecin) u;
                pst.setString(1,m.getNom());
                pst.setString(2,m.getPrenom());
                pst.setString(3,"médecin");
                pst.setString(4,m.getSexe());
                pst.setString(5,m.getEmail());
                pst.setString(6,m.getAdresse());
                pst.setString(7,m.getSpecialite());
                pst.setString(8, BCrypt.hashpw(m.getMotdepasse(), BCrypt.gensalt()));
                pst.executeUpdate(); 
                System.out.println("Le Médecin est ajoutée avec succés");
            }
            if(u instanceof Moderateur){
                String req = "INSERT INTO user(nom,prenom,role,sexe,email,motdepasse) VALUES (?,?,?,?,?,?);";
                PreparedStatement pst = cnx.prepareStatement(req);
                Moderateur m = (Moderateur) u;
                pst.setString(1,m.getNom());
                pst.setString(2,m.getPrenom());
                pst.setString(3,"modérateur");
                pst.setString(4,m.getSexe());
                pst.setString(5,m.getEmail());
                pst.setString(10, BCrypt.hashpw(m.getMotdepasse(), BCrypt.gensalt()));
                pst.executeUpdate(); 
                System.out.println("Le Modérateur est ajoutée avec succés");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(User u) {

        try {
            if(u instanceof Patient){
            String req = "UPDATE user SET nom=?, prenom=?, role=?, sexe=?, email=?, adresse=?, date_de_naissance=?,poids=?, taille=?,motdepasse=? WHERE ID_user=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            Patient p = (Patient) u;
            pst.setString(1,p.getNom());
            pst.setString(2,p.getPrenom());
            pst.setString(3,"patient");
            pst.setString(4,p.getSexe());
            pst.setString(5,p.getEmail());
            pst.setString(6,p.getAdresse());
            pst.setDate(7,Date.valueOf(p.getDate_de_naissance()));
            pst.setFloat(8,p.getPoids());
            pst.setInt(9,p.getTaille());
            pst.setString(10, BCrypt.hashpw(p.getMotdepasse(), BCrypt.gensalt()));
            pst.setInt(11,p.getId());
            pst.executeUpdate(); 
            System.out.println("Le Patient est modifiée avec succés");
            }
            if(u instanceof Medecin){
            String req = "UPDATE user SET nom=?, prenom=?, role=?, sexe=?, email=?, adresse=?, specialite=?, motdepasse=? WHERE ID_user=?";
                PreparedStatement pst = cnx.prepareStatement(req);
                Medecin m = (Medecin) u;
                pst.setString(1,m.getNom());
                pst.setString(2,m.getPrenom());
                pst.setString(3,"médecin");
                pst.setString(4,m.getSexe());
                pst.setString(5,m.getEmail());
                pst.setString(6,m.getAdresse());
                pst.setString(7,m.getSpecialite());
                pst.setString(8,BCrypt.hashpw(m.getMotdepasse(), BCrypt.gensalt()));
                pst.setInt(9,m.getId());
                pst.executeUpdate(); 
                System.out.println("Le Médecin est modifiée avec succés");
            }
            if(u instanceof Moderateur){
                String req = "UPDATE user SET nom=?, prenom=?, role=?, sexe=?, email=?,motdepasse=?  WHERE ID_user=?";
                PreparedStatement pst = cnx.prepareStatement(req);
                Moderateur m = (Moderateur) u;
                pst.setString(1,m.getNom());
                pst.setString(2,m.getPrenom());
                pst.setString(3,"modérateur");
                pst.setString(4,m.getSexe());
                pst.setString(5,m.getEmail());
                pst.setString(6,BCrypt.hashpw(m.getMotdepasse(), BCrypt.gensalt()));
                pst.setInt(7,m.getId());
                pst.executeUpdate(); 
                System.out.println("Le Modérateur est modifiée avec succés");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(User u) {
        try {      
            if(u instanceof Patient){
                String req = "DELETE from user WHERE ID_user=?";
                PreparedStatement pst = cnx.prepareStatement(req);
                Patient p = (Patient) u;
                pst.setInt(1,p.getId());
                pst.executeUpdate(); 
                System.out.println("le Patient est supprimée avec succés");
            }
            if(u instanceof Medecin){
                String req = "DELETE from user WHERE ID_user=?";
                PreparedStatement pst = cnx.prepareStatement(req);
                Medecin m = (Medecin) u;
                pst.setInt(1,m.getId());
                pst.executeUpdate(); 
                System.out.println("le médecin est supprimée avec succés");
            }
            if(u instanceof Moderateur){
                String req = "DELETE from user WHERE ID_user=?";
                PreparedStatement pst = cnx.prepareStatement(req);
                Moderateur m = (Moderateur) u;
                pst.setInt(1,m.getId());
                pst.executeUpdate(); 
                System.out.println("le modérateur est supprimée avec succés");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public List<User> afficher() {
        List<User> liste = new ArrayList();
        try {
            String req ="SELECT * FROM user";        
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rst =pst.executeQuery();
            while(rst.next()){
            String role =rst.getString("role");
            User user;
                if(role.contains("patient"))
                {
                    //int id, String nom, String prenom, String sexe, String email,String role, float poids, int taille,String adresse
                    int id = rst.getInt("ID_user");
                    String nom = rst.getString("nom");
                    String prenom = rst.getString("prenom");
                    String sexe = rst.getString("sexe");
                    String email = rst.getString("email");
                    String adresse = rst.getString("adresse");
                    float poids = rst.getFloat("poids");
                    int taille = rst.getInt("taille");
                    String motdepasse=rst.getString("motdepasse");
                    String date_de_naissance=rst.getString("date_de_naissance");
                    liste.add(new Patient(id, nom, prenom, sexe, email, poids, taille, adresse,date_de_naissance,motdepasse));                
                }
                if(role.contains("médecin")){
                    int id = rst.getInt("ID_user");
                    String nom = rst.getString("nom");
                    String prenom = rst.getString("prenom");
                    String sexe = rst.getString("sexe");
                    String email = rst.getString("email");
                    String adresse = rst.getString("adresse");
                    String specialite=rst.getString("specialite");
                    String motdepasse=rst.getString("motdepasse");
                    liste.add(new Medecin(id, nom, prenom, sexe, email, specialite, adresse,motdepasse));
                }
                if(role.contains("modérateur")){
                    int id = rst.getInt("ID_user");
                    String nom = rst.getString("nom");
                    String prenom = rst.getString("prenom");
                    String sexe = rst.getString("sexe");
                    String email = rst.getString("email");
                    String motdepasse = rst.getString("motdepasse");
                    liste.add(new Moderateur(id, nom, prenom, sexe, email,motdepasse));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        
        
        return liste;
    }
    
}
