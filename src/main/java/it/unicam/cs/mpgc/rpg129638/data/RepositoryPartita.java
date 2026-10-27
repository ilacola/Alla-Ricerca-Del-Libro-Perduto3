package it.unicam.cs.mpgc.rpg129638.data;

import it.unicam.cs.mpgc.rpg129638.controller.GameEngine;

public interface RepositoryPartita {

    void salva(GameEngine engine);

    GameEngine carica();

    boolean esisteSalvataggio();
}