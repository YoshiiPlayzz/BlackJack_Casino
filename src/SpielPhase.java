public enum SpielPhase {

    START("Start des Spiels"),
    EINSAETZE("Spieler setzen ihre Einsätze"),
    SPIELSTART("Erste Runde des Spiels"),
    SPIEL("Weitere Runden des Spiels"),
    DEALER_ZUG("Der Dealer muss ziehen"),
    SPIELENDE("Ende des Spiels");

    private String desc;

    SpielPhase(String desc){
        this.desc = desc;

    }

    public String getBeschreibung() {
        return desc;
    }
}
