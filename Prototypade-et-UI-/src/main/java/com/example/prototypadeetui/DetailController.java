package com.example.prototypadeetui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailController implements Initializable {

    @FXML
    private ScrollPane com;

    @FXML
    private Label dated;

    @FXML
    private Label datef;

    @FXML
    private TextField newcom;

    @FXML
    private ChoiceBox<Integer> note;

    @FXML
    private Label pays;

    @FXML
    private Label prix;

    @FXML
    private Label proprietaire;

    @FXML
    private Label type;

    @FXML
    private Label ville;
    private Utilisateur utilisateur;
    private Sejour sejour;


    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setSejour(Sejour sejour) {
        this.sejour = sejour;
    }

    @FXML
    void addcom(ActionEvent event) {

    }
    @FXML
    void addpanier(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //prix.setText(String.valueOf(sejour.getPrix()));
    }
}
