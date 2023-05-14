package com.example.prototypadeetui;

public class Panier {
    private  int id;
    private  int idUtilisateur;
    private int idSejour;

    public Panier(int id, int idUtilisateur, int idSejour) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.idSejour = idSejour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdSejour() {
        return idSejour;
    }

    public void setIdSejour(int idSejour) {
        this.idSejour = idSejour;
    }

    @Override
    public String toString() {
        return id+","+idUtilisateur+","+idSejour;
    }
}
