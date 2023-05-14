package com.example.prototypadeetui;

public class Reservation {
    private int id;
    private int idSejour;
    private int idutilisateur;
    private boolean valider;
    private boolean anuler;
    private boolean encour;

    public Reservation(int id, int idSejour, int idutilisateur, boolean valider, boolean anuler, boolean encour) {
        this.id = id;
        this.idSejour = idSejour;
        this.idutilisateur = idutilisateur;
        this.valider = valider;
        this.anuler = anuler;
        this.encour = encour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSejour() {
        return idSejour;
    }

    public void setIdSejour(int idSejour) {
        this.idSejour = idSejour;
    }

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public boolean isValider() {
        return valider;
    }

    public void setValider(boolean valider) {
        this.valider = valider;
    }

    public boolean isAnuler() {
        return anuler;
    }

    public void setAnuler(boolean anuler) {
        this.anuler = anuler;
    }

    public boolean isEncour() {
        return encour;
    }

    public void setEncour(boolean encour) {
        this.encour = encour;
    }

    @Override
    public String toString() {
        return id+","+idSejour+","+idutilisateur+","+valider+","+anuler+","+encour;
    }


}
