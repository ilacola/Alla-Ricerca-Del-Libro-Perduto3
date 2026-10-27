package it.unicam.cs.mpgc.rpg129638.data;

import com.google.gson.JsonObject;
import it.unicam.cs.mpgc.rpg129638.interactable.Interagibile;
import it.unicam.cs.mpgc.rpg129638.interactable.LibroPerduto;
import it.unicam.cs.mpgc.rpg129638.interactable.Mostro;
import it.unicam.cs.mpgc.rpg129638.interactable.Porta;
import it.unicam.cs.mpgc.rpg129638.interactable.Tesoro;
import it.unicam.cs.mpgc.rpg129638.interactable.TipoBonus;
import it.unicam.cs.mpgc.rpg129638.interactable.Trappola;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static it.unicam.cs.mpgc.rpg129638.data.LetturaJson.intero;
import static it.unicam.cs.mpgc.rpg129638.data.LetturaJson.posizione;
import static it.unicam.cs.mpgc.rpg129638.data.LetturaJson.testo;

public class FabbricaElementi {

    private final Map<String, Function<JsonObject, Interagibile>> creatori = new HashMap<>();

    public FabbricaElementi() {
        registra("TESORO", FabbricaElementi::creaTesoro);
        registra("TRAPPOLA", FabbricaElementi::creaTrappola);
        registra("MOSTRO", FabbricaElementi::creaMostro);
        registra("PORTA", FabbricaElementi::creaPorta);
        registra("LIBRO", FabbricaElementi::creaLibro);
    }

    public void registra(String tipo, Function<JsonObject, Interagibile> creatore) {
        creatori.put(tipo, creatore);
    }

    public Interagibile crea(JsonObject dati) {
        String tipo = testo(dati, "tipo");
        Function<JsonObject, Interagibile> creatore = creatori.get(tipo);
        if (creatore == null) {
            throw new PersistenzaException("Tipo di elemento sconosciuto: " + tipo);
        }
        return creatore.apply(dati);
    }

    private static Interagibile creaTesoro(JsonObject d) {
        return new Tesoro(testo(d, "nome"), posizione(d),
                TipoBonus.valueOf(testo(d, "bonus")), intero(d, "valore"));
    }

    private static Interagibile creaTrappola(JsonObject d) {
        return new Trappola(testo(d, "nome"), posizione(d),
                intero(d, "danno"), intero(d, "difficolta"),
                testo(d, "messaggioSuccesso"), testo(d, "messaggioFallimento"));
    }

    private static Interagibile creaMostro(JsonObject d) {
        return new Mostro(testo(d, "nome"), posizione(d),
                intero(d, "puntiVita"), intero(d, "forzaAttacco"), intero(d, "esperienza"));
    }

    private static Interagibile creaPorta(JsonObject d) {
        return new Porta(posizione(d));
    }

    private static Interagibile creaLibro(JsonObject d) {
        return new LibroPerduto(testo(d, "titolo"), posizione(d));
    }
}