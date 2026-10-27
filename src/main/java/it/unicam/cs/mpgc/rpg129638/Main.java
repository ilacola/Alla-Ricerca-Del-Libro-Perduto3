package it.unicam.cs.mpgc.rpg129638;

import it.unicam.cs.mpgc.rpg129638.controller.GameEngine;
import it.unicam.cs.mpgc.rpg129638.data.CaricatoreMondo;
import it.unicam.cs.mpgc.rpg129638.data.CaricatoreMondoJson;
import it.unicam.cs.mpgc.rpg129638.data.GestoreSalvataggi;
import it.unicam.cs.mpgc.rpg129638.data.PersistenzaException;
import it.unicam.cs.mpgc.rpg129638.data.RepositoryPartita;
import it.unicam.cs.mpgc.rpg129638.entity.Giocatore;
import it.unicam.cs.mpgc.rpg129638.model.Mondo;
import it.unicam.cs.mpgc.rpg129638.view.MenuIniziale;
import it.unicam.cs.mpgc.rpg129638.view.SchermataGioco;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String MONDO_INIZIALE = "/mondi/sotterranei.json";

    private final CaricatoreMondo caricatore = new CaricatoreMondoJson();
    private final RepositoryPartita repository = new GestoreSalvataggi(
            caricatore, MONDO_INIZIALE, GestoreSalvataggi.percorsoPredefinito());
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
        scena.setRoot(new MenuIniziale(
                this::nuovaPartita,
                this::continuaPartita,
                Platform::exit,
                repository.esisteSalvataggio()));
    }

    private void nuovaPartita() {
        Mondo mondo = caricatore.carica(MONDO_INIZIALE);
        avvia(new GameEngine(
                mondo,
                new Giocatore("Ricercatore", mondo.getStanzaCorrente().getPosizioneIniziale())));
    }

    private void continuaPartita() {
        try {
            avvia(repository.carica());
        } catch (PersistenzaException e) {
            System.err.println("Impossibile caricare la partita: " + e.getMessage());
        }
    }

    private void avvia(GameEngine engine) {
        scena.setRoot(new SchermataGioco(engine, () -> repository.salva(engine), this::mostraMenu));
    }

    public static void main(String[] args) {
        launch(args);
    }
}