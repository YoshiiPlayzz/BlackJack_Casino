package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Window extends JFrame {


    private ImagePanel backgroundImageLabel;
    private JLabel gut;
    private String dir;


    public Window() throws IOException {
        try {
            dir = new File(".").getCanonicalPath() + "\\src";
        } catch (IOException e) {
            e.printStackTrace();
        }
        gut = new JLabel("F");

        //SetUp
        setMaximumSize(new Dimension(1936, 1056));
        setMinimumSize(new Dimension(1936, 1056));
        setTitle("Black Jack");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = new Dimension(1936,1056);
        setIconImage(new ImageIcon(dir + "\\images\\Icons\\GameIcon\\icon.png").getImage());
        getContentPane().setLayout(null);

        setGuthabenFont(8000000);


        //Muss als letztes stehen!!!!
        backgroundImageLabel = new ImagePanel(ImageIO.read(new File(dir + "\\images\\background.png")));

        backgroundImageLabel.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());

        add(backgroundImageLabel);
        setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        setLocationRelativeTo(null);
        setVisible(true);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                backgroundImageLabel.setBounds(0, 0, getWidth(), getHeight());
                if (getWidth() > 1936 || getHeight() > 1056) {
                    setSize(1936, 1056);
                    setLocationRelativeTo(null);
                }

            }
        });
    }




    //Ändert das Guthaben auf der Font
    public void setGuthabenFont(int guthaben) {
        String str = gut.getText();
        if(str.equals("F")) {
            gut.setBounds(getWidth()-430, 30, 600, 60);
            gut.setFont(new Font("Arial", Font.BOLD, 40));
        }
        gut.setText("Guthaben: " + guthaben);
        if(str.equals("F")) {
            add(gut);
            setVisible(true);
        }

    }


}