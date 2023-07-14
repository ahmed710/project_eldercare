    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.esprit.Services;

    import com.esprit.entities.Medecin;
    import com.codename1.io.ConnectionRequest;
    import com.codename1.io.JSONParser;
    import com.codename1.io.NetworkManager;
    import com.esprit.utils.Statics;
    import java.io.ByteArrayInputStream;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;

    /**
     *
     * @author iheb
     */
    public class ServiceMedecin {

        private boolean responseResult;
        private List<Medecin> medecin;
        private List<String> emailList;
        private List<Integer> numtel;

        private final String URI = Statics.BASE_URL + "/medecin/";

        public ServiceMedecin() {
            medecin = new ArrayList();
        }

        public boolean ajouter(Medecin t) {
            ConnectionRequest request = new ConnectionRequest();

            request.setUrl(URI);
            request.setHttpMethod("POST");

            request.addArgument("ID_user", String.valueOf(t.getId()));
            request.addArgument("nom", t.getNom());
            request.addArgument("prenom", t.getPrenom());
            request.addArgument("sexe", t.getSexe());
            request.addArgument("email", t.getEmail());
            request.addArgument("adresse", t.getAdresse());
            request.addArgument("motdepasse", BCrypt.hashpw(t.getMotdepasse(), BCrypt.gensalt()));
            request.addArgument("etat", t.getEtat());
            request.addArgument("num_tel", String.valueOf(t.getNum_tel()));
            request.addArgument("url", t.getImageUrl());
            request.addArgument("specialite", t.getSpecialite());
            request.addArgument("role", t.getRole());




            request.addResponseListener((evt) -> {
                responseResult = request.getResponseCode() == 201; // Code HTTP 201 OK
            });
            NetworkManager.getInstance().addToQueueAndWait(request);

            return responseResult;
        }

        public boolean modifier(Medecin t) {
            ConnectionRequest request = new ConnectionRequest();

            request.setUrl(URI + t.getId());
            request.setHttpMethod("PUT");

            request.addArgument("ID_user", String.valueOf(t.getId()));
            request.addArgument("nom", t.getNom());
            request.addArgument("prenom", t.getPrenom());
            request.addArgument("sexe", t.getSexe());
            request.addArgument("email", t.getEmail());
            request.addArgument("adresse", t.getAdresse());
            request.addArgument("motdepasse", BCrypt.hashpw(t.getMotdepasse(), BCrypt.gensalt()));
            request.addArgument("etat", t.getEtat());
            request.addArgument("num_tel", String.valueOf(t.getNum_tel()));
            request.addArgument("url", t.getImageUrl());
            request.addArgument("specialite", t.getSpecialite());
            request.addArgument("role", t.getRole());

            request.addResponseListener((evt) -> {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
            });
            NetworkManager.getInstance().addToQueueAndWait(request);

            return responseResult;
        }

        public boolean supprimer(Medecin t) {
            ConnectionRequest request = new ConnectionRequest();

            request.setUrl(URI + t.getId());
            request.setHttpMethod("DELETE");

            request.addResponseListener((evt) -> {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
            });
            NetworkManager.getInstance().addToQueueAndWait(request);

            return responseResult;
        }

        public List<Medecin> afficher() {
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
                        String sexe =  obj.get("sexe").toString();
                        String email =  obj.get("email").toString();
                        String motdepasse =  obj.get("motdepasse").toString();
                        String etat =  obj.get("etat").toString();
                        int num_tel = (int) Float.parseFloat(obj.get("num_tel").toString());
                        String adresse =  obj.get("adresse").toString();
                        String specialite =  obj.get("specialite").toString();
    //                  String imageUrl =  obj.get("imageUrl").toString();

                        medecin.add(new Medecin(id,nom, prenom, sexe, email, motdepasse, etat, num_tel, specialite, adresse));
                    }

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(request);

            return medecin;
        }
            public List<String> afficherEmails() {
            ConnectionRequest request = new ConnectionRequest();

            request.setUrl(URI);
            request.setHttpMethod("GET");

            request.addResponseListener((evt) -> {
                try {
                    emailList = new ArrayList<>();
                    InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                    Map<String, Object> result = new JSONParser().parseJSON(jsonText);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

                    for (Map<String, Object> obj : list) {
                        String email =  obj.get("email").toString();
                        emailList.add(email);
                    }

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(request);

            return emailList;
        }
        public List<Integer> afficherNumTel() {
            ConnectionRequest request = new ConnectionRequest();

            request.setUrl(URI);
            request.setHttpMethod("GET");

            request.addResponseListener((evt) -> {
                try {
                    numtel = new ArrayList<>();
                    InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                    Map<String, Object> result = new JSONParser().parseJSON(jsonText);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

                    for (Map<String, Object> obj : list) {
                        String num_telString = obj.get("num_tel").toString();
                        double num_telDouble = Double.parseDouble(num_telString);
                        int num_tel = (int) num_telDouble;
                        numtel.add(num_tel);
                    }

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(request);

            return numtel;
        }

    }
