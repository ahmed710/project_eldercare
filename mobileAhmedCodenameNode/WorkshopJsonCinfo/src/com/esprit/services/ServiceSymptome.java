/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.esprit.entities.Symptome;
import com.esprit.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author abdel
 */
public class ServiceSymptome implements IService<Symptome> {

    private boolean responseResult;
    private List<Symptome> symptomes;

    private final String URI = Statics.BASE_URL + "/symptome/";

    public ServiceSymptome() {
        symptomes = new ArrayList();
    }

    public boolean ajouter(Symptome t) {
        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI);
        request.setHttpMethod("POST");

        request.addArgument("titre", t.getTitre());
        request.addArgument("description", t.getDescription());
        request.addArgument("frequenceParJour", String.valueOf(t.getFrequenceParJour()));
        request.addArgument("frequenceParSemaine", String.valueOf(t.getFrequenceParSemaine()));
        request.addArgument("duree", String.valueOf(t.getDuree()));
        request.addArgument("organe", t.getOrgane());
        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 201; // Code HTTP 201 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public boolean modifier(Symptome t) {
        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI + t.getID_Symptome());
        request.setHttpMethod("PUT");
        request.addArgument("titre",t.getTitre());
        request.addArgument("description", t.getDescription());
        request.addArgument("frequenceParJour",String.valueOf(t.getFrequenceParJour()));
        request.addArgument("frequenceParSemaine", String.valueOf(t.getFrequenceParSemaine()));
        request.addArgument("duree", String.valueOf(t.getDuree()));
        request.addArgument("organe", t.getOrgane());

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public boolean supprimer(Symptome t) {
        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI + t.getID_Symptome());
        request.setHttpMethod("DELETE");

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public List<Symptome> afficher() {
        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI);
        request.setHttpMethod("GET");

        request.addResponseListener((evt) -> {
            try {
                InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                Map<String, Object> result = new JSONParser().parseJSON(jsonText);
                List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

                for (Map<String, Object> obj : list) {
                    int ID_Symptome =(int) Float.parseFloat(obj.get("ID_Symptome").toString());
                    String titre = obj.get("titre").toString();
                    String description = obj.get("description").toString();
                    int frequenceParJour = (int) Float.parseFloat(obj.get("frequenceParJour").toString());
                    int frequenceParSemaine = (int) Float.parseFloat(obj.get("frequenceParSemaine").toString());
                    float duree = Float.parseFloat(obj.get("duree").toString());             
                    String organe = obj.get("organe").toString();
                    symptomes.add(new Symptome(ID_Symptome, titre, description, frequenceParJour, frequenceParSemaine, duree, organe));
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return symptomes;
    }
    
}
