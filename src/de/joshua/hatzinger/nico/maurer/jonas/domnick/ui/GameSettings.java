package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class GameSettings extends JFrame {

    private JButton spielen;
    private JTextField name;
    private JButton abr;
    private JLabel label;


    public GameSettings() {

        setResizable(false);
        setBackground(Color.GREEN);
        setSize(440, 300);
        setLayout(null);
        setFocusable(true);
        setLocationRelativeTo(null);


        name = new JTextField();
        name.setBounds(160,80,100,25);
        add(name);


        spielen = new JButton("Spielen");
        spielen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name.getText();
                if(name.getText().length() <= 3) {
                    System.out.println("Name zu kurz");
                }else{
                    //weiter
                }
            }
        });
        spielen.setBounds(260, 140, 100, 25);
        add(spielen);


        abr = new JButton("Zurück");
        abr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               dispose();
               new StartWindow();
            }
        });
        abr.setBounds(100,140, 100, 25);
        add(abr);

        setIconImage(new ImageIcon(Main.class.getResource("/images/Icons/GameIcon/icon.png")).getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

}
