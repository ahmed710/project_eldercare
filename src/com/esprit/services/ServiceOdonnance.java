/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.entities.Ordonnance;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * author Zouhour
 */
public class ServiceOdonnance implements IService<Ordonnance> {

    @SuppressWarnings("FieldMayBeFinal")
    private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Ordonnance o) {
        try {
            String req = "INSERT INTO Ordonnance(ID_patient,ID_medecin,date) VALUES (?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setDate(3, (Date) o.getDate());
            pst.setInt(2, o.getID_medecin());
            pst.setInt(1, o.getID_patient());

            // Convert the List<String> to a comma-separated string
            String medicamentIds = String.join(",", o.getID_medicament());
            pst.setString(4, medicamentIds);

            pst.executeUpdate();
            System.out.println("Ordonnance ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Ordonnance o) {
        try {
            String req = "UPDATE Ordonnance SET ID_patient=?, ID_medecin=?, date=?, ID_medicament=? WHERE ID_ordonnance=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setDate(3, (Date) o.getDate());
            pst.setInt(2, o.getID_medecin());
            pst.setInt(1, o.getID_patient());

            // Convert the List<String> to a comma-separated string
            String medicamentIds = String.join(",", o.getID_medicament());
            pst.setString(4, medicamentIds);

            pst.setInt(5, o.getID_ordonnance());
            pst.executeUpdate();
            System.out.println("Ordonnance modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Ordonnance p) {
        try {
            String req = "DELETE from Ordonnance WHERE ID_ordonnance=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getID_ordonnance());
            pst.executeUpdate();
            System.out.println("Ordonnance supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Ordonnance> afficher() {
        List<Ordonnance> list = new ArrayList<>();

        String req = "SELECT * FROM Ordonnance";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // Split the comma-separated string into a List<String>
                List<String> medicamentIds = Arrays.asList(rs.getString("ID_medicament").split(","));
                list.add(new Ordonnance( rs.getString("ID_medicament"),
                        rs.getInt("ID_patient"),rs.getInt("ID_medecin"),rs.getDate("Date")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}
