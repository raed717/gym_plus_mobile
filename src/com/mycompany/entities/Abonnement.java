/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Objects;

/**
 *
 * @author Raed
 */
public class Abonnement {

    private int id_abonnement;
    private String nom_ab;
    private int prix_ab;
    private String description;
    

    public Abonnement() {
    }

    public Abonnement(int id_abonnement, String nom_ab, int prix_ab, String description) {
        this.id_abonnement = id_abonnement;
        this.nom_ab = nom_ab;
        this.prix_ab = prix_ab;
        this.description = description;
    }

    public Abonnement(int aInt, String string, String string0, String string1, String string2, String string3, int aInt0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_abonnement() {
        return this.id_abonnement;
    }

    public void setId_abonnement(int id_abonnement) {
        this.id_abonnement = id_abonnement;
    }

    public String getNom_ab() {
        return this.nom_ab;
    }

    public void setNom_ab(String nom_ab) {
        this.nom_ab = nom_ab;
    }

    public int getPrix_ab() {
        return this.prix_ab;
    }

    public void setPrix_ab(int prix_ab) {
        this.prix_ab = prix_ab;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Abonnement id_abonnement(int id_abonnement) {
        setId_abonnement(id_abonnement);
        return this;
    }

    public Abonnement nom_ab(String nom_ab) {
        setNom_ab(nom_ab);
        return this;
    }

    public Abonnement prix_ab(int prix_ab) {
        setPrix_ab(prix_ab);
        return this;
    }

    public Abonnement description(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Abonnement)) {
            return false;
        }
        Abonnement abonnement = (Abonnement) o;
        return id_abonnement == abonnement.id_abonnement && Objects.equals(nom_ab, abonnement.nom_ab) && prix_ab == abonnement.prix_ab && Objects.equals(description, abonnement.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_abonnement, nom_ab, prix_ab, description);
    }

    @Override
    public String toString() {
        return "{" +
            " id_abonnement='" + getId_abonnement() + "'" +
            ", nom_ab='" + getNom_ab() + "'" +
            ", prix_ab='" + getPrix_ab() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
    
    
}
