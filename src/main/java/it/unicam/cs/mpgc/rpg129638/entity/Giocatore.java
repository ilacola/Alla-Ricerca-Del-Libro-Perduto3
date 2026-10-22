package it.unicam.cs.mpgc.rpg129638.entity;

import it.unicam.cs.mpgc.rpg129638.model.Posizione;

public class Giocatore {

    private final String nome;
    private Posizione posizione;

    public Giocatore(String nome, Posizione posizioneIniziale) {
        this.nome = nome;
        this.posizione = posizioneIniziale;
    }

    public String getNome() {
        return nome;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public void spostaA(Posizione nuovaPosizione) {
        this.posizione = nuovaPosizione;
    }
}