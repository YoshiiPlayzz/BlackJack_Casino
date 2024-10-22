package de.joshua.hatzinger.nico.maurer.jonas.domnick.ui;

import de.joshua.hatzinger.nico.maurer.jonas.domnick.game.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.*;

public class Window extends JFrame {

    //TODO: Spielverlauf

    private static final Map<Spieler, List<KartenLabel>> spielerList = new HashMap<>();
    private static final java.util.List<Spieler> spielerListUtil = new ArrayList<>();
    private final ImagePanel backgroundImageLabel;
    private final JLabel gut;
    private final JLabel muenze;
    private final JLabel dealerLabel;
    private final JLabel spielerLabel;
    private final JLabel dealerKartenWertLabel;
    private final JLabel spielerKartenWertLabel;
    private final JLabel dealerKartenWertLabelValue;
    private final JLabel spielerKartenWertLabelValue;
    private final java.util.List<KartenLabel> dealerKartenList;
    private final JSpinner einsatz;
    private final JButton surrender;
    private final JButton halten;
    private final JButton ziehen;
    private final JButton Verdoppeln;
    private final SpielManger sm;
    private final KartenLabel Kartenstapel;
    private final KartenLabel Kartenstapel2;
    private final KartenLabel Kartenstapel3;
    private final JLabel verloren;
    private final HashMap<Spieler, Integer> temp_einsatz = new HashMap<>();


    public Window() throws IOException {
        sm = new SpielManger(new Spiel(), this);
        surrender = new JButton(new ImageIcon(new ImageIcon(Main.class.getResource("/images/FrankreichFahne/FrankreichFlagge2.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        halten = new JButton(new ImageIcon(new ImageIcon(Main.class.getResource("/images/Buttons/715399.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        ziehen = new JButton(new ImageIcon(new ImageIcon(Main.class.getResource("/images/Buttons/2182944.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        Verdoppeln = new JButton(new ImageIcon(new ImageIcon(Main.class.getResource("/images/Buttons/x2-512.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        verloren = new JLabel(new ImageIcon(Main.class.getResource("/images/KaoCasino.png")));
        verloren.setBounds(400, 100, 677, 676);
        verloren.setVisible(false);

        surrender.setToolTipText("Aufgeben/Surrender");
        halten.setToolTipText("Karten halten");
        ziehen.setToolTipText("Karte ziehen");
        Verdoppeln.setToolTipText("Einsatz verdoppeln");

        surrender.setVisible(false);
        halten.setVisible(false);
        ziehen.setVisible(false);
        Verdoppeln.setVisible(false);

        surrender.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        halten.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ziehen.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Verdoppeln.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        surrender.addActionListener(l -> {

            if (sm.getSpiel().getPhase().equals(SpielPhase.SPIELSTART)) {
                sm.surrender();
                surrender.setVisible(false);
                Verdoppeln.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Wait that's illegal");
            }
        });

        halten.addActionListener(l -> {
            if (!sm.getSpiel().getPhase().equals(SpielPhase.DEALER_ZUG) || !sm.getSpiel().getPhase().equals(SpielPhase.SPIELENDE)) {
                sm.halten();
                surrender.setVisible(false);
                Verdoppeln.setVisible(false);
            }
        });
        ziehen.addActionListener(l -> {
            if (!sm.getSpiel().getPhase().equals(SpielPhase.DEALER_ZUG) || !sm.getSpiel().getPhase().equals(SpielPhase.SPIELENDE)) {
                sm.karteZiehen();
                surrender.setVisible(false);
                Verdoppeln.setVisible(false);
            }


        });
        Verdoppeln.addActionListener(l -> {
            if (sm.getSpiel().getPhase().equals(SpielPhase.SPIELSTART)) {
                sm.doppeln();


            } else {
                JOptionPane.showMessageDialog(this, "Wait that's illegal");
            }
        });

        ziehen.setBounds(50, 930, 40, 40);
        halten.setBounds(100, 930, 40, 40);
        surrender.setBounds(150, 930, 40, 40);
        Verdoppeln.setBounds(200, 930, 40, 40);


        Kartenstapel = new KartenLabel(Karte.HERZ_2);
        Kartenstapel2 = new KartenLabel(Karte.HERZ_2);
        Kartenstapel3 = new KartenLabel(Karte.HERZ_2);

        Kartenstapel.setBounds(1120, 90, 135, 204);
        Kartenstapel.karteUmdrehen();
        add(Kartenstapel);

        Kartenstapel2.setBounds(1125, 95, 135, 204);
        Kartenstapel2.karteUmdrehen();
        add(Kartenstapel2);

        Kartenstapel3.setBounds(1130, 100, 135, 204);
        Kartenstapel3.karteUmdrehen();
        add(Kartenstapel3);

        dealerKartenList = new ArrayList<>();


        Image avatar = ImageIO.read(Main.class.getResource("/images/Icons/UserIcon/avatar.png"));
        spielerLabel = new JLabel(new ImageIcon(avatar.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
        gut = new JLabel("F");

        Image blankCardDealerImage = new ImageIcon(ImageIO.read(Main.class.getResource("/images/leer_dealer.png"))).getImage();
        Image blankCardPlayerImage = new ImageIcon(ImageIO.read(Main.class.getResource("/images/leer_spieler.png"))).getImage();
        Image muenzeImage = new ImageIcon(ImageIO.read(Main.class.getResource("/images/Münze/Münze.png"))).getImage();
        muenze = new JLabel(new ImageIcon(muenzeImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        dealerKartenWertLabel = new JLabel(new ImageIcon(blankCardDealerImage.getScaledInstance(135, 204, Image.SCALE_SMOOTH)));
        spielerKartenWertLabel = new JLabel(new ImageIcon(blankCardPlayerImage.getScaledInstance(135, 204, Image.SCALE_SMOOTH)));
        dealerKartenWertLabel.setBounds(1530, 785, 135, 204);
        spielerKartenWertLabel.setBounds(1740, 785, 135, 204);
        dealerKartenWertLabelValue = new JLabel("Test1");
        spielerKartenWertLabelValue = new JLabel("Test2");
        SpinnerNumberModel m = new SpinnerNumberModel();
        m.setMinimum(100);
        m.setMaximum(800000);
        m.setStepSize(10);
        einsatz = new JSpinner(m);
        einsatz.setValue(100);
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
        setGuthabenFont(8000);

        Image de = ImageIO.read(Main.class.getResource("/images/Icons/KaoIcon/kaoDealer.png"));

        dealerLabel = new JLabel(new ImageIcon(de.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
        dealerLabel.setBounds(getWidth() / 2 - 250, 80, 230, 100);
        dealerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        dealerLabel.setText(sm.getSpiel().getDealer().getName());
        dealerLabel.setFont(new Font("Arial", Font.BOLD, 25));
        dealerLabel.setIconTextGap(25);




        /*Spieler sp = new Spieler("DDDDDDDDDDDDDDD");
        addSpieler(sp);
        //addSpielerkarten(sp, Karte.HERZ_3);
        //addSpielerkarten(sp, Karte.HERZ_6);
        //addSpielerkarten(sp, Karte.HERZ_9);
        //addSpielerkarten(sp, Karte.HERZ_9);
        //addSpielerkarten(sp, Karte.HERZ_9);
        //arrangeJlabel(sp, -100, 250);
        //int jj = getStartX(sp, dealerLabel);
       // arrangeJlabel(sp, jj, 250);

        setNowShownPlayer(sp);

        showCardValueSpieler(sp);
        showCardValueDealer();

        addDealerkarten(Karte.KREUZ_9);
        addDealerkarten(Karte.HERZ_ASS);


        //140


        Spieler ff = new Spieler("ZZZ");
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_SPACE) {
                    System.out.println("TTTT");
                    setNowShownPlayer(ff);
                    showCardValueSpieler(ff);
                }
                else{
                    setNowShownPlayer(sp);
                    showCardValueSpieler(sp);
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
        //addSpielerkarten(ff, Karte.KREUZ_9);
        //addSpielerkarten(ff, Karte.KREUZ_8);
        addSpielerkarten(ff, Karte.KREUZ_7);
        addSpielerkarten(ff, Karte.HERZ_ASS);
        arrangeJlabel(ff, -100, 680);
        int gg = getStartX(ff, spielerLabel);
        arrangeJlabel(ff,gg,566);
        showCardValueSpieler(ff);
        //566 ist der Wert für die obere Kante der Karte vom Spieler

        addDealerkarten(Karte.HERZ_ASS);
        addDealerkarten(Karte.HERZ_3);
        arrangeDealerJlabel(-100, 250);
        int hh = getStartXDealer(dealerLabel);
        arrangeDealerJlabel(hh, 250);*/
        add(surrender);
        add(halten);
        add(ziehen);
        add(Verdoppeln);
        add(dealerLabel);
        add(spielerLabel);
        add(dealerKartenWertLabelValue);
        add(spielerKartenWertLabelValue);
        add(dealerKartenWertLabel);
        add(einsatz);
        add(spielerKartenWertLabel);
        add(verloren);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //TODO: Abbrechen
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

                }
            }


        });


        //Muss als letztes stehen!!!!
        backgroundImageLabel = new ImagePanel(ImageIO.read(Main.class.getResource("/images/background.png")));

        backgroundImageLabel.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());

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

        /*
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ALT) {

                    KartenLabel l = spielerList.get(sp).get(2);
                    spielerList.get(sp).get(0).animate(l.getX(), l.getY(), 0.3);
                    spielerList.get(sp).get(1).animate(l.getX(), l.getY(), 0.3);
                    spielerList.get(sp).get(3).animate(l.getX(), l.getY(), 0.3);
                    spielerList.get(sp).get(4).animate(l.getX(), l.getY(), 0.3);
                    new java.util.Timer().schedule(new java.util.TimerTask() {
                        @Override
                        public void run() {

                            for (int i = 1; i < spielerList.get(sp).size(); i++) {
                                remove(spielerList.get(sp).get(i));
                            }
                            spielerList.get(sp).get(0).karteUmdrehen();
                            spielerList.get(sp).get(0).animate(20, 20, 0.3);

                        }
                    }, 1000);

                }
            }
        });*/


    }

    public static void addSpieler(Spieler spieler) {
        if (!spielerList.containsKey(spieler)) {
            spieler.addGuthaben(8000);
            spielerList.put(spieler, new ArrayList<>());
            spielerListUtil.add(spieler);
        }

    }

    //Fügt einen Spieler zu dem Spiel hinzu

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

    //Fügt dem Spieler die Kartenobjekte hinzu, die auf dem JFrame angezeigt werden sollen
    public void addSpielerkarten(Spieler spieler, Karte karte) {
        if (spielerList.containsKey(spieler)) {

            List<KartenLabel> cc = spielerList.get(spieler);
            cc.add(new KartenLabel(karte));
            spielerList.replace(spieler, cc);

        }
    }

    //Fügt dem Dealer die Kartenobjekte hinzu, die auf dem JFrame angezeigt werden sollen
    public void addDealerkarten(Karte karte, boolean verdeckt) {
        KartenLabel k = new KartenLabel(karte, verdeckt, 135, 204);

        dealerKartenList.add(k);

        arrangeDealerJlabel(-300, -300);

    }


    //Zentriert die Karten an den Namenslabel

    public void arrangeJlabel(Spieler spieler, int x, int y) {
        if (spielerList.containsKey(spieler)) {
            for (int i = 0; i < spielerList.get(spieler).size(); i++) {
                spielerList.get(spieler).get(i).setBounds(x, y, 135, 204);
                x += 150;
                add(spielerList.get(spieler).get(i));
            }

        }
    }

    //Zentriert die Karten an den Namenslabel mit einem bestimmten Abstand
    public void arrangeJLabel(Spieler spieler, int x, int y, int abstand) {
        if (spielerList.containsKey(spieler)) {
            for (int i = 0; i < spielerList.get(spieler).size(); i++) {
                spielerList.get(spieler).get(i).setBounds(x, y, 135, 204);
                x += abstand;
                add(spielerList.get(spieler).get(i));
            }


        }
    }

    //Zentriert die Karten vom Dealer an den Namenslabel
    public void arrangeDealerJlabel(int x, int y) {
        for (int i = 0; i < dealerKartenList.size(); i++) {
            dealerKartenList.get(i).setBounds(x, y, 135, 204);
            x += 150;
            add(dealerKartenList.get(i));
        }
    }

    public int getStartXDealer(JLabel jLabel) {
        int x = jLabel.getX();
        int size = 0;
        if (dealerKartenList.size() == 1) {
            x += jLabel.getWidth() / 2;
            return x - 135 / 2;
        } else {
            for (int i = 0; i < dealerKartenList.size(); i++) {
                size += dealerKartenList.get(i).getWidth();
            }
            size += (dealerKartenList.size() - 1) * 15;
            x += jLabel.getWidth() / 2;
            return x - size / 2;
        }
    }

    public int getStartX(Spieler spieler, JLabel jLabel) {
        int x = jLabel.getX();
        int size = 0;
        if (spielerList.get(spieler).size() == 1) {
            x += jLabel.getWidth() / 2;
            return x - spielerList.get(spieler).get(0).getWidth() / 2;
        } else {
            for (int i = 0; i < spielerList.get(spieler).size(); i++) {
                size += spielerList.get(spieler).get(i).getWidth();
            }
            size += (spielerList.get(spieler).size() - 1) * 15;
            x += jLabel.getWidth() / 2;
            return x - size / 2;
        }
    }

    public int getStartX(Spieler spieler, JLabel jLabel, int abstand) {
        int x = jLabel.getX();
        int size = 0;
        if (spielerList.get(spieler).size() == 1) {
            x += jLabel.getWidth() / 2;
            return x - spielerList.get(spieler).get(0).getWidth() / 2;
        } else {
            for (int i = 0; i < spielerList.get(spieler).size(); i++) {
                size += spielerList.get(spieler).get(i).getWidth();
            }
            size += (spielerList.get(spieler).size() - 1) * (abstand - spielerList.get(spieler).get(0).getWidth());
            x += jLabel.getWidth() / 2;
            return x - size / 2;
        }
    }


    public void setNowShownPlayer(Spieler spieler) {
        int x = 0;
        spielerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        spielerLabel.setText(spieler.getName());
        Font f = new Font("Arial", Font.BOLD, 25);
        spielerLabel.setFont(f);

        FontMetrics m = getFontMetrics(f);
        spielerLabel.setBounds(-100, 500, m.stringWidth(spieler.getName()) + spielerLabel.getIcon().getIconWidth() + 25 + 50, 100);

        if (spielerLabel.getWidth() < dealerLabel.getWidth()) {
            x = (dealerLabel.getWidth() - spielerLabel.getWidth()) / 2;
            x += dealerLabel.getX();
        } else {
            x = (spielerLabel.getWidth() - dealerLabel.getWidth()) / 2;
            x = dealerLabel.getX() - x;
        }

        spielerLabel.setBounds(x, 840, m.stringWidth(spieler.getName()) + spielerLabel.getIcon().getIconWidth() + 25 + 50, 100);
        spielerLabel.setIconTextGap(25);
    }

    public void showCardValueSpieler(Spieler spieler) {
        //TODO: Spielmanager Kartenwerte bekommen. Im Spielmanager bereits aussortieren. Nur dir passenden Werte. Immer den größeren außer er ist über 21
        Font f = new Font("Arial", Font.BOLD, 25);
        FontMetrics m = getFontMetrics(f);
        spielerKartenWertLabelValue.setFont(f);
        spielerKartenWertLabelValue.setBounds(getBlankXPos(spielerKartenWertLabel, spielerKartenWertLabelValue, f), 850, m.stringWidth(spielerKartenWertLabelValue.getText()), m.getHeight());
        int cc = 0;
        for (int c : spieler.getKartenSumme()) {
            System.out.println("Spielersumme: " + c);

            if (c > cc && c <= 21) {
                cc = c;
                System.out.println("0");
            } else if (c < cc && c <= 21) {
                System.out.println("1");
            } else {
                cc = c;
                System.out.println("2");
            }
        }
        spielerKartenWertLabelValue.setText(String.valueOf(cc));
        spielerKartenWertLabelValue.setBounds(getBlankXPos(spielerKartenWertLabel, spielerKartenWertLabelValue, f), 850, m.stringWidth(spielerKartenWertLabelValue.getText()) + 10, m.getHeight());

    }

    public void showCardValueDealer() {
        Font f = new Font("Arial", Font.BOLD, 25);
        FontMetrics m = getFontMetrics(f);
        dealerKartenWertLabelValue.setFont(f);
        int cc = 0;
        for (int c : sm.getSpiel().getDealer().getKartenSumme()) {
            System.out.println(c);

            if (c > cc && c <= 21) {
                cc = c;
                System.out.println("0");
            } else if (c < cc && c <= 21) {
                System.out.println("1");
            } else {
                cc = c;
                System.out.println("2");
            }
        }
        dealerKartenWertLabelValue.setText(String.valueOf(cc));
        dealerKartenWertLabelValue.setBounds(getBlankXPos(dealerKartenWertLabel, dealerKartenWertLabelValue, f), 850, m.stringWidth(dealerKartenWertLabelValue.getText()) + 10, m.getHeight());

    }


    //Zeigt die aufgedeckten Karten vom Dealer an

    public void showCardValueDealerVerdeckt() {
        Font f = new Font("Arial", Font.BOLD, 25);
        FontMetrics m = getFontMetrics(f);
        dealerKartenWertLabelValue.setFont(f);
        int cc = 0;
        for (KartenLabel k : dealerKartenList) {
            Karte karte = k.getKarte();
            int w1 = karte.getValue()[0];
            int w2 = karte.getValue()[1];
            if (!k.isKarteVerdeckt()) {
                cc += w1;
            }

        }
        dealerKartenWertLabelValue.setText(String.valueOf(cc));
        dealerKartenWertLabelValue.setBounds(getBlankXPos(dealerKartenWertLabel, dealerKartenWertLabelValue, f), 850, m.stringWidth(dealerKartenWertLabelValue.getText()) + 10, m.getHeight());

    }


    public int getBlankXPos(JLabel jLabelFrame, JLabel jLabelValue, Font f) {
        int x = jLabelFrame.getX() + jLabelFrame.getWidth() / 2;
        FontMetrics m = getFontMetrics(f);
        x = x - m.stringWidth(jLabelValue.getText()) / 2;
        return x;
    }


    public int getXAnimation(JPanel jpanel) {
        return (jpanel.getX() + jpanel.getWidth() / 2) - (230 / 2);
    }

    public void refreshBck() {
        remove(backgroundImageLabel);
        add(backgroundImageLabel);
    }

    public void spielStart() {
        surrender.setVisible(true);
        halten.setVisible(true);
        ziehen.setVisible(true);
        Verdoppeln.setVisible(true);
        sm.getSpiel().setPhase(SpielPhase.SPIELSTART);
        java.util.Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Spieler s : sm.getSpiel().getSpieler()) {

                    if (s.getKartenSumme()[0] == 21 || s.getKartenSumme()[1] == 21) {
                        s.setGewinner();
                        sm.getSpiel().naechsterZug();
                    }
                    if (s.getKartenSumme()[0] > 21 && s.getKartenSumme()[1] > 21) {

                        sm.getSpiel().naechsterZug();

                    }

                }
                if (sm.getSpiel().istJederFertig()) {
                    sm.getSpiel().setPhase(SpielPhase.DEALER_ZUG);
                    dealerZug();
                    cancel();
                }
            }
        }, 1000, 1000);


    }

    public void dealerZug() {
        if (sm.getSpiel().getPhase().equals(SpielPhase.DEALER_ZUG)) {
            Dealer d = sm.getSpiel().getDealer();
            for (KartenLabel k : dealerKartenList) {
                if (k.isKarteVerdeckt()) {
                    k.karteUmdrehen();
                    k.setBounds(k.getX(), k.getY(), 135, 204);
                    showCardValueDealer();
                }
            }
            while (d.takeCard()) {
                sm.dealerZiehen(false);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            sm.ende();
        }
    }

    public void reset() {
        for (Map.Entry<Spieler, List<KartenLabel>> e : spielerList.entrySet()) {
            for (KartenLabel l : e.getValue()) {
                remove(l);
            }
            e.getKey().reset();
            sm.getSpiel().getDealer().reset();

        }
        spielerList.clear();

        for (Spieler s : spielerListUtil) {
            spielerList.put(s, new ArrayList<>());
        }
        for (KartenLabel l : dealerKartenList) {
            remove(l);
        }
        dealerKartenList.clear();
        sm.getSpiel().reset();
        if (sm.getSpiel().getSpieler().get(0).getGuthaben() == 0) {
            System.out.println("Du hast kein Guthaben mehr");
            JOptionPane.showMessageDialog(this, "Du hast dein ganzes Guthaben verloren!");
            verloren.setVisible(true);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(-1);

        } else {
            sm.startSpiel();
        }
    }


    public void excecuteSpieler(Spieler spieler) {
        if (spielerExists(spieler) && spielerKartenExists(spieler)) {
            setNowShownPlayer(spieler);
            arrangeJlabel(spieler, -300, -300);
            int jj = getStartX(spieler, spielerLabel);
            arrangeJlabel(spieler, jj, 566);
            spielerList.get(spieler).get(0).setVisible(true);
            showCardValueSpieler(spieler);

            refreshBck();
        } else {
            System.out.println("Spieler wurde nicht gefunden!");
        }
    }

    public void excecuteDealer() {
        arrangeDealerJlabel(-300, -300);
        int jj = getStartXDealer(dealerLabel);
        arrangeDealerJlabel(jj, 250);
        showCardValueDealer();
        refreshBck();
    }

    private boolean spielerExists(Spieler spieler) {
        return spielerList.containsKey(spieler);
    }

    private boolean spielerKartenExists(Spieler spieler) {
        return spielerList.get(spieler).size() != 0;
    }

    public int einsatzSetzen(Spieler spieler) {


        return 0;
    }

    public SpielManger getSpielManager() {
        return sm;
    }

    public List<Spieler> getSpielerListUtil() {
        return spielerListUtil;
    }

}