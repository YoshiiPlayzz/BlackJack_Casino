package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;

import de.joshua.hatzinger.nico.maurer.jonas.domnick.game.Karte;

import javax.swing.*;

public class KartenLabel extends JLabel {
    private Karte karte;
    public KartenLabel(Karte karte) {
        this.karte = karte;
        setIcon(new ImageIcon(Main.class.getResource(this.karte.getKartenPfad())));
    }

    public Karte getKarte() {
        return karte;
    }


}
