package it.unicam.cs.mpgc.rpg129638.controller;

import it.unicam.cs.mpgc.rpg129638.entity.Giocatore;
import it.unicam.cs.mpgc.rpg129638.interactable.RisultatoInterazione;
import it.unicam.cs.mpgc.rpg129638.model.Direzione;
import it.unicam.cs.mpgc.rpg129638.model.Posizione;
import it.unicam.cs.mpgc.rpg129638.model.Stanza;

import java.util.Optional;

public class GameEngine {

    private final Stanza stanza;
    private final Giocatore giocatore;

    public GameEngine(Stanza stanza, Giocatore giocatore) {
        this.stanza = stanza;
        this.giocatore = giocatore;
    }

    public Stanza getStanza() {
        return stanza;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public boolean muovi(Direzione direzione) {
        Posizione candidata = giocatore.getPosizione()
                .spostata(direzione.getDeltaRiga(), direzione.getDeltaColonna());
        if (stanza.isTransitabile(candidata)) {
            giocatore.spostaA(candidata);
            return true;
        }
        return false;
    }

    public Optional<RisultatoInterazione> interagisci() {
        return stanza.getElementoIn(giocatore.getPosizione())
                .map(elemento -> elemento.interagisci(giocatore));
    }
}