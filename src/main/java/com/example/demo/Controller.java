package com.example.demo;

import javafx.beans.value.ChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {

    //Aktualisieren der Suche basierend auf dem ausgewähltem Suchmuster
    public ChangeListener updateSearch(TextField search, TableView tabelleUebergeben, ComboBox searchpattern, ComboBox schreibweise, FilteredList filtered, SortedList sorted) {

        TableView<Eintrag> tabelle = tabelleUebergeben;
        FilteredList<Eintrag> gefiltert = filtered;

        //mit Lambda die changed Methode in ChangeListener überschreiben
        ChangeListener listenerSuchstrategie = (ChangeListener<String>) ((observable, oldVal, newVal) -> {

            gefiltert.setPredicate(eintrag -> {
                if (newVal == null || newVal.isEmpty()) {
                    return true;
                }
                String sucher = newVal;
                String name = eintrag.getName();
                String ort = eintrag.getOrt();

                if (schreibweise.getValue().equals("mit Groß-/Kleinschreibung")) {
                    if (searchpattern.getValue().equals("Namen")) {
                        return name.contains(sucher);
                    } else if (searchpattern.getValue().equals("Orte")) {
                        return ort.contains(sucher);
                    } else {
                        return name.contains(sucher) || ort.contains(sucher);
                    }
                } else if (schreibweise.getValue().equals("ohne Groß-/Kleinschreibung")) {
                    if (searchpattern.getValue().equals("Namen")) {
                        return name.toLowerCase().contains(sucher);
                    } else if (searchpattern.getValue().equals("Orte")) {
                        return ort.toLowerCase().contains(sucher);
                    } else {
                        return name.toLowerCase().contains(sucher) || ort.toLowerCase().contains(sucher);
                    }
                }

                return false;
            });

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