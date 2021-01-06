package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class StartWindow extends JFrame {

    private final JButton verlassen;
    private final JButton einstellungen;
    private final JButton spielen;
    private final JLabel blackjack;


    public StartWindow() {

        setIconImage(new ImageIcon(Main.class.getResource("/images/Icons/GameIcon/icon.png")).getImage());

        //Zentrierter Text mit Schriftart 'Arial' auf Schriftgröße 50
        blackjack = new JLabel("Blackjack", SwingConstants.CENTER);
        blackjack.setFont(new Font("Arial", Font.BOLD, 50));
        blackjack.setBounds(0, 10, 440, 60);


        //Knöpfe
        spielen = new JButton("Spielen");
        spielen.setBackground(new Color(0xaeaeae));
        spielen.setForeground(Color.WHITE);

        spielen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                spielen.setBackground(new Color(0x4F4F4F));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                spielen.setBackground(new Color(0xAEAEAE));
            }
        });
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