package it.unicam.cs.mpgc.rpg129638.interactable;

import it.unicam.cs.mpgc.rpg129638.entity.Giocatore;
import it.unicam.cs.mpgc.rpg129638.model.Posizione;

public class LibroPerduto implements Interagibile {

    private final String titolo;
    private final Posizione posizione;

    public LibroPerduto(String titolo, Posizione posizione) {
        this.titolo = titolo;
        this.posizione = posizione;
    }

    @Override
    public Posizione getPosizione() {
        return posizione;
    }

    @Override
    public TipoElemento getTipo() {
        return TipoElemento.LIBRO;
    }

    @Override
    public RisultatoInterazione interagisci(Giocatore giocatore) {
        return new RisultatoInterazione("Stringi tra le mani: " + titolo + "!",
                EffettoInterazione.COMPLETA_IL_MONDO);
    }

    @Override
    public boolean isEsaurito() {
        return false;
    }
}