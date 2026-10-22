package it.unicam.cs.mpgc.rpg129638.view;

import it.unicam.cs.mpgc.rpg129638.controller.GameEngine;
import it.unicam.cs.mpgc.rpg129638.model.Direzione;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class SchermataGioco extends VBox {

    private final GameEngine engine;
    private final VistaStanza vistaStanza;

    public SchermataGioco(GameEngine engine, Runnable azioneTornaAlMenu) {
        this.engine = engine;
        this.vistaStanza = new VistaStanza(engine.getStanza(), engine.getGiocatore());

        Label titolo = new Label("Stanza 1");
        titolo.getStyleClass().add("titolo");

        Button tornaAlMenu = new Button("Menu");
        tornaAlMenu.setFocusTraversable(false);
        tornaAlMenu.setOnAction(evento -> azioneTornaAlMenu.run());

        setAlignment(Pos.CENTER);
        setSpacing(20);
        getChildren().addAll(titolo, vistaStanza, tornaAlMenu);

        setFocusTraversable(true);
        setOnKeyPressed(this::gestisciTasto);
        Platform.runLater(this::requestFocus);
    }

    private void gestisciTasto(KeyEvent evento) {
        Direzione direzione = switch (evento.getCode()) {
            case UP -> Direzione.SU;
            case DOWN -> Direzione.GIU;
            case LEFT -> Direzione.SINISTRA;
            case RIGHT -> Direzione.DESTRA;
            default -> null;
        };
        if (direzione != null && engine.muovi(direzione)) {
            vistaStanza.aggiorna();
        }
        evento.consume();
    }
}