/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Client;
import com.mycompany.utils.Static;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Raed
 */
public class ServiceClient {
    
        
    public static ServiceClient instance = null; //singleton
    public ArrayList<Client> Clients;
    private ConnectionRequest req;
    public boolean resultOK;

    private ServiceClient() {
        req = new ConnectionRequest();
    }

    public static ServiceClient getInstance() {
        if (instance == null) {
            instance = new ServiceClient();
        }
        return instance;
    }

    public boolean addClient(Client c) {
        String url = Static.BASE_URL + "/Client/addClient?" + "nom=" + c.getNom() + "&prenom=" + c.getPrenom() + "&adresse=" + c.getAdresse() + "&mail=" + c.getMail() + "&mdp_client=" +c.getMdp_client() + "&id_abonnement=" +c.getId_abonnement();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean updateClient(Client t) {
        String url = Static.BASE_URL + "/Client/updateClient/" + t.getId() + "?" + "nom=" + t.getNom() + "&prenom=" + t.getPrenom() + "&mail=" + t.getAdresse() + "&mail=" + t.getMail() +"&type=" + t.getMdp_client() + "&description=" + t.getId_abonnement();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean deleteClient(Client t) {
        String url = Static.BASE_URL + "/Client/deleteClient/" + t.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Client> getAllClient() {
        String url = Static.BASE_URL + "/Client/allClient";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Clients = parseREC(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Clients;
    }

    public ArrayList<Client> parseREC(String jsonText) {
        try {
            Clients = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Client C = new Client();
                C.setId((int) Double.parseDouble(obj.get("id").toString()));
                C.setNom(obj.get("nom").toString());
                C.setPrenom(obj.get("prenom").toString());
                C.setAdresse(obj.get("adresse").toString());
                C.setMail(obj.get("mail").toString());
                C.setMdp_client(obj.get("mdp_client").toString());
                C.setId_abonnement((int) Double.parseDouble(obj.get("id_abonnement").toString()));
                Clients.add(C);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

       
    }
         return Clients;
    }
    
    public boolean ChangerEtatClient(Client C) {
        String url = Static.BASE_URL + "/Client/updateClient/" + C.getId() + "?" + "nom=" + C.getNom() + "&prenom=" + C.getPrenom()+ "&prenom=" + C.getAdresse() + "&mail=" + C.getMail() + "&type=" + C.getMdp_client()+"&etat=" +C.getId_abonnement();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
}
