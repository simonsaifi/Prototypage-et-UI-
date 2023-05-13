package com.example.prototypadeetui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemController  {

    @FXML
    private Label dateD;

    @FXML
    private Label dateF;

    @FXML
    private Label pays;

    @FXML
    private Label prix;

    @FXML
    private Label type;

    @FXML
    private Label ville;

    private Sejour sejour;

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
        System.out.println("item "+utilisateur );
    }

    private String utilisateur;

    public ItemController() {
        utilisateur="";

    }


    public void setSejour(Sejour sejour){
        this.sejour=sejour;


        setlabel();
    }
    public void setlabel() {
        ville.setText(sejour.getVile());
        type.setText(sejour.getTypeL());
        prix.setText(String.valueOf(sejour.getPrix())+" €");
        pays.setText(sejour.getPays());
        dateD.setText(String.valueOf(sejour.getDebut()));
        dateF.setText(String.valueOf(sejour.getFin()));
    }

    @FXML
    void reserver(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("detailView.fxml"));
        Parent root=loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        DetailController controller=loader.getController();
        controller.setSejour(sejour,utilisateur);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



}