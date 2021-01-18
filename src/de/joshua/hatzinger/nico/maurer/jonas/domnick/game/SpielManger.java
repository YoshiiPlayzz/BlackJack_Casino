package de.joshua.hatzinger.nico.maurer.jonas.domnick.game;

import de.joshua.hatzinger.nico.maurer.jonas.domnick.ui.Window;

import java.util.List;

public class SpielManger {

    private final Spiel spiel;
    private final Window w;


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



    }


    public void dealerZiehen() {

    }

    public void karteZiehen() {

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
