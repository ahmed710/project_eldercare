/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.entities.Ordonnance;
import com.esprit.utils.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * author Zouhour
 */
public class ServiceOdonnance implements IService<Ordonnance> {

    @SuppressWarnings("FieldMayBeFinal")
    private Connection cnx = DataSource.getInstance().getCnx();
    
    public List getAllpatient() {
        List list = new ArrayList<>();
        String req = "SELECT nom , prenom from user where role ='patient'";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                String nom = rs.getString("nom")+ " "+rs.getString("prenom");
                  list.add(nom);
                 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return list;
    }
             public List getAlldocs() {
        List list = new ArrayList<>();
        String req = "SELECT nom , prenom from user where role ='médecin'";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                    String nom = rs.getString("nom")+ " "+rs.getString("prenom");
                   list.add(nom);   
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
             
      public List getAllOrdonnances() {
        List list = new ArrayList<>();
        String req = "SELECT ID_ordonnace from Ordonnance ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                    int ID_ordonnace = rs.getInt("ID_ordonnace");
                   list.add(ID_ordonnace);   
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
  
    @Override
    public void ajouter(Ordonnance o) {
        try {
<<<<<<< Updated upstream
            String req = "INSERT INTO Ordonnance(ID_patient,ID_medecin,date,ID_medicament) VALUES (?,?,?,?);";
=======
            String req = "INSERT INTO Ordonnance(ID_patient,ID_medecin,date,ID_medicament,nom_prenomMedecin"
                    + ",nom_prenomPatient) VALUES (?,?,?,?,?,?)";
            String req2 ="select ID_user from user where nom= "+o.getNom_prenomPatient();
            String req3 ="select ID_user from user where nom= "+o.getNom_prenomMedecin();
            
>>>>>>> Stashed changes
            PreparedStatement pst = cnx.prepareStatement(req);
            PreparedStatement pst2 = cnx.prepareStatement(req2);
            PreparedStatement pst3 = cnx.prepareStatement(req3);
            
            pst.setString(6, o.getNom_prenomPatient());
            pst.setString(5, o.getNom_prenomMedecin());
            pst.setDate(3, (Date) o.getDate());
            pst.setString(2, o.getID_medecin());
            pst.setString(1, o.getID_patient());
            
//            pst.setString(1, req2);

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
            String req = "UPDATE Ordonnance SET ID_patient=?, ID_medecin=?, date=?, ID_medicament=? WHERE ID_ordonnance=?"
                    + "nom_prenomPatient=?,nom_prenomMedecin=?";
            PreparedStatement pst = cnx.prepareStatement(req);
             pst.setString(5, o.getNom_prenomPatient());
            pst.setString(4, o.getNom_prenomMedecin());
            pst.setDate(3, (Date) o.getDate());
            pst.setString(2, o.getID_medecin());
            pst.setString(1, o.getID_patient());

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
            String req = "DELETE from ordonnance WHERE ID_ordonnace=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getID_ordonnance());
            pst.executeUpdate();
            System.out.println("Ordonnance supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

<<<<<<< Updated upstream
//    @Override
//    public List<Ordonnance> afficher() {
//        List<Ordonnance> list = new ArrayList<>();
//
//        String req = "SELECT * FROM Ordonnance";
//        try {
//            PreparedStatement pst = cnx.prepareStatement(req);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                
//                List<String> medicamentIds = Arrays.asList(rs.getString("ID_medicament").split(","));
//                
//                //cast Array to list<string> + esm lcolonne fl paramétres
//                list.add(new Ordonnance( (List<String>) rs.getArray("ID_medicament"),
//                        rs.getInt("ID_patient"),rs.getInt("ID_medecin"),
//                        rs.getDate("Date")));
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return list;
//    }
//    
//    

@Override
public List<Ordonnance> afficher() {
    List<Ordonnance> list = new ArrayList<>();

    String req = "SELECT * FROM Ordonnance";
    try {
        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            
            String medicamentIdsString = rs.getString("ID_medicament");
            List<String> medicamentIds = new ArrayList<>();
            if (medicamentIdsString != null) {
                String[] medicamentIdArray = medicamentIdsString.split(",");
                medicamentIds = Arrays.asList(medicamentIdArray);
            }
            int idordonnance = rs.getInt("ID_ordonnace");
            int patientId = rs.getInt("ID_patient");
            int medecinId = rs.getInt("ID_medecin");
            Date date = rs.getDate("Date");

            list.add(new Ordonnance(idordonnance,medicamentIds, patientId, medecinId, date));
=======
   @Override
public List<Ordonnance> afficher() {
    List<Ordonnance> list = new ArrayList<>();
    
        String req = "SELECT * FROM Ordonnance";
        //    String req = "SELECT o.ID_ordonnace, m.nom_medicament AS nom_medicament, "
//            + "CASE WHEN u.role = 'médecin' THEN u.nom ELSE NULL END AS nom_medecin,"
//            + " CASE WHEN u.role = 'patient' THEN u.nom ELSE NULL END AS nom_patient, o.date FROM ordonnance o "
//            + "INNER JOIN user u ON o.ID_medecin = u.ID_user "
//            + "INNER JOIN medicament m ON o.ID_medicament = m.idMedicament WHERE u.role IN ('medecin', 'patient');";
    try {
        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            Ordonnance o = new Ordonnance();
                        o.setNom_prenomMedecin(rs.getString("nom_prenomPatient"));
            o.setNom_prenomMedecin(rs.getString("nom_prenomMedecin"));
            o.setID_ordonnance(rs.getInt("ID_ordonnace"));
            o.setID_medicament(rs.getString("ID_medicament"));
            o.setID_medecin(rs.getString("ID_medecin"));
            o.setID_patient(rs.getString("ID_patient"));
            o.setDate(Date.valueOf(rs.getString("date")));
            
            list.add(o);
>>>>>>> Stashed changes
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
<<<<<<< Updated upstream

    return list;
}

    
    

    
    public List<String> afficher_Medicaments() {
        List<String> list = new ArrayList<>();
=======
>>>>>>> Stashed changes

    return list;
}

//    
//    public List<String> afficher_Medicaments() {
//        List<String> list = new ArrayList<>();
//
//        String req = "SELECT * FROM medicaments";
//        try {
//            PreparedStatement pst = cnx.prepareStatement(req);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                
//                List<String> medicamentIds = Arrays.asList(rs.getString("ID_medicament").split(","));
//                
//                //cast Array to list<string> + esm lcolonne fl paramétres
//                list.addAll((Collection<? extends String>) rs.getArray("ID_medicament"));
//                
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return list;
//    }
    
//         public List<Ordonnance> rechercherParTitre(String titre) throws SQLException {
//    Connection cnx = DataSource.getInstance().getCnx();
//    List<Ordonnance> OrdonnanceList = new ArrayList<>();
//    String req = "SELECT * FROM Ordonnance WHERE titre LIKE ?";
//    PreparedStatement pst = cnx.prepareStatement(req);
//    pst.setString(1, "%" + titre + "%");
//    ResultSet rs = pst.executeQuery();
//    while (rs.next()) {
//        Ordonnance Ordonnance = new Ordonnance();
////        Ordonnance.setId(rs.getInt("id"));
//        Ordonnance.setID_medecin(rs.getInt("titre"));
//        Ordonnance.setID_ordonnance(rs.getInt("description"));
////        Ordonnance.setID_medicament(rs.getString("email"));
//        Ordonnance.setNbrPrises(rs.getInt("numerodetel"));
//        Ordonnance.setID_patient(rs.getInt("numerodetel"));
//        OrdonnanceList.add(Ordonnance);
//    }
//    if (OrdonnanceList.size() == 0) {
//        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setHeaderText(null);
//        alert.setContentText("Il n'y a aucun Ordonnance correspondant à ce titre.");
//        alert.showAndWait();
//    }
//    return OrdonnanceList;
//}
         
}
