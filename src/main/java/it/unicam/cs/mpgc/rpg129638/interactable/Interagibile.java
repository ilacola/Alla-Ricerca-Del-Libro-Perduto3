package it.unicam.cs.mpgc.rpg129638.interactable;

import it.unicam.cs.mpgc.rpg129638.entity.Giocatore;
import it.unicam.cs.mpgc.rpg129638.model.Posizione;

public interface Interagibile {

    Posizione getPosizione();

    RisultatoInterazione interagisci(Giocatore giocatore);
}

