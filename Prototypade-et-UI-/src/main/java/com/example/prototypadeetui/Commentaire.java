package com.example.prototypadeetui;

import java.time.LocalDate;
import java.util.Date;

public class Commentaire {
    private int id;
    private int idSejour;
    private int idutilisateur;
    private String message;
    private int note;
    private LocalDate date;

    public Commentaire(int id, int idSejour, int idutilisateur, String message, int note, LocalDate date) {
        this.id = id;
        this.idSejour = idSejour;
        this.idutilisateur = idutilisateur;
        this.message = message;
        this.note = note;
        this.date=date;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return id+","+idSejour+","+","+idutilisateur+","+message+","+note+","+date;
    }
}
