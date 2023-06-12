package com.example.demo;

import javafx.beans.value.ChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {
    public ChangeListener updateSearch(TextField search, TableView tabelleUebergeben, ComboBox searchpattern, FilteredList filtered, SortedList sorted) {
        TableView<Eintrag> tabelle = tabelleUebergeben;
        FilteredList<Eintrag> gefiltert = filtered;

        ChangeListener listenerSuchstrategie = (ChangeListener<String>) ((observable, oldVal, newVal) -> {
            if (searchpattern.getValue().equals("mit Groß-Kleinschreibung")) {
                gefiltert.setPredicate(bahnunternehmen -> {

                    if (newVal == null || newVal.isEmpty()) {
                        return true;
                    }
                    String sucher = newVal;
                    if (bahnunternehmen.getName().contains(sucher)) {
                        return true;
                    } else if (bahnunternehmen.getStrasse().contains(sucher)) {
                        return true;
                    } else if (bahnunternehmen.getPlz().contains(sucher)) {
                        return true;
                    } else if (bahnunternehmen.getOrt().contains(sucher)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            } else if (searchpattern.getValue().equals("ohne Groß-Kleinschreibung")) {
                gefiltert.setPredicate(bahnunternehmen -> {
                    if (newVal == null || newVal.isEmpty()) {
                        return true;
                    }
                    String sucher = newVal.toLowerCase();
                    if (bahnunternehmen.getName().toLowerCase().contains(sucher)) {
                        return true;
                    } else if (bahnunternehmen.getStrasse().toLowerCase().contains(sucher)) {
                        return true;
                    } else if (bahnunternehmen.getPlz().toLowerCase().contains(sucher)) {
                        return true;
                    } else if (bahnunternehmen.getOrt().toLowerCase().contains(sucher)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            } else if (searchpattern.getValue().equals("Namen")) {
                gefiltert.setPredicate(bahnunternehmen -> {
                    if (newVal == null || newVal.isEmpty()) {
                        return true;
                    }
                    String sucher = newVal.toLowerCase();
                    if (bahnunternehmen.getName().toLowerCase().contains(sucher)) {
                        return true;
                    } else {
                        return false;
                    }

                });
            } else if (searchpattern.getValue().equals("Orte")) {
                gefiltert.setPredicate(bahnunternehmen -> {
                    if (newVal == null || newVal.isEmpty()) {
                        return true;
                    }
                    String sucher = newVal.toLowerCase();
                    if (bahnunternehmen.getOrt().toLowerCase().contains(sucher)) {
                        return true;
                    } else {
                        return false;
                    }
                });

            }

        });
        return listenerSuchstrategie;
    }

    public ChangeListener updateBox(TableView tabelleUebergeben, ComboBox box, FilteredList gefiltert, SortedList sortiert) {
        TableView<Eintrag> tabelle = tabelleUebergeben;
        FilteredList<Eintrag> filtered = gefiltert;

        ChangeListener guterPers = (ChangeListener<String>) ((observable, oldVal, newVal) -> {
            if(box.getValue().equals("Ungefiltert")){
                filtered.setPredicate(bahnunternehmen->{
                    return true;
                });
            }else if(box.getValue().equals("Güterverkehr")){
                filtered.setPredicate(bahnunternehmen->{
                    if(bahnunternehmen.getGueterverkehr().equals("Ja")){
                        return true;
                    }else {
                        return false;
                    }
                });
            }else if(box.getValue().equals("Personenverkehr")){
                filtered.setPredicate(bahnunternehmen->{
                    if(bahnunternehmen.getPersonenverkehr().equals("Ja")){
                        return true;
                    }else{
                        return false;
                    }
                });
            }else if(box.getValue().equals("Güter- und Personenverkehr")){
                filtered.setPredicate(bahnunternehmen->{
                    if(bahnunternehmen.getGueterverkehr().equals("Ja")&& bahnunternehmen.getPersonenverkehr().equals("Ja")){
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