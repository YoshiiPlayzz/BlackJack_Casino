public class Main {
    public static void main(String[] args) {
        Spiel s = new Spiel();
        SpielManger sm = new SpielManger(s, new String[]{"Schorsch"}, true);
        sm.startSpiel();

    }
}

