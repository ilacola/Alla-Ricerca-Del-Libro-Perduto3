package it.unicam.cs.mpgc.rpg129638.interactable;

import it.unicam.cs.mpgc.rpg129638.entity.Giocatore;
import it.unicam.cs.mpgc.rpg129638.entity.Statistiche;
import it.unicam.cs.mpgc.rpg129638.model.Posizione;

import java.util.Optional;

public class Trappola implements Interagibile {

    private final String nome;
    private final Posizione posizione;
    private final int danno;
    private final int difficolta;
    private final String messaggioSuccesso;
    private final String messaggioFallimento;
    private boolean scattata;

    public Trappola(String nome, Posizione posizione, int danno, int difficolta,
                    String messaggioSuccesso, String messaggioFallimento) {
        this.nome = nome;
        this.posizione = posizione;
        this.danno = danno;
        this.difficolta = difficolta;
        this.messaggioSuccesso = messaggioSuccesso;
        this.messaggioFallimento = messaggioFallimento;
        this.scattata = false;
    }

    @Override
    public Posizione getPosizione() {
        return posizione;
    }

    @Override
    public TipoElemento getTipo() {
        return TipoElemento.TRAPPOLA;
    }

    @Override
    public RisultatoInterazione interagisci(Giocatore giocatore) {
        return scatta(giocatore);
    }

    @Override
    public Optional<RisultatoInterazione> alIngresso(Giocatore giocatore) {
        return Optional.of(scatta(giocatore));
    }

    @Override
    public boolean isEsaurito() {
        return scattata;
    }

    @Override
    public boolean isVisibileA(Giocatore giocatore) {
        return giocatore.getStatistiche().getIntuito() >= difficolta;
    }

    private RisultatoInterazione scatta(Giocatore giocatore) {
        scattata = true;
        Statistiche statistiche = giocatore.getStatistiche();
        if (statistiche.getIntuito() >= difficolta) {
            return new RisultatoInterazione(nome + ": " + messaggioSuccesso);
        }
        statistiche.subisciDanno(danno);
        return new RisultatoInterazione(nome + ": " + messaggioFallimento + " -" + danno + " vita");
    }
}