package it.unicam.cs.mpgc.rpg129638.model;

public enum TipoTile {
    MURO('M', false),
    PAVIMENTO('P', true),
    LIBRERIA('L', false);

    private final char simbolo;
    private final boolean attraversabile;

    TipoTile(char simbolo, boolean attraversabile) {
        this.simbolo = simbolo;
        this.attraversabile = attraversabile;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public boolean isAttraversabile() {
        return attraversabile;
    }

    public static TipoTile daSimbolo(char simbolo) {
        for (TipoTile tipo : values()) {
            if (tipo.simbolo == simbolo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Simbolo di cella sconosciuto: " + simbolo);
    }
}