/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.esprit.entities.Maladie_Symptome;
import com.esprit.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author abdel
 */
public class ServiceMaladieSymptome implements IService<Maladie_Symptome> {

    private boolean responseResult;
    private List<Maladie_Symptome> maladiesSymptomes;
    
    private final String URI = Statics.BASE_URL + "/personne/";

    public ServiceMaladieSymptome() {
        maladiesSymptomes = new ArrayList();
    }

    public boolean ajouter(Maladie_Symptome t) {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI);
        request.setHttpMethod("POST");

        request.addArgument("ID_Symptome", String.valueOf(t.getID_Symptome()));
        request.addArgument("ID_Maladie", String.valueOf(t.getID_Maladie()));
        request.addArgument("taux_de_correspondance",String.valueOf(t.getTaux_de_correspondance()));

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 201; // Code HTTP 201 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public boolean modifier(Maladie_Symptome t) {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI + t.getID_Maladie()+'/'+t.getID_Symptome());
        request.setHttpMethod("PUT");

        request.addArgument("ID_Symptome", String.valueOf(t.getID_Symptome()));
        request.addArgument("ID_Maladie", String.valueOf(t.getID_Maladie()));
        request.addArgument("taux_de_correspondance",String.valueOf(t.getTaux_de_correspondance()));

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public boolean supprimer(Maladie_Symptome t) {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI + t.getID_Maladie()+'/'+t.getID_Symptome());
        request.setHttpMethod("DELETE");

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public List<Maladie_Symptome> afficher() {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI);
        request.setHttpMethod("GET");

        request.addResponseListener((evt) -> {
            try {
                InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                Map<String, Object> result = new JSONParser().parseJSON(jsonText);
                List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

                for (Map<String, Object> obj : list) {
                    int ID_Maladie = (int) Float.parseFloat(obj.get("ID_Maladie").toString());
                    int ID_Symptome = (int) Float.parseFloat(obj.get("ID_Symptome").toString());
                    Float taux_de_correspondance =  Float.parseFloat(obj.get("taux_de_correspondance").toString());
                    maladiesSymptomes.add(new Maladie_Symptome(ID_Maladie, ID_Symptome, taux_de_correspondance));
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return maladiesSymptomes;
    }
}
