public class Spieler extends Entity {


    private int guthaben;

    public Spieler(String name) {
        super(name);
    }

    public int getGuthaben() {
        return guthaben;
    }

    public void setGuthaben(int guthaben) {
        this.guthaben = guthaben;
    }

    public void addGuthaben(int guthaben) {
        if (guthaben > 0)
            this.guthaben += guthaben;
    }

    public void setGuthabenMinus(int abzug) {
        if (abzug > 0 || guthaben - abzug >= 0) {
            guthaben -= abzug;
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}