package projet_eldercare.sevices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projet_eldercare.entites.Symptome;
import projet_eldercare.utils.DataSource;

/**
 *
 * author 21693
 */
public class ServiceSymptome implements servicesInterface<Symptome> {
    private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Symptome s) {
        try {
            String req = "INSERT INTO Symptome(ID_Symptome, titre, description, frequenceParJour, frequenceParSemaine, duree, organe) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, s.getID_Symptome());
            pst.setString(2, s.getTitre());
            pst.setString(3, s.getDescription());
            pst.setInt(4, s.getFrequenceParJour());
            pst.setInt(5, s.getFrequenceParSemaine());
            pst.setFloat(6, s.getDuree());
            pst.setString(7, s.getOrgane());
            pst.executeUpdate();
            System.out.println("Symptome ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Symptome s) {
        try {
            String req = "UPDATE Symptome SET titre=?, description=?, frequenceParJour=?, frequenceParSemaine=?, duree=?, organe=? WHERE ID_Symptome=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, s.getTitre());
            pst.setString(2, s.getDescription());
            pst.setInt(3, s.getFrequenceParJour());
            pst.setInt(4, s.getFrequenceParSemaine());
            pst.setFloat(5, s.getDuree());
            pst.setString(6, s.getOrgane());
            pst.setInt(7, s.getID_Symptome());
            pst.executeUpdate();
            System.out.println("Symptome modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Symptome s) {
        try {
            String req = "DELETE FROM Symptome WHERE ID_Symptome=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, s.getID_Symptome());
            pst.executeUpdate();
            System.out.println("Symptome supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Symptome> afficher() {
        List<Symptome> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM Symptome";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int ID_Symptome = rs.getInt("ID_Symptome");
                String titre = rs.getString("titre");
                String description = rs.getString("description");
                int frequenceParJour = rs.getInt("frequenceParJour");
                int frequenceParSemaine = rs.getInt("frequenceParSemaine");
                float duree = rs.getFloat("duree");
                String organe = rs.getString("organe");
                Symptome symptome = new Symptome(ID_Symptome, titre, description, frequenceParJour, frequenceParSemaine, duree, organe);
                list.add(symptome);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public int getSymptomeIdByTitre(String symptomeTitre) {
        String query = "SELECT ID_Symptome FROM Symptome WHERE titre = ?";

        try (
             PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, symptomeTitre);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("ID_Symptome");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; 
    }
}
