package LinieKolejowe;

public class WagonTowarowyPodstawowy extends Wagon{
    protected int maxUdzwig;
    public WagonTowarowyPodstawowy(boolean czyElektryczny, int wagaWagonu, int wagaNetto,int maxUdzwig ) {
        super(czyElektryczny,wagaWagonu,wagaNetto);
        this.maxUdzwig = maxUdzwig;
    }

    @Override
    public String toString() {
        return "Wagon towarowy podstawowy o ID: " + this.ID + "\nWaga wagonu: " + this.wagaWagonu + "\nWaga netto: " + this.wagaNetto + "\nWaga brutto: "
                + this.wagaBrutto + "\nMax Udzwig: " + maxUdzwig;
    }
}
