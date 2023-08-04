package LinieKolejowe;

public class WagonPasazerski extends Wagon{
    private int liczbaMiejsc;
    private boolean czyPrzedzialy;

    public WagonPasazerski(int wagaWagonu, int wagaNetto, int liczbaMiejsc, boolean czyPrzedzialy) {
        super(true,wagaWagonu,wagaNetto);
        this.liczbaMiejsc = liczbaMiejsc;
        this.czyPrzedzialy = czyPrzedzialy;
    }

    @Override
    public String toString() {
        return "Wagon pasazerski o ID: " + this.ID + "\nWaga wagonu: " + this.wagaWagonu + "\nWaga netto: " + this.wagaNetto + "\nWaga brutto: "
                + this.wagaBrutto + "\nLiczba miejsc: " + liczbaMiejsc;
    }

}
