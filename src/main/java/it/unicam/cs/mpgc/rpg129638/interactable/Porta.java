package it.unicam.cs.mpgc.rpg129638.interactable;

import it.unicam.cs.mpgc.rpg129638.entity.Giocatore;
import it.unicam.cs.mpgc.rpg129638.model.Posizione;

public class Porta implements Interagibile {

    private final Posizione posizione;

    public Porta(Posizione posizione) {
        this.posizione = posizione;
    }

    @Override
    public Posizione getPosizione() {
        return posizione;
    }

    @Override
    public TipoElemento getTipo() {
        return TipoElemento.PORTA;
    }

    @Override
    public RisultatoInterazione interagisci(Giocatore giocatore) {
        return new RisultatoInterazione("Apri la porta e prosegui nelle profondità...", true);
    }

    @Override
    public boolean isEsaurito() {
        return false;
    }
}