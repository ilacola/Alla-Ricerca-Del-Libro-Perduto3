package it.unicam.cs.mpgc.rpg129638.controller;

import it.unicam.cs.mpgc.rpg129638.entity.Giocatore;
import it.unicam.cs.mpgc.rpg129638.interactable.RisultatoInterazione;
import it.unicam.cs.mpgc.rpg129638.model.Direzione;
import it.unicam.cs.mpgc.rpg129638.model.Mondo;
import it.unicam.cs.mpgc.rpg129638.model.Posizione;
import it.unicam.cs.mpgc.rpg129638.model.Stanza;

import java.util.Optional;

public class GameEngine {

    private final Mondo mondo;
    private final Giocatore giocatore;

    public GameEngine(Mondo mondo, Giocatore giocatore) {
        this.mondo = mondo;
        this.giocatore = giocatore;
    }

    public Mondo getMondo() {
        return mondo;
    }

    public Stanza getStanza() {
        return mondo.getStanzaCorrente();
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public boolean isGameOver() {
        return !giocatore.getStatistiche().isVivo();
    }

    public EsitoMovimento muovi(Direzione direzione) {
        if (isGameOver()) {
            return new EsitoMovimento(false, Optional.empty());
        }
        Posizione candidata = giocatore.getPosizione()
                .spostata(direzione.getDeltaRiga(), direzione.getDeltaColonna());
        if (!getStanza().isTransitabile(candidata)) {
            return new EsitoMovimento(false, Optional.empty());
        }
        giocatore.spostaA(candidata);
        Optional<RisultatoInterazione> evento = getStanza().getElementoIn(candidata)
                .flatMap(elemento -> elemento.alIngresso(giocatore));
        return new EsitoMovimento(true, evento);
    }

    public Optional<RisultatoInterazione> interagisci() {
        if (isGameOver()) {
            return Optional.empty();
        }
        return getStanza().getElementoIn(giocatore.getPosizione())
                .map(elemento -> elemento.interagisci(giocatore));
    }
}