package com.example.prototypadeetui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class DetailController {

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
    private VBox vbox;
    @FXML
    private Label user;


    @FXML
    private Label ville;
    private Utilisateur utilisateur;
    private Sejour sejour;
    private ArrayList<Commentaire> commentaires;
    private BDDCommentaire bddCommentaire;
    private BDDUtilisateur bddUtilisateur;

    public DetailController() {
       commentaires=new ArrayList<Commentaire>();
       bddCommentaire=new BDDCommentaire();
       bddUtilisateur=new BDDUtilisateur();


    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setSejour(Sejour sejour,String user){
        this.sejour = sejour;
        commentaires=bddCommentaire.getCommentaires(sejour.getId());
        System.out.println("detail"+ user);
        utilisateur=bddUtilisateur.getutilisateur(user);
        System.out.println(utilisateur);
        try {
            setDetail();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addcom(ActionEvent event) {
        if (utilisateur!=null){
            bddCommentaire.addCommentaire(sejour.getId(),utilisateur.getId(),newcom.getText(),5, LocalDate.now());
            System.out.println("commentaire ajouter");
        }

    }

    @FXML
    void addpanier(ActionEvent event) {

    }
    void setDetail() throws IOException {
        pays.setText(sejour.getPays());
        proprietaire.setText(sejour.getProprietaire());
        datef.setText(String.valueOf(sejour.getFin()));
        dated.setText(String.valueOf(sejour.getDebut()));
        prix.setText(String.valueOf(sejour.getPrix()));
        type.setText(sejour.getTypeL());
        ville.setText(sejour.getVile());
        if (utilisateur!=null){
            user.setText(utilisateur.getLogin());
        }

        Node[] nodes=new Node[commentaires.size()];
        for (int i=0;i<commentaires.size();i++){
            System.out.println("comm");
            FXMLLoader fmx=new FXMLLoader(getClass().getResource("itemComment.fxml"));
            nodes[i]= fmx.load();
            ItemComment itemController=fmx.getController();
            itemController.setCommentaire(commentaires.get(i));


            vbox.getChildren().add(nodes[i]);

        }
    }
    @FXML
    void backHome(MouseEvent event) {

    }
}
