/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;


/**
 *
 * @author Raed
 */
public class Client {
    

    private int id;
    private String nom,prenom,adresse,mail;
    private int id_abonnement;
    private String mdp_client;



    public Client(int id, String nom, String prenom, String adresse, String mail,String mdp_client, int id_abonnement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mail = mail;
        this.mdp_client = mdp_client;
        this.id_abonnement = id_abonnement;
    }


    public Client() {
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp_client() {
        return this.mdp_client;
    }

    public void setMdp_client(String mdp_client) {
        this.mdp_client = mdp_client;
    }

    public int getId_abonnement() {
        return this.id_abonnement;
    }

    public void setId_abonnement(int id_abonnement) {
        this.id_abonnement = id_abonnement;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", mdp_client='" + getMdp_client() + "'" +
            ", mail='" + getMail() + "'" +
            ", id_abonnement='" + getId_abonnement() + "'" +
            "}";
    }
    
boolean authentifie;
    public void setAuthentifie(boolean b) {
       this.authentifie=b;
    }
    public boolean getAuthentifie(){
        return this.authentifie;
    }


    
}
