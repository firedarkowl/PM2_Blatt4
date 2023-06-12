package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasse um die einzelnen Einträger csv Datei zu repräsentieren
 */

public class Eintrag {

    private final StringProperty name;
    private final StringProperty strasse;
    private final StringProperty plz;
    private final StringProperty ort;
    private final StringProperty gueterverkehr;
    private final StringProperty personenverkehr;

    public Eintrag(String name, String adresse, String plz, String ort, String gueterverkehr, String personenverkehr){
        this.name= new SimpleStringProperty(name);
        this.strasse= new SimpleStringProperty(adresse);
        this.plz= new SimpleStringProperty(plz);
        this.ort= new SimpleStringProperty(ort);
        this.gueterverkehr= new SimpleStringProperty(gueterverkehr);
        this.personenverkehr= new SimpleStringProperty(personenverkehr);
    }

    public String getName(){
        return name.getValue();
    }
    public String getStrasse(){
        return strasse.getValue();
    }
    public String getPlz(){
        return plz.getValue();
    }
    public String getOrt(){
        return ort.getValue();
    }
    public String getGueterverkehr(){
        return gueterverkehr.getValue();
    }
    public String getPersonenverkehr(){
        return personenverkehr.getValue();
    }
}
