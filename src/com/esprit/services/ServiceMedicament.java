package com.esprit.gui;

import com.esprit.entities.medicament;
import com.esprit.services.IService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.esprit.utils.DataSource;


public class ServiceMedicament implements IService<medicament>{
     private Connection cnx = DataSource.getInstance().getCnx();
   
    public void ajouter(medicament m) {
        try {
            String req = "INSERT INTO medicament(nom_medicament,prix,description,categorie) VALUES (?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, m.getNom());
            pst.setFloat(2, m.getPrix());
            pst.setString(3, m.getDescription());
            pst.setString(4, m.getCategorie());
            pst.executeUpdate();
            System.out.println("Medicament ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public void modifier(medicament m) {
        try {
            String req = "UPDATE medicament SET nom_medicament=?, prix=?,"
                    + "description=?,categorie=? WHERE idMedicament=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1,m.getNom());
            pst.setFloat(2,m.getPrix());
            pst.setString(3,m.getDescription());
            pst.setString(4,m.getCategorie());
            pst.setInt(5,m.getIdMedicament());
            pst.executeUpdate();
            System.out.println("Le medicament "+m.getNom()+" est modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    public void supprimer(medicament m) {
        try {
            String req = "DELETE from medicament WHERE idMedicament=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,m.getIdMedicament());
            pst.executeUpdate();
            System.out.println("Medicament supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
     
       
        public List getAllMedicaments() {
        List list = new ArrayList<>();
        String req = "SELECT nom_medicament from medicament";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(rs.getString("nom_medicament"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
       
        return list;
    }

    @Override
    public List<medicament> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    
}
   