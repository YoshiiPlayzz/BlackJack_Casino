package de.joshua.hatzinger.nico.maurer.jonas.domnick.game;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {


    private final String name;
    private List<Karte> inventar;

    private boolean gewinner;

    public Entity(String name) {
        inventar = new ArrayList<>();
        this.name = name;
        this.gewinner = false;
    }

    public void setGewinner() {
        this.gewinner = true;
    }

    public boolean hatGewonnen() {
        return this.gewinner;
    }

    public String getName() {
        return name;
    }

    public List<Karte> getInventar() {
        return inventar;
    }

    public void reset() {
        getInventar().clear();
        gewinner = false;

    }


    public void setInventar(List<Karte> karte) {
        this.inventar = karte;
    }

    public boolean containsKarte(Karte karte) {
        return inventar.contains(karte);
    }

    public void aufgeben() {
        this.gewinner = false;
    }


    public int[] getKartenSumme() {
        int count1 = 0;
        int count2 = 0;
        if (!inventar.isEmpty()) {
            for (Karte karte : inventar) {
                //Es entstehen 2 Kartenwerte. Eine Value mit Ass und eine ohne Ass.
                if (karte.getValue()[1] == 1) {
                    count1 += karte.getValue()[0];
                    count2 += karte.getValue()[1];
                } else {
                    count1 += karte.getValue()[0];
                    count2 += karte.getValue()[0];
                }
            }

        }
        //Wird als int Array zurückgegeben
        return new int[]{count1, count2};
    }

    public void addKarte(Karte karte) {
        inventar.add(karte);
    }
}
