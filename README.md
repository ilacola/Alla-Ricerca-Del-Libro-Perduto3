# Alla ricerca del libro perduto

Gioco di ruolo in Java (interfaccia grafica JavaFX) in cui un ricercatore di libri perduti
attraversa le stanze di un mondo, affronta custodi, trappole e tesori e cerca di trovare
il libro perduto nell'ultima stanza. Il primo mondo è **"I sotterranei della biblioteca"**.

Progetto per il corso di *Metodologie di Programmazione* (A.A. 2025/26) - UNICAM.
Package principale: `it.unicam.cs.mpgc.rpg129638`.

La documentazione completa (funzionalità, classi e responsabilità, dati e persistenza,
punti di estensione, uso dell'AI) è nella **[Wiki del repository](../../wiki)**.

---

## Come si gioca

| Comando | Azione |
|---|---|
| Frecce | Muovi il ricercatore |
| Spazio | Interagisci con ciò che c'è nella cella (combatti, raccogli, apri) |
| S | Salva la partita |
| Esc | Torna al menu |

- Il **custode** (mostro) di ogni stanza va sconfitto prima di poter usare la porta o
  prendere il libro. Si combatte un colpo alla volta, premendo Spazio.
- Le **trappole** sono invisibili finché l'intuito del ricercatore non raggiunge la loro
  difficoltà: se la vedi, la schivi.
- I **tesori** migliorano una statistica (salute, forza o intuito).
- Se la salute scende a zero la partita finisce; trovare il libro perduto la vince.
- Dal menu, **Continua** riprende l'ultima partita salvata.

---

## Come eseguire il progetto

### Prerequisiti
- Java 25 (LTS)
- Gradle (non serve installarlo: si usa il wrapper incluso nel progetto)

JavaFX e le altre librerie vengono scaricate automaticamente da Gradle.

### Istruzioni

```bash
git clone https://github.com/ilacola/Alla-Ricerca-Del-Libro-Perduto3.git
cd Alla-Ricerca-Del-Libro-Perduto3
```

### Build del progetto
```bash
./gradlew build
```

### Esecuzione
```bash
./gradlew run
```

---

## Dati e salvataggio

- I **mondi** sono descritti in file JSON (`src/main/resources/mondi`): aggiungere o
  modificare mappe, mostri, trappole e tesori non richiede di ricompilare il codice.
- Il **salvataggio** della partita è un file JSON nella cartella dell'utente
  (`~/.alla-ricerca-del-libro-perduto/salvataggio.json`), fuori dal progetto.

---

## Uso di strumenti di AI

Per la realizzazione del progetto ho utilizzato **Claude (Anthropic)**, tramite la sua
interfaccia di chat, come assistente durante tutto lo sviluppo. L'idea del gioco, le sue
regole e le scelte di design sono mie; il codice è stato in larga parte scritto da me con
la correzione di errori da parte di Claude.

**Cosa ho deciso io**
- il concept (ricercatore, mondi, stanze, custodi, trappole, tesori, libro perduto) e le statistiche
- le regole di gioco: trappole che si vedono in base all'intuito, porta e libro bloccati
  finché il custode è in piedi, combattimento a colpi, comandi da tastiera
- lo stile grafico (biblioteca antica) e la palette dei colori

**Per cosa ho usato Claude**
- discutere l'architettura (separazione tra model, controller e view, interfacce come
  `Interagibile`, principi SOLID) e valutare alternative di design
- correggere il codice Java e il CSS
- spiegarmi i concetti (eventi JavaFX, record, enum, `Optional`, lambda, Gradle)
- generare gli sprite in pixel art tramite uno script, che ho approvato
- preparare una bozza del README e della Wiki, che ho poi rivisto

La dichiarazione dettagliata è nella pagina **Uso di strumenti di AI** della Wiki.
