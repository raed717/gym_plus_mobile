/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.mycompany.entities.Produit;
import java.util.ArrayList;

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
    
    
    
    
    
    
}
