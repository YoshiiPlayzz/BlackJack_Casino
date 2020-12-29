package de.joshua.hatzinger.nico.maurer.jonas.domnick;

import java.util.List;
import java.util.Scanner;

public class SpielManger {

    private Spiel spiel;
    private Scanner s;
    private boolean konsolenEingabe;


    public SpielManger(Spiel spiel, String[] name, boolean konsolenEingabe) {
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
                spieler.setGuthaben(1000);
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
            spiel.naechsterZug();
        }
        spiel.setPhase(SpielPhase.SPIELSTART);
        spiel.firstCards();
        int c = 0;
        for (Entity e : spiel.getSpieler()) {
            if (e.hatGewonnen()) c++;
        }
        if (c != spiel.getSpieler().size()) {
            System.out.println("Alle Spieler haben 2 Karten gezogen! " + spiel.getAktuellerSpieler().getName() + " darf anfangen!");

        } else {
            dealerZiehen();

        }

//TODO: Dealer eine Karte aufdecken
        while (!spiel.istZuende()) {
            if (!spiel.getAktuellerSpieler().hatGewonnen() && !(spiel.getAktuellerSpieler().getKartenSumme()[0] > 21 || spiel.getAktuellerSpieler().getKartenSumme()[1] > 21)) {
                sendAktuellerSpielerKarten();
                karteZiehen();
            }
            spiel.setPhase(SpielPhase.SPIEL);
            dealerZiehen();


        }


    }


    public void dealerZiehen() {
        int index = spiel.getSpieler().indexOf(spiel.getAktuellerSpieler());
        if (index + 2 > spiel.getSpielerAnzahl()) {
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
                System.out.println("Du hast verloren");
                spiel.naechsterZug();
            } else if (werte[0] == 21 || werte[1] == 21) {
                System.out.println("Du hast gewonnen!");
                spiel.getAktuellerSpieler().setGewinner();
                spiel.naechsterZug();
            } else {
                karteZiehen();
            }


        } else if (line.equalsIgnoreCase("Stand")) {
            System.out.println("Du gehst raus");
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
                } else {
                    System.out.println("Du kannst deinen  Einsatz nicht verdoppeln, da du zu wenig Guthaben dafür hast!");
                }
                karteZiehen();
            } else {
                System.out.println("Du kannst jetzt nicht mehr verdoppeln!");
            }


        } else if (line.equalsIgnoreCase("Surrender")) {

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
                if (spiel.getAktuellerSpieler() instanceof Spieler) {
                    Spieler s = (Spieler) spiel.getAktuellerSpieler();
                    einsatz = s.getGuthaben();
                    s.setGuthaben(0);
                }
            }
            if (spiel.aktuellerSpielerEinsatzSetzen(einsatz)) {
                if (spiel.getAktuellerSpieler() instanceof Spieler) {
                    System.out.println("Du hast " + einsatz + " gesetzt!");
                    Spieler s = (Spieler) spiel.getAktuellerSpieler();
                    System.out.println("Der Spieler hat noch " + s.getGuthaben());
                }
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
