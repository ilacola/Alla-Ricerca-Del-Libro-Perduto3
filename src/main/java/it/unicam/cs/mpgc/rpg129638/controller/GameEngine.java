package it.unicam.cs.mpgc.rpg129638.controller;

import it.unicam.cs.mpgc.rpg129638.entity.Giocatore;
import it.unicam.cs.mpgc.rpg129638.interactable.EffettoInterazione;
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

    public boolean isVittoria() {
        return mondo.isCompletato();
    }

    public boolean isPartitaTerminata() {
        return isGameOver() || isVittoria();
    }

    public EsitoMovimento muovi(Direzione direzione) {
        if (isPartitaTerminata()) {
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
        if (isPartitaTerminata()) {
            return Optional.empty();
        }
        return getStanza().getElementoIn(giocatore.getPosizione())
                .map(elemento -> elemento.interagisci(giocatore))
                .map(this::applicaEffetto);
    }

    private RisultatoInterazione applicaEffetto(RisultatoInterazione risultato) {
        if (risultato.effetto() == EffettoInterazione.NESSUNO) {
            return risultato;
        }
        if (!getStanza().isCompletata()) {
            return new RisultatoInterazione(
                    "Il custode è ancora in piedi: finché non lo sconfiggi non puoi proseguire.");
        }
        switch (risultato.effetto()) {
            case PASSA_ALLA_STANZA_SUCCESSIVA -> {
                if (mondo.haStanzaSuccessiva()) {
                    mondo.passaAllaStanzaSuccessiva();
                    giocatore.spostaA(getStanza().getPosizioneIniziale());
                }
            }
            case COMPLETA_IL_MONDO -> mondo.completa();
            default -> { }
        }
        return risultato;
    }
}