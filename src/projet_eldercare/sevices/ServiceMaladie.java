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
import projet_eldercare.entites.Maladie;
import projet_eldercare.utils.DataSource;

/**
 *
 * @author 21693
 */
public class ServiceMaladie implements  servicesInterface<Maladie> {

    private  Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Maladie m) {
        try {
            String req = "INSERT INTO Maladie(id_maladie, titre, description, categorie, situation) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, m.getId_maladie());
            pst.setString(2, m.getTitre());
            pst.setString(3, m.getDescription());
            pst.setString(4,m.getCategorie()) ; 
            pst.setString(5, m.getSituation());
            pst.executeUpdate() ; 
            System.out.println("Maladie ajouter !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @Override
    
    public void modifier(Maladie m){
        try{
            String req = "UPDATE Maladie SET titre=?, description=?,categorie=?,situation=? WHERE Id_Maladie = ?;" ; 
            PreparedStatement pst = cnx.prepareStatement(req) ; 
            pst.setString(1, m.getTitre());
            pst.setString(2, m.getDescription());
            pst.setString(3,m.getCategorie()) ; 
            pst.setString(4, m.getSituation());
            pst.setString(5,m.getId_maladie()) ; 
             pst.executeUpdate();
             System.out.println("Maladie modifier !");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    @Override
    public void supprimer(Maladie m){
    try{
        String req = "DELETE FROM Maladie WHERE Id_Maladie=? ; "; 
        PreparedStatement pst = cnx.prepareStatement(req) ; 
        pst.setString(1,m.getId_maladie()) ; 
        pst.executeUpdate() ; 
        System.out.println("Maladie supprimer !");
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
}   
    
    @Override 
    public List<Maladie> afficher(){
        List<Maladie> list =new ArrayList() ; 
        try{
            String req = "SELECT * FROM Maladie" ; 
            PreparedStatement pst = cnx.prepareStatement(req) ; 
            ResultSet rs= pst.executeQuery() ; 
            while (rs.next()){
               list.add(new Maladie(rs.getString("Id_maladie"),rs.getString("titre"),rs.getString("description"),rs.getString("categorie"),rs.getString("situation"))) ; 
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list ;
    }


}
