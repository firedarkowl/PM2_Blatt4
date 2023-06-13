package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.tools.Borders;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //Icon und Titel festlegen
        stage.getIcons().add(new Image("C:/Users/nasty/Documents/Studium/HAW/4_Semester/Pm2/pm2_blatt4/src/zug.png"));
        stage.setTitle("Bahnunternehmen");

        //BorderPane als Hauptlayout
        BorderPane wurzel = new BorderPane();
        //Inhalt in die Mitte setzen
        wurzel.setCenter((new View().getBorderPane()));
        var scene = new Scene(new StackPane(wurzel), 900, 700);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}