package it.unicam.cs.mpgc.rpg129638.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unicam.cs.mpgc.rpg129638.model.Posizione;

final class LetturaJson {

    private LetturaJson() {
    }

    static JsonElement valore(JsonObject oggetto, String campo) {
        JsonElement trovato = oggetto.get(campo);
        if (trovato == null) {
            throw new PersistenzaException("Campo mancante \"" + campo + "\" in: " + oggetto);
        }
        return trovato;
    }

    static String testo(JsonObject oggetto, String campo) {
        return valore(oggetto, campo).getAsString();
    }

    static int intero(JsonObject oggetto, String campo) {
        return valore(oggetto, campo).getAsInt();
    }

    static boolean booleano(JsonObject oggetto, String campo) {
        return valore(oggetto, campo).getAsBoolean();
    }

    static JsonObject oggetto(JsonObject oggetto, String campo) {
        return valore(oggetto, campo).getAsJsonObject();
    }

    static JsonArray array(JsonObject oggetto, String campo) {
        return valore(oggetto, campo).getAsJsonArray();
    }

    static Posizione posizione(JsonObject oggetto) {
        return new Posizione(intero(oggetto, "riga"), intero(oggetto, "colonna"));
    }
}