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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Hello!");
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