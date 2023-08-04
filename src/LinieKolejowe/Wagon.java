package LinieKolejowe;

public abstract class Wagon {
    protected boolean czyElektryczny;
    protected int wagaWagonu;
    protected int wagaNetto;
    protected int wagaBrutto;
    static int newID = 1000;


    Lokomotywa lokomotywa;
    protected int ID;
    public Wagon(boolean czyElektryczny, int wagaWagonu, int wagaNetto) {
        this.czyElektryczny = czyElektryczny;
        this.lokomotywa = null;
        this.wagaWagonu = wagaWagonu;
        this.wagaNetto = wagaNetto;
        this.wagaBrutto = this.wagaWagonu + this.wagaNetto;
        setID();
    }

    public void podlaczWagon(Lokomotywa lokomotywa) {
        this.lokomotywa = lokomotywa;
    }
    public int getWagaBrutto() {
        return wagaBrutto;
    }

    public boolean czyElektryczny() {
        return this.czyElektryczny;
    }

    public int getID() {
        return ID;
    }

    private void setID() {
        newID++;
        this.ID = newID;
    };

    public Lokomotywa getLokomotywa() {
        return lokomotywa;
    }

    @Override
    public boolean equals(Object wagon) {
        return (this.ID == ((Wagon)wagon).ID);
    }
    @Override
    public abstract String toString() ;

}
