package it.unicam.cs.mpgc.rpg129638;

import it.unicam.cs.mpgc.rpg129638.controller.GameEngine;
import it.unicam.cs.mpgc.rpg129638.entity.Giocatore;
import it.unicam.cs.mpgc.rpg129638.model.Posizione;
import it.unicam.cs.mpgc.rpg129638.model.Stanza;
import it.unicam.cs.mpgc.rpg129638.model.TipoTile;
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
        GameEngine engine = new GameEngine(
                creaStanzaDiProva(),
                new Giocatore("Ricercatore", new Posizione(1, 1)));
        scena.setRoot(new SchermataGioco(engine, this::mostraMenu));
    }

    private Stanza creaStanzaDiProva() {
        TipoTile m = TipoTile.MURO;
        TipoTile p = TipoTile.PAVIMENTO;
        TipoTile l = TipoTile.LIBRERIA;
        TipoTile[][] mappa = {
                {m, m, m, m, m, m, m, m},
                {m, p, p, p, p, p, p, m},
                {m, p, p, l, l, p, p, m},
                {m, p, p, p, p, p, p, m},
                {m, m, m, m, m, m, m, m}
        };
        return new Stanza("Una stanza di prova", mappa);
    }

    public static void main(String[] args) {
        launch(args);
    }
}