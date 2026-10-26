package it.unicam.cs.mpgc.rpg129638.interactable;

import it.unicam.cs.mpgc.rpg129638.entity.Statistiche;

import java.util.function.BiConsumer;

public enum TipoBonus {
    SALUTE("salute", Statistiche::cura),
    FORZA("forza", Statistiche::aumentaForza),
    INTUITO("intuito", Statistiche::aumentaIntuito);

    private final String nome;
    private final BiConsumer<Statistiche, Integer> effetto;

    TipoBonus(String nome, BiConsumer<Statistiche, Integer> effetto) {
        this.nome = nome;
        this.effetto = effetto;
    }

    public void applica(Statistiche statistiche, int valore) {
        effetto.accept(statistiche, valore);
    }

    public String getNome() {
        return nome;
    }
}