/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.esprit.entities.Commande;
import com.esprit.entities.Pharmacie;
import com.esprit.entities.Medicament;
import com.esprit.utlis.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Date;

/**
 *
 * @author Wimbee018
 */
public class ServiceCommande implements IService<Commande> {
    private boolean responseResult;
    private List<Commande> commandes;
    
    private final String URI = Statics.BASE_URL + "/commande/";

    public ServiceCommande() {
        commandes = new ArrayList();
    }
    
     public boolean ajouter(Commande t) {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI);
        request.setHttpMethod("POST");

        request.addArgument("ID_pharmacie", String.valueOf(t.getID_pharmacie()));
        request.addArgument("date_commande", t.getDate_commande());
        request.addArgument("adresse", t.getAdresse());
        request.addArgument("etat", t.getEtat());
        
        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 201; // Code HTTP 201 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
    public boolean modifier(Commande t) {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI + String.valueOf(t.getID_commande()));
        request.setHttpMethod("PUT");

        request.addArgument("date_commande", t.getDate_commande());
        request.addArgument("adresse", t.getAdresse());
        request.addArgument("etat", t.getEtat());

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
     public boolean supprimer(Commande t) {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI + String.valueOf(t.getID_commande()));
        request.setHttpMethod("DELETE");

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
    public List<Commande> afficher() {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI);
        request.setHttpMethod("GET");

        request.addResponseListener((evt) -> {
            try {
                InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                Map<String, Object> result = new JSONParser().parseJSON(jsonText);
                List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

                for (Map<String, Object> obj : list) {
                    int id = (int) Float.parseFloat(obj.get("ID_commande").toString());
                    int id_pha = (int) Float.parseFloat(obj.get("ID_pharmacie").toString());
                    String adresse = obj.get("adresse").toString();
                    String date_commande = obj.get("date_commande").toString();
                    String etat = obj.get("etat").toString();
                    commandes.add(new Commande(id, id_pha, adresse,date_commande,etat));
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return commandes;
    }

    
}
