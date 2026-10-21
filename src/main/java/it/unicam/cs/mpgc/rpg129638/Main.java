package it.unicam.cs.mpgc.rpg129638;

import it.unicam.cs.mpgc.rpg129638.view.MenuIniziale;
import it.unicam.cs.mpgc.rpg129638.view.SchermataGioco;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private Scene scena;

    @Override
    public void start(Stage stage) {
        scena = new Scene(new Pane(), 800, 600);
        scena.getStylesheets().add(
                getClass().getResource("/style.css").toExternalForm());

        mostraMenu();

        stage.setTitle("Alla ricerca del libro perduto");
        stage.setScene(scena);
        stage.show();
    }

    private void mostraMenu() {
        scena.setRoot(new MenuIniziale(this::mostraGioco, Platform::exit));
    }

    private void mostraGioco() {
        scena.setRoot(new SchermataGioco(this::mostraMenu));
    }

    public static void main(String[] args) {
        launch(args);
    }
}