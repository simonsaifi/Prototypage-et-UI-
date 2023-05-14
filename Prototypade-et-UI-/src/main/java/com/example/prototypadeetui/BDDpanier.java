package com.example.prototypadeetui;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BDDpanier {
    private String fEnregistrement="C:\\Users\\pedro\\IdeaProjects\\Prototypage-et-UI-\\Prototypade-et-UI-\\src\\bdd\\panier.txt";
    private ArrayList<Panier> paniers;

    public BDDpanier( ) {

        InitialisePanier();

    }
    public void InitialisePanier(){

        try {
            this.paniers = new ArrayList<Panier>();

            FileInputStream file = new FileInputStream(fEnregistrement);

            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
            {
                String data[]=scanner.nextLine().split(",");
                if (!data[0].equals("")){
                    Panier panier=new Panier(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]));
                    paniers.add(panier);
                }

            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Panier> getPaniers(){
        return paniers;
    }
    public ArrayList<Panier> getPaniers(int iduti){
        return (ArrayList<Panier>) paniers.stream().filter(panier -> panier.getIdUtilisateur()==iduti).collect(Collectors.toList());
    }


    public void enregistementPanier(Panier panier){
        try {
            //String path =  new File("src/bdd/utilisateur.txt").getAbsolutePath();
            File file = new File(fEnregistrement);
            //File file = new File("C:/Users/pedro/IdeaProjects/Prototypage-et-UI-/Prototypade-et-UI-/src/bdd/utilisateur.txt");
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write('\n'+panier.toString());
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPanier(int idUti,int idsejour ){
        Panier panier =new Panier(paniers.size(),idUti,idsejour);
        paniers.add(panier);
        enregistementPanier(panier);
    }
    public Panier getPanier(int iduti,int idSejour){
        for (int i=0;i<paniers.size();i++){
            if (paniers.get(i).getIdUtilisateur()==iduti && paniers.get(i).getIdSejour()==idSejour){
                return paniers.get(i);
            }
        }
        return null;
    }
    public void suprimerPanier(int id) throws IOException {

        File inputFile = new File(fEnregistrement);
        File tempFile = new File("my.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));


        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.matches("^"+id+".*")) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }


}


