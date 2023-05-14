package com.example.prototypadeetui;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BDDReservation {
    private String fEnregistrement="C:\\Users\\pedro\\IdeaProjects\\Prototypage-et-UI-\\Prototypade-et-UI-\\src\\bdd\\reservation.txt";
    private ArrayList<Reservation> reservations;
    private BDDSejour bddSejour;

    public BDDReservation( ) {
        InitialiseReservation();
        bddSejour=new BDDSejour();

    }
    public void InitialiseReservation(){

        try {
            reservations= new ArrayList<Reservation>();
            FileInputStream file = new FileInputStream(fEnregistrement);

            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
            {
                String data[]=scanner.nextLine().split(",");
                Reservation reservation=new Reservation(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]),Boolean.parseBoolean(data[3]),Boolean.parseBoolean(data[4]),Boolean.parseBoolean(data[5]));
                reservations.add(reservation);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Reservation> getReservation(){
        return reservations;
    }
    public void enregistementReservation(Reservation reservation){
        try {
            //String path =  new File("src/bdd/utilisateur.txt").getAbsolutePath();
            File file = new File(fEnregistrement);
            //File file = new File("C:/Users/pedro/IdeaProjects/Prototypage-et-UI-/Prototypade-et-UI-/src/bdd/utilisateur.txt");
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write('\n'+reservation.toString());
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Reservation> getReservationsUtilisateur(int idUtilisateurs){
        return (ArrayList<Reservation>) reservations.stream().filter(reservation -> reservation.getIdutilisateur()==idUtilisateurs).collect(Collectors.toList());
    }
    public ArrayList<Reservation> getReservationsbySejour(int sejour){
        return (ArrayList<Reservation>) reservations.stream().filter(reservation ->reservation.getIdSejour()==sejour).collect(Collectors.toList());
    }
    public void setvalid(int id ,boolean valid){
        ArrayList<Reservation> Lreservation= (ArrayList<Reservation>) reservations.stream().filter(reservation -> reservation.getId()==id).collect(Collectors.toList());
        Lreservation.get(0).setValider(valid);
    }
    public void setannuler(int id ,boolean annuler){
        ArrayList<Reservation> Lreservation= (ArrayList<Reservation>) reservations.stream().filter(reservation -> reservation.getId()==id).collect(Collectors.toList());
        Lreservation.get(0).setAnuler(annuler);
    }public void setencours(int id ,boolean encour){
        ArrayList<Reservation> Lreservation= (ArrayList<Reservation>) reservations.stream().filter(reservation -> reservation.getId()==id).collect(Collectors.toList());
        Lreservation.get(0).setEncour(encour);
    }

    public void addReservation(int idSejour, int idutilisateur, boolean valider, boolean anuler, boolean encour){
        Reservation reservation=new Reservation(reservations.size(),idSejour,idutilisateur,valider,anuler,encour);
        reservations.add(reservation);
        enregistementReservation(reservation);

    }
    public void modifier(int id,boolean valid,boolean annuler,boolean encours){
        setannuler(id,annuler);
        setencours(id,encours);
        setvalid(id,valid);
        ArrayList<Reservation> Lreservation= (ArrayList<Reservation>) reservations.stream().filter(reservation -> reservation.getId()==id).collect(Collectors.toList());
        try {
            // Lire le contenu du fichier dans une liste de chaînes de caractères
            List<String> lines = Files.readAllLines(Path.of(fEnregistrement));

            // Vérifier si la ligne à modifier existe
            if (id >= 1 && id <= lines.size()) {
                // Modifier la ligne spécifiée avec le nouveau contenu
                lines.set(id - 1, Lreservation.get(0).toString());

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
