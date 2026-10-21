package it.unicam.cs.mpgc.rpg129638.model;

public enum TipoTile {
    MURO(false),
    PAVIMENTO(true),
    LIBRERIA(false);

    private final boolean attraversabile;

    TipoTile(boolean attraversabile) {
        this.attraversabile = attraversabile;
    }

    public boolean isAttraversabile() {
        return attraversabile;
    }
}