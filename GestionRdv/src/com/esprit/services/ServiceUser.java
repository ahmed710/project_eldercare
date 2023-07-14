/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.esprit.entities.RendezVous;
import com.esprit.entities.User;
import com.esprit.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DONIG
 */
public class ServiceUser implements IService<User> {

    private boolean responseResult;
    private List<User> medecins;

    private final String URI = Statics.BASE_URL + "/medecin/";

    public ServiceUser() {
        medecins = new ArrayList();
    }

    @Override
    public List<User> afficher() {
        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI);
        request.setHttpMethod("GET");

        request.addResponseListener((evt) -> {
            try {
                InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                Map<String, Object> result = new JSONParser().parseJSON(jsonText);
                List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

                for (Map<String, Object> obj : list) {
                  
                        int id = (int) Float.parseFloat(obj.get("ID_user").toString());
                        String nom = obj.get("nom").toString();
                        String prenom = obj.get("prenom").toString();
                        medecins.add(new User(id, nom, prenom));
                    
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return medecins;
    }

}
