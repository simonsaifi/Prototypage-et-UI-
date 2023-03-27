package com.example.prototypadeetui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InscriptionController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField login;

    @FXML
    private TextField nom;

    @FXML
    private TextField pass;

    @FXML
    private TextField prenon;

    @FXML
    void connexion(ActionEvent event) throws IOException {
        root= FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void incription(ActionEvent event) {

    }

}
