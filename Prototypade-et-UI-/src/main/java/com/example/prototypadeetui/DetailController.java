package com.example.prototypadeetui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class DetailController  {

    @FXML
    private ScrollPane com;

    @FXML
    private Label dated;

    @FXML
    private Label datef;

    @FXML
    private TextField newcom;

    @FXML
    private ChoiceBox<String> note;
    private String [] choicenote={"0","1","2","3","4","5"};
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
    private Button butonCom;

    @FXML
    private Label ville;
    private Utilisateur utilisateur;
    private Sejour sejour;
    private ArrayList<Commentaire> commentaires;
    private BDDCommentaire bddCommentaire;
    private BDDUtilisateur bddUtilisateur;
    private Parent root;

    private Scene scene;
    private Stage stage;


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
        System.out.println(utilisateur);
        if (utilisateur!=null){
            bddCommentaire.addCommentaire(sejour.getId(),utilisateur.getId(),newcom.getText(),Integer.parseInt(note.getValue()), LocalDate.now());
            System.out.println("commentaire ajouter");
            vbox.getChildren().remove(0,commentaires.size());
            bddCommentaire.InitialiseCommentaire();
            commentaires=bddCommentaire.getCommentaires(sejour.getId());

            try {
                setDetail();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
        note.getItems().addAll(choicenote);
        Node[] nodes=new Node[commentaires.size()];
        for (int i=0;i<commentaires.size();i++){
            System.out.println("comm");
            FXMLLoader fmx=new FXMLLoader(getClass().getResource("itemComment.fxml"));
            AnchorPane root = new AnchorPane();
            fmx.setRoot(root);
            try {
                nodes[i]=fmx.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ItemComment itemController=fmx.getController();
            itemController.setCommentaire(commentaires.get(i));


            vbox.getChildren().add(nodes[i]);

        }
        if (utilisateur!=null){
            user.setText(utilisateur.getLogin());
            butonCom.setDisable(false);
        }
        else{
            butonCom.setDisable(true);
        }


        System.out.println(commentaires);
    }
    @FXML
    void backHome(MouseEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Mainpage.fxml"));
        try {
            root=loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
