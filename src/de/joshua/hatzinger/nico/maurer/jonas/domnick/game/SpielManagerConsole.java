package de.joshua.hatzinger.nico.maurer.jonas.domnick.game;


import java.util.List;
import java.util.Scanner;

public class SpielManagerConsole {

    private final Spiel spiel;
    private final boolean konsolenEingabe;
    private Scanner s;


    public SpielManagerConsole(Spiel spiel, String[] name, boolean konsolenEingabe) {
        this.spiel = spiel;
        generateSpieler(name);
        this.konsolenEingabe = konsolenEingabe;
        if (this.konsolenEingabe) this.s = new Scanner(System.in);
    }


    private void generateSpieler(String[] name) {
        if (!spiel.getPhase().equals(SpielPhase.SPIELSTART) || !spiel.getPhase().equals(SpielPhase.SPIELENDE)) {
            int c = name.length;
            if (name.length >= 7) {
                c = 7;
                System.out.println("Es können nur max. 7 Spieler mitspielen!");
            }
            for (int i = 0; i < c; i++) {
                Spieler spieler = new Spieler(name[i]);
                spieler.setGuthaben(8000);
                spiel.addSpieler(spieler);
            }
        }
    }

    public void startSpiel() {
        System.out.println("Das Spiel wird mit " + (spiel.getSpieler().size()) + " Spieler(n) gestartet!");
        spiel.setPhase(SpielPhase.EINSAETZE);
        spiel.toErstenSpieler();
        for (int i = 0; i < spiel.getSpieler().size(); i++) {
            einsatzSetzen();
            spiel.naechsterSpieler();
        }
        spiel.setPhase(SpielPhase.SPIELSTART);
        spiel.firstCards();

        //TODO: Dealer eine Karte aufdecken
        while (!spiel.istZuende()) {
            if (spiel.istJederFertig()) {
                spiel.setPhase(SpielPhase.SPIELENDE);
                System.out.println("Das Spiel ist jetzt vorbei!");
                break;
            }
            if (!spiel.getAktuellerSpieler().hatGewonnen()
                    && !(spiel.getAktuellerSpieler().getKartenSumme()[0] > 21
                    || spiel.getAktuellerSpieler().getKartenSumme()[1] > 21)) {
                System.out.println(spiel.getAktuellerSpieler().getName() + " darf anfangen!");
                sendAktuellerSpielerKarten();
                karteZiehen();
            }


        }
        dealerZiehen();

    }


    public void dealerZiehen() {

        //TODO: Multiplayer fixen
        if (spiel.istJederFertig()) {
            while (spiel.getDealer().takeCard()) {
                spiel.getDealer().addKarte(spiel.getKarten().remove(0));
                if (spiel.getDealer().hat21()) {
                    System.out.println("Der Dealer hat 21!");
                    spiel.getDealer().setGewinner();
                }
            }
            spiel.ende();
        }
    }
//TODO: Versicherung für Ass
// TODO: Splitten

    public void karteZiehen() {
        String ausgabe = "";
        if (spiel.getPhase().equals(SpielPhase.SPIELSTART)) {
            ausgabe = "Hit|Stand|Verdoppeln|Surrender";
        } else if (spiel.getPhase().equals(SpielPhase.SPIEL)) {
            ausgabe = "Hit|Stand";
        }
        System.out.println(ausgabe);
        String line = s.nextLine();
        if (line.equalsIgnoreCase("Hit")) {
            if (spiel.getPhase().equals(SpielPhase.SPIELSTART)) {
                spiel.setPhase(SpielPhase.SPIEL);
            }
            spiel.ziehKarte();

            int[] werte = spiel.getAktuellerSpieler().getKartenSumme();
            sendAktuellerSpielerKarten();

            if (werte[0] > 21 && werte[1] > 21) {
                System.out.println(spiel.getAktuellerSpieler().getName() + " ist über 21!");
                spiel.naechsterZug();
                if (!spiel.istJederFertig())
                    spiel.setPhase(SpielPhase.SPIELSTART);
            } else if (werte[0] == 21 || werte[1] == 21) {
                System.out.println(spiel.getAktuellerSpieler().getName() + " hat Black Jack!");
                spiel.getAktuellerSpieler().setGewinner();
                spiel.naechsterZug();
                spiel.setPhase(SpielPhase.SPIELSTART);
            } else {
                karteZiehen();
            }
        } else if (line.equalsIgnoreCase("Stand")) {
            System.out.println(spiel.getAktuellerSpieler().getName() + " geht raus!");
            spiel.getAktuellerSpieler().setFertig();
            spiel.setPhase(SpielPhase.SPIELSTART);
            spiel.naechsterZug();
        } else if (line.equalsIgnoreCase("Verdoppeln")) {
            if (spiel.getPhase().equals(SpielPhase.SPIELSTART)) {
                Spieler s = spiel.getAktuellerSpieler();
                int einsatz = spiel.getEinsatz().get(s);
                if (s.getGuthaben() - einsatz >= 0) {
                    s.setGuthabenMinus(spiel.getEinsatz().get(s));
                    spiel.getEinsatz().replace(s, spiel.getEinsatz().get(s) * 2);
                    System.out.println("Du hast deinen Einsatz verdoppelt!");
                    spiel.setPhase(SpielPhase.SPIEL);
                    sendAktuellerSpielerKarten();
                } else {
                    System.out.println("Du kannst deinen  Einsatz nicht verdoppeln, da du zu wenig Guthaben dafür hast!");
                }
                karteZiehen();
            } else {
                System.out.println("Du kannst jetzt nicht mehr verdoppeln!");
            }


        } else if (line.equalsIgnoreCase("Surrender")) {
            System.out.println(spiel.getAktuellerSpieler().getName() + " gibt auf!");
            spiel.getAktuellerSpieler().aufgeben(spiel.getSpielerEinsatz());
            spiel.naechsterZug();
        } else {
            System.out.println("Gebe einen gültigen Wert ein!");
            karteZiehen();
        }


    }

    public void einsatzSetzen() {

        System.out.println("Der Spieler " + spiel.getAktuellerSpieler().getName() + " muss einen Einsatz setzen");
        String line = s.nextLine();
        int einsatz;
        try {
            einsatz = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            einsatz = 0;
        }
        if (einsatz > 0 || line.contains("all")) {
            if (line.contains("all")) {

                Spieler s = spiel.getAktuellerSpieler();
                einsatz = s.getGuthaben();
                s.setGuthaben(0);

            }
            if (spiel.aktuellerSpielerEinsatzSetzen(einsatz)) {
                Spieler s = spiel.getAktuellerSpieler();
                System.out.println(s.getName() + " hat " + einsatz + " gesetzt!");
                System.out.println("Der Spieler '" + s.getName() + "' hat noch " + s.getGuthaben());
            } else {
                einsatzSetzen();
            }
        } else {
            System.out.println("Du musst einen gültigen Betrag setzen!");
            einsatzSetzen();
        }


    }

    public List<Karte> getAktuellerSpielerKarten() {
        return spiel.getAktuellerSpieler().getInventar();

    }

    public void sendAktuellerSpielerKarten() {
        StringBuilder sb = new StringBuilder("Karten von '" + spiel.getAktuellerSpieler().getName() + "': ");
        for (Karte k : getAktuellerSpielerKarten()) {
            sb.append(k.getKartenString()).append(" ");


        }
        sb.append("\n");
        int[] summe = spiel.getAktuellerSpieler().getKartenSumme();
        if (summe[0] == summe[1]) {
            sb.append(summe[0]);
        } else {
            sb.append(summe[0]).append("/").append(summe[1]);
        }


        System.out.println(sb.toString());

    }

    public void einsatzVonAktuellenSpielerSetzen(int einsatz) {

    }


}