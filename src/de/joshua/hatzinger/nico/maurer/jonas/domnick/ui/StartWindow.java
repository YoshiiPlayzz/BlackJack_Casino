package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartWindow extends JFrame {

    private final JButton verlassen;
    private final JButton regeln;
    private final JButton spielen;
    private final JLabel blackjack;


    public StartWindow() {

        setIconImage(new ImageIcon(Main.class.getResource("/images/Icons/GameIcon/icon.png")).getImage());

        //Zentrierter Text mit Schriftart 'Arial' auf Schriftgröße 50


        ImageIcon header = new ImageIcon(new ImageIcon(Main.class.getResource("/images/header.png")).getImage().getScaledInstance(240, 120, Image.SCALE_SMOOTH));
        blackjack = new JLabel(header);
        //blackjack.setFont(new Font("Arial", Font.BOLD, 50));
        blackjack.setBounds(0, 10, 420, 120);

        Font buttonFont = new Font("Arial Black", Font.BOLD, 36);

        //Knöpfe
        spielen = new JButton("Spielen");
        spielen.setBounds(30, 160, 360, 50);

        spielen.setBorder(BorderFactory.createLineBorder(new Color(0x15C0DE), 1, true));
        spielen.setBackground(new Color(0x15C0DE));
        spielen.setForeground(new Color(0xFFFFFF));
        spielen.setFont(buttonFont);

        spielen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                spielen.setBackground(new Color(0x129EB7));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                spielen.setBackground(new Color(0x15C0DE));
            }
        });

        spielen.addActionListener(e -> {
            dispose();
            new GameSettings();
        });

        regeln = new JButton("Regeln");
        regeln.setBounds(30, 300, 360, 50);
        regeln.setBackground(new Color(0x15C0DE));
        regeln.setForeground(new Color(0xFFFFFF));
        regeln.setFont(buttonFont);
        regeln.setBorder(BorderFactory.createLineBorder(new Color(0x15C0DE), 1, true));

        regeln.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                regeln.setBackground(new Color(0x129EB7));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                regeln.setBackground(new Color(0x15C0DE));
            }
        });

        regeln.addActionListener(e -> {
            dispose();
            new RegelWindow();
        });


        verlassen = new JButton("Verlassen");
        verlassen.setBounds(30, 450, 360, 50);
        verlassen.setBackground(new Color(0x15C0DE));
        verlassen.setForeground(new Color(0xFFFFFF));
        verlassen.setFont(buttonFont);
        verlassen.setBorder(BorderFactory.createLineBorder(new Color(0x15C0DE), 1, true));

        verlassen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                verlassen.setBackground(new Color(0x129EB7));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                verlassen.setBackground(new Color(0x15C0DE));
            }
        });

        verlassen.addActionListener(e -> {

            int dialogResult = JOptionPane.showConfirmDialog(getContentPane(), "Willst du Black Jack schließen?", "Achtung", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                System.exit(-1);
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

        getContentPane().setBackground(Color.WHITE);

        add(blackjack);
        add(spielen);
        add(regeln);
        add(verlassen);


    }

}