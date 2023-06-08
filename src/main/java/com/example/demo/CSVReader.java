package com.example.demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Klasse um die einzelnen Einträge in eine ArrayList einzuspeichern
 */

public class CSVReader {

    private final ArrayList<Eintrag> csvimport = new ArrayList<>();

    public ArrayList<Eintrag> readcsv(String csvfile){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(csvfile));
            reader.readLine();
            //wir nehmen ready() weil so überprüft werden kann ob noch DAten im Strom sind, wenn ja, liest er weiter, wenn nicht, geht er aus while raus
            while(reader.ready()){
                String zeile = reader.readLine();
                String[] woerter;
                woerter = zeile.split(";"); //der gute alter REGEX Kack

                String name = woerter[0];
                String strasse = woerter[1];
                String plz = woerter[2];
                String ort = woerter[3];
                String gueterverkehr = woerter[4];
                String personenverkehr = woerter[5];

                csvimport.add(new Eintrag(name, strasse, plz, ort, gueterverkehr, personenverkehr));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return csvimport;
    }

}
