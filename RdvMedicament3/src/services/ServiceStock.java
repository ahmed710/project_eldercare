/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Medicament;
import entities.RendezVous;
import entities.Stock;
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

/**
 *
 * @author DONIG
 */
public class ServiceStock implements IService<Stock> {
    
 private static ServiceStock instance;
    private Statement st;
    private ResultSet rs;
    DataSource cs=DataSource.getInstance();
    private ObservableList<Medicament> medicationNamess = FXCollections.observableArrayList();
    private ObservableList<String> medicationNames = FXCollections.observableArrayList();
       private ObservableList<Stock> stocks = FXCollections.observableArrayList();

    private ServiceStock() {
        
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceStock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ServiceStock getInstance(){
        if(instance==null) 
            instance=new ServiceStock();
        return instance;
    }    
    
     public void ajouter(Stock o) {
        String req = "INSERT IGNORE INTO stock (id_pharm,id_medic,quantite) VALUES ('" + o.getIdPharmacie() + "', '" + o.getIdMedicament() + "', '" + o.getQuantite() +  "')";
        //String req="insert into Reclamation (nom,prenom,email,numTel,message,date) values ('"+o.getNom()+"','"+o.getPrenom()+"','"+o.getEmail()+"','"+o.getNumTel()+"','"+o.getMessage()+"','"+o.getDate()+"',')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    
           public boolean modifier(Stock p) {
       // String qry = "UPDATE Reclamation SET nom = '"+p.getNom()+"', prenom = '"+p.getPrenom()+"', email = '"+p.getEmail()+"', numTel = '"+p.getNumTel()+"', message = '"+p.getMessage()+"' WHERE id = "+p.getId();
         String qry = "UPDATE stock SET  id_pharm = '"+p.getIdPharmacie()+"', id_medic = '"+p.getIdMedicament()+"', quantite = '"+p.getQuantite()+"' WHERE idStock = "+p.getIdStock();
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
 
                public Stock displayById(int id) {
           String req="select * from stock where idStock="+id;
           Stock p=new Stock();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
             p.setIdStock(rs.getInt(1));
                p.setIdPharmacie(rs.getString(2));
                p.setIdMedicament(rs.getString(3));
                p.setQuantite(rs.getInt(4));
              
  
        } catch (SQLException ex) {
            Logger.getLogger(ServiceStock.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
       
           
      public void supprimer(Stock r) {
             String req = "DELETE from stock WHERE idStock="+r.getIdStock();
            Stock p=displayById(r.getIdStock());
            
             if(p!=null)
              try {
           
            st.executeUpdate(req);
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      
    @Override
    public List<Stock> afficher() {
        List<Stock> list = new ArrayList<>();
        
        String req = "SELECT * FROM stock";
        try {
            rs=st.executeQuery(req);
            while(rs.next()) {
//                list.add(new RendezVous(rs.getDate("date"), rs.getInt("id_patient"),
//                        rs.getInt("id_medecin"),rs.getString("etat")));
           Stock p=new Stock();
                p.setIdStock(rs.getInt(1));
                p.setIdPharmacie(rs.getString(2));
                p.setIdMedicament(rs.getString(3));
                p.setQuantite(rs.getInt(4));
               
                list.add(p);
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
    
    
      public ObservableList<Stock> displayAll() {
        String req="select * from Stock";
        ObservableList<Stock> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
           Stock p=new Stock();
              p.setIdStock(rs.getInt(1));
                p.setIdPharmacie(rs.getString(2));
                p.setIdMedicament(rs.getString(3));
                p.setQuantite(rs.getInt(4));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
 //************* 
      public void loadMedicaments() {
        ServiceMedicament pdao = ServiceMedicament.getInstance();
        List<Medicament> medicaments = pdao.displayAll();
                medicationNamess.clear();

        medicationNamess.addAll(medicaments);
        medicationNames.addAll(medicaments.stream()
                .map(Medicament::getNom)
                .collect(Collectors.toList()));
        System.out.println(medicationNamess);
    }

    public ObservableList<Medicament> getMedicationNamess() {
        return medicationNamess;
    }

    public ObservableList<String> getMedicationNames() {
        return medicationNames;
    }
    
    //**********
     public void loadStocks() {
       ServiceStock pdao = ServiceStock.getInstance();
        List<Stock> medicaments = pdao.displayAll();
        stocks.clear();
        stocks.addAll(medicaments);
        System.out.println(stocks);
    }
     
      public ObservableList<Stock> getstock() {
        return stocks;
    }
      
      //********
      
      public List<Stock> recherchermedic(String recherche) {
    List<Stock> produits = afficher().stream()
            .filter(x -> 
                x.getIdMedicament().contains(recherche) )
            .collect(Collectors.toList());
    return produits;       
}
      
          public List<Stock> rechercherpharm(String recherche) {
    List<Stock> produits = afficher().stream()
            .filter(x -> 
                x.getIdPharmacie().contains(recherche) )
            .collect(Collectors.toList());
    return produits;       
}
  

      
      
      
}
