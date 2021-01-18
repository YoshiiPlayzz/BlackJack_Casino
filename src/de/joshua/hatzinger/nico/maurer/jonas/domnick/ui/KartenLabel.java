package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;

import de.joshua.hatzinger.nico.maurer.jonas.domnick.game.Karte;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

public class KartenLabel extends JLabel {
    private final Karte karte;
    private final ImageIcon i;
    private ImageIcon used;
    private boolean karteVerdeckt;


    public KartenLabel(Karte karte) {
        this.karte = karte;
        this.karteVerdeckt = false;
        this.i = new ImageIcon(Main.class.getResource(this.karte.getKartenPfad()));
        this.used = i;
        setIcon(used);
    }

    public Karte getKarte() {
        return karte;
    }


    public void karteUmdrehen() {
        if (!karteVerdeckt) {
            ImageIcon x = new ImageIcon(Main.class.getResource("/images/Karten/Backsite.png"));
            used = new ImageIcon(x.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
            setIcon(used);
        } else {
            used = this.i;
            setIcon(used);
        }
        karteVerdeckt = !karteVerdeckt;
    }


    public void animate(int x, int y, double delay) {

        int xDiff = (getX() - x);
        int yDiff = (getY() - y);

        boolean xBigger = x > getX();
        boolean yBigger = y < getY();
        System.out.println(xBigger + "->" + yBigger);

        Timer t = new Timer(100, e -> {

            int px = (int) (xDiff / delay / 10);
            int py = (int) (yDiff / delay / 10);

            setBounds(getX() - px, getY() - py, getWidth(), getHeight());

        });
        t.start();

        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                t.stop();
                if (getX() != x || getY() != y) {
                    animate(x, y, 0.1);
                }
                System.out.println((getX() == x ? "X correct: " : "X wrong: ") + getX() + " - " + (getY() == y ? "Y correct: " : "Y wrong: ") + getY());
            }
        }, (long) (delay * 1000) + 100);


    }


    //Setzt automatisch die Größe des Bildes auf die Werte width und height
    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        setSize(width, height);

    }

    public void setSize(int width, int height) {
        if (width != 0 && width != getWidth() || height != 0 || height != getHeight()) {
            used = new ImageIcon(used.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
            setIcon(used);
        }
    }

}
