package de.joshua.hatzinger.nico.maurer.jonas.domnick.game;

import de.joshua.hatzinger.nico.maurer.jonas.domnick.ui.Window;

import java.util.HashMap;
import java.util.List;

public class SpielManger {

    private final Spiel spiel;
    private final Window w;

    private final HashMap<Spieler, Integer> einsaetze = new HashMap<>();


    public SpielManger(Spiel spiel, Window w) {
        this.spiel = spiel;
        this.w = w;
    }

    public SpielManger(Spiel spiel, Window w, String[] name) {
        this.spiel = spiel;
        this.w = w;
        generateSpieler(name);
    }

    private void generateSpieler(String[] name) {
        if (!spiel.getPhase().equals(SpielPhase.SPIELSTART) || !spiel.getPhase().equals(SpielPhase.SPIELENDE)) {
            int c = name.length;
            if (name.length <= 7) {
                c = 7;
            }
            for (int i = 0; i < c; i++) {
                Spieler spieler = new Spieler(name[i]);
                spieler.setGuthaben(8000);
                spiel.addSpieler(spieler);
                Window.addSpieler(spieler);
            }
        }
    }

    public void startSpiel() {
        spiel.setPhase(SpielPhase.EINSAETZE);
        System.out.println("Einsatz setzen");

        for (int i = 0; i < spiel.getSpielerAnzahl(); i++) {
            Spieler s = spiel.getAktuellerSpieler();
            int m = w.einsatzSetzen(spiel.getAktuellerSpieler());
            einsaetze.put(s, m);
            s.setGuthabenMinus(m);
            w.setGuthabenFont(s.getGuthaben());
        }

    }


    public void dealerZiehen() {

    }

    public void karteZiehen() {
        w.addSpielerkarten(spiel.getAktuellerSpieler(), spiel.ziehKarte());
    }

    public void einsatzSetzen() {


    }

    public List<Karte> getAktuellerSpielerKarten() {
        return null;
    }

    public void sendAktuellerSpielerKarten() {

    }

    public void einsatzVonAktuellenSpielerSetzen(int einsatz) {

    }


}
