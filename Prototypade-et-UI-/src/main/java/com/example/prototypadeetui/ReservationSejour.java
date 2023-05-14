package com.example.prototypadeetui;

import java.time.LocalDate;

public class ReservationSejour {
    private int id;
    private String vile;
    private String pays;
    private int prix;
    private String typeL;
    private LocalDate debut;
    private LocalDate fin;
    private String proprietaire;
    private String  status;

    public ReservationSejour(int id,String vile, String pays, int prix, String typeL, LocalDate debut, LocalDate fin, String proprietaire, String status) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVile() {
        return vile;
    }

    public void setVile(String vile) {
        this.vile = vile;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getTypeL() {
        return typeL;
    }

    public void setTypeL(String typeL) {
        this.typeL = typeL;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(boolean encours,boolean annuler,boolean valider) {
        if (encours==true){
            status=" en attente de réponse";
        }
        if (annuler==true){
            status="reservation annuler";
        }
        if (valider==true){
            status="reservation confirmer";
        }
    }
}
