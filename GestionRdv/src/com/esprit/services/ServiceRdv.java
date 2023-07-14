/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.esprit.entities.RendezVous;
import com.esprit.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author DONIG
 */
public class ServiceRdv implements IService<RendezVous> {

    private boolean responseResult;
    private List<RendezVous> rdvs;

    private final String URI = Statics.BASE_URL + "/rendezvous/";

    public ServiceRdv() {
        rdvs = new ArrayList();
    }

    public List<RendezVous> afficher() {
        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI);
        request.setHttpMethod("GET");

        request.addResponseListener((evt) -> {
            try {
                InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                Map<String, Object> result = new JSONParser().parseJSON(jsonText);
                List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

                for (Map<String, Object> obj : list) {
                    String idPatient = obj.get("id_patient").toString();
                    if (idPatient.equals("Bilel")) { // Filtrer par ID patient
                        String date = obj.get("date").toString();
                        String medecin = obj.get("id_medecin").toString();
                        String etat = obj.get("etat").toString();
                        rdvs.add(new RendezVous(date, medecin, etat));
                    }
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return rdvs;
    }

    public boolean ajouter(RendezVous r,Date d) {
        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI);
        request.setHttpMethod("POST");

      
        String outputFormat = "yyyy-MM-dd HH:mm:ss";


        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);

       
            System.out.println(r.getDate());
           
            String outputDate = outputDateFormat.format(d);
            System.out.println(outputDate);

            request.addArgument("date", outputDate);
            request.addArgument("id_patient", "Bilel");
            request.addArgument("id_medecin", r.getMedecin());
            request.addArgument("etat", "En attente");
      
        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 201; // Code HTTP 201 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
}
