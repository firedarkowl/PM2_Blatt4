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
                String s = reader.readLine();
                //TODO
                //Idee: ich trenne die eingelesene Zeile sinnvoll und speichere die Daten ab in jeweils die Felder vom Eintrag
                //die Einträge speichere ich dann in ArrayList csvimmport
                //komm ich noch nicht drauf, muss grübeln
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return csvimport;
    }

}
