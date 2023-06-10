/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import utlis.DataSource;
import entities.Pharmacie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wimbee018
 */
public class ServicePharmacie implements IService<Pharmacie>{
        private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Pharmacie p) {
        try {
            String req = "INSERT INTO pharmacie(nom_pharmacie,adresse_pha,telephone) VALUES (?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getNom_pharmacie());
            pst.setString(2, p.getAdresse_pha());
            pst.setString(3, p.getTelephone());            
            pst.executeUpdate();
            System.out.println("Personne ajoutée !");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }       
        
    }

    @Override
    public void modifier(Pharmacie p) {
        try {
            String req = "UPDATE pharmacie SET nom_pharmacie=?, adresse_pha=?, telephone=? WHERE ID_pharmacie=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(4, p.getID_pharmacie());
            pst.setString(1, p.getNom_pharmacie());
            pst.setString(2, p.getAdresse_pha());
            pst.setString(3, p.getTelephone()); 
            pst.executeUpdate();
            System.out.println("Pharmacie modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void supprimer(Pharmacie p) {
        try {
            String req = "DELETE FROM `pharmacie` WHERE ID_pharmacie=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getID_pharmacie());
            pst.executeUpdate();
            System.out.println("Pharmacie supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public List<Pharmacie> afficher() {
        List<Pharmacie> list = new ArrayList<>();
        
        String req = "SELECT * FROM pharmacie";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Pharmacie(rs.getInt("ID_pharmacie"), rs.getString("nom_pharmacie"), rs.getString("adresse_pha"),rs.getString("telephone")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
    
    public List<String> getNomPhar() {
        List<String> list = new ArrayList<>();
        
        String req = "SELECT * FROM pharmacie";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(rs.getString("nom_pharmacie"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
    public int getIdPharByNom(String p) {
    int id = -1;
    try {
        String req = "SELECT ID_pharmacie FROM pharmacie WHERE nom_pharmacie=?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, p);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            id = rs.getInt("ID_pharmacie");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    //System.out.println("getIdPharByNom done");
    return id;
}

   
}
    

