package de.joshua.hatzinger.nico.maurer.jonas.domnick;

public enum Farbe {
    KARO(true),
    HERZ(true),
    PIK(false),
    KREUZ(false);


    private boolean istRot;

    Farbe(boolean istRot) {
        this.istRot = istRot;

    }

    public boolean istRot() {
        return istRot;
    }
}
