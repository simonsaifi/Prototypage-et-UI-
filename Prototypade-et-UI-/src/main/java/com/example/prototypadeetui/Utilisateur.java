package com.example.prototypadeetui;

public class Utilisateur {
    private String nom;
    private String prenom;
    private String login;
    private String pass;

    public Utilisateur(String nom, String prenom, String login, String pass) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.pass = pass;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return nom+","+prenom+","+login+","+pass;
    }
}
