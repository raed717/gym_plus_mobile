/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Abonnement;
import com.mycompany.services.ServiceAbonnement;
import java.util.ArrayList;

/**
 *
 * @author Raed
 */
public class Listeabonnement {
    
    Form current;
    ArrayList<Abonnement> data = new ArrayList<>();
  
    Container all = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container tri = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    

    private void listeab() {
        data = ServiceAbonnement.getInstance().getAllAbonnement();
        
        for (int i = 0; i < data.size(); i++) {
            Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Abonnement ab = new Abonnement();
            ab.setId_abonnement(data.get(i).getId_abonnement());
            ab.setNom_ab(data.get(i).getNom_ab());
            ab.setPrix_ab(data.get(i).getPrix_ab());
            ab.setPrix_ab(data.get(i).getPrix_ab());
            ab.setDescription(data.get(i).getDescription());
            Label separation = new Label("----------------------------");
            Label titre = new Label("Titre : " + data.get(i).getNom_ab());
            Label Prix = new Label("Prix : " + data.get(i).getPrix_ab());
            Label Dscription = new Label("Dscription : " + data.get(i).getDescription());
            Button share = new Button(FontImage.MATERIAL_SHARE);
            Button achat = new Button(FontImage.MATERIAL_CARD_GIFTCARD);
            
            
            share.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                String clientId = "1171134366245722";
                String redirectURI = "http://www.codenameone.com/";
                String clientSecret = "XXXXXXXXXXXXXXXXXXXXXXXXXX";
                Login fb = FacebookConnect.getInstance();
                fb.setClientId(clientId);
                fb.setRedirectURI(redirectURI);
                fb.setClientSecret(clientSecret);
                if(!fb.isUserLoggedIn()){
                    fb.doLogin();
                }else{
                    //get the token and now you can query the facebook API
                    String token = fb.getAccessToken().getToken();
                }
  

                }
            });
            
            
            x.addAll(titre, Prix, Dscription);
            xx.addAll(achat,share);
            all.addAll(x, Dscription, xx, separation);

        }
        
        

    }

    public Listeabonnement(Form previous) {

        setTitle("Listes  Des Abonnements");
        listeab();
        addAll(all);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                 e -> previous.showBack());
    }
    
}
