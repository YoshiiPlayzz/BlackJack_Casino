package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;

import de.joshua.hatzinger.nico.maurer.jonas.domnick.game.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Window extends JFrame {

    //TODO: Spielverlauf

    private final Spiel spiel;
    private final ImagePanel backgroundImageLabel;
    private final JLabel gut;
    private final JLabel muenze;
    private final JLabel dealerLabel;
    private final JLabel spielerLabel;
    private final JLabel dealerKartenWertLabel;
    private final JLabel spielerKartenWertLabel;
    private static final Map<Spieler, List<KartenLabel>> spielerList = new HashMap<>();
    private static final java.util.List<Spieler> spielerListUtil = new ArrayList<>();
    private final java.util.List<KartenLabel> dealerKartenList;



    public Window() throws IOException {

        spiel = new Spiel();
        Image avatar = ImageIO.read(Main.class.getResource("/images/Icons/UserIcon/avatar.png"));
        spielerLabel = new JLabel(new ImageIcon(avatar.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
        gut = new JLabel("F");
        Image i = new ImageIcon(ImageIO.read(Main.class.getResource("/images/Münze/Münze.png"))).getImage();
        muenze = new JLabel(new ImageIcon(i.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        dealerKartenList = new ArrayList<>();
        dealerKartenWertLabel = new JLabel();
        spielerKartenWertLabel = new JLabel();

        gut.setForeground(Color.WHITE);

        //SetUp
        setMaximumSize(new Dimension(1936, 1056));
        setMinimumSize(new Dimension(1936, 1056));
        setTitle("Black Jack");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = new Dimension(1936, 1056);
        setIconImage(new ImageIcon(Main.class.getResource("/images/Icons/GameIcon/icon.png")).getImage());
        getContentPane().setLayout(null);
        add(muenze);
        setGuthabenFont(8000000);

        Image de = ImageIO.read(Main.class.getResource("/images/Icons/KaoIcon/kaoDealer.png"));

        dealerLabel = new JLabel(new ImageIcon(de.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
        dealerLabel.setBounds(getWidth() / 2 - 250, 80, 230, 100);
        dealerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        dealerLabel.setText(spiel.getDealer().getName());
        dealerLabel.setFont(new Font("Arial", Font.BOLD, 25));
        dealerLabel.setIconTextGap(25);



        Spieler sp = new Spieler("DDDDDDDDDD");
        addSpieler(sp);
        addSpielerkarten(sp, Karte.HERZ_3);
        addSpielerkarten(sp, Karte.HERZ_6);
        addSpielerkarten(sp, Karte.HERZ_9);
        addSpielerkarten(sp, Karte.HERZ_9);
        addSpielerkarten(sp, Karte.HERZ_9);
        arrangeJlabel(sp, -100, 250);
        int jj = getStartX(sp, dealerLabel);
        arrangeJlabel(sp, jj, 250);

        setNowShownPlayer(sp);
        showCardValues(sp);


        //140


        Spieler ff = new Spieler("ZZZ");
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_SPACE) {
                    System.out.println("TTTT");
                    setNowShownPlayer(ff);
                }
                else{
                    setNowShownPlayer(sp);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        setNowShownPlayer(ff);
        addSpieler(ff);
        addSpielerkarten(ff, Karte.KREUZ_9);
        addSpielerkarten(ff, Karte.KREUZ_8);
        addSpielerkarten(ff, Karte.KREUZ_7);
        arrangeJlabel(ff, -100, 680);
        int gg = getStartX(ff, spielerLabel);
        arrangeJlabel(ff,gg,566);
        //566 ist der Wert für die obere Kante der Karte

        JLabel bb = new JLabel("I");
        bb.setBounds(100,850,3,3);
        bb.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        add(bb);


        add(dealerLabel);
        add(spielerLabel);
        add(dealerKartenWertLabel);
        add(spielerKartenWertLabel);
        //TODO: Wenn Nico fertig ist den Spielernamen getten.




        //Muss als letztes stehen!!!!
        backgroundImageLabel = new ImagePanel(ImageIO.read(Main.class.getResource("/images/background.png")));

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
        Font f = new Font("Arial", Font.BOLD, 40);
        if (str.equals("F")) {
            gut.setBounds(getWidth() - 430, 30, 600, 60);
            gut.setFont(f);
        }
        FontMetrics m = getFontMetrics(f);
        gut.setText("Guthaben: " + guthaben);
        muenze.setBounds(getWidth() - 430 + m.stringWidth(gut.getText()) - 5, 30, 60, 60);


        if (str.equals("F")) {
            add(gut);
        }

    }

    public static void addSpieler(Spieler spieler) {
        if (!spielerList.containsKey(spieler)) {
            spielerList.put(spieler, new ArrayList<>());
            spielerListUtil.add(spieler);
        }

    }

    public void addSpielerkarten(Spieler spieler, Karte karte) {
        if (spielerList.containsKey(spieler)) {
            List<KartenLabel> cc = spielerList.get(spieler);
            cc.add(new KartenLabel(karte));
            spielerList.replace(spieler, cc);

        }
    }

    public void arrangeJlabel(Spieler spieler, int x, int y) {
        if (spielerList.containsKey(spieler)) {
            for (int i = 0; i < spielerList.get(spieler).size(); i++) {
                spielerList.get(spieler).get(i).setBounds(x, y, 135, 204);
                x += 150;
                add(spielerList.get(spieler).get(i));
            }


        }
    }

    public void arrangeJLabel(Spieler spieler, int x, int y, int abstand) {
        if (spielerList.containsKey(spieler)) {
            for (int i = 0; i < spielerList.get(spieler).size(); i++) {
                spielerList.get(spieler).get(i).setBounds(x, y, 135, 204);
                x += abstand;
                add(spielerList.get(spieler).get(i));
            }


        }
    }

    public int getStartX(Spieler spieler, JLabel jLabel) {
        int x = jLabel.getX();
        int size = 0;
        if (spielerList.get(spieler).size() == 1) {
            x += jLabel.getWidth()/2;
            return x - spielerList.get(spieler).get(0).getWidth() / 2;
        }else {
            for (int i = 0; i < spielerList.get(spieler).size(); i++) {
                size += spielerList.get(spieler).get(i).getWidth();
            }
            size += (spielerList.get(spieler).size() - 1) * 15;
            x += jLabel.getWidth()/2;
            return x - size / 2;
        }
    }

    public int getStartX(Spieler spieler, JLabel jLabel, int abstand) {
        int x = jLabel.getX();
        int size = 0;
        if (spielerList.get(spieler).size() == 1) {
            x += jLabel.getWidth()/2;
            return x - spielerList.get(spieler).get(0).getWidth() / 2;
        }else {
            for (int i = 0; i < spielerList.get(spieler).size(); i++) {
                size += spielerList.get(spieler).get(i).getWidth();
            }
            size += (spielerList.get(spieler).size() - 1) * (abstand - spielerList.get(spieler).get(0).getWidth());
            x += jLabel.getWidth()/2;
            return x - size / 2;
        }
    }

    public void setNowShownPlayer(Spieler spieler){
        int x = 0;
        spielerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        spielerLabel.setText(spieler.getName());
        Font f = new Font("Arial", Font.BOLD, 25);
        spielerLabel.setFont(f);

        FontMetrics m = getFontMetrics(f);
        spielerLabel.setBounds(-100, 500, m.stringWidth(spieler.getName()) + spielerLabel.getIcon().getIconWidth() + 25 + 50, 100);

        if(spielerLabel.getWidth() < dealerLabel.getWidth()){
            x = (dealerLabel.getWidth() - spielerLabel.getWidth()) / 2;
            x += dealerLabel.getX();
        }else{
            x = (spielerLabel.getWidth() - dealerLabel.getWidth()) / 2;
            x = dealerLabel.getX() - x;
        }

        spielerLabel.setBounds(x, 840, m.stringWidth(spieler.getName()) + spielerLabel.getIcon().getIconWidth() + 25 + 50, 100);
        spielerLabel.setIconTextGap(25);
    }

    public void showCardValues(Spieler spieler){
        //TODO: Spielmanager Kartenwerte bekommen. Im Spielmanager bereits aussortieren. Nur dir passenden Werte. Immer den größeren außer er ist über 21
        dealerKartenWertLabel.setBounds(1510,800,300,50);
        dealerKartenWertLabel.setFont(new Font("Arial", Font.BOLD,25));
        dealerKartenWertLabel.setForeground(Color.WHITE);
        dealerKartenWertLabel.setText("Kartenwert vom Dealer: ");
        spielerKartenWertLabel.setBounds(1510,850,300,50);
        spielerKartenWertLabel.setFont(new Font("Arial", Font.BOLD,25));
        spielerKartenWertLabel.setForeground(Color.WHITE);
        spielerKartenWertLabel.setText("Kartenwert von dir: ");

    }

    public int getXAnimation(JPanel jpanel){
        return (jpanel.getX() + jpanel.getWidth() / 2) - (230 / 2);
    }





}