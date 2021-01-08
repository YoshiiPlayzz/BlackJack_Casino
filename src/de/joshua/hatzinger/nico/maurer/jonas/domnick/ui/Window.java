package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;

import de.joshua.hatzinger.nico.maurer.jonas.domnick.game.Karte;
import de.joshua.hatzinger.nico.maurer.jonas.domnick.game.Spiel;
import de.joshua.hatzinger.nico.maurer.jonas.domnick.game.Spieler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Window extends JFrame {

    //TODO: Spielverlauf

    private final ImagePanel backgroundImageLabel;
    private final JLabel gut;
    private final JLabel muenze;
    private static final Map<Spieler, List<KartenLabel>> spielerList = new HashMap<>();
    private final JLabel dealer;
    private final Spiel spiel;
    private final java.util.List<KartenLabel> dealerKartenList;



    public Window() throws IOException {

        dealerKartenList = new ArrayList<>();

        spiel = new Spiel();

        gut = new JLabel("F");

        Image i = new ImageIcon(ImageIO.read(Main.class.getResource("/images/Münze/Münze.png"))).getImage();

        muenze = new JLabel(new ImageIcon(i.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

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
        Image i1 = ImageIO.read(Main.class.getResource("/images/Karten/Backsite.png"));
        dealer = new JLabel(new ImageIcon(de.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
        dealer.setBounds(getWidth() / 2 - 250, 80, 230, 100);
        dealer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        dealer.setText(spiel.getDealer().getName());
        dealer.setFont(new Font("Arial", Font.BOLD, 25));
        dealer.setIconTextGap(25);


        Spieler sp = new Spieler("Test");
        addSpieler(sp);
        addSpielerkarten(sp, Karte.HERZ_3);
        addSpielerkarten(sp, Karte.HERZ_6);
        addSpielerkarten(sp, Karte.HERZ_9);
        System.out.println(spielerList.get(sp).get(0).getWidth());
        arrangeJlabel(sp, -100, 250);
        int jj = getStartX(sp, getWidth() / 2 - 250);
        arrangeJlabel(sp, jj, 250);

        //140


        add(dealer);
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

    public int getStartX(Spieler spieler, int x) {
        int size;
        if (spielerList.get(spieler).size() == 1) {
            return x - spielerList.get(spieler).get(0).getWidth() / 2;

        } else if (spielerList.get(spieler).size() == 2) {
            size = spielerList.get(spieler).get(0).getWidth() * 2 + 150;
            return x;
        } else {
            for (int i = 0; i < spielerList.get(spieler).size(); i++) {
                size =+ spielerList.get(spieler).get(i).getWidth();
                System.out.println(spielerList.get(spieler).size());
                System.out.println(spielerList.get(spieler).get(i).getWidth());
                System.out.println(size);
            }
            size =+ (spielerList.get(spieler).size() - 1) * 150;
            System.out.println(size);
            x -= size / 2;
            return x;
        }
    }


}