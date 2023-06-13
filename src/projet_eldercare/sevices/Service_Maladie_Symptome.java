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
import javafx.event.ActionEvent;
import projet_eldercare.entites.Maladie;
import projet_eldercare.entites.Maladie_Symptome;
import projet_eldercare.entites.Symptome;
import projet_eldercare.utils.DataSource;

/**
 *
 * author 21693
 */
public class Service_Maladie_Symptome {
    private Connection cnx = DataSource.getInstance().getCnx();

    
    public void ajouter(Maladie_Symptome m) {
        try {
            String req = "INSERT INTO Maladie_Symptome(ID_Symptome, ID_Maladie, taux_de_correspondance) VALUES (?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, m.getID_Symptome());
            pst.setInt(2, m.getID_Maladie());
            pst.setFloat(3, m.getTaux_de_correspondance());
            pst.executeUpdate();
            System.out.println("Maladie_Symptome ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Maladie_Symptome> afficher() {
        List<Maladie_Symptome> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM Maladie_Symptome";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int ID_Symptome = rs.getInt("ID_Symptome");
                int ID_Maladie = rs.getInt("ID_Maladie");
                float taux_de_correspondance = rs.getFloat("taux_de_correspondance");
                Maladie_Symptome maladieSymptome = new Maladie_Symptome(ID_Symptome, ID_Maladie, taux_de_correspondance);
                list.add(maladieSymptome);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
     
    public void modifier(Maladie_Symptome s) {
        try {
              
            String req = "UPDATE Maladie_Symptome SET ID_Symptome=? , ID_Maladie=?, taux_de_correspondance=? WHERE ID_Symptome=? AND ID_Maladie=?";
            PreparedStatement pst = cnx.prepareStatement(req); 
            
            pst.setInt(1, s.getID_Symptome());
            pst.setInt(2,s.getID_Maladie());
            pst.setFloat(3, s.getTaux_de_correspondance());
            pst.setInt(4, s.getID_Symptome());
            pst.setInt(5,s.getID_Maladie());
            pst.executeUpdate();
            System.out.println("Maladie Symptome  modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void supprimer(Maladie_Symptome s) {
        try {
            String req = "DELETE FROM Maladie_Symptome WHERE ID_Symptome=? AND ID_Maladie=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, s.getID_Symptome());
            pst.setInt(2, s.getID_Maladie());
            pst.executeUpdate();
            System.out.println("Maladie symptome supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 
    public List<Symptome> getRelatedSymptomes(int diseaseId) {
        List<Symptome> symptomes = new ArrayList<>();
        try{
        String query = "SELECT s.* FROM symptome s JOIN maladie_symptome ms ON s.ID_Symptome = ms.ID_Symptome WHERE ms.ID_Maladie = ?";

             PreparedStatement statement = cnx.prepareStatement(query) ;
            statement.setInt(1, diseaseId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int symptomeId = resultSet.getInt("ID_Symptome");
                String symptomeName = resultSet.getString("titre");

                Symptome symptome = new Symptome(symptomeId, symptomeName);
                symptomes.add(symptome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return symptomes;
    }
    public List<Maladie> getRelatedMaladies(int id) {
        List<Maladie> maladies = new ArrayList<>();
        try{
        String query = "SELECT m.* FROM maladie m JOIN maladie_symptome ms ON m.ID_Maladie = ms.ID_Maladie WHERE ms.ID_Symptome = ?";

             PreparedStatement statement = cnx.prepareStatement(query) ;
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int maladieId = resultSet.getInt("ID_Maladie");
                String maladieTitre = resultSet.getString("titre");

                Maladie maladie = new Maladie(maladieId, maladieTitre);
                maladies.add(maladie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maladies;
    }
    
    public String getTitreMaladieById(int id) {
        String titre = null;
        try {
            String req = "SELECT titre FROM Maladie WHERE ID_Maladie = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                titre = rs.getString("titre");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return titre;
    }
        public String getTitreSymptomeById(int id) {
        String titre = null;
        try {
            String req = "SELECT titre FROM Symptome WHERE ID_Symptome = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                titre = rs.getString("titre");
    
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return titre;
    }
}
