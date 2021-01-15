package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;

import de.joshua.hatzinger.nico.maurer.jonas.domnick.game.Spieler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class GameSettings extends JFrame {

    private final JButton spielen;
    private final JTextField name;
    private final JButton abr;
    private final JLabel label;


    public GameSettings() {

        setResizable(false);
        setTitle("Black Jack");
        setSize(320, 210);
        setLayout(null);
        setFocusable(true);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(Main.class.getResource("/images/Icons/GameIcon/icon.png")).getImage());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        name = new JTextField();
        name.setBounds(40, 60, 220, 25);
        name.setFont(new Font("Segoe UI Emoji", Font.PLAIN, name.getFont().getSize()));

        label = new JLabel("Name eingeben:");
        label.setBounds(40, 25, 100, 25);

        spielen = new JButton("Spielen");
        name.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    spielen.doClick();
                } else {
                    if (label.getText().equals("Name zu kurz") || label.getText().equals("Name zu lang")) {
                        label.setForeground(Color.BLACK);
                        label.setText("Name eingeben:");
                    }
                }
            }
        });

        spielen.addActionListener(e -> {
            name.getText();
            if (name.getText().replace(" ", "").length() <= 3) {
                label.setText("Name zu kurz");
                label.setForeground(Color.RED);
            } else if (name.getText().replace(" ", "").length() > 15) {
                label.setText("Name zu lang");
                label.setForeground(Color.RED);
            } else {
                Window.addSpieler(new Spieler(name.getText()));
                dispose();
                try {
                    new Window();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        spielen.setBounds(160, 120, 100, 25);

        spielen.setBackground(new Color(0x15C0DE));
        spielen.setForeground(new Color(0xFFFFFF));
        spielen.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        abr = new JButton("Zurück");

        abr.setBackground(new Color(0x15C0DE));
        abr.setForeground(new Color(0xFFFFFF));
        abr.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        abr.addActionListener(e -> {
            dispose();
            new StartWindow();
        });
        abr.setBounds(40, 120, 100, 25);

        //Programm darf nicht gestoppt werden -> neues Startwindow wird geöffnet
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new StartWindow();
            }
        });


        add(label);
        add(name);
        add(spielen);
        add(abr);


        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
    }

}
