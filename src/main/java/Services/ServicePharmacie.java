/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;
import Enities.Pharmacie;
import utlis.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author houcem
 */
public class ServicePharmacie implements IServices<Pharmacie> {
    
    private Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouter(Pharmacie pharmacie) {
        try {
            String req = "INSERT INTO pharmacie(adresse_pha, ID_pharmacie, nom_pharmacie, telephone) VALUES (?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, pharmacie.getAdresse_pha());
            pst.setInt(2, pharmacie.getID_pharmacie());
            pst.setString(3, pharmacie.getNom_pharmacie());
            pst.setString(4, pharmacie.getTelephone());
            pst.executeUpdate();
            System.out.println("Pharmacie ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifier(Pharmacie pharmacie) {
        try {
            String req = "UPDATE pharmacie SET adresse_pha=?, nom_pharmacie=?, telephone=? WHERE ID_pharmacie=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, pharmacie.getAdresse_pha());
            pst.setString(2, pharmacie.getNom_pharmacie());
            pst.setString(3, pharmacie.getTelephone());
            pst.setInt(4, pharmacie.getID_pharmacie());
            pst.executeUpdate();
            System.out.println("Pharmacie modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimer(Pharmacie pharmacie) {
        try {
            String req = "DELETE FROM pharmacie WHERE ID_pharmacie=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, pharmacie.getID_pharmacie());
            pst.executeUpdate();
            System.out.println("Pharmacie supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Pharmacie> afficher() {
        List<Pharmacie> list = new ArrayList<>();
        
        String req = "SELECT * FROM pharmacie";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Pharmacie(rs.getString("adresse_pha"), rs.getInt("ID_pharmacie"), rs.getString("nom_pharmacie"), rs.getString("telephone")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
}
