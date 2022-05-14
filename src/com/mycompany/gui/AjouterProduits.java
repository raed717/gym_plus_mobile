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
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
   import com.codename1.ui.Toolbar;
 import com.codename1.ui.util.Resources;
import com.mycompany.entities.Produit;
 import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.services.ServiceProduit;
import javafx.scene.control.Tab;
   
/**
 *
 * @author firas
 */
public class AjouterProduits extends BaseForm {
    Form current; 
        Resources theme;

        public AjouterProduits(Resources res) {

            super("Newsfeed", BoxLayout.y());
  
            
        Toolbar tb = new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        
                setTitle("Ajouter Produits");
                getContentPane().setScrollVisible(false);
                
                tb.addSearchCommand(e-> {
                
                
                        });
                Tabs swipe = new Tabs();
                Label s1= new Label();
                Label s2 = new Label();
                 
                addTab(swipe,s1,res.getImage("back-logo.png"), "","",res);
                
                
                
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
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Afficher", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
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
               
                
                
                
                
               
        TextField description = new TextField("",  "entrer description!!");
               description.setUIID("TexttFieldBlack");
                                        addStringValue("description", description);

           
               TextField name = new TextField("",  "entrer nom!!");
                              name.setUIID("TexttFieldBlack");
                addStringValue("name", name);
                          
               TextField  image = new TextField("",  "entrer image!!");
             image.setUIID("TexttFieldBlack");
                addStringValue("image", image);
                
                      TextField  idCategorie = new TextField("",  "entrer id!!");
             idCategorie.setUIID("TexttFieldBlack");
                addStringValue("idCategorie", idCategorie);     
            
               
                  TextField price = new TextField("", "entrer prix!!");
                                                                price.setUIID("TexttFieldBlack");
                                                                  addStringValue("price", price);
                       
                    
                      
                Button save= new Button("Ajouter");
                                addStringValue("", save);
                save.addActionListener((e) -> {
                    try{
                        
                        if (description.getText() =="" ||name.getText()==""||price.getText()=="" ||image.getText()==""){
        Dialog.show("veuillez verifier les données ", "","OK", "Cancel");
                        
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

                            
                            System.out.println("produits=+" +p);
                            
                            ServiceProduit.getInstance().AddProduit(p);
   iDialog.dispose();
   new ListProduit(res).show();
   
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

    private void addTab(Tabs swipe, Label spacer ,Image image, String string, String text, Resources res) {
 int size = Math.min(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight());
 
        ScaleImageLabel imageScale= new ScaleImageLabel(image); 
        imageScale.setUIID("container");
imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
Label overLay= new Label("", "ImageOverLay"); 

Container page1 = 
         LayeredLayout.encloseIn(imageScale,
                 
        overLay, 
        BorderLayout. south(
        BoxLayout.encloseY(
                new SpanLabel( text, "LargeWhiteText"),
                                 spacer
                                 
                   
                )
        )
                
        
                
         ); 
swipe.addTab("", res.getImage("back-logo.png") , page1);

    }
    public void bindButtonSelection(Button btn , Label l) {
    
    btn.addActionListener( e->{
        
        if (btn.isSelected()){ 
            updateArrowPosition(btn,l) ; 
        }
        
        
    });
            }

    private void updateArrowPosition(Button btn, Label l) {
        
         l.getParent().repaint(); 

     }
    
    
        }
