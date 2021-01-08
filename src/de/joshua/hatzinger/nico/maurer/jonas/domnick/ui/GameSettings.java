package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;

import de.joshua.hatzinger.nico.maurer.jonas.domnick.game.Spieler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GameSettings extends JFrame {

    private JButton spielen;
    private JTextField name;
    private JButton abr;
    private JLabel label;


    public GameSettings() {

        setResizable(false);

        setSize(440, 300);
        setLayout(null);
        setFocusable(true);
        setLocationRelativeTo(null);

        name = new JTextField();
        name.setBounds(160,80,100,25);
        add(name);

        label = new JLabel("Name eingeben");
        label.setBounds(165, 40,100,25);
        add(label);

        spielen = new JButton("Spielen");
        name.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()== KeyEvent.VK_ENTER){
                    spielen.doClick();
                }else{
                    if(label.getText().equals("Name zu kurz")){
                        label.setText("Name eingeben");
                    }
                }
            }
        });
        
        spielen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name.getText();
                if(name.getText().length() <= 3) {
                    label.setText("Name zu kurz");
                }else{
                    Window.addSpieler(new Spieler(name.getText()));
                    dispose();
                    try {
                        new Window();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        spielen.setBounds(220, 140, 100, 25);
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
