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
    private final Label titolo;
    private final Label messaggio;

    public SchermataGioco(GameEngine engine, Runnable azioneTornaAlMenu) {
        this.engine = engine;
        this.vistaStanza = new VistaStanza(engine);
        this.pannelloStatistiche = new PannelloStatistiche(engine.getGiocatore().getStatistiche());

        titolo = new Label();
        titolo.getStyleClass().add("titolo");
        aggiornaTitolo();

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
        if (!engine.isPartitaTerminata()) {
            if (evento.getCode() == KeyCode.SPACE) {
                interagisci();
            } else {
                muovi(evento.getCode());
            }
        }
        evento.consume();
    }

    private void interagisci() {
        messaggio.setText(engine.interagisci()
                .map(RisultatoInterazione::messaggio)
                .orElse("Non c'è nulla con cui interagire qui."));
        aggiornaSchermata();
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
            aggiornaSchermata();
        }
    }

    private void aggiornaSchermata() {
        aggiornaTitolo();
        pannelloStatistiche.aggiorna();
        vistaStanza.aggiorna();
        if (engine.isGameOver()) {
            messaggio.setText(messaggio.getText() + "\nGAME OVER: "
                    + engine.getGiocatore().getNome() + " è caduto nei sotterranei.");
        } else if (engine.isVittoria()) {
            messaggio.setText(messaggio.getText() + "\nVITTORIA! "
                    + engine.getGiocatore().getNome() + " ha trovato il libro perduto!");
        }
    }

    private void aggiornaTitolo() {
        titolo.setText(engine.getMondo().getNome() + " - Stanza "
                + engine.getMondo().getNumeroStanzaCorrente() + "/"
                + engine.getMondo().getNumeroStanze());
    }
}