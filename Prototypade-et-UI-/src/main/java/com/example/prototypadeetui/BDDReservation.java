package com.example.prototypadeetui;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public ArrayList<Reservation> getReservationsProprietaire(int idproprietaire){
        return (ArrayList<Reservation>) reservations.stream().filter(reservation -> bddSejour.getidPro(reservation.getIdSejour())==idproprietaire).collect(Collectors.toList());
    }
    public void setvalid(int id ,Boolean valid){
        ArrayList<Reservation> Lreservation= (ArrayList<Reservation>) reservations.stream().filter(reservation -> reservation.getId()==id).collect(Collectors.toList());
        Lreservation.get(0).setValider(valid);
    }
    public void setannuler(int id ,Boolean annuler){
        ArrayList<Reservation> Lreservation= (ArrayList<Reservation>) reservations.stream().filter(reservation -> reservation.getId()==id).collect(Collectors.toList());
        Lreservation.get(0).setAnuler(annuler);
    }public void setencours(int id ,Boolean encour){
        ArrayList<Reservation> Lreservation= (ArrayList<Reservation>) reservations.stream().filter(reservation -> reservation.getId()==id).collect(Collectors.toList());
        Lreservation.get(0).setEncour(encour);
    }

    public void addReservation(int idSejour, int idutilisateur, boolean valider, boolean anuler, boolean encour){
        Reservation reservation=new Reservation(reservations.size(),idSejour,idutilisateur,valider,anuler,encour);
        reservations.add(reservation);
        enregistementReservation(reservation);

    }

}
