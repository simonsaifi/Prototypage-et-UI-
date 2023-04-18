package com.example.prototypadeetui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;


import java.net.URL;
import java.util.ResourceBundle;

public class MainpageController implements Initializable {

    @FXML
    private TextField search;

    @FXML
    private AnchorPane searchIcon;

    @FXML
    private VBox vbox;

    @FXML
    private ListView<String> listview;

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
        final int NUM_NAMES = 10000;

        ObservableList<String> names = FXCollections.observableArrayList();
        for (int i = 1; i <= NUM_NAMES; i++) {
            names.add("Location " + i);
        }
        this.listview.setItems(names);


    }

}
