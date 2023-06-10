/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import entities.Commande;
import entities.Medicament;
import entities.Pharmacie;
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
 * @author Wimbee018
 */
public class ServiceCommande implements IService<Commande>{
    private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Commande c) {
        try {
            String req = "INSERT INTO commande(ID_pharmacie,date_commande, adresse, etat) VALUES (?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, c.getID_pharmacie());
            pst.setDate(2, c.getDate_commande());
            pst.setString(3, c.getAdresse());
            pst.setString(4, c.getEtat());
            pst.executeUpdate();
            System.out.println("Commande ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

    @Override
    public void modifier(Commande c) {
        try {
            String req = "UPDATE commande SET date_commande=?, adresse=? ,etat=? ,ID_pharmacie=? WHERE ID_commande=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(5, c.getID_commande());
            pst.setDate(1, c.getDate_commande());
            pst.setString(2, c.getAdresse());
            pst.setString(3, c.getEtat());            
            pst.setInt(4, c.getID_pharmacie());
            pst.executeUpdate();
            System.out.println("Commande modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Commande c) {
        try {
            String req = "DELETE from commande WHERE ID_commande=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, c.getID_commande());
            pst.executeUpdate();
            System.out.println("Commande supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public List<Commande> afficher() {
        List<Commande> list = new ArrayList<>();
        
        String req = "SELECT * FROM commande";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Commande(rs.getInt("ID_commande"), rs.getInt("ID_pharmacie") ,rs.getString("adresse"),rs.getDate("date_commande"),rs.getString("etat")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
    
        public List<Pharmacie> getNom() {
    List<Pharmacie> list = new ArrayList<>();
    String req = "SELECT * FROM pharmacie JOIN commande ON pharmacie.ID_pharmacie = commande.ID_pharmacie GROUP BY pharmacie.nom_pharmacie";

    try {
        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            list.add(new Pharmacie(rs.getString("nom_pharmacie")));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    return list;
}

         public List<Commande> getCommWithPhar() {
    List<Commande> list = new ArrayList<>();
    String req = "SELECT * FROM  commande JOIN pharmacie ON pharmacie.ID_pharmacie = commande.ID_pharmacie";

    try {
        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
        Commande c = new Commande();
        c.setAdresse(rs.getString("adresse"));
        c.setDate_commande(rs.getDate("Date_commande"));
        c.setEtat(rs.getString("etat"));
        //System.out.println(rs.getString("etat"));
        c.setID_commande(rs.getInt("ID_commande"));
        c.setID_pharmacie(rs.getInt("ID_pharmacie"));
        c.setNom_pharmacie(rs.getString("nom_pharmacie"));
            //System.out.println(c.getNom_pharmacie());
        //System.out.println(rs.getString("nom_pharmacie"));
        list.add(c);
        //list.add(new Pharmacie(rs.getString("nom_pharmacie")));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    return list;
}

    public List<Medicament> getMedFromComm(Commande c) {
        List<Medicament> list = new ArrayList<>();
        
        String req = "SELECT * FROM medicament m JOIN ordonnance o ON m.ID_medicament = o.ID_medicament JOIN commande c ON c.ID_commande = o.Id_Commande WHERE o.Id_Commande="+c.getID_commande();
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Medicament m = new Medicament();
                m.setCategorie(rs.getString("categorie"));
                m.setNom_medicament(rs.getString("nom_medicament"));
                m.setPrix(rs.getInt("prix"));
                list.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }

}
