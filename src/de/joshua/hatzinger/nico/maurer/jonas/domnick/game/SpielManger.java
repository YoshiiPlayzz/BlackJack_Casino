package de.joshua.hatzinger.nico.maurer.jonas.domnick.game;

import de.joshua.hatzinger.nico.maurer.jonas.domnick.ui.Window;

import javax.swing.*;
import java.util.HashMap;

public class SpielManger {

    private final Spiel spiel;
    private final Window w;

    private final HashMap<Spieler, Integer> einsaetze = new HashMap<>();


    public SpielManger(Spiel spiel, Window w) {
        this.spiel = spiel;
        this.w = w;
    }

    public SpielManger(Spiel spiel, Window w, String[] name) {
        this.spiel = spiel;
        this.w = w;
        generateSpieler(name);
    }

    private void generateSpieler(String[] name) {
        if (!spiel.getPhase().equals(SpielPhase.SPIELSTART) || !spiel.getPhase().equals(SpielPhase.SPIELENDE)) {
            int c = name.length;
            if (name.length <= 7) {
                c = 7;
            }
            for (int i = 0; i < c; i++) {
                Spieler spieler = new Spieler(name[i]);

                spiel.addSpieler(spieler);
                Window.addSpieler(spieler);
            }
        }
    }

    public void startSpiel() {
        for (Spieler s : w.getSpielerListUtil()) {
            if (!spiel.getSpieler().contains(s)) {
                s.setGuthaben(8000);
                spiel.addSpieler(s);
            }
        }
        spiel.toErstenSpieler();
        spiel.setPhase(SpielPhase.EINSAETZE);
        System.out.println("Einsatz setzen");

        for (int i = 0; i < spiel.getSpielerAnzahl(); i++) {
            Spieler s = spiel.getSpieler().get(i);
            String einsatz = null;
            int r_einsatz = 0;
            while (einsatz == null) {
                einsatz = JOptionPane.showInputDialog(w, "Bitte gebe deinen Einsatz ein!",
                        JOptionPane.INFORMATION_MESSAGE);
                try {

                    //TODO: 5er Schritte
                    r_einsatz = Integer.parseInt(einsatz);
                    if (r_einsatz < 50) {
                        JOptionPane optionPane = new JOptionPane("Bitte gebe eine Nummer ab 50 ein!", JOptionPane.ERROR_MESSAGE);
                        JDialog dialog = optionPane.createDialog("Fehler bei der Eingabe");
                        dialog.setAlwaysOnTop(true);
                        dialog.setVisible(true);
                        einsatz = null;
                    }
                    if (s.getGuthaben() - r_einsatz < 0) {
                        JOptionPane optionPane = new JOptionPane("Du darfst nicht mehr als dein Guthaben eingeben", JOptionPane.ERROR_MESSAGE);
                        JDialog dialog = optionPane.createDialog("Fehler bei der Eingabe");
                        dialog.setAlwaysOnTop(true);
                        dialog.setVisible(true);
                        einsatz = null;
                    }

                } catch (NumberFormatException e) {
                    einsatz = null;
                    JOptionPane optionPane = new JOptionPane("Bitte gebe eine Nummer ab 50 ein!", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Fehler bei der Eingabe");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }


            }
            getSpiel().aktuellerSpielerEinsatzSetzen(r_einsatz);
            w.setGuthabenFont(getSpiel().getAktuellerSpieler().getGuthaben());


        }

        System.out.println("Alle Einsätze gemacht");
        System.out.println("Alle Spieler ziehen jetzt Karten");
        for (int x = 0; x < 2; x++) {
            for (int i = 0; i < spiel.getSpielerAnzahl(); i++) {
                karteZiehen();
                w.excecuteSpieler(spiel.getAktuellerSpieler());
                spiel.naechsterSpieler();
            }
            dealerZiehen(x == 1);
        }
        w.showCardValueDealerVerdeckt();
        System.out.println("Alle Spieler und der Dealer haben 2 Karten bekommen");
        w.spielStart();


    }

    public void halten() {
        if (spiel.getPhase().equals(SpielPhase.SPIELSTART)) {
            spiel.setPhase(SpielPhase.SPIEL);
        }
        JOptionPane.showMessageDialog(w, "Du hälst deine Karten");
        spiel.naechsterZug();


    }

    public void surrender() {
        if (spiel.getPhase().equals(SpielPhase.SPIELSTART)) {
            spiel.setPhase(SpielPhase.SPIEL);
            JOptionPane.showMessageDialog(w, "Du gibst auf!");
            spiel.getAktuellerSpieler().aufgeben(spiel.getSpielerEinsatz());
            spiel.naechsterZug();
        } else {
            JOptionPane.showMessageDialog(w, "Du kannst nicht mehr aufgeben!");
        }
    }

    public void doppeln() {
        if (spiel.getPhase().equals(SpielPhase.SPIELSTART)) {
            spiel.setPhase(SpielPhase.SPIEL);
            if (spiel.getEinsatz().get(spiel.getAktuellerSpieler()) * 2 <= spiel.getAktuellerSpieler().getGuthaben()) {
                JOptionPane.showMessageDialog(w, "Du verdoppelst deinen Einsatz auf " + spiel.getEinsatz().get(spiel.getAktuellerSpieler()) * 2 + "!");

                spiel.getEinsatz().replace(spiel.getAktuellerSpieler(), spiel.getEinsatz().get(spiel.getAktuellerSpieler()) * 2);
                spiel.getAktuellerSpieler().setGuthabenMinus(spiel.getEinsatz().get(spiel.getAktuellerSpieler()));
                w.setGuthabenFont(spiel.getAktuellerSpieler().getGuthaben());
            } else {
                JOptionPane.showMessageDialog(w, "Du hast zu wenig Guthaben zum verdoppeln!");
            }
        } else {
            JOptionPane.showMessageDialog(w, "Du kannst nicht mehr aufgeben!");
        }
    }


    public Spiel getSpiel() {
        return spiel;
    }


    public void dealerZiehen(boolean verdeckt) {
        w.addDealerkarten(spiel.ziehKarteDealer(), verdeckt);
        w.excecuteDealer();
    }

    public void karteZiehen() {
        w.addSpielerkarten(spiel.getAktuellerSpieler(), spiel.ziehKarte());
        w.excecuteSpieler(spiel.getAktuellerSpieler());
    }


    public void ende() {
        if (spiel.istJederFertig()) {
            spiel.setPhase(SpielPhase.SPIELENDE);
            JOptionPane.showMessageDialog(w, "Die Runde ist zuende");
            for (Spieler s : spiel.getSpieler()) {
                if (s.hatGewonnen()) {
                    int[] summe = s.getKartenSumme();
                    if (!spiel.getDealer().hatGewonnen()) {
                        //Einsatz 3:2
                        int add = spiel.getEinsatz().get(s) + (int) (spiel.getEinsatz().get(s) * 1.5);
                        s.addGuthaben(add);
                        w.setGuthabenFont(s.getGuthaben());
                    } else if (spiel.getDealer().hatGewonnen()) {
                        //Einsatz 1:1
                        int add = spiel.getEinsatz().get(s);
                        s.addGuthaben(add);
                        w.setGuthabenFont(s.getGuthaben());
                    }
                    JOptionPane.showMessageDialog(w, "Du hast gewonnen!");

                } else {
                    //Spieler hat nicht gewonnen

                    if (!spiel.getDealer().hatGewonnen()) {

                        //EVEN
                        int[] dealer_summe = spiel.getDealer().getKartenSumme();
                        int[] spieler_summe = s.getKartenSumme();


                        //dealer_summe[1] < spieler_summe[0]; 19 --> 20 Spieler gewonnen
                        //dealer_summe[0] < spieler_summe[1]; 19 --> 20 => Spieler gewonnnen


                        if (((dealer_summe[0] == spieler_summe[0])
                                || (dealer_summe[1] == spieler_summe[1])
                                || (dealer_summe[0] == spieler_summe[1])
                                || (dealer_summe[1] == spieler_summe[0]))) {
                            //Spieler bekommt Einsatz zurück
                            s.addGuthaben(spiel.getEinsatz().get(s));
                            w.setGuthabenFont(s.getGuthaben());
                            JOptionPane.showMessageDialog(w, "Du bist im Gleichstand mit dem Dealer!");

                        } else if (dealer_summe[0] < spieler_summe[1] && spieler_summe[1] < 21) {
                            //Spieler gewonnen
                            //Einsatz 3:2
                            int add = spiel.getEinsatz().get(s) + (int) (spiel.getEinsatz().get(s) * 1.5);
                            s.addGuthaben(add);
                            w.setGuthabenFont(s.getGuthaben());
                            JOptionPane.showMessageDialog(w, "Du hast gewonnen!");
                        } else if (dealer_summe[1] < spieler_summe[0] && spieler_summe[0] < 21) {
                            //Spieler gewonnen
                            //Einsatz 3:2
                            int add = spiel.getEinsatz().get(s) + (int) (spiel.getEinsatz().get(s) * 1.5);
                            s.addGuthaben(add);
                            w.setGuthabenFont(s.getGuthaben());
                            JOptionPane.showMessageDialog(w, "Du hast gewonnen!");
                        } else {
                            //Dealer gewonnen
                            JOptionPane.showMessageDialog(w, "Du hast verloren!");
                        }

                        System.out.println("Der Dealer hatte " + (spiel.getDealer().getKartenSumme()[0] == spiel.getDealer().getKartenSumme()[1] ? spiel.getDealer().getKartenSumme()[0] : spiel.getDealer().getKartenSumme()[0] + "/" + spiel.getDealer().getKartenSumme()[1]));


                    } else {
                        JOptionPane.showMessageDialog(w, "Du hast verloren!");
                    }
                }
            }
            try {
                Thread.sleep(2000);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            w.reset();
        }
    }

}
