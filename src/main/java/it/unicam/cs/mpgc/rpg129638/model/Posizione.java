package it.unicam.cs.mpgc.rpg129638.model;

public record Posizione(int riga, int colonna) {

    public Posizione spostata(int deltaRiga, int deltaColonna) {
        return new Posizione(riga + deltaRiga, colonna + deltaColonna);
    }
}