package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;

import javax.swing.*;
import java.awt.*;

public class LoadingScreen extends JFrame {

    private final JLabel l;
    private final JProgressBar p;

    public LoadingScreen() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Black Jack");
        setResizable(false);
        setBackground(Color.GREEN);
        setSize(440, 300);
        setLayout(null);
        setLocationRelativeTo(null);


        setIconImage(new ImageIcon(Main.class.getResource("/images/Icons/GameIcon/icon.png")).getImage());
        ImageIcon knossi = new ImageIcon(Main.class.getResource("/images/8000_Euro.gif"));
        l = new JLabel(knossi);
        l.setBounds(80, 20, knossi.getIconWidth() - 60, knossi.getIconHeight());

        p = new JProgressBar();
        p.setBounds(80, 220, knossi.getIconWidth() - 60, 20);
        p.setForeground(Color.GREEN);

        setVisible(true);
        add(l);
        add(p);
        Main.playSound();
        try {
            for (int i = 1; i < 17; i++) {
                Thread.sleep(186);

                p.setValue(100 / 14 * (i + 1));
                if (i == 16) {
                    dispose();
                    new StartWindow();
                }
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}