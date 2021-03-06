/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
 import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
   import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
 import com.codename1.ui.util.Resources;
import com.mycompany.entities.Produit;
 import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.services.ServiceProduit;
import java.util.ArrayList;
  import javafx.scene.control.Tab;
   

/**
 *
 * @author firas
 */
public class ListProduit extends BaseForm{
        Form current; 
 
            public ListProduit(Resources res) {

    super("Newsfeed", BoxLayout.y());
  
            
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        
                setTitle("Ajouter Produits");
                getContentPane().setScrollVisible(false);
                
                tb.addSearchCommand(e-> {
                
                
                        });
                Tabs swipe = new Tabs();
                Label s1= new Label();
                Label s2 = new Label();
                 
                addTab(swipe,s1,res.getImage("back-logo.jpeg"), "","",res);
                
                
                
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Produits", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Modifer", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Afficher", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
                      new ListProduit(res).show();

            refreshTheme();
 
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
         ArrayList<Produit> Produits = ServiceProduit.getInstance().afficherProduit();
              for (Produit rec : Produits){
                  
              String urlImage = "back-logo.png";

            Image placeholder = Image.createImage(120, 90);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);

            addButton(urlim, rec.getDescription(), rec.getName(),rec.getPrice(), rec.getId(),res,rec);

            ScaleImageLabel imag = new ScaleImageLabel(urlim);

            Container containerImg = new Container();

            imag.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        } 
            }
            
     private void addTab(Tabs swipe,Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() < size){
            image = image.scaledHeight(size);
        }
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ){
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
            
        }
        
        ScaleImageLabel imageScale   = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverLay");
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                            overLay,
                            BorderLayout.south(
                            BoxLayout.encloseY(
                            new SpanLabel(text,"LargeWhiteText"),
                                    spacer
                                    ))
        );
        
        swipe.addTab("",res.getImage("back-logoo.jpg"),page1);
        
        
        
            
           this.getToolbar().addCommandToLeftSideMenu("Back", null, ev -> {
               try {
                } catch (Exception ex) {
            
               }
               
               
               
        });
        

    }
    
    public void bindButtonSelection(Button btn , Label l){
        
        btn.addActionListener(e->{
            if(btn.isSelected()){
                updateArrowPosition(btn,l);
            }
            });
        
    }

    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
    }

    private void addButton(Image img, String description, String name, float price, int id,  Resources res,Produit rec) {
     
       int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
         Button imag = new Button(img.fill(width, height));
        imag.setUIID("Label");
        Container cnt = BorderLayout.west(imag);
   Label  NameTxt = new  Label("name" +name,"NewsTopLine2");
  Label  idp = new  Label("ip_produit" +id,"NewsTopLine2");
 createLineSeparator();
 
 
  cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseY(idp) )); 
 
  Label lsupp = new Label();
  lsupp.setUIID("NewsTopLine");
  Style supprimerstyle= new Style(lsupp.getUnselectedStyle());
  supprimerstyle.setFgColor(0xf21f1f);
        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerstyle);
        lsupp.setIcon(supprimerImage);
  lsupp.setTextPosition(RIGHT);
  lsupp.addPointerPressedListener( l-> {
  Dialog dig = new Dialog("suppression");
  if (dig.show("Suppression", "vous voilez  supprimer ce produit?", "Annuler" , "Oui") ) {
      dig.dispose(); 
  }
          else{ 
                  dig.dispose();
                  if (ServiceProduit.getInstance().deleteProduit(rec.getId())){
                      new ListProduit(res).show();
                  }
  }
  });
  
  
  
    cnt.add(BorderLayout.CENTER, BoxLayout.encloseY( 
             BoxLayout.encloseX(NameTxt,lsupp)));

  
 
            add(cnt);
            }}