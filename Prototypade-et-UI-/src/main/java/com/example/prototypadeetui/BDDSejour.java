package com.example.prototypadeetui;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public ArrayList<Sejour> getsejourPro(int idpro){
        return  (ArrayList<Sejour>) sejours.stream().filter(sejour->bddUtilisateur.getutilisateur(sejour.getProprietaire()).getId()==idpro).collect(Collectors.toList());

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
    public void modifier(int id,Boolean status){

        ArrayList<Sejour> Lsejour= (ArrayList<Sejour>) sejours.stream().filter(sejour -> sejour.getId()==id).collect(Collectors.toList());
        Lsejour.get(0).setStatus(status);
        try {
            // Lire le contenu du fichier dans une liste de chaînes de caractères
            List<String> lines = Files.readAllLines(Path.of(fEnregistrement));

            // Vérifier si la ligne à modifier existe
            if (id >= 1 && id <= lines.size()) {
                // Modifier la ligne spécifiée avec le nouveau contenu
                lines.set(id - 1,Lsejour.get(0).toString());

            } else {
                System.out.println("Ligne spécifiée à modifier introuvable.");
                return;
            }

            // Écrire le contenu modifié dans le fichier
            Files.write(Path.of(fEnregistrement), lines);
            System.out.println("Fichier modifié avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la modification du fichier : " + e.getMessage());
        }
    }

}
