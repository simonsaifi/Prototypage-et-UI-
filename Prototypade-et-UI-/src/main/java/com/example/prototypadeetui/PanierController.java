package com.example.prototypadeetui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class PanierController {

    @FXML
    private Label user;

    @FXML
    private VBox vbox;
    private BDDUtilisateur bddUtilisateur;
    private BDDpanier bddPanier;
    private BDDSejour bddSejour;
    private BDDReservation bddReservation;
    private Utilisateur utilisateur;
    private ArrayList<Sejour> sejours;
    private ArrayList<Panier>panier;
    private Parent root;
    private Scene scene;
    private Stage stage;
    Node[] nodes;

    public PanierController() {
        bddPanier=new BDDpanier();
        bddUtilisateur=new BDDUtilisateur();
        panier=new ArrayList<Panier>();
        bddSejour=new BDDSejour();
        sejours=new ArrayList<Sejour>();
        bddReservation=new BDDReservation();
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = bddUtilisateur.getutilisateur(utilisateur);
        user.setText(this.utilisateur.getLogin());
        setPanier();


    }
    public void setPanier(){
        panier=bddPanier.getPaniers(this.utilisateur.getId());
        for(int i=0;i<panier.size();i++){
            sejours.add(bddSejour.getSejour(panier.get(i).getIdSejour()));
        }
        aficherliste(sejours);
    }
    public void aficherliste(ArrayList<Sejour> as){
        nodes=new Node[as.size()];
        for (int i=0;i<nodes.length;i++){
            try {
                FXMLLoader fmx=new FXMLLoader(getClass().getResource("itemPanier.fxml"));
                nodes[i]= fmx.load();
                ItemPanierController itemController=fmx.getController();
                itemController.setSejour(as.get(i),bddPanier.getPanier(utilisateur.getId(),as.get(i).getId()),utilisateur);
                vbox.getChildren().add(nodes[i]);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    @FXML
    void backhome(MouseEvent event) {
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
    }

    @FXML
    void validerPanier(MouseEvent event) {
        for(int i=0;i<panier.size();i++){
            bddReservation.addReservation(panier.get(i).getIdSejour(),utilisateur.getId(),false,false,true);
        }
        Stage popupStage = new Stage();

        popupStage.initOwner(this.stage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initStyle(StageStyle.UTILITY);
        popupStage.setTitle("Popup Window");

        Label label = new Label("votre reservation est prise en compte");
        VBox vbox = new VBox(label);
        Scene scene = new Scene(vbox, 200, 100);
        popupStage.setScene(scene);

        popupStage.showAndWait();
        for(int i=0;i<panier.size();i++){
            try {
                bddPanier.suprimerPanier(panier.get(i).getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
    }



}
