package it.unicam.cs.mpgc.rpg129638.model;

import java.util.List;

public class Mondo {

    private final String nome;
    private final List<Stanza> stanze;
    private int indiceCorrente = 0;
    private boolean completato = false;

    public Mondo(String nome, List<Stanza> stanze) {
        if (stanze.isEmpty()) {
            throw new IllegalArgumentException("Un mondo deve avere almeno una stanza");
        }
        this.nome = nome;
        this.stanze = List.copyOf(stanze);
    }

    public String getNome() {
        return nome;
    }

    public Stanza getStanzaCorrente() {
        return stanze.get(indiceCorrente);
    }

    public int getNumeroStanzaCorrente() {
        return indiceCorrente + 1;
    }

    public int getNumeroStanze() {
        return stanze.size();
    }

    public boolean haStanzaSuccessiva() {
        return indiceCorrente < stanze.size() - 1;
    }

    public void passaAllaStanzaSuccessiva() {
        if (!haStanzaSuccessiva()) {
            throw new IllegalStateException("Non esiste una stanza successiva");
        }
        indiceCorrente++;
    }

    public void completa() {
        completato = true;
    }

    public boolean isCompletato() {
        return completato;
    }

    public List<Stanza> getStanze() {
        return stanze;
    }

    public void ripristina(int indiceStanza, boolean completato) {
        if (indiceStanza < 0 || indiceStanza >= stanze.size()) {
            throw new IllegalArgumentException("Indice di stanza non valido: " + indiceStanza);
        }
        this.indiceCorrente = indiceStanza;
        this.completato = completato;
    }
}