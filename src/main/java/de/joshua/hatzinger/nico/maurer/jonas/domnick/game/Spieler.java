package de.joshua.hatzinger.nico.maurer.jonas.domnick.game;

public class Spieler extends Entity {


    private int guthaben;
    private boolean istFertig;

    public Spieler(String name) {
        super(name);
    }

    public int getGuthaben() {
        return guthaben;
    }

    public void setGuthaben(int guthaben) {
        this.guthaben = guthaben;
    }

    public void addGuthaben(int guthaben) {
        if (guthaben > 0)
            this.guthaben += guthaben;
    }

    public void setGuthabenMinus(int abzug) {
        if (abzug > 0 || guthaben - abzug >= 0) {
            guthaben -= abzug;
        }
    }

    public boolean istFertig() {
        return istFertig;
    }

    public void setFertig() {
        this.istFertig = true;
    }



    public void aufgeben(int einsatz){
        super.aufgeben();
        setFertig();
        addGuthaben(einsatz/2);
    }
    @Override
    public String toString() {
        return getName();
    }
}