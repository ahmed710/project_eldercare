
package com.esprit.tests;

import Controllers.ManageOrdonnanceController;
import com.esprit.entities.Traitement;
import com.esprit.entities.Ordonnance;
import com.esprit.entities.medicament;
import com.esprit.gui.ServiceMedicament;
import com.esprit.services.ServiceOdonnance;
import com.esprit.services.ServiceTraitement;

import java.sql.Date;
<<<<<<< Updated upstream
import java.util.ArrayList;
import java.util.List;
=======
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
>>>>>>> Stashed changes

/**
 *
 * @author Zouhour
 */
public class EldercarePI {

    /**
     * @param args the command line arguments
     */
<<<<<<< Updated upstream
    public static void main(String[] args) {
//
  
//    Ordonnance ordonance1 = new Ordonnance("k15", 50, 80, Date.valueOf("2023-01-31");
    ServiceOdonnance so1 = new ServiceOdonnance();
    so1.afficher();
    List med = new ArrayList();
    med.add("k15");
//    so1.ajouter(new Ordonnance( med ,50, 80, Date.valueOf("2023-01-31")));
//    so1.ajouter(new Ordonnance("k15", 50, 80, Date.valueOf("2016-08-05")));
   
    System.out.println(so1.afficher());   
    so1.supprimer(new Ordonnance( 23,med,50, 80, Date.valueOf("2023-01-31")));
    
=======
    public static void main(String[] args) throws SQLException {
//        try {
            //
            
//    Ordonnance ordonance1 = new Ordonnance("k15", 50, 80, Date.valueOf("2023-01-31");
    ServiceOdonnance so1 = new ServiceOdonnance();
    so1.afficher();
//    List med = new ArrayList();
//    med.add("k15");
//    so1.ajouter(new Ordonnance( med ,50, 80, Date.valueOf("2023-01-31")));
//    so1.ajouter(new Ordonnance("k15", 50, 80, Date.valueOf("2016-08-05")));
//
//    System.out.println(so1.afficher());   
//    so1.supprimer(new Ordonnance( 23,med,50, 80, Date.valueOf("2023-01-31")));
//
//
//     /********************************************************************************/
////      
//    Traitement T1 = new Traitement(null, null, 
//            "aaaaa", "Parkizol", 1, 3);
//    ServiceTraitement sT1 = new ServiceTraitement();
//    sT1.ajouter(T1);
//    System.out.println(sT1.afficher());
//////    
////    }

   

ServiceOdonnance so = new ServiceOdonnance();
 System.out.println(so.afficher());



Ordonnance o = new Ordonnance("Adol", null, "zon rouissi", "bilel");
>>>>>>> Stashed changes
      

//        ObservableList<Ordonnance> ordonnanceList = FXCollections.observableArrayList();
//        ServiceOdonnance so = new ServiceOdonnance();
//        Ordonnance o = new Ordonnance("test", null, "test", "test");
//        so.ajouter(o);
//        System.out.println(so.getAlldocs());
//        System.out.println(so.getAllpatient());
//        ordonnanceList.addAll(so.afficher());
//        System.out.println(ordonnanceList);

} }


