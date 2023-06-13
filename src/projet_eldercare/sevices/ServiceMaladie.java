package projet_eldercare.sevices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projet_eldercare.entites.Maladie;
import projet_eldercare.utils.DataSource;

/**
 * Service class for Maladie entity.
 */
public class ServiceMaladie implements servicesInterface<Maladie> {

    private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Maladie m) {
        try {
            String req = "INSERT INTO Maladie(ID_Maladie, titre, description, classification_etiologique, classification_fonctionelle) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, m.getID_Maladie());
            pst.setString(2, m.getTitre());
            pst.setString(3, m.getDescription());
            pst.setString(4, m.getClassification_etiologique());
            pst.setString(5, m.getClassification_fonctionelle());
            pst.executeUpdate();
            System.out.println("Maladie ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Maladie m) {
        try {
            String req = "UPDATE Maladie SET titre=?, description=?, classification_etiologique=?, classification_fonctionelle=? WHERE ID_Maladie=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, m.getTitre());
            pst.setString(2, m.getDescription());
            pst.setString(3, m.getClassification_etiologique());
            pst.setString(4, m.getClassification_fonctionelle());
            pst.setInt(5, m.getID_Maladie());
            pst.executeUpdate();
            System.out.println("Maladie modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
        public int getMaladieIdByTitre(String titre) {
        String query = "SELECT ID_Maladie FROM maladie WHERE titre = ?";

        try (
             PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, titre);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("ID_Maladie");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if the disease is not found
    }

    @Override
    public void supprimer(Maladie m) {
        try {
            String req = "DELETE FROM Maladie WHERE ID_Maladie=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, m.getID_Maladie());
            pst.executeUpdate();
            System.out.println("Maladie supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public List<Maladie> afficher() {
        List<Maladie> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM Maladie";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int ID_Maladie = rs.getInt("ID_Maladie");
                String titre = rs.getString("titre");
                String description = rs.getString("description");
                String classification_etiologique = rs.getString("classification_etiologique");
                String classification_fonctionelle =rs.getString("classification_fonctionelle");
                Maladie maladie = new Maladie(ID_Maladie, titre, description, classification_etiologique, classification_fonctionelle);
                list.add(maladie);
            }
        }catch(SQLException ex ){
            ex.getMessage() ; 
        }
        return list ; 
    }

 
}
