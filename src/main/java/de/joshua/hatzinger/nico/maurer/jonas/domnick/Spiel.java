package de.joshua.hatzinger.nico.maurer.jonas.domnick;

import java.util.*;

public class Spiel {
    private List<Spieler> spieler;
    private Spieler aktuellerSpieler;
    private Map<Entity, Integer> einsatz;
    private List<Karte> karten;
    private Dealer dealer;
    private SpielPhase phase;
    private boolean istZuende = false;

    public Spiel() {
        karten = new ArrayList<>();
        spieler = new ArrayList<>();
        aktuellerSpieler = null;
        phase = SpielPhase.START;
        einsatz = new HashMap<>();
        //Karten werden generiert und gemischt
        generateKartenDeck();
        dealer = new Dealer();
    }

    public boolean istZuende() {
        return phase == SpielPhase.SPIELENDE;
    }

    public SpielPhase getPhase() {
        return phase;
    }

    public void setPhase(SpielPhase phase) {
        this.phase = phase;
    }


    /**
     * Generiert ein "neues" Kartendeck
     */

    public void generateKartenDeck() {
        for (int i = 0; i < 6; i++) {
            //Alle Karten werden in die Liste eingefügt und gemischt
            karten.addAll(Arrays.asList(Karte.values()));
            Collections.shuffle(karten);
        }
    }

    public void firstCards() {
        aktuellerSpieler = spieler.get(0);

        //TODO: Für Tests Entfernen
        //aktuellerSpieler.addKarte(Karte.HERZ_ZEHN);


        for (int i = 0; i < spieler.size() * 2; i++) {
            if (aktuellerSpieler.getInventar().size() < 2) {
                ziehKarte();
                naechsterZug();
            }
        }
        //DEALER zieht die ersten beiden Karten

        for (int i = 0; i < 2; i++) {
            if (dealer.getInventar().size() < 2) {
                dealer.addKarte(karten.remove(0));
            }
        }
        for (Spieler e : getSpieler()) {
            if (e.getKartenSumme()[0] == 21 || e.getKartenSumme()[1] == 21) {
                e.setGewinner();
                System.out.println(e.getName() + " hat 21!");
            }
        }
        Dealer d = getDealer();
        if (d.hat21()) {
            d.setGewinner();
            System.out.println("Der Dealer hat 21!");
            ende();
        }
    }


    public boolean aktuellerSpielerEinsatzSetzen(int einsatz) {
        if (!this.einsatz.containsKey(aktuellerSpieler)) {
            Spieler s = aktuellerSpieler;
            if (s.getGuthaben() - einsatz >= 0) {
                s.setGuthabenMinus(einsatz);
                this.einsatz.put(aktuellerSpieler, einsatz);
                return true;
            } else {
                System.out.println("Du hast nur  " + s.getGuthaben() + " und kannst nicht " + einsatz + " setzten!");
            }
        } else {
            System.out.println("Du hast bereits einen Einsatz gemacht");
        }
        return false;
    }


    /**
     * Lässt den aktuellenSpieler die oberste Karte aus den karten ziehen
     */

    public void ziehKarte() {
        aktuellerSpieler.addKarte(karten.remove(0));
    }

    public int getSpielerAnzahl() {
        return spieler.size();
    }

    public Spieler getAktuellerSpieler() {
        return aktuellerSpieler;
    }

    public void setAktuellerSpieler(Spieler aktuellerSpieler) {
        this.aktuellerSpieler = aktuellerSpieler;
    }

    public void toErstenSpieler() {
        aktuellerSpieler = getSpieler().get(0);
    }

    public boolean addSpieler(Spieler spieler) {
        getSpieler().add(spieler);
        return !getSpieler().contains(spieler);
    }

    public void naechsterZug() {
        if (spieler.indexOf(aktuellerSpieler) + 2 > spieler.size()) {
            aktuellerSpieler = spieler.get(0);
        } else {
            aktuellerSpieler = spieler.get(spieler.indexOf(aktuellerSpieler) + 1);
        }
    }

    public void ende() {
        setPhase(SpielPhase.SPIELENDE);
        System.out.println();
        System.out.println("Das Spiel ist vorbei!");
        for (Spieler s : getSpieler()) {
            if (s.hatGewonnen()) {
                int[] summe = s.getKartenSumme();
                if (!getDealer().hatGewonnen()) {
                    //Einsatz 3:2
                    int add = einsatz.get(s) + (int) (einsatz.get(s) * 1.5);
                    s.addGuthaben(add);
                    System.out.println(s.getName() + " hat " + add + " gewonnen!");
                } else if (getDealer().hatGewonnen()) {
                    //Einsatz 1:1
                    int add = einsatz.get(s);
                    s.addGuthaben(add);
                    System.out.println(s.getName() + " hat " + add + " gewonnen!");
                }


            } else {
                //Spieler hat nicht gewonnen

                if (!getDealer().hatGewonnen()) {

                    //EVEN
                    int[] dealer_summe = getDealer().getKartenSumme();
                    int[] spieler_summe = s.getKartenSumme();


                    //dealer_summe[1] < spieler_summe[0]; 19 --> 20 Spieler gewonnen
                    //dealer_summe[0] < spieler_summe[1]; 19 --> 20 => Spieler gewonnnen


                    if (((dealer_summe[0] == spieler_summe[0])
                            || (dealer_summe[1] == spieler_summe[1])
                            || (dealer_summe[0] == spieler_summe[1])
                            || (dealer_summe[1] == spieler_summe[0]))) {
                        //Spieler bekommt Einsatz zurück



                    } else if (dealer_summe[0] < spieler_summe[1] && spieler_summe[1] < 21) {
                        //Spieler gewonnen
                        System.out.println("Gewonnen");
                    } else if (dealer_summe[1] < spieler_summe[0] && spieler_summe[0] < 21) {
                        //Spieler gewonnen
                        System.out.println("Gewonnen");
                    } else {
                        //Dealer gewonnen
                        System.out.println("Verloren");
                    }

                    System.out.println("Er hatte " + (getDealer().getKartenSumme()[0] == getDealer().getKartenSumme()[1] ? getDealer().getKartenSumme()[0] : getDealer().getKartenSumme()[0] + "/" + getDealer().getKartenSumme()[1]));


//                    if (dealer_summe[0] == spieler_summe[0] ||
//                            dealer_summe[0] == spieler_summe[1] ||
//                            dealer_summe[1] == spieler_summe[0] ||
//                            dealer_summe[1] == spieler_summe[1]) {
//                        //Einsatz 1:1
//                        int add = einsatz.get(s);
//                        s.addGuthaben(add);
//                        System.out.println(s.getName() + " hat " + add + " gewonnen!");
//
//                        //ODD
//
//
//                        //Zocker 1: 33/22
//                        //Dealer: 17/7
//
//                        //Zocker 1: 13/3
//                        //Dealer 17/7
//
//                    }else if(dealer_summe[0] < spieler_summe[0]){
//                        //Spieler gewonnen
//                        //Einsatz 2:1
//                        int add = 2 * einsatz.get(s);
//                        s.addGuthaben(add);
//                        System.out.println(s.getName() + " hat " + add + " gewonnen!")
//
//
//                    }else if(dealer_summe[0] > spieler_summe[0]){
//
//                    }else {
//                        //Einsatz 2:1
//                        int add = 2 * einsatz.get(s);
//                        s.addGuthaben(add);
//                        System.out.println(s.getName() + " hat " + add + " gewonnen!");
//                    }
                } else {
                    //Dealer hat gewonnen
                }
            }
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Spieler> getSpieler() {
        return spieler;
    }

    public List<Karte> getKarten() {
        return karten;
    }

    public Map<Entity, Integer> getEinsatz() {
        return einsatz;
    }
}
