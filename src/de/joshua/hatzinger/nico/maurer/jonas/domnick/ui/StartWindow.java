package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;


import de.joshua.hatzinger.nico.maurer.jonas.domnick.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class StartWindow extends JFrame {

    private String dir;
    private JButton verlassen;
    private JButton einstellungen;
    private JButton spielen;
    private JLabel blackjack;


    public StartWindow() {
        try {
            dir = new File(".").getCanonicalPath() + "\\src";
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(dir);

        //Zentrierter Text mit Schriftart 'Arial' auf Schriftgröße 50
        blackjack = new JLabel("Blackjack", SwingConstants.CENTER);
        blackjack.setFont(new Font("Arial", Font.BOLD, 50));
        blackjack.setBounds(0, 10, 440, 60);


        //Knöpfe
        spielen = new JButton("Spielen");
        spielen.setBounds(30, 160, 360, 50);
        einstellungen = new JButton("Einstellungen");
        einstellungen.setBounds(30, 300, 360, 50);
        verlassen = new JButton("Verlassen");
        verlassen.setBounds(30, 450, 360, 50);


        verlassen.addActionListener(e -> {

            int dialogResult = JOptionPane.showConfirmDialog(getContentPane(), "Willst du Black Jack schließen?", "Achtung", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                System.exit(-1);
            }
        });

        spielen.addActionListener(e -> {
            dispose();
            try {
                new Window();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("Black Jack");
        setResizable(false);
        setBackground(Color.GREEN);
        setSize(440, 670);
        setLayout(null);
        setFocusable(true);
        setLocationRelativeTo(null);

        addKeyListener(new KeyAdapter() {


            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    verlassen.doClick();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    spielen.doClick();
                } else if (e.getKeyCode() == KeyEvent.VK_ALT) {
                    Main.playSound();
                }
            }
        });

        setVisible(true);


        add(blackjack);
        add(spielen);
        add(einstellungen);
        add(verlassen);


    }

}