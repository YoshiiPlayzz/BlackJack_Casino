package de.joshua.hatzinger.nico.maurer.jonas.domnick.game;

public enum Karte {

    //TODO: Karten erweitern um src

    KARO_ZWEI(2, Farbe.KARO),
    KARO_DREI(3, Farbe.KARO),
    KARO_VIER(4, Farbe.KARO),
    KARO_FUENF(5, Farbe.KARO),
    KARO_SECHS(6, Farbe.KARO),
    KARO_SIEBEN(7, Farbe.KARO),
    KARO_ACHT(8, Farbe.KARO),
    KARO_NEUN(9, Farbe.KARO),
    KARO_ZEHN(10, Farbe.KARO),
    KARO_BUBE(10, Farbe.KARO),
    KARO_DAME(10, Farbe.KARO),
    KARO_KOENIG(10, Farbe.KARO),
    KARO_ASS(11, Farbe.KARO),
    KREUZ_ZWEI(2, Farbe.KREUZ),
    KREUZ_DREI(3, Farbe.KREUZ),
    KREUZ_VIER(4, Farbe.KREUZ),
    KREUZ_FUENF(5, Farbe.KREUZ),
    KREUZ_SECHS(6, Farbe.KREUZ),
    KREUZ_SIEBEN(7, Farbe.KREUZ),
    KREUZ_ACHT(8, Farbe.KREUZ),
    KREUZ_NEUN(9, Farbe.KREUZ),
    KREUZ_ZEHN(10, Farbe.KREUZ),
    KREUZ_BUBE(10, Farbe.KREUZ),
    KREUZ_DAME(10, Farbe.KREUZ),
    KREUZ_KOENIG(10, Farbe.KREUZ),
    KREUZ_ASS(11, Farbe.KREUZ),
    PIK_ZWEI(2, Farbe.PIK),
    PIK_DREI(3, Farbe.PIK),
    PIK_VIER(4, Farbe.PIK),
    PIK_FUENF(5, Farbe.PIK),
    PIK_SECHS(6, Farbe.PIK),
    PIK_SIEBEN(7, Farbe.PIK),
    PIK_ACHT(8, Farbe.PIK),
    PIK_NEUN(9, Farbe.PIK),
    PIK_ZEHN(10, Farbe.PIK),
    PIK_BUBE(10, Farbe.PIK),
    PIK_DAME(10, Farbe.PIK),
    PIK_KOENIG(10, Farbe.PIK),
    PIK_ASS(11, Farbe.PIK),
    HERZ_ZWEI(2, Farbe.HERZ),
    HERZ_DREI(3, Farbe.HERZ),
    HERZ_VIER(4, Farbe.HERZ),
    HERZ_FUENF(5, Farbe.HERZ),
    HERZ_SECHS(6, Farbe.HERZ),
    HERZ_SIEBEN(7, Farbe.HERZ),
    HERZ_ACHT(8, Farbe.HERZ),
    HERZ_NEUN(9, Farbe.HERZ),
    HERZ_ZEHN(10, Farbe.HERZ),
    HERZ_BUBE(10, Farbe.HERZ),
    HERZ_DAME(10, Farbe.HERZ),
    HERZ_KOENIG(10, Farbe.HERZ),
    HERZ_ASS(11, Farbe.HERZ);

    private int value;
    private Farbe farbe;

    Karte(int value, Farbe farbe) {
        this.value = value;
        this.farbe = farbe;
    }

    /**
     * Mehrere Werte für Karten werden zurückgegeben
     * @return die Werte einer Karte
     */

    public int[] getValue() {
        int[] values = {value, 0};
        if (toString().contains("ASS")) {
            values[1] = 1;
        }
        return values;
    }

    public String getKartenString(){
        if(getValue()[0] != getValue()[1]){
            return getFarbe().toString() + " "  +toString().split("_")[1] + " = " + getValue()[0];
        }else{
            return getFarbe().toString() + " "  +toString().split("_")[1] + " = " + getValue()[0] +" oder " + getValue()[1];
        }

    }

    /**
     * Farbe der Karten
     *
     *  @return die Farbe der Karte
     */

    public Farbe getFarbe() {
        return farbe;
    }
}