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

public class Window extends JFrame {


    private ImagePanel backgroundImageLabel;
    private JMenuBar bar;
    private JMenu einstellung;

    private final String dir = new File(".").getCanonicalPath()+ "\\src";


    public Window() throws IOException {


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setIconImage(new ImageIcon(dir +"\\images\\Icons\\GameIcon\\icon.png").getImage());
        getContentPane().setLayout(null);

        bar = new JMenuBar();
        einstellung = new JMenu("Einstellung");
        bar.add(einstellung);


        backgroundImageLabel = new ImagePanel(ImageIO.read(new File(dir + "\\images\\background.png")));

        backgroundImageLabel.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());

        add(bar);
        setJMenuBar(bar);
        add(backgroundImageLabel);
        setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        setTitle("Black Jack");
        setVisible(true);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                backgroundImageLabel.setBounds(0, 0, getWidth(), getHeight());
            }
        });


    }






}
