/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.esprit.entities.Maladie;
import com.esprit.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author ahmed
 */
public class ServiceMaladie implements IService<Maladie> {

    private boolean responseResult;
    private List<Maladie> maladies;
    
    private final String URI = Statics.BASE_URL + "/maladie/";

    public ServiceMaladie() {
        maladies = new ArrayList();
    }

    public boolean ajouter(Maladie t) {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI);
        request.setHttpMethod("POST");

        request.addArgument("titre", t.getTitre());
        request.addArgument("description", t.getDescription());
        request.addArgument("classification_etiologique", t.getClassification_etiologique());
        request.addArgument("classification_fonctionelle", t.getClassification_fonctionelle());

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 201; // Code HTTP 201 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public boolean modifier(Maladie t) {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI + t.getID_Maladie());
        request.setHttpMethod("PUT");

        request.addArgument("titre", t.getTitre());
        request.addArgument("description", t.getDescription());
        request.addArgument("classification_etiologique", t.getClassification_etiologique());
        request.addArgument("classification_fonctionelle", t.getClassification_fonctionelle());

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public boolean supprimer(Maladie t) {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI + t.getID_Maladie());
        request.setHttpMethod("DELETE");

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public List<Maladie> afficher() {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI);
        request.setHttpMethod("GET");

        request.addResponseListener((evt) -> {
            try {
                InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                Map<String, Object> result = new JSONParser().parseJSON(jsonText);
                List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");
                System.out.println(list);

                for (Map<String, Object> obj : list) {
                    int id = (int) Float.parseFloat(obj.get("ID_Maladie").toString());
                    String titre = obj.get("titre").toString();
                    String description = obj.get("description").toString();
                    String classification_etiologique = obj.get("classification_etiologique").toString();
                    String classification_fonctionelle = obj.get("classification_fonctionelle").toString();
                    maladies.add(new Maladie(id,titre, description, classification_etiologique,classification_fonctionelle));
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return maladies;
    }
}
