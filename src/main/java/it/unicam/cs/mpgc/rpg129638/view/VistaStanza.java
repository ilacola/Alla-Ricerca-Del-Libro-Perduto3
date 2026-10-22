package it.unicam.cs.mpgc.rpg129638.view;

import it.unicam.cs.mpgc.rpg129638.entity.Giocatore;
import it.unicam.cs.mpgc.rpg129638.model.Posizione;
import it.unicam.cs.mpgc.rpg129638.model.Stanza;
import it.unicam.cs.mpgc.rpg129638.model.TipoTile;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class VistaStanza extends GridPane {

    private static final int DIMENSIONE_CELLA = 48;

    public VistaStanza(Stanza stanza, Giocatore giocatore) {
        setAlignment(Pos.CENTER);
        for (int riga = 0; riga < stanza.getRighe(); riga++) {
            for (int colonna = 0; colonna < stanza.getColonne(); colonna++) {
                Posizione posizione = new Posizione(riga, colonna);
                Region cella = new Region();
                cella.setPrefSize(DIMENSIONE_CELLA, DIMENSIONE_CELLA);
                cella.getStyleClass().add("tile-" + stanza.getTile(posizione).name().toLowerCase());
                if (posizione.equals(giocatore.getPosizione())) {
                    cella.getStyleClass().add("giocatore");
                }
                add(cella, colonna, riga);
            }
        }
    }
}