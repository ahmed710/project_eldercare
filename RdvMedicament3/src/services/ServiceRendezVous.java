/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import entities.RendezVous;
import entities.Stock;


import utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
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
public class ServiceRendezVous implements IService<RendezVous> {
    
   //  private Connection cnx = DataSource.getInstance().getCnx();
   private static ServiceRendezVous instance;
    private Statement st;
    private ResultSet rs;
    DataSource cs=DataSource.getInstance();
        private ObservableList<RendezVous> rdvs = FXCollections.observableArrayList();

    
    private ServiceRendezVous() {
        
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRendezVous.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ServiceRendezVous getInstance(){
        if(instance==null) 
            instance=new ServiceRendezVous();
        return instance;
    }
    
    
    public void ajouter(RendezVous o) {
        String req = "INSERT IGNORE INTO rendezvous (date, id_patient, id_medecin, etat) VALUES ('" + o.getDate() + "', '" + o.getIdPatient() + "', '" + o.getIdMedecin() + "', '" + o.getEtat() +  "')";
        //String req="insert into Reclamation (nom,prenom,email,numTel,message,date) values ('"+o.getNom()+"','"+o.getPrenom()+"','"+o.getEmail()+"','"+o.getNumTel()+"','"+o.getMessage()+"','"+o.getDate()+"',')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRendezVous.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  

       public boolean modifier(RendezVous p) {
       // String qry = "UPDATE Reclamation SET nom = '"+p.getNom()+"', prenom = '"+p.getPrenom()+"', email = '"+p.getEmail()+"', numTel = '"+p.getNumTel()+"', message = '"+p.getMessage()+"' WHERE id = "+p.getId();
         String qry = "UPDATE rendezvous SET  date = '"+p.getDate()+"', id_patient = '"+p.getIdPatient()+"', id_medecin = '"+p.getIdMedecin()+"', etat = '"+p.getEtat()+"' WHERE idR = "+p.getIdR();
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRendezVous.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//     public void delete(Reclamation o) {
//        String req="delete from Reclamation where id="+o.getId();
//        Reclamation p=displayById(o.getId());
//        
//          if(p!=null)
//              try {
//           
//            st.executeUpdate(req);
//             
//        } catch (SQLException ex) {
//            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
//        }else System.out.println("n'existe pas");
//    }
//    
          public RendezVous displayById(int id) {
           String req="select * from rendezvous where idR ="+id;
           RendezVous p=new RendezVous();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
             p.setIdR(rs.getInt(1));
                p.setDate(rs.getDate(2));
                p.setIdPatient(rs.getString(3));
                p.setIdMedecin(rs.getString(4));
                p.setEtat(rs.getString(5));
  
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRendezVous.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
       
       
    public void supprimer(RendezVous r) {
             String req = "DELETE from rendezvous WHERE idR="+r.getIdR();
            RendezVous p=displayById(r.getIdR());
            
             if(p!=null)
              try {
           
            st.executeUpdate(req);
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @Override
    public List<RendezVous> afficher() {
        List<RendezVous> list = new ArrayList<>();
        
        String req = "SELECT * FROM rendezvous";
        try {
            rs=st.executeQuery(req);
            while(rs.next()) {
//                list.add(new RendezVous(rs.getDate("date"), rs.getInt("id_patient"),
//                        rs.getInt("id_medecin"),rs.getString("etat")));
     RendezVous p=new RendezVous();
                p.setIdR(rs.getInt(1));
                p.setDate(rs.getDate(2));
                p.setIdPatient(rs.getString(3));
                p.setIdMedecin(rs.getString(4));
                p.setEtat(rs.getString(5));
               
                list.add(p);
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
    
    public ObservableList<RendezVous> displayAll() {
        String req="select * from rendezvous";
        ObservableList<RendezVous> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                  RendezVous p=new RendezVous();
                p.setIdR(rs.getInt(1));
                p.setDate(rs.getDate(2));
                p.setIdPatient(rs.getString(3));
                p.setIdMedecin(rs.getString(4));
                p.setEtat(rs.getString(5));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRendezVous.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    //**********
     public void loadRdvs() {
       ServiceRendezVous pdao = ServiceRendezVous.getInstance();
        List<RendezVous> medicaments = pdao.displayAll();
        rdvs.clear();
        rdvs.addAll(medicaments);
        System.out.println(rdvs);
    }
     
      public ObservableList<RendezVous> getRdvs() {
        return rdvs;
    }
      
       public List<RendezVous> filtrerParDate(java.util.Date startDate, java.util.Date endDate) {
    List<RendezVous> events = afficher().stream()
            .filter(e -> e.getDate().compareTo(startDate) >= 0 && e.getDate().compareTo(endDate) <= 0)
            .collect(Collectors.toList());
    return events;
}
    
}
