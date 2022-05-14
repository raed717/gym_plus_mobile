/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Seance;
 
/**
 *
 * @author firas
 */
public class AjouterSeances extends BaseForm {
    Form current; 
    Resources theme;
        public AjouterSeances(Resources res) {

            super("Newsfeed", BoxLayout.y());
            
        Toolbar tb = new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        
                setTitle("Ajouter Seances");  
                getContentPane().setScrollVisible(false);
                
                TextField type_seance= new TextField ("", "entrer type seance!"); 
                type_seance.setUIID("TextFieldBlack"); 
                 addStringValue("type_seance", type_seance);
                
                TextField date_debut= new TextField ("","entrer date debut!"); 
                date_debut.setUIID("TextFieldBlack"); 
                addStringValue("date_debut", date_debut );
                
                TextField date_fin= new TextField ("","entrer date fin!"); 
                date_fin.setUIID("TextFieldBlack"); 
                addStringValue("date_fin", date_fin);
           
                TextField id_coach= new TextField ("","entrer coach!"); 
                id_coach.setUIID("TextFieldBlack"); 
                addStringValue("id_coach", id_coach); 
                
                
               
                
                Button save= new Button ("ajouter");
                addStringValue("",save);
                
                save.addActionListener((e) -> {
                    try{
                        
                     if (type_seance.getText() =="" ||date_debut.getText()==""||date_fin.getText()=="" ||id_coach.getText()==""){
        Dialog.show("veuillez verifier les données ", "" ,"OK", "Cancel");
                        
                     }
                    else { 
                            InfiniteProgress ip = new InfiniteProgress();
                            final Dialog iDialog = ip.showInfiniteBlocking(); 
                            
                            Seance p = new Seance (
                                     String.valueOf(type_seance.getText()).toString(),
                               
                               String.valueOf(date_debut.getText()),
                               String.valueOf(date_fin.getText()).toString(),
                                   
                              
                                Integer.parseInt(id_coach.getText()));
                              

                            
                            System.out.println("seances=" +p);
iDialog.dispose();
refreshTheme();
                            
                             }
             
                            
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                        
                }             );
                
                }


    private void addStringValue(String s, Component v) {
        add (BorderLayout.west(new Label(s,"PaddedLabel"))
                .add(BorderLayout.CENTER,v));
                add(createLineSeparator(0xeeeeee));
                
     }
}
