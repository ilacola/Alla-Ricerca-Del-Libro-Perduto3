package it.unicam.cs.mpgc.rpg129638.entity;

public class Statistiche {

    private final int puntiVitaMax;
    private int puntiVita;
    private int forza;
    private int intuito;
    private int esperienza;

    public Statistiche(int puntiVitaMax, int forza, int intuito) {
        if (puntiVitaMax <= 0) {
            throw new IllegalArgumentException("I punti vita massimi devono essere positivi");
        }
        this.puntiVitaMax = puntiVitaMax;
        this.puntiVita = puntiVitaMax;
        this.forza = forza;
        this.intuito = intuito;
        this.esperienza = 0;
    }

    public void subisciDanno(int danno) {
        puntiVita = Math.max(0, puntiVita - danno);
    }

    public void cura(int quantita) {
        puntiVita = Math.min(puntiVitaMax, puntiVita + quantita);
    }

    public void aumentaForza(int valore) {
        forza += valore;
    }

    public void aumentaIntuito(int valore) {
        intuito += valore;
    }

    public void aumentaEsperienza(int valore) {
        esperienza += valore;
    }

    public boolean isVivo() {
        return puntiVita > 0;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public int getPuntiVitaMax() {
        return puntiVitaMax;
    }

    public int getForza() {
        return forza;
    }

    public int getIntuito() {
        return intuito;
    }

    public int getEsperienza() {
        return esperienza;
    }
}