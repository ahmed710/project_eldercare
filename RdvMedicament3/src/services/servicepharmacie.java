/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

//import com.esprit.entities.medecin;
import entities.pharmacie;
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
public class servicepharmacie {
    private static servicepharmacie instance;
    private Statement st;
    private ResultSet rs;
    DataSource cs=DataSource.getInstance();
        private ObservableList<pharmacie> pharm = FXCollections.observableArrayList();
            private ObservableList<String> pharmacies = FXCollections.observableArrayList();

 private servicepharmacie() {
        
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(servicepharmacie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static servicepharmacie getInstance(){
        if(instance==null) 
            instance=new servicepharmacie();
        return instance;
    }
    
    
    
    public List<pharmacie> afficher() {
        List<pharmacie> list = new ArrayList<>();
        
        String req = "SELECT * FROM pharmacie";
        try {
            rs=st.executeQuery(req);
            while(rs.next()) {
//                list.add(new RendezVous(rs.getDate("date"), rs.getInt("id_patient"),
//                        rs.getInt("id_medecin"),rs.getString("etat")));
     pharmacie p=new pharmacie();
                p.setID_pharmacie(rs.getInt(1));
                p.setNom_pharmacie(rs.getString(2));
                p.setAdresse_pha(rs.getString(3));
                p.setTelephone(rs.getInt(4));
              
               
                list.add(p);
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
    
    public ObservableList<pharmacie> displayAll() {
        String req="select * from pharmacie";
        ObservableList<pharmacie> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                 pharmacie p=new pharmacie();
                p.setID_pharmacie(rs.getInt(1));
                p.setNom_pharmacie(rs.getString(2));
                p.setAdresse_pha(rs.getString(3));
                p.setTelephone(rs.getInt(4));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(servicepharmacie.class.getName()).log(Level.SEVERE, null, ex);
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
     public void loadpharmacies() {
       servicepharmacie pdao = servicepharmacie.getInstance();
        List<pharmacie> pharmaciies = pdao.displayAll();
        pharm.addAll(pharmaciies);
                pharmacies.clear();

        pharmacies.addAll(pharmaciies.stream()
                .map(pharmacie::getNom_pharmacie)
                .collect(Collectors.toList()));
      //  System.out.println(medecins);
    }
     
      public ObservableList<pharmacie> getPharmacie() {
        return pharm;
    }
     
      public ObservableList<String> getpharmacies() {
        return pharmacies;
    }
    
    
    
    
    
}
