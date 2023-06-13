package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;

public class View {

    private TableView<Eintrag> table;

    private TextField searchbar = new TextField("Search");

    private ComboBox<String> box = new ComboBox(FXCollections.observableArrayList("Personenverkehr", "Güterverkehr", "Güter- und Personenverkehr", "ungefiltert"));

    private ComboBox<String> searchpattern = new ComboBox<>(FXCollections.observableArrayList("Namen", "Orte"));

    private ComboBox<String> schreibweise = new ComboBox<>(FXCollections.observableArrayList( "mit Groß-/Kleinschreibung", "ohne Groß-/Kleinschreibung"));
    private FilteredList filtered;

    private SortedList sorted;

    public View(){
        try{
            this.table = getTabelle();
        } catch (IOException ex){
            throw new RuntimeException();
        }
    }

    private TableView getTabelle() throws IOException {

        TableView<Eintrag> newTable = new TableView<Eintrag>();

        //wir lesen die csv datei ein
        CSVReader reader = new CSVReader();
        ArrayList<Eintrag> list = reader.readcsv("src/csvfile.csv");

        //mithilfe von observableList erzeugen wir eine beobachtbare ArrayList
        //können so die Änderungen an den enthaltenen Elementen verfolgen
        var data = FXCollections.<Eintrag>observableArrayList();
        data.addAll(list);
        filtered = new FilteredList<>(data, p -> true);
        sorted = new SortedList<Eintrag>(filtered);
        newTable.setItems(data);

        TableColumn<Eintrag, String> name = new TableColumn<>("FIRMA");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        newTable.getColumns().add(name);
        name.setMaxWidth(110);

        TableColumn<Eintrag, String> strasse = new TableColumn<>("STRASSE");
        strasse.setCellValueFactory(new PropertyValueFactory<>("strasse"));
        newTable.getColumns().add(strasse);
        strasse.setMaxWidth(110);

        TableColumn<Eintrag, String> plz = new TableColumn<>("PLZ");
        plz.setCellValueFactory(new PropertyValueFactory<>("plz"));
        newTable.getColumns().add(plz);
        plz.setMaxWidth(110);

        TableColumn<Eintrag, String> ort = new TableColumn<>("ORT");
        ort.setCellValueFactory(new PropertyValueFactory<>("ort"));
        newTable.getColumns().add(ort);
        ort.setMaxWidth(110);

        TableColumn<Eintrag, String> gueterverkehr = new TableColumn<>("GUTERVERK.");
        gueterverkehr.setCellValueFactory(new PropertyValueFactory<>("gueterverkehr"));
        newTable.getColumns().add(gueterverkehr);
        gueterverkehr.setMaxWidth(110);

        TableColumn<Eintrag, String> personenverkehr = new TableColumn<>("PERSONENVERK.");
        personenverkehr.setCellValueFactory(new PropertyValueFactory<>("personenverkehr"));
        newTable.getColumns().add(personenverkehr);
        personenverkehr.setMaxWidth(110);

        return newTable;
    }

    //liefert das Layout(BordrPane mit Header)
    public BorderPane getBorderPane() {
        BorderPane layout = new BorderPane();
        box.setValue("Ungefiltert");
        box.setBackground(new Background(new BackgroundFill(Color.DEEPPINK , null, null)));

        searchpattern.setValue("Name"); //ohne Groß-/Kleinschreibung
        searchpattern.setBackground(new Background(new BackgroundFill(Color.DEEPPINK , null, null)));
        layout.setTop(getHBox());

        schreibweise.setValue("ohne Groß-/Kleinschreibung");
        schreibweise.setBackground(new Background(new BackgroundFill(Color.DEEPPINK , null, null)));
        layout.setTop(getHBox());

        Controller controller = new Controller();
        searchbar.textProperty().addListener(controller.updateSearch(searchbar, table, searchpattern, filtered, sorted));
        searchbar.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK , null, null)));
        box.valueProperty().addListener(controller.updateBox(table, box, filtered, sorted));

        table.setItems(sorted);
        layout.setCenter(table);
        return layout;
    }


    //liefert Header
    public HBox getHBox(){
        HBox box = new HBox();
        //Aussehen Kopfzeile
        box.setPadding(new Insets(15, 12, 15, 12));
        box.setSpacing(10);
        box.setBackground(new Background(new BackgroundFill(Color.HOTPINK , null, null)));
        //Was ist in der Kopfzeile(Suchzeile, PoG, Suchstrategie)
        searchbar.setPrefSize(200, 20);
        this.box.setPrefSize(200, 20);
        searchpattern.setPrefSize(200, 20);
        schreibweise.setPrefSize(200, 20);
        //Alles hinzufügen
        box.getChildren().addAll(this.box, searchbar, searchpattern, schreibweise);

        return box;
    }
}
