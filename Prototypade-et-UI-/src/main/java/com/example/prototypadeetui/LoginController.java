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
    void login(ActionEvent event) throws IOException {
        BDDUtilisateur bdd=new BDDUtilisateur();
        if (!login.getText().isEmpty()&&!pass.getText().isEmpty()){
            boolean connect= bdd.CheckConnection(login.getText(),pass.getText());
            if (connect){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Mainpage.fxml"));
                root=loader.load();
                MainpageController controller=loader.getController();
                controller.setUtilisateur(login.getText());
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene (root);

                stage.setScene(scene);
                stage.show();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("incription.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
    }

}
