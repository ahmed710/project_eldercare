/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_eldercare.sevices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projet_eldercare.entites.Symptome;
import projet_eldercare.utils.DataSource;

/**
 *
 * @author 21693
 */
public class ServiceSymptome implements servicesInterface<Symptome> {
        private  Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Symptome s) {
        try {

            String req = "INSERT INTO Symptome(titre,description,frequence,duree,organe)VALUES(?,?,?,?,?) ; ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, s.getTitre());
            pst.setString(2, s.getDescription());
            pst.setInt(3,s.getFrequence()) ; 
            pst.setInt(4, s.getDuree());
            pst.setString(5, s.getOrgane());
            pst.executeUpdate(req) ; 
            System.out.println("Symptome ajouter !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @Override
    public void modifier(Symptome s){
        try{
            String req = "UPDATE Symptome SET titre=?, description=?,frequence=?,duree=?,organe=? WHERE idSymptome = ?;" ; 
            PreparedStatement pst = cnx.prepareStatement(req) ; 
            pst.setString(1, s.getTitre());
            pst.setString(2, s.getDescription());
            pst.setInt(3,s.getFrequence()) ; 
            pst.setInt(4, s.getDuree());
            pst.setString(5, s.getOrgane()); 
            pst.setString(6, s.getIdSymptome());
             pst.executeUpdate();
             System.out.println("Symptome modifier !");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    @Override
    public void supprimer(Symptome s){
    try{
        String req = "DELETE FROM Symptome WHERE id_Symptome=? ; "; 
        PreparedStatement pst = cnx.prepareStatement(req) ; 
        pst.setString(1,s.getIdSymptome()) ; 
        pst.executeUpdate() ; 
        System.out.println("Maladie supprimer !");
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
}   
    
    @Override 
    public List<Symptome> afficher(){
        List<Symptome> list =new ArrayList() ; 
        try{
            String req = "SELECT * FORM Symptome ; " ; 
            PreparedStatement pst = cnx.prepareStatement(req) ; 
            ResultSet rs= pst.executeQuery() ; 
            while (rs.next()){
               list.add(new Symptome(rs.getString("idSymptome"),rs.getString("titre"),rs.getString("description"),rs.getInt("frequence"),rs.getInt("duree"),rs.getString("organe"))) ; 
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list ;
    }
}
