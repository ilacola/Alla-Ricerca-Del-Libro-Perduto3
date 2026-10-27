package it.unicam.cs.mpgc.rpg129638.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import it.unicam.cs.mpgc.rpg129638.controller.GameEngine;
import it.unicam.cs.mpgc.rpg129638.entity.Giocatore;
import it.unicam.cs.mpgc.rpg129638.entity.Statistiche;
import it.unicam.cs.mpgc.rpg129638.interactable.Interagibile;
import it.unicam.cs.mpgc.rpg129638.model.Mondo;
import it.unicam.cs.mpgc.rpg129638.model.Posizione;
import it.unicam.cs.mpgc.rpg129638.model.Stanza;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GestoreSalvataggi implements RepositoryPartita {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final CaricatoreMondo caricatore;
    private final String risorsaMondo;
    private final Path file;

    public GestoreSalvataggi(CaricatoreMondo caricatore, String risorsaMondo, Path file) {
        this.caricatore = caricatore;
        this.risorsaMondo = risorsaMondo;
        this.file = file;
    }

    public static Path percorsoPredefinito() {
        return Path.of(System.getProperty("user.home"),
                ".alla-ricerca-del-libro-perduto", "salvataggio.json");
    }

    @Override
    public boolean esisteSalvataggio() {
        return Files.exists(file);
    }

    @Override
    public void salva(GameEngine engine) {
        Mondo mondo = engine.getMondo();

        JsonObject radice = new JsonObject();
        radice.addProperty("stanzaCorrente", mondo.getNumeroStanzaCorrente() - 1);
        radice.addProperty("completato", mondo.isCompletato());
        radice.add("giocatore", scriviGiocatore(engine.getGiocatore()));

        JsonArray stati = new JsonArray();
        for (Stanza stanza : mondo.getStanze()) {
            JsonArray statiStanza = new JsonArray();
            for (Interagibile elemento : stanza.getElementi()) {
                statiStanza.add(elemento.getStato());
            }
            stati.add(statiStanza);
        }
        radice.add("stati", stati);

        try {
            Files.createDirectories(file.getParent());
            Files.writeString(file, GSON.toJson(radice), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenzaException("Impossibile scrivere il salvataggio in " + file, e);
        }
    }

    @Override
    public GameEngine carica() {
        JsonObject radice = leggiFile();
        try {
            Mondo mondo = caricatore.carica(risorsaMondo);
            mondo.ripristina(LetturaJson.intero(radice, "stanzaCorrente"),
                    LetturaJson.booleano(radice, "completato"));
            ripristinaStati(mondo, LetturaJson.array(radice, "stati"));
            Giocatore giocatore = leggiGiocatore(LetturaJson.oggetto(radice, "giocatore"));
            return new GameEngine(mondo, giocatore);
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new PersistenzaException("Salvataggio non valido: " + file, e);
        }
    }

    private JsonObject leggiFile() {
        try {
            String testo = Files.readString(file, StandardCharsets.UTF_8);
            return JsonParser.parseString(testo).getAsJsonObject();
        } catch (IOException | JsonParseException | IllegalStateException e) {
            throw new PersistenzaException("Impossibile leggere il salvataggio " + file, e);
        }
    }

    private JsonObject scriviGiocatore(Giocatore giocatore) {
        Statistiche s = giocatore.getStatistiche();
        JsonObject dati = new JsonObject();
        dati.addProperty("nome", giocatore.getNome());
        dati.addProperty("riga", giocatore.getPosizione().riga());
        dati.addProperty("colonna", giocatore.getPosizione().colonna());
        dati.addProperty("puntiVitaMax", s.getPuntiVitaMax());
        dati.addProperty("puntiVita", s.getPuntiVita());
        dati.addProperty("forza", s.getForza());
        dati.addProperty("intuito", s.getIntuito());
        dati.addProperty("esperienza", s.getEsperienza());
        return dati;
    }

    private Giocatore leggiGiocatore(JsonObject dati) {
        Statistiche statistiche = new Statistiche(
                LetturaJson.intero(dati, "puntiVitaMax"),
                LetturaJson.intero(dati, "puntiVita"),
                LetturaJson.intero(dati, "forza"),
                LetturaJson.intero(dati, "intuito"),
                LetturaJson.intero(dati, "esperienza"));
        Posizione posizione = LetturaJson.posizione(dati);
        return new Giocatore(LetturaJson.testo(dati, "nome"), posizione, statistiche);
    }

    private void ripristinaStati(Mondo mondo, JsonArray stati) {
        List<Stanza> stanze = mondo.getStanze();
        if (stati.size() != stanze.size()) {
            throw new PersistenzaException("Il salvataggio non corrisponde al mondo");
        }
        for (int i = 0; i < stanze.size(); i++) {
            List<Interagibile> elementi = stanze.get(i).getElementi();
            JsonArray statiStanza = stati.get(i).getAsJsonArray();
            if (statiStanza.size() != elementi.size()) {
                throw new PersistenzaException("Il salvataggio non corrisponde alla stanza " + (i + 1));
            }
            for (int j = 0; j < elementi.size(); j++) {
                JsonElement stato = statiStanza.get(j);
                elementi.get(j).ripristinaStato(stato.getAsInt());
            }
        }
    }
}