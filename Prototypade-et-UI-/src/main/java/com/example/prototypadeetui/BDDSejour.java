package com.example.prototypadeetui;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BDDSejour {
    private String fEnregistrement="C:\\Users\\pedro\\IdeaProjects\\Prototypage-et-UI-\\Prototypade-et-UI-\\src\\bdd\\sejour.txt";
    private ArrayList<Sejour> sejours;
    private BDDUtilisateur bddUtilisateur;

    public BDDSejour( ) {
        this.sejours= new ArrayList<Sejour>();
        InitialiseSejour();
        bddUtilisateur=new BDDUtilisateur();

    }
    public void InitialiseSejour(){

        try {


            FileInputStream file = new FileInputStream(fEnregistrement);

            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
            {
                String data[]=scanner.nextLine().split(",");
                LocalDate datedebut=LocalDate.parse(data[5]);
                LocalDate datefin=LocalDate.parse(data[6]);

                boolean status=Boolean.parseBoolean(data[8]);
                Sejour sejour=new Sejour(Integer.parseInt(data[0]),data[1],data[2],Integer.parseInt(data[3]),data[4],datedebut,datefin,data[7],status);
                sejours.add(sejour);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Sejour> getSejours(){
        return (ArrayList<Sejour>) sejours.stream().filter(sejour -> sejour.getStatus()==false).collect(Collectors.toList());
    }
    public int getidPro(int id){
        ArrayList<Sejour> Lsejour= (ArrayList<Sejour>) sejours.stream().filter(sejour->sejour.getId()==id).collect(Collectors.toList());
        return bddUtilisateur.getutilisateur(Lsejour.get(0).getId()).getId();
    }
    public void enregistementSejour(Sejour sejour){
        try {
            //String path =  new File("src/bdd/utilisateur.txt").getAbsolutePath();
            File file = new File(fEnregistrement);
            //File file = new File("C:/Users/pedro/IdeaProjects/Prototypage-et-UI-/Prototypade-et-UI-/src/bdd/utilisateur.txt");
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write('\n'+sejour.toString());
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSejour(String vile, String pays, String typeL,int prix, LocalDate debut, LocalDate fin, String proprietaire, Boolean status){
        Sejour sejour =new Sejour(sejours.size(), vile, pays, prix,typeL, debut, fin, proprietaire, status);
        sejours.add(sejour);
        enregistementSejour(sejour);
    }

    public Sejour getSejour(int id){
        for (int i=0;i<sejours.size();i++){
            if (id==sejours.get(i).getId()){
                return sejours.get(i);
            }
        }
        return null;
    }

}
