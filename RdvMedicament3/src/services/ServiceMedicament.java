/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import entities.Medicament;

import utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static javax.swing.UIManager.getInt;


/**
 *
 * @author DONIG
 */
public class ServiceMedicament implements IService<Medicament>{
   //  private Connection cnx = DataSource.getInstance().getCnx();
    private static ServiceMedicament instance;
      private Statement st;
    private ResultSet rs;
        DataSource cs=DataSource.getInstance();
        
          private ObservableList<Medicament> persons = FXCollections.observableArrayList();
    private ObservableList<String> medicationNames = FXCollections.observableArrayList();
    


    // Autres variables et méthodes de la classe
      private ServiceMedicament() {
        
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMedicament.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ServiceMedicament getInstance() {
        if (instance == null) {
            instance = new ServiceMedicament();
        }
        return instance;
    }
//    public void ajouter(Medicament m) {
//        try {
//            String req = "INSERT INTO medicament(nom_medicament,prix,description,categorie) VALUES (?,?,?,?);";
//            PreparedStatement pst = cnx.prepareStatement(req);
//            pst.setString(1, m.getNom());
//            pst.setFloat(2, m.getPrix());
//            pst.setString(3, m.getDescription());
//            pst.setString(4, m.getCategorie());
//            pst.executeUpdate();
//            System.out.println("Medicament ajouté !");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
     
    public void ajouter(Medicament o) {
     //   String req = "INSERT IGNORE INTO Reclamation (nom, prenom, email, numTel, message, date, typeR) VALUES ('" + o.getNom() + "', '" + o.getPrenom() + "', '" + o.getEmail() + "', '" + o.getNumTel() + "', '" + o.getMessage() + "', '" + o.getDate()+ "', '" + o.getTypeR() + "')";
        String req="insert into medicament (nom_medicament,prix,description,categorie) values ('"+o.getNom()+"','"+o.getPrix()+"','"+o.getDescription()+"','"+o.getCategorie()+"')";
       // String req = "INSERT INTO medicament(nom_medicament,prix,description,categorie) VALUES (?,?,?,?);";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMedicament.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
    
//      public void modifier(Medicament m) {
//        try {
//            String req = "UPDATE medicament SET nom_medicament=?, prix=?,"
//                    + "description=?,categorie=? WHERE idMedicament=?";
//            PreparedStatement pst = cnx.prepareStatement(req);
//            pst.setString(1,m.getNom());
//            pst.setFloat(2,m.getPrix());
//            pst.setString(3,m.getDescription());
//            pst.setString(4,m.getCategorie());
//            pst.setInt(5,m.getIdMedicament());
//            pst.executeUpdate();
//            System.out.println("Le medicament "+m.getNom()+" est modifié !");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
    
     public boolean modifier(Medicament p) {
       // String qry = "UPDATE Reclamation SET nom = '"+p.getNom()+"', prenom = '"+p.getPrenom()+"', email = '"+p.getEmail()+"', numTel = '"+p.getNumTel()+"', message = '"+p.getMessage()+"' WHERE id = "+p.getId();
         String qry = "UPDATE medicament SET  nom_medicament = '"+p.getNom()+"', prix = '"+p.getPrix()+"', description = '"+p.getDescription()+"', categorie = '"+p.getCategorie()+"' WHERE idMedicament = "+p.getIdMedicament();
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMedicament.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    

      
    public Medicament displayById(int id) {
    String req = "SELECT * FROM medicament WHERE idMedicament="+id;
           Medicament p=new Medicament();

    try {
    rs=st.executeQuery(req);
                              //  System.out.println("Le médicament avec l'ID " + p.getIdMedicament() + " n'existe pas.");

    rs.next();
           
            p.setIdMedicament(rs.getInt(1));
                     System.out.println("Le médicament avec l'ID " + p.getIdMedicament() + " n'existe pas.");

            p.setNom(rs.getString(2));
                                 System.out.println("Le nom " + p.getNom() + " n'existe pas.");

            p.setPrix(rs.getFloat(3));
            p.setDescription(rs.getString(4));
            p.setCategorie(rs.getString(5));
        }
    
    catch (SQLException ex) {
        Logger.getLogger(ServiceMedicament.class.getName()).log(Level.SEVERE, null, ex);
    }
    return p;
}




//   
//
//public void supprimer(Medicament o) {
//    String req = "DELETE FROM medicament WHERE idMedicament=?";
//    try {
//        PreparedStatement pst = cnx.prepareStatement(req);
//        pst.setInt(1, o.getIdMedicament());
//        int rowsAffected = pst.executeUpdate();
//        if (rowsAffected > 0) {
//            System.out.println("Le médicament avec l'ID " + o.getIdMedicament() + " a été supprimé !");
//        } else {
//            System.out.println("Le médicament avec l'ID " + o.getIdMedicament() + " n'existe pas.");
//        }
//    } catch (SQLException ex) {
//        System.out.println(ex.getMessage());
//    }
//}

public void supprimer(Medicament o) {
        String req="delete from medicament where idMedicament="+o.getIdMedicament();
        Medicament p=displayById(o.getIdMedicament());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMedicament.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

 




 public ObservableList<Medicament> displayAll() {
        String req="select * from medicament";
        ObservableList<Medicament> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Medicament p=new Medicament();
                     
            p.setIdMedicament(rs.getInt(1));
                  //   System.out.println("Le médicament avec l'ID " + p.getIdMedicament() + " n'existe pas.");

            p.setNom(rs.getString(2));
                               //  System.out.println("Le nom " + p.getNom() + " n'existe pas.");

            p.setPrix(rs.getFloat(3));
            p.setDescription(rs.getString(4));
            p.setCategorie(rs.getString(5));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMedicament.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
     @Override
    public List<Medicament> afficher() {
        List<Medicament> list = new ArrayList<>();
        String req = "SELECT idMedicament,nom_medicament,prix,description,categorie FROM medicament";
        try {
//            PreparedStatement pst = cnx.prepareStatement(req);
//            ResultSet rs = pst.executeQuery
 rs=st.executeQuery(req);
                 Medicament p=new Medicament();

            while(rs.next()) {
               p.setIdMedicament(rs.getInt(1));
                  //   System.out.println("Le médicament avec l'ID " + p.getIdMedicament() + " n'existe pas.");

            p.setNom(rs.getString(2));
                               //  System.out.println("Le nom " + p.getNom() + " n'existe pas.");

            p.setPrix(rs.getFloat(3));
            p.setDescription(rs.getString(4));
            p.setCategorie(rs.getString(5));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
    
   
    public List<Medicament> affichernom() {
        List<Medicament> list = new ArrayList<>();
        String req = "SELECT nom_medicament FROM medicament";
        try {
//            PreparedStatement pst = cnx.prepareStatement(req);
//            ResultSet rs = pst.executeQuery();
 rs=st.executeQuery(req);

            while(rs.next()) {
                list.add(new Medicament(rs.getString("nom_medicament"), rs.getFloat("prix"),rs.getString("description"),
                        rs.getString("categorie")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
    
  public List<Medicament> rechercher(String recherche) {
    List<Medicament> produits = afficher().stream()
            .filter(x -> 
                x.getNom().contains(recherche) 
                )
            .collect(Collectors.toList());
    return produits;       
}
  
  //*************
  //************* 
      public void loadMedicaments() {
        ServiceMedicament pdao = ServiceMedicament.getInstance();
        List<Medicament> medicaments = pdao.displayAll();
                persons.clear();

        persons.addAll(medicaments);
        medicationNames.addAll(medicaments.stream()
                .map(Medicament::getNom)
                .collect(Collectors.toList()));
        System.out.println(persons);
    }

    public ObservableList<Medicament> getPersons() {
        return persons;
    }

    public ObservableList<String> getMedicationNames() {
        return medicationNames;
    }

    
}
