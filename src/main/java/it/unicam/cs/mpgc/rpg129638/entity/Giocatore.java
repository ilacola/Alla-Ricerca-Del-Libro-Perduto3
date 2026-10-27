package it.unicam.cs.mpgc.rpg129638.entity;

import it.unicam.cs.mpgc.rpg129638.model.Posizione;

public class Giocatore {

    private final String nome;
    private final Statistiche statistiche;
    private Posizione posizione;

    public Giocatore(String nome, Posizione posizioneIniziale, Statistiche statistiche) {
        this.nome = nome;
        this.posizione = posizioneIniziale;
        this.statistiche = statistiche;
    }

    public Giocatore(String nome, Posizione posizioneIniziale) {
        this(nome, posizioneIniziale, new Statistiche(100, 10, 10));
    }

    public String getNome() {
        return nome;
    }

    public Statistiche getStatistiche() {
        return statistiche;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public void spostaA(Posizione nuovaPosizione) {
        this.posizione = nuovaPosizione;
    }
}