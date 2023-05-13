package com.example.prototypadeetui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainpageController implements Initializable {

    @FXML
    private TextField search;
    private Stage stage;
    private Scene scene;
    @FXML
    private AnchorPane searchIcon;

    @FXML
    private VBox vbox=null;

    @FXML
    private ListView<reservations> listview;
    @FXML
    private ChoiceBox<String> filtre;
    @FXML
    private Label Lconnection;


    private String  utilisateur;
    private Parent root;
    private BDDSejour bddSejour;
    private ArrayList<Sejour> sejours;
    private  ArrayList<Sejour> sejoursToShow;

    private ArrayList<ItemController> controllers;

    private String [] choicefiltre={"ville","pays","prix superieur","prix inferieur","type de logement"};
    private int nbNodes;
    Node[] nodes;
    public String getUtilisateur() {
        return utilisateur;
    }
    public MainpageController(){
        utilisateur="";
        sejours=new ArrayList<Sejour>();
        sejoursToShow=new ArrayList<Sejour>();
        controllers=new ArrayList<ItemController>();

    }
    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
        Lconnection.setText(utilisateur);
        for (int i=0;i<controllers.size();i++){
            controllers.get(i).setUtilisateur(utilisateur);
        }

        System.out.println(utilisateur);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bddSejour = new BDDSejour();
        sejours = bddSejour.getSejours();
        aficherliste(sejours);
        filtre.getItems().addAll(choicefiltre);
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date datedebut;
        Date datefin;
        try {
            datedebut = new Date(sdf.parse("2023-08-25").getTime());

            datefin = new Date(sdf.parse("2023-08-31").getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        bddSejour.addSejour("porto", "portugal", "villa",150, datedebut, datefin,"cylia",false);*/

    }
    public void aficherliste(ArrayList<Sejour> as){
        nodes=new Node[as.size()];
        for (int i=0;i<nodes.length;i++){
            try {
                FXMLLoader fmx=new FXMLLoader(getClass().getResource("item.fxml"));
                nodes[i]= fmx.load();
                ItemController itemController=fmx.getController();
                itemController.setSejour(as.get(i));
                controllers.add(itemController);
                vbox.getChildren().add(nodes[i]);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        nbNodes=as.size();
    }
    @FXML
    void connection(MouseEvent event) throws IOException {
            if (utilisateur!=null){
                utilisateur=null;
                Lconnection.setText("connection");
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                root=loader.load();
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }


    }

    @FXML
    void onChangeSearch(KeyEvent event) {
       String input=search.getText();
       String typeFilter=filtre.getValue();
       switch (typeFilter){
           case "ville":
               sejoursToShow= (ArrayList<Sejour>) sejours.stream().filter(sejour->sejour.getVile().matches("^"+input+".*")).collect(Collectors.toList());
               break;
           case "pays":
               sejoursToShow= (ArrayList<Sejour>) sejours.stream().filter(sejour->sejour.getPays().matches("^"+input+".*")).collect(Collectors.toList());
               break;
           case "type de logement":
               sejoursToShow= (ArrayList<Sejour>) sejours.stream().filter(sejour->sejour.getTypeL().matches("^"+input+".*")).collect(Collectors.toList());
               break;
           case "prix superieur":
               sejoursToShow= (ArrayList<Sejour>) sejours.stream().filter(sejour->sejour.getPrix()>Integer.parseInt(input)).collect(Collectors.toList());
               break;
           case "prix inferieur":
               sejoursToShow= (ArrayList<Sejour>) sejours.stream().filter(sejour->sejour.getPrix()<Integer.parseInt(input)).collect(Collectors.toList());
               break;
       }
       //sejoursToShow= (ArrayList<Sejour>) sejours.stream().filter(sejour->sejour.getVile().matches("^"+input+".*")).collect(Collectors.toList());
       for(int i=0;i<nbNodes;i++){
           vbox.getChildren().remove(0);
       }
       aficherliste(sejoursToShow);

    }



    @FXML
    void search(MouseEvent event) {
        System.out.println(search.getText());
    }

    public class  reservations {
        String ville;
        String date;
        String prix;
        Button suprimer;

        public reservations(String ville, String date, String prix) {
            this.ville = ville;
            this.date = date;
            this.prix = prix;
            this.suprimer = new Button();
        }

        @Override
        public String toString() {
            return ville +"  "+date+"  "+prix;
        }
    }

}
