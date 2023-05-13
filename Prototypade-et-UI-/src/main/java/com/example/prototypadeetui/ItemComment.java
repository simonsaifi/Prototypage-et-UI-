package com.example.prototypadeetui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ItemComment {
    @FXML
    private Label comment;

    @FXML
    private Label date;

    @FXML
    private Label note;

    @FXML
    private Label pseudo;
    private  Commentaire commentaire;
    private BDDUtilisateur bddUtilisateur;

    public ItemComment() {
        bddUtilisateur=new BDDUtilisateur();
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
        setlabel();
    }

    public void setlabel(){
        Utilisateur uti=bddUtilisateur.getutilisateur(commentaire.getIdSejour());
        date.setText(String.valueOf(commentaire.getDate()));
        note.setText(String.valueOf(commentaire.getNote()));
        comment.setText(commentaire.getMessage());
        pseudo.setText(uti.getLogin());
    }
}
