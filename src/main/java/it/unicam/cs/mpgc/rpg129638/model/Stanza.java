package it.unicam.cs.mpgc.rpg129638.model;

import it.unicam.cs.mpgc.rpg129638.interactable.Interagibile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stanza {

    private final String descrizione;
    private final TipoTile[][] griglia;
    private final Posizione posizioneIniziale;
    private final List<Interagibile> elementi = new ArrayList<>();

    public Stanza(String descrizione, TipoTile[][] griglia, Posizione posizioneIniziale) {
        this.descrizione = descrizione;
        this.griglia = griglia;
        this.posizioneIniziale = posizioneIniziale;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Posizione getPosizioneIniziale() {
        return posizioneIniziale;
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

    public boolean isTransitabile(Posizione posizione) {
        return contiene(posizione) && getTile(posizione).isAttraversabile();
    }

    public void aggiungiElemento(Interagibile elemento) {
        elementi.add(elemento);
    }

    public Optional<Interagibile> getElementoIn(Posizione posizione) {
        return elementi.stream()
                .filter(elemento -> !elemento.isEsaurito())
                .filter(elemento -> elemento.getPosizione().equals(posizione))
                .findFirst();
    }

    public boolean isCompletata() {
        return elementi.stream()
                .noneMatch(elemento -> elemento.bloccaUscita() && !elemento.isEsaurito());
    }
}