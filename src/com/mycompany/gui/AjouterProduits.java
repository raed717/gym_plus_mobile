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
import com.mycompany.entities.Produit;
 
/**
 *
 * @author firas
 */
public class AjouterProduits extends BaseForm {
    Form current; 
        public AjouterProduits(Resources res) {

            super("Newsfeed", BoxLayout.y());
            
        Toolbar tb = new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        
                setTitle("Ajouter Produits");
                getContentPane().setScrollVisible(false);
                
                TextField description= new TextField ("", "entrer description!"); 
                description.setUIID("TextFieldBlack"); 
                 addStringValue("", description);
                
                TextField name= new TextField ("","entrer name!"); 
                name.setUIID("TextFieldBlack"); 
                addStringValue(" ", name );
                
                TextField price= new TextField ("","entrer price!"); 
                price.setUIID("TextFieldBlack"); 
                addStringValue("name", price);
           
                TextField image= new TextField ("","entrer image!"); 
                image.setUIID("TextFieldBlack"); 
                addStringValue("image", image); 
                
                
                   TextField idCategorie= new TextField (" ","entrer idCatgorie!"); 
                idCategorie.setUIID("TextFieldBlack"); 
                addStringValue("idCatgorie", idCategorie); 
                
                
                Button btnAjouter= new Button ("ajouter");
                addStringValue("",btnAjouter);
                
                btnAjouter.addActionListener((e) -> {
                    try{
                        
                        if (description.getText() =="" ||name.getText()==""||price.getText()=="" ||image.getText()==""||idCategorie.getText()==""){
        Dialog.show("veuillez verifier les données ", "" ,"OK", "Cancel");
                        
                     }
                    else { 
                            InfiniteProgress ip = new InfiniteProgress();
                            final Dialog iDialog = ip.showInfiniteBlocking(); 
                            
                            Produit p = new Produit (
                               Integer.parseInt(idCategorie.getText()),
                               String.valueOf(description.getText()),
                               String.valueOf(name.getText()).toString(),
                               String.valueOf(image.getText()).toString(),
                              Float.parseFloat(price.getText()));

                            
                            System.out.println("produits=" +p);
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
                .add(BorderLayout.CENTER,s));
                add(createLineSeparator(0xeeeeee));
                
     }
}
