package it.unicam.cs.mpgc.rpg129638.controller;

import it.unicam.cs.mpgc.rpg129638.interactable.RisultatoInterazione;

import java.util.Optional;

public record EsitoMovimento(boolean mosso, Optional<RisultatoInterazione> evento) {
}