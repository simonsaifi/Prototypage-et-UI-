package com.example.prototypadeetui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainpageController implements Initializable {

    @FXML
    private TextField search;

    @FXML
    private AnchorPane searchIcon;

    @FXML
    private VBox vbox;

    private String  utilisateur;

    public String getUtilisateur() {
        return utilisateur;
    }
    public MainpageController(){
        utilisateur="";

    }
    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
        search.setText(utilisateur);
        System.out.println(utilisateur);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Node node=new Node() ;

    }
}
