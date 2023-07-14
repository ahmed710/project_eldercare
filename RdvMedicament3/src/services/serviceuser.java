/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

//import com.esprit.entities.medecin;
import entities.user;
import utils.DataSource;
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

/**
 *
 * @author DONIG
 */
public class serviceuser {
    
     private static serviceuser instance;
    private Statement st;
    private ResultSet rs;
    DataSource cs=DataSource.getInstance();
        private ObservableList<user> usr = FXCollections.observableArrayList();
            private ObservableList<String> usersmedecins = FXCollections.observableArrayList();
                        private ObservableList<String> userspatients = FXCollections.observableArrayList();


 private serviceuser() {
        
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(serviceuser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static serviceuser getInstance(){
        if(instance==null) 
            instance=new serviceuser();
        return instance;
    }
    
    
    public List<user> afficher() {
        List<user> list = new ArrayList<>();
        
        String req = "SELECT * FROM user";
        try {
            rs=st.executeQuery(req);
            while(rs.next()) {
//                list.add(new RendezVous(rs.getDate("date"), rs.getInt("id_patient"),
//                        rs.getInt("id_medecin"),rs.getString("etat")));
     user p=new user();
                p.setID_user(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setPrenom(rs.getString(3));
                p.setRole(rs.getString(4));
                p.setSexe(rs.getString(5));
                 p.setEmail(rs.getString(6));
                  p.setAdresse(rs.getString(7));
                   p.setDate_de_naissance(rs.getDate(8));
                    p.setPoids(rs.getFloat(9));
                     p.setTaille(rs.getInt(10));
                      p.setSpecialite(rs.getString(11));
                       p.setMotdepasse(rs.getString(12));
               
                list.add(p);
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
    
    public ObservableList<user> displayAll() {
        String req="select * from user";
        ObservableList<user> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                user p=new user();
                p.setID_user(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setPrenom(rs.getString(3));
                p.setRole(rs.getString(4));
                p.setSexe(rs.getString(5));
                 p.setEmail(rs.getString(6));
                  p.setAdresse(rs.getString(7));
                   p.setDate_de_naissance(rs.getDate(8));
                    p.setPoids(rs.getFloat(9));
                     p.setTaille(rs.getInt(10));
                      p.setSpecialite(rs.getString(11));
                       p.setMotdepasse(rs.getString(12));
               
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(serviceuser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
//       ServiceMedicament pdao = ServiceMedicament.getInstance();
//        List<Medicament> medicaments = pdao.displayAll();
//        medicationNamess.addAll(medicaments);
//        medicationNames.addAll(medicaments.stream()
//                .map(Medicament::getNom)
//                .collect(Collectors.toList()));
    
//        System.out.println(medicationNamess);
    //**********
     public void loaduesrsmedecin() {
       serviceuser pdao = serviceuser.getInstance();
        List<user> userss = pdao.displayAll();
        usr.addAll(userss);
                        usersmedecins.clear();

        usersmedecins.addAll(userss.stream()
                .filter(u ->u.getRole().equals("medecin"))
                   .map(u->u.getNom()+" "+u.getPrenom())
                .collect(Collectors.toList()));
  
    }
     
      public void loaduesrspatient() {
       serviceuser pdao = serviceuser.getInstance();
        List<user> userss = pdao.displayAll();
        usr.addAll(userss);
                userspatients.clear();

        userspatients.addAll(userss.stream()
                .filter(u ->u.getRole().equals("patient"))
                .map(u->u.getNom()+" "+u.getPrenom())
                .collect(Collectors.toList()));
  
    }
     
     
      public ObservableList<user> getPersons() {
        return usr;
    }
     
      public ObservableList<String> getUsersmedecins() {
        return usersmedecins;
    }
      
       public ObservableList<String> getUserspatients() {
        return userspatients;
    }
    
    
}
