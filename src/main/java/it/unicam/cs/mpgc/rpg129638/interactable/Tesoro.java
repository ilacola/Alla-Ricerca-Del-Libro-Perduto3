package it.unicam.cs.mpgc.rpg129638.interactable;

import it.unicam.cs.mpgc.rpg129638.entity.Giocatore;
import it.unicam.cs.mpgc.rpg129638.model.Posizione;

public class Tesoro implements Interagibile {

    private final String nome;
    private final Posizione posizione;
    private boolean raccolto;

    public Tesoro(String nome, Posizione posizione) {
        this.nome = nome;
        this.posizione = posizione;
        this.raccolto = false;
    }

    @Override
    public Posizione getPosizione() {
        return posizione;
    }

    @Override
    public RisultatoInterazione interagisci(Giocatore giocatore) {
        if (raccolto) {
            return new RisultatoInterazione("Hai già raccolto: " + nome + ".");
        }
        raccolto = true;
        return new RisultatoInterazione("Hai trovato: " + nome + "!");
    }
}