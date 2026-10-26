package it.unicam.cs.mpgc.rpg129638.interactable;

public record RisultatoInterazione(String messaggio, EffettoInterazione effetto) {

    public RisultatoInterazione(String messaggio) {
        this(messaggio, EffettoInterazione.NESSUNO);
    }
}