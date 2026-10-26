package it.unicam.cs.mpgc.rpg129638;

import it.unicam.cs.mpgc.rpg129638.controller.GameEngine;
import it.unicam.cs.mpgc.rpg129638.entity.Giocatore;
import it.unicam.cs.mpgc.rpg129638.interactable.Mostro;
import it.unicam.cs.mpgc.rpg129638.interactable.Porta;
import it.unicam.cs.mpgc.rpg129638.interactable.Tesoro;
import it.unicam.cs.mpgc.rpg129638.interactable.TipoBonus;
import it.unicam.cs.mpgc.rpg129638.interactable.Trappola;
import it.unicam.cs.mpgc.rpg129638.model.Mondo;
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

import java.util.List;

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
        Mondo mondo = new Mondo("I sotterranei della biblioteca",
                List.of(creaStanza1(), creaStanza2()));
        GameEngine engine = new GameEngine(
                mondo,
                new Giocatore("Ricercatore", mondo.getStanzaCorrente().getPosizioneIniziale()));
        scena.setRoot(new SchermataGioco(engine, this::mostraMenu));
    }

    private Stanza creaStanza1() {
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
        Stanza stanza = new Stanza("Una stanza buia piena di ragnatele", mappa, new Posizione(1, 1));
        stanza.aggiungiElemento(new Tesoro(
                "Pozione del Sapiente", new Posizione(3, 5), TipoBonus.INTUITO, 5));
        stanza.aggiungiElemento(new Trappola(
                "Frecce nascoste", new Posizione(3, 3), 20, 12,
                "noti le piastrelle a pressione e schivi il meccanismo!",
                "una raffica di frecce ti colpisce!"));
        stanza.aggiungiElemento(new Mostro(
                "Ragno Custode", new Posizione(1, 4), 30, 8, 50));
        stanza.aggiungiElemento(new Porta(new Posizione(1, 6)));
        return stanza;
    }

    private Stanza creaStanza2() {
        TipoTile m = TipoTile.MURO;
        TipoTile p = TipoTile.PAVIMENTO;
        TipoTile l = TipoTile.LIBRERIA;
        TipoTile[][] mappa = {
                {m, m, m, m, m, m, m, m, m},
                {m, p, p, p, l, p, p, p, m},
                {m, p, p, p, l, p, p, p, m},
                {m, p, p, p, p, p, p, p, m},
                {m, m, m, m, m, m, m, m, m}
        };
        Stanza stanza = new Stanza("Un archivio polveroso", mappa, new Posizione(1, 1));
        stanza.aggiungiElemento(new Tesoro(
                "Tisana ristoratrice", new Posizione(3, 1), TipoBonus.SALUTE, 30));
        stanza.aggiungiElemento(new Mostro(
                "Scheletro Bibliotecario", new Posizione(2, 6), 40, 10, 80));
        return stanza;
    }

    public static void main(String[] args) {
        launch(args);
    }
}