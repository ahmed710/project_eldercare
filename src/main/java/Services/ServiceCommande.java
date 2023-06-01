/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;
import Enities.Commande;
import utlis.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author houcem
 */


public class ServiceCommande implements IServices<Commande>{
    
    private Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouter(Commande commande) {
        try {
            String req = "INSERT INTO commande(adresse, Date_commande, ID_commande, ID_ordonnance, ID_pharmacie, prix_totale) VALUES (?,?,?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, commande.getAdresse());
            pst.setDate(2, (Date) commande.getDate_commande());
            pst.setInt(3, commande.getID_commande());
            pst.setInt(4, commande.getID_ordonnance());
            pst.setInt(5, commande.getID_pharmacie());
            pst.setDouble(6, commande.getPrix_totale());
            pst.executeUpdate();
            System.out.println("Commande ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifier(Commande commande) {
        try {
            String req = "UPDATE commande SET adresse=?, Date_commande=?, ID_ordonnance=?, ID_pharmacie=?, prix_totale=? WHERE ID_commande=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, commande.getAdresse());
            pst.setDate(2, (Date) commande.getDate_commande());
            pst.setInt(3, commande.getID_ordonnance());
            pst.setInt(4, commande.getID_pharmacie());
            pst.setDouble(5, commande.getPrix_totale());
            pst.setInt(6, commande.getID_commande());
            pst.executeUpdate();
            System.out.println("Commande modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimer(Commande commande) {
        try {
            String req = "DELETE FROM commande WHERE ID_commande=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, commande.getID_commande());
            pst.executeUpdate();
            System.out.println("Commande supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
public List<Commande> afficher() {
    List<Commande> list = new ArrayList<>();

    String req = "SELECT * FROM commande";
    try {
        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            list.add(new Commande(rs.getString("adresse"), rs.getDate("Date_commande"),
                    rs.getInt("ID_commande"), rs.getInt("ID_ordonnance"),
                    rs.getInt("ID_pharmacie"), rs.getDouble("prix_totale")));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}

}

