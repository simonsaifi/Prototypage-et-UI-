package com.example.prototypadeetui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button log;

    @FXML
    private TextField login;

    @FXML
    private PasswordField pass;

    @FXML
    private Button register;

    @FXML
    void login(ActionEvent event) {
        BDDUtilisateur bdd=new BDDUtilisateur();
        if (!login.getText().isEmpty()&&!pass.getText().isEmpty()){
            boolean connect= bdd.CheckConnection(login.getText(),pass.getText());
            if (connect){
                System.out.println("bienvenue "+login.getText());
            }
            else {
                System.out.println("une erreur est survenue");
            }
        }
        else{
            System.out.println("veuillez  remplir tous les champ");
        }
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        root= FXMLLoader.load(getClass().getResource("incription.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
    }

}
