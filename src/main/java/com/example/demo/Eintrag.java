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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getStrasse() {
        return strasse.get();
    }

    public StringProperty strasseProperty() {
        return strasse;
    }

    public String getPlz() {
        return plz.get();
    }

    public StringProperty plzProperty() {
        return plz;
    }

    public String getOrt() {
        return ort.get();
    }

    public StringProperty ortProperty() {
        return ort;
    }

    public String getGueterverkehr() {
        return gueterverkehr.get();
    }

    public StringProperty gueterverkehrProperty() {
        return gueterverkehr;
    }

    public String getPersonenverkehr() {
        return personenverkehr.get();
    }

    public StringProperty personenverkehrProperty() {
        return personenverkehr;
    }
}
