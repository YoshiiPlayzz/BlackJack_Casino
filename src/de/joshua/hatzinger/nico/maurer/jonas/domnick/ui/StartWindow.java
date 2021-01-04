package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class StartWindow extends JFrame {


    private JButton verlassen;
    private JButton einstellungen;
    private JButton spielen;
    private JLabel blackjack;

    public StartWindow() {
        spielen = new JButton("Spielen");
        einstellungen = new JButton("Einstellungen");
        verlassen = new JButton("Verlassen");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Black Jack");
        setBackground(Color.GREEN);
        setSize(440, 670);
        setLayout(new GridLayout(0,1));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("Size: " + getWidth() + "x" + getHeight());
            }
        });
        add(spielen);
        add(einstellungen);
        add(verlassen);

    }
}
