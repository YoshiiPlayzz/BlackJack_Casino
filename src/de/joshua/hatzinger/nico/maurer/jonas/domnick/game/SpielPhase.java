package de.joshua.hatzinger.nico.maurer.jonas.domnick.game;

public enum SpielPhase {

    START("Start des Spiels"),
    EINSAETZE("Spieler setzen ihre Einsätze"),
    SPIELSTART("Erste Runde des Spielers"),
    SPIEL("Weitere Runden des Spielers"),
    DEALER_ZUG("Der Dealer muss ziehen"),
    SPIELENDE("Ende des Spiels");

    private final String desc;

    SpielPhase(String desc) {
        this.desc = desc;

    }

    public String getBeschreibung() {
        return desc;
    }
}
