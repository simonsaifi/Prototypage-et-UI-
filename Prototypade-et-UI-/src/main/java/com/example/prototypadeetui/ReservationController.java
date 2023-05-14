package com.example.prototypadeetui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ReservationController {

    @FXML
    private Label user;

    @FXML
    private VBox vbox;
    private Utilisateur utilisateur;
    private BDDUtilisateur bddUtilisateur;
    private BDDReservation bddReservation;
    private  BDDSejour bddSejour;
    private ArrayList<Reservation> reservations;
    private ArrayList<Sejour> sejours;

    private Parent root;
    private Scene scene;
    private Stage stage;

    public ReservationController() {
        bddUtilisateur=new BDDUtilisateur();
        bddReservation=new BDDReservation();
        bddSejour=new BDDSejour();
        reservations=new ArrayList<Reservation>();
        sejours=new ArrayList<Sejour>();
    }
    public void setUtilisateur(String utilisateur) {
        this.utilisateur = bddUtilisateur.getutilisateur(utilisateur);
        user.setText(this.utilisateur.getLogin());
        if(this.utilisateur.getPro()==false){
            setReservationUti();
        }
        else {
            setReservationsPro();
        }


    }
    public void setReservationsPro() {
        sejours = bddSejour.getsejourPro(utilisateur.getId());
        ArrayList<ReservationSejour> rs = new ArrayList<ReservationSejour>();
        for (int i = 0; i < sejours.size(); i++) {
            reservations = bddReservation.getReservationsbySejour(sejours.get(i).getId());
            if (reservations.size() > 0) {
                for (int j = 0; j < reservations.size(); j++) {
                    ReservationSejour rso = new ReservationSejour(reservations.get(j).getId(), sejours.get(i).getVile(), sejours.get(i).getPays(), sejours.get(i).getPrix(), sejours.get(i).getTypeL(), sejours.get(i).getDebut(), sejours.get(i).getFin(), sejours.get(i).getProprietaire(), " ");
                    rso.setStatus(reservations.get(j).isEncour(), reservations.get(j).isAnuler(), reservations.get(j).isValider());
                    rs.add(rso);
                }
            } else {
                ReservationSejour rso = new ReservationSejour(sejours.get(i).getId(), sejours.get(i).getVile(), sejours.get(i).getPays(), sejours.get(i).getPrix(), sejours.get(i).getTypeL(), sejours.get(i).getDebut(), sejours.get(i).getFin(), sejours.get(i).getProprietaire(), "pas de reservation");
                rs.add(rso);
            }

        }
        setaffichage(rs);
    }
    public void setaffichage(ArrayList<ReservationSejour> rs){
        Node[] nodes=new Node[rs.size()];
        for (int i=0;i<nodes.length;i++){
            try {
                FXMLLoader fmx=new FXMLLoader(getClass().getResource("itemReservation.fxml"));
                nodes[i]= fmx.load();
                ItemReservationController itemController=fmx.getController();
                itemController.setReservationSejour(rs.get(i),utilisateur);
                vbox.getChildren().add(nodes[i]);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void setReservationUti(){
        reservations=bddReservation.getReservationsUtilisateur(utilisateur.getId());
        System.out.println(reservations);
        ArrayList<ReservationSejour> rs=new ArrayList<ReservationSejour>();
        for(int i=0;i< reservations.size();i++){
            Sejour sejour=bddSejour.getSejour(reservations.get(i).getIdSejour());
            ReservationSejour rso=new ReservationSejour(reservations.get(i).getId(), sejour.getVile(),sejour.getPays(),sejour.getPrix(),sejour.getTypeL(),sejour.getDebut(),sejour.getFin(), sejour.getProprietaire(),"");
            rso.setStatus(reservations.get(i).isEncour(),reservations.get(i).isAnuler(),reservations.get(i).isValider());
            rs.add(rso);
        }
        setaffichage(rs);



    }

    @FXML
    void backHome(MouseEvent event) {
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
