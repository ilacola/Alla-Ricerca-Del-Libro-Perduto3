package it.unicam.cs.mpgc.rpg129638.view;

import it.unicam.cs.mpgc.rpg129638.controller.EsitoMovimento;
import it.unicam.cs.mpgc.rpg129638.controller.GameEngine;
import it.unicam.cs.mpgc.rpg129638.interactable.RisultatoInterazione;
import it.unicam.cs.mpgc.rpg129638.model.Direzione;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class SchermataGioco extends VBox {

    private static final String GUIDA =
            "Frecce: muoviti   Spazio: interagisci   Esc: menu";

    private final GameEngine engine;
    private final Runnable azioneTornaAlMenu;
    private final VistaStanza vistaStanza;
    private final PannelloStatistiche pannelloStatistiche;
    private final Label titolo;
    private final Label messaggio;

    public SchermataGioco(GameEngine engine, Runnable azioneTornaAlMenu) {
        this.engine = engine;
        this.azioneTornaAlMenu = azioneTornaAlMenu;
        this.vistaStanza = new VistaStanza(engine);
        this.pannelloStatistiche = new PannelloStatistiche(engine.getGiocatore().getStatistiche());

        titolo = new Label();
        titolo.getStyleClass().add("titolo");
        aggiornaTitolo();

        messaggio = new Label(GUIDA);
        messaggio.getStyleClass().add("messaggio");

        setAlignment(Pos.CENTER);
        setSpacing(20);
        getChildren().addAll(titolo, messaggio, vistaStanza, pannelloStatistiche);

        setFocusTraversable(true);
        setOnKeyPressed(this::gestisciTasto);
        Platform.runLater(this::requestFocus);
    }

    private void gestisciTasto(KeyEvent evento) {
        if (evento.getCode() == KeyCode.ESCAPE) {
            azioneTornaAlMenu.run();
        } else if (!engine.isPartitaTerminata()) {
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
                    + engine.getGiocatore().getNome() + " è caduto nei sotterranei."
                    + "\nPremi Esc per tornare al menu.");
        } else if (engine.isVittoria()) {
            messaggio.setText(messaggio.getText() + "\nVITTORIA! "
                    + engine.getGiocatore().getNome() + " ha trovato il libro perduto!"
                    + "\nPremi Esc per tornare al menu.");
        }
    }

    private void aggiornaTitolo() {
        titolo.setText(engine.getMondo().getNome() + " - Stanza "
                + engine.getMondo().getNumeroStanzaCorrente() + "/"
                + engine.getMondo().getNumeroStanze());
    }
}

//guarda sinceramente quelle immagini le vorrei cambiare perchè non si addicono molto al contesto