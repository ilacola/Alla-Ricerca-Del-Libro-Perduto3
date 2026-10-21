package it.unicam.cs.mpgc.rpg129638;

import it.unicam.cs.mpgc.rpg129638.view.MenuIniziale;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        MenuIniziale menu = new MenuIniziale();
        Scene scena = new Scene(menu, 800, 600);
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