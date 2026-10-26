package it.unicam.cs.mpgc.rpg129638.interactable;

import it.unicam.cs.mpgc.rpg129638.entity.Giocatore;
import it.unicam.cs.mpgc.rpg129638.entity.Statistiche;
import it.unicam.cs.mpgc.rpg129638.model.Posizione;

public class Mostro implements Interagibile {

    private final String nome;
    private final Posizione posizione;
    private final int forzaAttacco;
    private final int esperienzaRilasciata;
    private int puntiVita;

    public Mostro(String nome, Posizione posizione, int puntiVita,
                  int forzaAttacco, int esperienzaRilasciata) {
        this.nome = nome;
        this.posizione = posizione;
        this.puntiVita = puntiVita;
        this.forzaAttacco = forzaAttacco;
        this.esperienzaRilasciata = esperienzaRilasciata;
    }

    @Override
    public Posizione getPosizione() {
        return posizione;
    }

    @Override
    public RisultatoInterazione interagisci(Giocatore giocatore) {
        Statistiche statistiche = giocatore.getStatistiche();

        int dannoInflitto = statistiche.getForza();
        puntiVita = Math.max(0, puntiVita - dannoInflitto);

        if (puntiVita == 0) {
            statistiche.aumentaEsperienza(esperienzaRilasciata);
            return new RisultatoInterazione("Colpisci " + nome + " (-" + dannoInflitto
                    + ") e lo sconfiggi! +" + esperienzaRilasciata + " esperienza");
        }

        statistiche.subisciDanno(forzaAttacco);
        return new RisultatoInterazione("Colpisci " + nome + " (-" + dannoInflitto
                + ", restano " + puntiVita + " PV). Lui ti colpisce: -" + forzaAttacco + " vita");
    }

    @Override
    public boolean isEsaurito() {
        return puntiVita == 0;
    }

    @Override
    public TipoElemento getTipo() {
        return TipoElemento.MOSTRO;
    }

    @Override
    public boolean bloccaUscita() {
        return true;
    }
}