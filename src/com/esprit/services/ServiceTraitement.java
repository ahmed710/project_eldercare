/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.entities.Traitement;
import com.esprit.entities.Traitement;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zouhour
 */
public class ServiceTraitement implements IService <Traitement> {

     private Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouter(Traitement o) {
        try {
            String req = "INSERT INTO Traitement( nbr_prises =?,ID_medicament,"
                    + "Description,Date_Fin,Date_Debut,ID_traitement) VALUES (?,?,?,?,?,?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);

            pst.setInt(1,   o.getNbr_prises());
            pst.setInt(4,  o.getID_ordonnance()); /*a voir*/
            pst.setString(5, o.getID_medicament());
            pst.setString(6, o.getDescription());
            pst.setDate(7, (Date) o.getDate_Fin()); /*a voir*/
            pst.setDate(8, (Date) o.getDate_Debut());
            pst.setInt(9, o.getID_traitement());
            
            pst.executeUpdate();
            System.out.println("Traitement ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifier(Traitement o) {
        try {
            String req = "UPDATE Traitement SET  nbr_prises =? ,ID_ordonnance=?,"
                    + "ID_medicament=?,description=? , Date_Fin=? , Date_Debut=? "
                    + " WHERE ID_Traitement=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setInt(1,  o.getNbr_prises());
            pst.setInt(4,  o.getID_ordonnance()); /*a voir*/
            pst.setString(5, o.getID_medicament());
            pst.setString(6, o.getDescription());
            pst.setDate(7, (Date) o.getDate_Fin()); /*a voir*/
            pst.setDate(8, (Date) o.getDate_Debut());
            pst.setInt(9, o.getID_traitement());
            
            pst.executeUpdate();
            System.out.println("Traitement modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimer(Traitement p) {
        try {
            String req = "DELETE from Traitement WHERE ID_Traitement=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getID_traitement());
            pst.executeUpdate();
            System.out.println("Traitement supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Traitement> afficher() {
        List<Traitement> list = new ArrayList<>();
        
        String req = "SELECT * FROM Traitement";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Traitement(rs.getInt("ID_traitement"),rs.getDate("Date_Debut"),
                        rs.getDate("Date_Fin")
                     ,rs.getString("description"),rs.getString("ID_medicament"),
                        rs.getInt("ID_ordonnance"),
                     rs.getInt( "nbr_prises")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
}
