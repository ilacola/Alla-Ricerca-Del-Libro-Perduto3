package it.unicam.cs.mpgc.rpg129638.data;

public class PersistenzaException extends RuntimeException {

    public PersistenzaException(String messaggio) {
        super(messaggio);
    }

    public PersistenzaException(String messaggio, Throwable causa) {
        super(messaggio, causa);
    }
}