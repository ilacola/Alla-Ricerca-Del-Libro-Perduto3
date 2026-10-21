package it.unicam.cs.mpgc.rpg129638.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MenuIniziale extends VBox {

    public MenuIniziale(Runnable azioneNuovaPartita, Runnable azioneEsci) {
        Label titolo = new Label("Alla ricerca del libro perduto");
        titolo.getStyleClass().add("titolo");

        Button nuovaPartita = new Button("Nuova partita");
        Button esci = new Button("Esci");

        setAlignment(Pos.CENTER);
        setSpacing(20);
        getChildren().addAll(titolo, nuovaPartita, esci);

        nuovaPartita.setOnAction(evento -> azioneNuovaPartita.run());
        esci.setOnAction(evento -> azioneEsci.run());
    }
}