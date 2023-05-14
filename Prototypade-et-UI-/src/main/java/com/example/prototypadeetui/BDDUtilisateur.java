package com.example.prototypadeetui;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class BDDUtilisateur {
    private String fEnregistrement="C:\\Users\\pedro\\IdeaProjects\\Prototypage-et-UI-\\Prototypade-et-UI-\\src\\bdd\\utilisateur.txt";
    private ArrayList<Utilisateur> utilisateurs;

    public BDDUtilisateur( ) {
        this.utilisateurs = new ArrayList<Utilisateur>();
        InitialiseUtilisateur();

    }
    public void InitialiseUtilisateur(){

        try {


            FileInputStream file = new FileInputStream(fEnregistrement);

            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
            {
                String data[]=scanner.nextLine().split(",");
                if (!data[0].equals("")){
                    Utilisateur utilisateur=new Utilisateur(Integer.parseInt(data[0]),data[1],data[2],data[3],data[4],Boolean.parseBoolean(data[5]));
                    utilisateurs.add(utilisateur);
                }

            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Utilisateur> getUtilisateurs(){
        return utilisateurs;
    }
    public Utilisateur getutilisateur(int id){
        for (int i=0;i<utilisateurs.size();i++){
            if (utilisateurs.get(i).getId()==id){
                return utilisateurs.get(i);
            }
        }
        return null;
    }
    public Utilisateur getutilisateur(String login){
        for (int i=0;i<utilisateurs.size();i++){
            if (utilisateurs.get(i).getLogin().equals(login)){
                return utilisateurs.get(i);
            }
        }
        return null;
    }
    public void enregistementUtilisateur(Utilisateur utilisateur){
        try {
           //String path =  new File("src/bdd/utilisateur.txt").getAbsolutePath();
            File file = new File(fEnregistrement);
            //File file = new File("C:/Users/pedro/IdeaProjects/Prototypage-et-UI-/Prototypade-et-UI-/src/bdd/utilisateur.txt");
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write('\n'+utilisateur.toString());
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUtilisateur(String nom ,String prenom,String login ,String pass,Boolean pro){
        Utilisateur utilisateur =new Utilisateur(utilisateurs.size(),nom,prenom,login,pass,pro);
        utilisateurs.add(utilisateur);
        enregistementUtilisateur(utilisateur);
    }

    public boolean CheckConnection(String login ,String pass){
        for (Utilisateur utilisateur:utilisateurs){
            if (login.equals(utilisateur.getLogin())&& pass.equals(utilisateur.getPass())) {
                return true;
            }
        }
        return false;

    }
}
