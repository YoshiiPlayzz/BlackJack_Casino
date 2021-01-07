package de.joshua.hatzinger.nico.maurer.jonas.domnick.game;

public enum Karte {

    //TODO: Karten erweitern um src

    KARO_2(2, Farbe.KARO),
    KARO_3(3, Farbe.KARO),
    KARO_4(4, Farbe.KARO),
    KARO_5(5, Farbe.KARO),
    KARO_6(6, Farbe.KARO),
    KARO_7(7, Farbe.KARO),
    KARO_8(8, Farbe.KARO),
    KARO_9(9, Farbe.KARO),
    KARO_10(10, Farbe.KARO),
    KARO_BUBE(10, Farbe.KARO),
    KARO_DAME(10, Farbe.KARO),
    KARO_KOENIG(10, Farbe.KARO),
    KARO_ASS(11, Farbe.KARO),
    KREUZ_2(2, Farbe.KREUZ),
    KREUZ_3(3, Farbe.KREUZ),
    KREUZ_4(4, Farbe.KREUZ),
    KREUZ_5(5, Farbe.KREUZ),
    KREUZ_6(6, Farbe.KREUZ),
    KREUZ_7(7, Farbe.KREUZ),
    KREUZ_8(8, Farbe.KREUZ),
    KREUZ_9(9, Farbe.KREUZ),
    KREUZ_10(10, Farbe.KREUZ),
    KREUZ_BUBE(10, Farbe.KREUZ),
    KREUZ_DAME(10, Farbe.KREUZ),
    KREUZ_KOENIG(10, Farbe.KREUZ),
    KREUZ_ASS(11, Farbe.KREUZ),
    PIK_2(2, Farbe.PIK),
    PIK_3(3, Farbe.PIK),
    PIK_4(4, Farbe.PIK),
    PIK_5(5, Farbe.PIK),
    PIK_6(6, Farbe.PIK),
    PIK_7(7, Farbe.PIK),
    PIK_8(8, Farbe.PIK),
    PIK_9(9, Farbe.PIK),
    PIK_10(10, Farbe.PIK),
    PIK_BUBE(10, Farbe.PIK),
    PIK_DAME(10, Farbe.PIK),
    PIK_KOENIG(10, Farbe.PIK),
    PIK_ASS(11, Farbe.PIK),
    HERZ_2(2, Farbe.HERZ),
    HERZ_3(3, Farbe.HERZ),
    HERZ_4(4, Farbe.HERZ),
    HERZ_5(5, Farbe.HERZ),
    HERZ_6(6, Farbe.HERZ),
    HERZ_7(7, Farbe.HERZ),
    HERZ_8(8, Farbe.HERZ),
    HERZ_9(9, Farbe.HERZ),
    HERZ_10(10, Farbe.HERZ),
    HERZ_BUBE(10, Farbe.HERZ),
    HERZ_DAME(10, Farbe.HERZ),
    HERZ_KOENIG(10, Farbe.HERZ),
    HERZ_ASS(11, Farbe.HERZ);

    private final int value;
    private final Farbe farbe;

    Karte(int value, Farbe farbe) {
        this.value = value;
        this.farbe = farbe;
    }

    /**
     * Mehrere Werte für Karten werden zurückgegeben
     *
     * @return die Werte einer Karte
     */

    public int[] getValue() {
        int[] values = {value, 0};
        if (toString().contains("ASS")) {
            values[1] = 1;
        }
        return values;
    }

    public String getKartenString() {
        if (getValue()[0] != getValue()[1]) {
            return getFarbe().toString() + " " + toString().split("_")[1] + " = " + getValue()[0];
        } else {
            return getFarbe().toString() + " " + toString().split("_")[1] + " = " + getValue()[0] + " oder " + getValue()[1];
        }

    }

    public String kartenPfad() {
        String s = "/images/Karten/";
        String kn = toString().replace("_", "").toLowerCase();

        if (!kn.contains("bube") && !kn.contains("dame") && !kn.contains("koenig") && !kn.contains("ass")) {

            s += kn.substring(0, 1).toUpperCase() + kn.substring(1) + ".png";
        } else {
            String[] a = toString().toLowerCase().split("_");
            kn = a[0].substring(0, 1).toUpperCase() + a[0].substring(1) + a[1].substring(0, 1).toUpperCase() + a[1].substring(1);
            s += kn + ".png";
        }

        return s;
    }

    /**
     * Farbe der Karten
     *
     * @return die Farbe der Karte
     */

    public Farbe getFarbe() {
        return farbe;
    }
}