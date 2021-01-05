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
    private JMenuBar bar;
    private JMenu einstellung;
    private JLabel gut = new JLabel("F");

    private final String dir = new File(".").getCanonicalPath() + "\\src";


    public Window() throws IOException {

        setMaximumSize(new Dimension(1936, 1056));
        setMinimumSize(new Dimension(993, 559));
        setTitle("Black Jack");




        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setIconImage(new ImageIcon(dir + "\\images\\Icons\\GameIcon\\icon.png").getImage());
        getContentPane().setLayout(null);

        setGuthabenFont(8000000);


        //Muss als letztes stehen!!!!
        backgroundImageLabel = new ImagePanel(ImageIO.read(new File(dir + "\\images\\background.png")));

        //backgroundImageLabel.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());

        add(backgroundImageLabel);
        //setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        setVisible(true);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                backgroundImageLabel.setBounds(0, 0, getWidth(), getHeight());
                if (getWidth() > 1936 || getHeight() > 1056) {
                    setSize(1936, 1056);
                    setLocationRelativeTo(null);
                }
                gut.setBounds(getWidth()-300, 20, 600, 25);
            }
        });
    }





    public void setGuthabenFont(int guthaben) {
        String str = gut.getText();
        if(str.equals("F")) {
            gut.setBounds(getWidth()-300, 20, 600, 25);
            gut.setFont(new Font("Arial", Font.BOLD, 20));
        }
        gut.setText("Guthaben: " + guthaben);
        if(str.equals("F")) {
            add(gut);
            setVisible(true);
        }

    }


}