package com.example.prototypadeetui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ItemPanierController {

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
    private Panier panier;
    private BDDpanier bdDpanier;
    private PanierController controller;
    private Utilisateur utilisateur;
    private Parent root;
    private Scene scene;
    private Stage stage;
    public  ItemPanierController(){
        bdDpanier=new BDDpanier();
    }
    public void setSejour(Sejour sejour,Panier panier,Utilisateur utilisateur){
        this.sejour=sejour;
        this.panier=panier;
        this.utilisateur=utilisateur;
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
    void suprimer(ActionEvent event) {
        try {
            bdDpanier.suprimerPanier(panier.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Mainpage.fxml"));
            try {
                root=loader.load();
                MainpageController controller=loader.getController();
                controller.setUtilisateur(utilisateur.getLogin());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
