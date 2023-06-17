package com.esprit.services;

import com.esprit.entities.Traitement;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceTraitement implements IService<Traitement> {

    private Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Traitement o) {
        try {
            String req = "INSERT INTO Traitement ("
                    + "nbr_prises, "
                    + "ID_ordonnance, "
                    + "ID_medicament, "
                    + "Description, "
                    + "Date_Fin,"
                    + " Date_Debut, "
                    + "ID_traitement) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(req);

            pst.setInt(1, o.getNbr_prises());
            
            pst.setInt(2, o.getID_ordonnance());
            pst.setString(3, o.getID_medicament());
           
            pst.setString(4, o.getDescription());
            pst.setDate(5, (Date) o.getDate_Fin());
            pst.setDate(6, (Date) o.getDate_Debut());
            pst.setInt(7, o.getID_traitement());

            pst.executeUpdate();
            System.out.println("Traitement ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Traitement o) {
        try {
            String req = "UPDATE Traitement SET nbr_prises = ?, ID_ordonnance = ?, ID_medicament = ?, description = ?, Date_Fin = ?, Date_Debut = ? WHERE ID_Traitement = ?";
            PreparedStatement pst = cnx.prepareStatement(req);

            pst.setInt(1, o.getNbr_prises());
            pst.setInt(2, o.getID_ordonnance());
            pst.setString(3, o.getID_medicament());
            pst.setString(4, o.getDescription());
            pst.setDate(5, (Date) o.getDate_Fin());
            pst.setDate(6, (Date) o.getDate_Debut());
            pst.setInt(7, o.getID_traitement());

            pst.executeUpdate();
            System.out.println("Traitement modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(Traitement p) {
        try {
            String req = "DELETE from Traitement WHERE ID_traitement=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getID_traitement());
            pst.executeUpdate();
            System.out.println("Traitement supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Traitement> afficher() {
        List<Traitement> list = new ArrayList<>();

        String req = "SELECT * FROM traitement";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                        int ID_traitement =rs.getInt("ID_traitement");
                        Date Date_Debut = rs.getDate("Date_Debut");
                        Date Date_Fin = rs.getDate("Date_Fin");
                        String description = rs.getString("description");
                        String ID_medicament = rs.getString("ID_medicament");
                        int ID_ordonnance = rs.getInt("ID_ordonnance");
                        int nbr_prises = rs.getInt("nbr_prises");
            list.add(new Traitement(Date_Debut, Date_Fin, description, ID_medicament, ID_ordonnance, nbr_prises));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}
