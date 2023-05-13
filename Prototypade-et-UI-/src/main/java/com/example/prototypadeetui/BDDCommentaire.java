package com.example.prototypadeetui;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BDDCommentaire {
    private String fEnregistrement="C:\\Users\\pedro\\IdeaProjects\\Prototypage-et-UI-\\Prototypade-et-UI-\\src\\bdd\\commentaire.txt";
    private ArrayList<Commentaire> commentaires;

    public BDDCommentaire( ) {
        this.commentaires= new ArrayList<Commentaire>();
        InitialiseCommentaire();

    }
    public void InitialiseCommentaire(){

        try {


            FileInputStream file = new FileInputStream(fEnregistrement);

            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
            {
                String data[]=scanner.nextLine().split(",");
                LocalDate localDate = LocalDate.parse(data[5]);
                Commentaire commentaire=new Commentaire(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]),data[3],Integer.parseInt(data[4]),localDate);
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
    public void enregistementComentaire(Commentaire commentaire){
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
    public ArrayList<Commentaire> getCommentaires(int sejourId){
        return (ArrayList<Commentaire>) commentaires.stream().filter(commentaire -> commentaire.getIdSejour()==sejourId).collect(Collectors.toList());
    }

    public void addCommentaire(int idSejour, int idutilisateur, String message, int note, LocalDate date){
        Commentaire commentaire=new Commentaire(commentaires.size(),idSejour,idutilisateur,message,note,date);
        commentaires.add(commentaire);
        enregistementComentaire(commentaire);
    }
}
