/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.Commande;
import com.esprit.entities.Pharmacie;
import com.esprit.utlis.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Wimbee018
 */
public class IServicePharmacie implements IService<Pharmacie>{
    private boolean responseResult;
    private List<Pharmacie> pharmacies;
    private List<String> nompharmacies;
    private List<String> idlist;
    
    private final String URI = Statics.BASE_URL + "/pharmacie/";

    public IServicePharmacie() {
        pharmacies = new ArrayList();
    }
    
     public boolean ajouter(Pharmacie t) {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI);
        request.setHttpMethod("POST");

        request.addArgument("nom_pharmacie", t.getNom_pharmacie());
        request.addArgument("adresse_pha",t.getAdresse_pha());
        request.addArgument("telephone", t.getTelephone());
        
        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 201; // Code HTTP 201 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
    public boolean modifier(Pharmacie t) {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI + String.valueOf(t.getID_pharmacie()));
        request.setHttpMethod("PUT");

        request.addArgument("nom_pharmacie", t.getNom_pharmacie());
        request.addArgument("adresse_pha",t.getAdresse_pha());
        request.addArgument("telephone", t.getTelephone());

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
     public boolean supprimer(Pharmacie t) {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI + String.valueOf(t.getID_pharmacie()));
        request.setHttpMethod("DELETE");

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
    public List<Pharmacie> afficher() {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI);
        request.setHttpMethod("GET");

        request.addResponseListener((evt) -> {
            try {
                InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                Map<String, Object> result = new JSONParser().parseJSON(jsonText);
                List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

                for (Map<String, Object> obj : list) {

                    int id_pha = (int) Float.parseFloat(obj.get("ID_pharmacie").toString());
                    String nom_pharmacie = obj.get("nom_pharmacie").toString();
                    String adresse_pha = obj.get("adresse_pha").toString();
                    String telephone = obj.get("telephone").toString();
                    pharmacies.add(new Pharmacie(id_pha, nom_pharmacie,adresse_pha,telephone));
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return pharmacies;
    }
    public List<String> getNomPhar() {
        ConnectionRequest request = new ConnectionRequest();
//        nompharmacies=new ArrayList();
        request.setUrl(URI);
        request.setHttpMethod("GET");

        request.addResponseListener((evt) -> {
            try {
                InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                Map<String, Object> result = new JSONParser().parseJSON(jsonText);
                List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");
                 nompharmacies = new ArrayList<>();
                for (Map<String, Object> obj : list) {

                    String nom_pharmacie = obj.get("nom_pharmacie").toString();
                    nompharmacies.add(nom_pharmacie);
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return nompharmacies;
    }
//    public int getIdPharByNom(String p){
//        ConnectionRequest request = new ConnectionRequest();
//
//
//        request.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                try {
//                    request.setUrl(URI);
//                    request.setHttpMethod("GET");
//                    InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
//                    Map<String, Object> result = new JSONParser().parseJSON(jsonText);
//                    List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");
//                    request.addArgument("nom_pharmacie", p);
//                    if (!list.isEmpty()) {
//                        Map<String, Object> obj = list.get(0);
//                        int id =Integer.parseInt(obj.get("ID_pharmacie").toString());
//                    }
//                    
//                } catch (IOException ex) {
//                    System.out.println(ex.getMessage());
//                }
//            }
//        });
//
//
//        return id;
//    }

}
