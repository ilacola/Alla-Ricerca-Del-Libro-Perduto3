package it.unicam.cs.mpgc.rpg129638.interactable;

import it.unicam.cs.mpgc.rpg129638.entity.Giocatore;
import it.unicam.cs.mpgc.rpg129638.model.Posizione;

import java.util.Optional;

public interface Interagibile {

    Posizione getPosizione();

    TipoElemento getTipo();

    RisultatoInterazione interagisci(Giocatore giocatore);

    boolean isEsaurito();

    default boolean bloccaUscita() {
        return false;
    }

    default boolean isVisibileA(Giocatore giocatore) {
        return true;
    }

    default Optional<RisultatoInterazione> alIngresso(Giocatore giocatore) {
        return Optional.empty();
    }

    default int getStato() {
        return isEsaurito() ? 1 : 0;
    }

    default void ripristinaStato(int stato) {
    }
}