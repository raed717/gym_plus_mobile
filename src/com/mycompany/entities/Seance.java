/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;
import java.util.Date;

/**
 *
 * @author saisi
 */
public class Seance {
     private int id_seance;
    private String type_seance;
    private String date_debut, date_fin;
    private int id_coach;

    public Seance(int id_seance, String type_seance, String date_debut, String date_fin, int id_coach) {
        this.id_seance = id_seance;
        this.type_seance = type_seance;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_coach = id_coach;
    }

    public Seance(String type_seance, String date_debut, String date_fin, int id_coach) {
        this.type_seance = type_seance;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_coach = id_coach;
    }

    public int getId_seance() {
        return id_seance;
    }

    public String getType_seance() {
        return type_seance;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public int getId_coach() {
        return id_coach;
    }

    public void setId_seance(int id_seance) {
        this.id_seance = id_seance;
    }

    public void setType_seance(String type_seance) {
        this.type_seance = type_seance;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    @Override
    public String toString() {
        return "Seance{" + "id_seance=" + id_seance + ", type_seance=" + type_seance + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", id_coach=" + id_coach + '}';
    }

    
}
