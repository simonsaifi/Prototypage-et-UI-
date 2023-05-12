package com.example.prototypadeetui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Mainpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1600 , 800);
        stage.setTitle("Application de Reservation de voyages");

        stage.setScene(scene);
        stage.show();
        BDDUtilisateur bddUtilisateur=new BDDUtilisateur();
       /*ArrayList<Utilisateur> utilisateurs=bddUtilisateur.getUtilisateurs();
        for (Utilisateur u :utilisateurs){
            System.out.println(u.toString());
        }*/
    }

    public static void main(String[] args) {
        launch();
    }
}