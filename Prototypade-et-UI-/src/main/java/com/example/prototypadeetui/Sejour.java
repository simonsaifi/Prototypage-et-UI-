package com.example.prototypadeetui;

import java.util.Date;

public class Sejour {
    private int id;
    private String vile;
    private String pays;
    private int prix;
    private String typeL;
    private Date debut;
    private Date fin;
    private String proprietaire;
    private Boolean status;

    public Sejour(int id ,String vile, String pays, int prix, String typeL, Date debut, Date fin, String proprietaire, Boolean status) {
        this.id=id;
        this.vile = vile;
        this.pays = pays;
        this.prix = prix;
        this.typeL = typeL;
        this.debut = debut;
        this.fin = fin;
        this.proprietaire = proprietaire;
        this.status = status;
    }

    public String getVile() {
        return vile;
    }

    public String getPays() {
        return pays;
    }

    public int getPrix() {
        return prix;
    }

    public String getTypeL() {
        return typeL;
    }

    public Date getDebut() {
        return debut;
    }

    public Date getFin() {
        return fin;
    }
    public int getId(){return id;}
    public String getProprietaire() {
        return proprietaire;
    }

    public Boolean getStatus() {
        return status;
    }
    public void setVile(String vile) {
        this.vile = vile;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setTypeL(String typeL) {
        this.typeL = typeL;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id+","+vile+","+pays+","+prix+","+typeL+","+debut+","+fin+","+proprietaire+","+status;
    }
}
