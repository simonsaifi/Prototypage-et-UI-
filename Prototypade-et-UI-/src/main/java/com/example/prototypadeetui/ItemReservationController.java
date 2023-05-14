package com.example.prototypadeetui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ItemReservationController {

    @FXML
    private Button buttonConfirmer;

    @FXML
    private Button buttonRefuser;

    @FXML
    private Label dateD;

    @FXML
    private Label dateF;

    @FXML
    private Label pays;

    @FXML
    private Label prix;

    @FXML
    private Label proprietaire;

    @FXML
    private Label status;

    @FXML
    private Label type;

    @FXML
    private Label ville;
    private Utilisateur utilisateur;
    private ReservationSejour reservationSejour;
    private BDDReservation bddReservation;
    private BDDSejour bddSejour;

    public ItemReservationController() {
        bddReservation=new BDDReservation();
        bddSejour=new BDDSejour();
    }

    public  void setReservationSejour(ReservationSejour rs, Utilisateur utilisateur){
        this.utilisateur=utilisateur;
        if (utilisateur.getPro()==false  || rs.getStatus().equals("pas de reservation")){
            buttonConfirmer.setVisible(false);
            buttonRefuser.setVisible(false);
        }
        reservationSejour=rs;
        dateD.setText(String.valueOf(rs.getDebut()));
        dateF.setText(String.valueOf(rs.getFin()));
        pays.setText(rs.getPays());
        prix.setText(String.valueOf(rs.getPrix()));
        proprietaire.setText(rs.getProprietaire());
        status.setText(rs.getStatus());
        type.setText(rs.getTypeL());
        ville.setText(rs.getVile());
    }

    @FXML
    void confirmer(ActionEvent event) {
        bddReservation.modifier(reservationSejour.getId(), true,false,false);
        reservationSejour.setStatus(false,false,true);
        status.setText(reservationSejour.getStatus());

    }

    @FXML
    void refuser(ActionEvent event) {
        bddReservation.modifier(reservationSejour.getId(), false,true,false);
        reservationSejour.setStatus(false,true,false);
        status.setText(reservationSejour.getStatus());

    }

}
