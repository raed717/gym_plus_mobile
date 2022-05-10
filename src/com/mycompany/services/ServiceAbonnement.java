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
import com.mycompany.entities.Abonnement;
import com.mycompany.utils.Static;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Raed
 */
public class ServiceAbonnement {
    
        
    public static ServiceAbonnement instance = null; //singleton
    public ArrayList<Abonnement> Abonnements;
    private ConnectionRequest req;
    public boolean resultOK;

    private ServiceAbonnement() {
        req = new ConnectionRequest();
    }

    public static ServiceAbonnement getInstance() {
        if (instance == null) {
            instance = new ServiceAbonnement();
        }
        return instance;
    }

    public boolean addAbonnement(Abonnement c) {
        String url = Static.BASE_URL + "/Abonnement/addAbonnement?" + "nom_ab=" + c.getNom_ab() + "&prix_ab=" + c.getPrix_ab() + "&description=" + c.getDescription();
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

    public boolean updateAbonnement(Abonnement t) {
        String url = Static.BASE_URL + "/Abonnement/updateAbonnement/" + t.getId_abonnement() + "?" + "nom_ab=" + t.getNom_ab() + "&prix_ab=" + t.getPrix_ab() + "&description=" + t.getDescription();
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

    public boolean deleteAbonnement(Abonnement t) {
        String url = Static.BASE_URL + "/Abonnement/deleteAbonnement/" + t.getId_abonnement();
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

    public ArrayList<Abonnement> getAllAbonnement() {
        String url = Static.BASE_URL + "/Abonnement/allAbonnement";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Abonnements = parseREC(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Abonnements;
    }

    public ArrayList<Abonnement> parseREC(String jsonText) {
        try {
            Abonnements = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Abonnement C = new Abonnement();
                C.setId_abonnement((int) Double.parseDouble(obj.get("id_abonnement").toString()));
                C.setNom_ab(obj.get("nom_ab").toString());
                C.setPrix_ab((int) Double.parseDouble(obj.get("prix_ab").toString()));
                C.setDescription(obj.get("description").toString());
                Abonnements.add(C);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

       
    }
         return Abonnements;
    }
    
    public boolean ChangerEtatAbonnement(Abonnement C) {
        String url = Static.BASE_URL + "/Abonnement/updateAbonnement/" + C.getId_abonnement() + "?" + "nom_ab=" + C.getNom_ab() + "&prix_ab=" + C.getPrix_ab()+ "&description=" + C.getDescription();
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
