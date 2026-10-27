package it.unicam.cs.mpgc.rpg129638.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import it.unicam.cs.mpgc.rpg129638.model.Mondo;
import it.unicam.cs.mpgc.rpg129638.model.Stanza;
import it.unicam.cs.mpgc.rpg129638.model.TipoTile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CaricatoreMondoJson implements CaricatoreMondo {

    private final FabbricaElementi fabbrica;

    public CaricatoreMondoJson(FabbricaElementi fabbrica) {
        this.fabbrica = fabbrica;
    }

    public CaricatoreMondoJson() {
        this(new FabbricaElementi());
    }

    @Override
    public Mondo carica(String nomeRisorsa) {
        try (InputStream flusso = getClass().getResourceAsStream(nomeRisorsa)) {
            if (flusso == null) {
                throw new PersistenzaException("Risorsa non trovata: " + nomeRisorsa);
            }
            JsonObject radice = JsonParser
                    .parseReader(new InputStreamReader(flusso, StandardCharsets.UTF_8))
                    .getAsJsonObject();
            return costruisciMondo(radice);
        } catch (IOException | JsonParseException
                 | IllegalStateException | IllegalArgumentException e) {
            throw new PersistenzaException("Impossibile leggere " + nomeRisorsa, e);
        }
    }

    private Mondo costruisciMondo(JsonObject radice) {
        List<Stanza> stanze = new ArrayList<>();
        for (JsonElement dati : LetturaJson.array(radice, "stanze")) {
            stanze.add(costruisciStanza(dati.getAsJsonObject()));
        }
        return new Mondo(LetturaJson.testo(radice, "nome"), stanze);
    }

    private Stanza costruisciStanza(JsonObject dati) {
        List<String> righe = new ArrayList<>();
        for (JsonElement riga : LetturaJson.array(dati, "mappa")) {
            righe.add(riga.getAsString());
        }
        Stanza stanza = new Stanza(
                LetturaJson.testo(dati, "descrizione"),
                costruisciGriglia(righe),
                LetturaJson.posizione(LetturaJson.oggetto(dati, "partenza")));
        for (JsonElement elemento : LetturaJson.array(dati, "elementi")) {
            stanza.aggiungiElemento(fabbrica.crea(elemento.getAsJsonObject()));
        }
        return stanza;
    }

    private TipoTile[][] costruisciGriglia(List<String> righe) {
        TipoTile[][] griglia = new TipoTile[righe.size()][];
        for (int r = 0; r < righe.size(); r++) {
            String riga = righe.get(r);
            if (riga.length() != righe.get(0).length()) {
                throw new PersistenzaException("Le righe della mappa hanno lunghezze diverse");
            }
            griglia[r] = new TipoTile[riga.length()];
            for (int c = 0; c < riga.length(); c++) {
                griglia[r][c] = TipoTile.daSimbolo(riga.charAt(c));
            }
        }
        return griglia;
    }
}