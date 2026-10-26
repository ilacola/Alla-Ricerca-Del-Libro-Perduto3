package it.unicam.cs.mpgc.rpg129638.interactable;

public record RisultatoInterazione(String messaggio, boolean passaAllaStanzaSuccessiva) {

    public RisultatoInterazione(String messaggio) {
        this(messaggio, false);
    }
}