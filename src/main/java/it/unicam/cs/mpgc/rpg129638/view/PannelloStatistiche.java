package it.unicam.cs.mpgc.rpg129638.view;

import it.unicam.cs.mpgc.rpg129638.entity.Statistiche;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PannelloStatistiche extends HBox {

    private final Statistiche statistiche;
    private final Label testo = new Label();

    public PannelloStatistiche(Statistiche statistiche) {
        this.statistiche = statistiche;
        testo.getStyleClass().add("statistiche");
        setAlignment(Pos.CENTER);
        getChildren().add(testo);
        aggiorna();
    }

    public void aggiorna() {
        testo.setText("Vita " + statistiche.getPuntiVita() + "/" + statistiche.getPuntiVitaMax()
                + "   Forza " + statistiche.getForza()
                + "   Intuito " + statistiche.getIntuito()
                + "   Esperienza " + statistiche.getEsperienza());
    }
}