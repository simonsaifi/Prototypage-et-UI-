package com.example.prototypadeetui;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class BDDCommentaire {
    private String fEnregistrement="C:\\Users\\pedro\\IdeaProjects\\Prototypage-et-UI-\\Prototypade-et-UI-\\src\\bdd\\sejour.txt";
    private ArrayList<Commentaire> commentaires;

    public BDDCommentaire( ) {
        this.commentaires= new ArrayList<Commentaire>();
        InitialiseSejour();

    }
    public void InitialiseSejour(){

        try {


            FileInputStream file = new FileInputStream(fEnregistrement);

            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
            {
                String data[]=scanner.nextLine().split(",");
                Commentaire commentaire=new Commentaire(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]),data[3],Integer.parseInt(data[5]));
                commentaires.add(commentaire);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Commentaire> getCommentaires(){
        return commentaires;
    }
    public void enregistementSejour(Commentaire commentaire){
        try {
            //String path =  new File("src/bdd/utilisateur.txt").getAbsolutePath();
            File file = new File(fEnregistrement);
            //File file = new File("C:/Users/pedro/IdeaProjects/Prototypage-et-UI-/Prototypade-et-UI-/src/bdd/utilisateur.txt");
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write('\n'+commentaire.toString());
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSejour(int id, int idSejour, int idutilisateur, String message, int note){
        Commentaire commentaire=new Commentaire(id,idSejour,idutilisateur,message,note);
        commentaires.add(commentaire);
        enregistementSejour(commentaire);
    }
}
