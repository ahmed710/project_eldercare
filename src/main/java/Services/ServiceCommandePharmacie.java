/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;
import Enities.CommandePharmacie;
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

public class ServiceCommandePharmacie implements IServices<CommandePharmacie> {
    
    private Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouter(CommandePharmacie commandePharmacie) {
        try {
            String req = "INSERT INTO commande_pharmacie(ID_commande, ID_pharmacie, quantite, disponibilite) VALUES (?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, commandePharmacie.getID_commande());
            pst.setInt(2, commandePharmacie.getID_pharmacie());
            pst.setInt(3, commandePharmacie.getQuantite());
            pst.setBoolean(4, commandePharmacie.isDisponibilite());
            pst.executeUpdate();
            System.out.println("Commande Pharmacie ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifier(CommandePharmacie commandePharmacie) {
        try {
            String req = "UPDATE commande_pharmacie SET ID_pharmacie=?, quantite=?, disponibilite=? WHERE ID_commande=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, commandePharmacie.getID_pharmacie());
            pst.setInt(2, commandePharmacie.getQuantite());
            pst.setBoolean(3, commandePharmacie.isDisponibilite());
            pst.setInt(4, commandePharmacie.getID_commande());
            pst.executeUpdate();
            System.out.println("Commande Pharmacie modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimer(CommandePharmacie commandePharmacie) {
        try {
            String req = "DELETE FROM commande_pharmacie WHERE ID_commande=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, commandePharmacie.getID_commande());
            pst.executeUpdate();
            System.out.println("Commande Pharmacie supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
public List<CommandePharmacie> afficher() {
    List<CommandePharmacie> list = new ArrayList<>();

    String req = "SELECT * FROM commande_pharmacie";
    try {
        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int ID_commande = rs.getInt("ID_commande");
            int ID_pharmacie = rs.getInt("ID_pharmacie");
            int quantite = rs.getInt("quantite");
            boolean disponibilite = rs.getBoolean("disponibilite");

            CommandePharmacie commandePharmacie = new CommandePharmacie(ID_commande, ID_pharmacie, quantite, disponibilite);
            list.add(commandePharmacie);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}

}

