package de.joshua.hatzinger.nico.maurer.jonas.domnick.game;

public class Dealer extends Entity {

    public Dealer() {
        super("Kao der Dealer");
    }

    public boolean takeCard(){
        int[] cardValue = getKartenSumme();
        return (cardValue[0] <= 16 && cardValue[1] <= 16);
    }

    public boolean hat21(){
        int[] cardValue = getKartenSumme();
        return (cardValue[0] == 21 || cardValue[1] == 21);
    }

}
