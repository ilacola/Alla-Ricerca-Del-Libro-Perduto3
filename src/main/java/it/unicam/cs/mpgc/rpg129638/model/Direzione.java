package it.unicam.cs.mpgc.rpg129638.model;

public enum Direzione {
    SU(-1, 0),
    GIU(1, 0),
    SINISTRA(0, -1),
    DESTRA(0, 1);

    private final int deltaRiga;
    private final int deltaColonna;

    Direzione(int deltaRiga, int deltaColonna) {
        this.deltaRiga = deltaRiga;
        this.deltaColonna = deltaColonna;
    }

    public int getDeltaRiga() {
        return deltaRiga;
    }

    public int getDeltaColonna() {
        return deltaColonna;
    }
}