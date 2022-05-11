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
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Seance;
import com.mycompany.utils.Static;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author saisi
 */
public class ServiceSeance {
     public ArrayList<Seance> Seances;
    public static ServiceSeance instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceSeance() {
        req = new ConnectionRequest();
    }

    public static ServiceSeance getInstance() {
        if (instance == null) {
            instance = new ServiceSeance();
        }
        return instance;
    }
    
    
    public void ajouterSeances(Seance s) {
        String url = Static.BASE_URL + "/addTabSeance?typeSeance="+s.getType_seance()+"&dateDebut="+s.getDate_debut() +"&dateFin="+s.getDate_fin()+"&idCoach="+s.getId_coach();
            req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e)-> {
            
            String str = new String (req.getResponseData());
            System.out.println("data=="+str);
            
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    
}

      public ArrayList<Seance> afficherSeance() {
        String url = Static.BASE_URL + "/AfficherTabSeances";
        req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                            Seances = new ArrayList<>();

                 JSONParser jsonp;
                 jsonp=new JSONParser();
                 
                 try{
                     
                     Map<String,Object>mapSeances= jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()) .toCharArray()   ));
                      List<Map<String,Object>> listOfMaps= (List<Map<String,Object>>)mapSeances.get("root");
                      
                     for(Map<String, Object> obj : listOfMaps) {
                       Seance pr= new Seance();
                                             
                 
                      float id_seance= Float.parseFloat(obj.get("id_seance").toString());
                      
                      String type_seance= obj.get("type_seancece").toString();
                      String date_debut= obj.get("date_debut").toString();
                      String date_fin= obj.get("date_fin").toString();
                      float id_coach= Float.parseFloat(obj.get("id_coach").toString());
                      

                                               pr.setId_seance((int)id_seance);
                                               pr.setId_coach((int)id_coach); 
                                               pr.setType_seance(type_seance); 
                                               pr.setDate_debut(date_debut); 
                                               pr.setDate_fin(date_fin); 
                                               
                                               
                                                                Seances.add(pr);

                     }
                     
                 } catch (IOException ex) {
                     ex.printStackTrace();
                 }
            }
        }); 
                 NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
           return Seances;
     
}
       public boolean ModifierSeance(Seance s) {
 
        String url = Static.BASE_URL + "/updateTabSeance?id_seance=" + s.getId_seance()+ "typeSeance="+s.getType_seance()+"&dateDebut="+s.getDate_debut() +"&dateFin="+s.getDate_fin()+"&idCoach="+s.getId_coach();

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;  
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
       
        return resultOK;
       }
     public boolean deleteSeance(Seance s) {
        String url = Static.BASE_URL + "/SupprimerTabSeances" + s.getId_seance();
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
