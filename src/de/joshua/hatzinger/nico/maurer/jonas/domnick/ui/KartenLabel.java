package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;

import de.joshua.hatzinger.nico.maurer.jonas.domnick.game.Karte;

import javax.swing.*;
import java.awt.*;

public class KartenLabel extends JLabel {
    private final Karte karte;
    private ImageIcon i;

    public KartenLabel(Karte karte) {
        this.karte = karte;
        this.i = new ImageIcon(Main.class.getResource(this.karte.getKartenPfad()));
        setIcon(i);
    }

    public Karte getKarte() {
        return karte;
    }


    //Setzt automatisch die Größe des Bildes auf die Werte width und height
    @Override
    public void setBounds(int x, int y, int width, int height) {
        setSize(width, height);
        super.setBounds(x, y, width, height);

    }

    public void setSize(int w, int h) {
        this.i = new ImageIcon(i.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        setIcon(i);
    }


}
