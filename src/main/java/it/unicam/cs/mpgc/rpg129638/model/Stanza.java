package it.unicam.cs.mpgc.rpg129638.model;

public class Stanza {

    private final String descrizione;
    private final TipoTile[][] griglia;

    public Stanza(String descrizione, TipoTile[][] griglia) {
        this.descrizione = descrizione;
        this.griglia = griglia;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getRighe() {
        return griglia.length;
    }

    public int getColonne() {
        return griglia[0].length;
    }

    public boolean contiene(Posizione posizione) {
        return posizione.riga() >= 0 && posizione.riga() < getRighe()
                && posizione.colonna() >= 0 && posizione.colonna() < getColonne();
    }

    public TipoTile getTile(Posizione posizione) {
        if (!contiene(posizione)) {
            throw new IllegalArgumentException("Posizione fuori dalla stanza: " + posizione);
        }
        return griglia[posizione.riga()][posizione.colonna()];
    }
}