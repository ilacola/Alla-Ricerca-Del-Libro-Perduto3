package it.unicam.cs.mpgc.rpg129638;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Label titolo = new Label("Alla ricerca del libro perduto");
        titolo.getStyleClass().add("titolo");

        StackPane radice = new StackPane(titolo);
        Scene scena = new Scene(radice, 800, 600);
        scena.getStylesheets().add(
                getClass().getResource("/style.css").toExternalForm());

        stage.setTitle("Alla ricerca del libro perduto");
        stage.setScene(scena);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}