package com.example.demo;

import javafx.beans.value.ChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {

    //Aktualisieren der Suche basierend auf dem ausgewähltem Suchmuster
    public ChangeListener updateSearch(TextField search, TableView tabelleUebergeben, ComboBox searchpattern, FilteredList filtered, SortedList sorted) {

        //ne Tabelle anlegen, damit wir an den ursprünglichen Daten nicht manipulieren
        TableView<Eintrag> tabelle = tabelleUebergeben;
        //hier wird später der passende Filter zugewiesen
        FilteredList<Eintrag> gefiltert = filtered;

        //hier reagiren wir auf die Veränderungen
        //mit Lambda imlementieren wir hier die changed Methode in dem ChangeListener Interface
        ChangeListener listenerSuchstrategie = (ChangeListener<String>) ((observable, oldVal, newVal) -> {

            //Suchmuster überprüfen und die entsprechenden Filter anwenden
            if (searchpattern.getValue().equals("mit Groß-/Kleinschreibung")) {
                //Filter für die gefilterte Liste setzen
                gefiltert.setPredicate(eintrag -> {
                    //wenn der neu übergebene Suchwert leer ist oder null, sollen die bisherigen Elemente in der gefilterten Liste behalten werden
                    //newVal ist der Suchkriteriumwert
                    if (newVal == null || newVal.isEmpty()) {
                        return true;
                    }
                    String sucher = newVal;
                    if (eintrag.getName().contains(sucher)) {
                        return true;
                    } else if (eintrag.getStrasse().contains(sucher)) {
                        return true;
                    } else if (eintrag.getPlz().contains(sucher)) {
                        return true;
                    } else if (eintrag.getOrt().contains(sucher)) {
                        return true;
                    } else {
                        return false;
                    }
                });
                //funktioniert analog
            } else if (searchpattern.getValue().equals("ohne Groß-/Kleinschreibung")) {
                gefiltert.setPredicate(eintrag -> {
                    if (newVal == null || newVal.isEmpty()) {
                        return true;
                    }
                    String sucher = newVal.toLowerCase();
                    if (eintrag.getName().toLowerCase().contains(sucher)) {
                        return true;
                    } else if (eintrag.getStrasse().toLowerCase().contains(sucher)) {
                        return true;
                    } else if (eintrag.getPlz().toLowerCase().contains(sucher)) {
                        return true;
                    } else if (eintrag.getOrt().toLowerCase().contains(sucher)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            } else if (searchpattern.getValue().equals("Namen")) {
                gefiltert.setPredicate(eintrag -> {
                    if (newVal == null || newVal.isEmpty()) {
                        return true;
                    }
                    String sucher = newVal.toLowerCase();
                    if (eintrag.getName().toLowerCase().contains(sucher)) {
                        return true;
                    } else {
                        return false;
                    }

                });
            } else if (searchpattern.getValue().equals("Orte")) {
                gefiltert.setPredicate(eintrag -> {
                    if (newVal == null || newVal.isEmpty()) {
                        return true;
                    }
                    String sucher = newVal.toLowerCase();
                    if (eintrag.getOrt().toLowerCase().contains(sucher)) {
                        return true;
                    } else {
                        return false;
                    }
                });

            }

        });
        return listenerSuchstrategie;
    }

    //ComboBox aktualisieren basierend auf der ausgewählten Option
    public ChangeListener updateBox(TableView tabelleUebergeben, ComboBox box, FilteredList gefiltert, SortedList sortiert) {

        TableView<Eintrag> tabelle = tabelleUebergeben;
        FilteredList<Eintrag> filtered = gefiltert;

        ChangeListener guterPers = (ChangeListener<String>) ((observable, oldVal, newVal) -> {
            if(box.getValue().equals("Ungefiltert")){
                filtered.setPredicate(eintrag->{
                    return true;
                });
            }else if(box.getValue().equals("Güterverkehr")){
                filtered.setPredicate(eintrag->{
                    if(eintrag.getGueterverkehr().equals("Ja")){
                        return true;
                    }else {
                        return false;
                    }
                });
            }else if(box.getValue().equals("Personenverkehr")){
                filtered.setPredicate(eintrag->{
                    if(eintrag.getPersonenverkehr().equals("Ja")){
                        return true;
                    }else{
                        return false;
                    }
                });
            }else if(box.getValue().equals("Güter- und Personenverkehr")){
                filtered.setPredicate(eintrag->{
                    if(eintrag.getGueterverkehr().equals("Ja")&& eintrag.getPersonenverkehr().equals("Ja")){
                        return true;
                    }else{
                        return false;
                    }
                });
            }
        });

        return guterPers;
    }
}