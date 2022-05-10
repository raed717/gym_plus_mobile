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
import com.mycompany.entities.Produit;
 import com.mycompany.utils.Static;
import java.util.ArrayList;
 import java.io.IOException;
import java.util.Map;
import java.util.List;
 
/**
 *
 * @author firas
 */
public class ServiceProduit {
      public ArrayList<Produit> Produits;
    public static ServiceProduit instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceProduit() {
        req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }
    
    
    public void AddProduit(Produit p) {
        String url = Static.BASE_URL + "/addProduit?description="+p.getDescription()+"&name="+p.getName() +"&price="+p.getPrice()+"&idCatgorie="+p.getIdCategorie()+"&image="+p.getImage();
            req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e)-> {
            
            String str = new String (req.getResponseData());
            System.out.println("data=="+str);
            
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    
}

      public ArrayList<Produit> afficherProduit() {
        String url = Static.BASE_URL + "AfficherProduits";
        req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                            Produits = new ArrayList<>();

                 JSONParser jsonp;
                 jsonp=new JSONParser();
                 
                 try{
                     
                     Map<String,Object>mapProduits= jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()) .toCharArray()   ));
                      List<Map<String,Object>> listOfMaps= (List<Map<String,Object>>)mapProduits.get("root");
                      
                     for(Map<String, Object> obj : listOfMaps) {
                       Produit pr= new Produit();
                                             
                 
                      float id= Float.parseFloat(obj.get("id").toString());
                      float idCatgorie= Float.parseFloat(obj.get("idCatgorie").toString());
                      String description= obj.get("description").toString();
                      String name= obj.get("name").toString();
                      String image= obj.get("image").toString();
                       float price= Float.parseFloat(obj.get("price").toString());

                                               pr.setId((int)id);
                                               pr.setIdCategorie((int)idCatgorie); 
                                               pr.setDescription(description); 
                                               pr.setName(name); 
                                               pr.setImage(image); 
                                               pr.setPrice(price);
                                               
                                                                Produits.add(pr);

                     }
                     
                 } catch (IOException ex) {
                     ex.printStackTrace();
                 }
            }
        }); 
                 NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
           return Produits;
     
}}

