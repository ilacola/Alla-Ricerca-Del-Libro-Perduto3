package it.unicam.cs.mpgc.rpg129638.view;

import it.unicam.cs.mpgc.rpg129638.controller.EsitoMovimento;
import it.unicam.cs.mpgc.rpg129638.controller.GameEngine;
import it.unicam.cs.mpgc.rpg129638.interactable.RisultatoInterazione;
import it.unicam.cs.mpgc.rpg129638.model.Direzione;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class SchermataGioco extends VBox {

    private final GameEngine engine;
    private final VistaStanza vistaStanza;
    private final PannelloStatistiche pannelloStatistiche;
    private final Label messaggio;

    public SchermataGioco(GameEngine engine, Runnable azioneTornaAlMenu) {
        this.engine = engine;
        this.vistaStanza = new VistaStanza(engine.getStanza(), engine.getGiocatore());
        this.pannelloStatistiche = new PannelloStatistiche(engine.getGiocatore().getStatistiche());

        Label titolo = new Label("Stanza 1");
        titolo.getStyleClass().add("titolo");

        messaggio = new Label(" ");
        messaggio.getStyleClass().add("messaggio");

        Button tornaAlMenu = new Button("Menu");
        tornaAlMenu.setFocusTraversable(false);
        tornaAlMenu.setOnAction(evento -> azioneTornaAlMenu.run());

        setAlignment(Pos.CENTER);
        setSpacing(20);
        getChildren().addAll(titolo, messaggio, vistaStanza, pannelloStatistiche, tornaAlMenu);

        setFocusTraversable(true);
        setOnKeyPressed(this::gestisciTasto);
        Platform.runLater(this::requestFocus);
    }

    private void gestisciTasto(KeyEvent evento) {
        if (evento.getCode() == KeyCode.SPACE) {
            interagisci();
        } else {
            muovi(evento.getCode());
        }
        evento.consume();
    }

    private void interagisci() {
        messaggio.setText(engine.interagisci()
                .map(RisultatoInterazione::messaggio)
                .orElse("Non c'è nulla con cui interagire qui."));
        pannelloStatistiche.aggiorna();
        vistaStanza.aggiorna();
    }

    private void muovi(KeyCode tasto) {
        Direzione direzione = switch (tasto) {
            case UP -> Direzione.SU;
            case DOWN -> Direzione.GIU;
            case LEFT -> Direzione.SINISTRA;
            case RIGHT -> Direzione.DESTRA;
            default -> null;
        };
        if (direzione == null) {
            return;
        }
        EsitoMovimento esito = engine.muovi(direzione);
        if (esito.mosso()) {
            messaggio.setText(esito.evento()
                    .map(RisultatoInterazione::messaggio)
                    .orElse(" "));
            pannelloStatistiche.aggiorna();
            vistaStanza.aggiorna();
        }
    }
}