package LinieKolejowe;
import java.util.LinkedList;
import java.util.List;

public class Lokomotywa {
    static int newID = 1000;
    private int maxLiczbaWagonow;
    private int maxUciag;
    private int maxLiczbaWagonowElektrycznych;
    private Stacja stacjaMacierzysta;
    private Stacja stacjaZrodlowa;
    private Stacja stacjaDocelowa;
    private List<Wagon> wagony = new LinkedList();

    private String nazwa;
    private int ID;
    private double predkosc;

    public Lokomotywa(String nazwa) {
        this.nazwa = nazwa;
        this.maxLiczbaWagonow = 0;
        this.maxUciag = 0;
        this.maxLiczbaWagonowElektrycznych = 0;

        this.stacjaMacierzysta = null;
        this.stacjaZrodlowa = null;
        this.stacjaDocelowa = null;

        this.predkosc = 100.0;
        newID++;
        this.ID = newID;
    }

    public Lokomotywa(String nazwa, int maxLiczbaWagonow, int maxUciag, int maxLiczbaWagonowElektrycznych, Stacja stacjaMacierzysta, Stacja stacjaZrodlowa, Stacja stacjaDocelowa, double predkosc) {
        this.nazwa = nazwa;
        this.maxLiczbaWagonow = maxLiczbaWagonow;
        this.maxUciag = maxUciag;
        this.maxLiczbaWagonowElektrycznych = maxLiczbaWagonowElektrycznych;

        this.stacjaMacierzysta = stacjaMacierzysta;
        this.stacjaZrodlowa = stacjaZrodlowa;
        this.stacjaDocelowa = stacjaDocelowa;

        this.predkosc = predkosc;
        newID++;
        this.ID = newID;
    }

    public void zmienMaxLiczbeWagonow(int maxLiczbaWagonow) {
        this.maxLiczbaWagonow = maxLiczbaWagonow;
    }

    public void zmienMaxUciag(int maxUciag) {
        this.maxUciag = maxUciag;
    }

    public void zmienMaxLiczbeWagonowElektrycznych(int maxLiczbaWagonowElektrycznych) {
        this.maxLiczbaWagonowElektrycznych = maxLiczbaWagonowElektrycznych;
    }

    public void zmienStacjeMacierzysta(Stacja stacjaMacierzysta) {
        this.stacjaMacierzysta = stacjaMacierzysta;
    }

    public void zmienStacjeZrodlowa(Stacja stacjaZrodlowa) {
        this.stacjaZrodlowa = stacjaZrodlowa;
    }

    public void zmienStacjeDocelowa(Stacja stacjaDocelowa) {
        this.stacjaDocelowa = stacjaDocelowa;
    }

    public boolean czyMoznaDolaczycWagon(Wagon wagon) {
        if(wagony.size() >= maxLiczbaWagonow)
            return false;
        int podlaczoneWagonyElektryczne = 0;
        int wagaBruttoWagonow = 0;
        for(Wagon w : wagony) {
            if(w.getID() == wagon.getID())
                return false;
            if(w.czyElektryczny())
                podlaczoneWagonyElektryczne++;
            wagaBruttoWagonow += w.getWagaBrutto();

        }
        if(wagon.czyElektryczny())
            podlaczoneWagonyElektryczne++;
        if(podlaczoneWagonyElektryczne > maxLiczbaWagonowElektrycznych)
            return false;
        if((wagaBruttoWagonow + wagon.getWagaBrutto() > maxUciag))
            return false;
        return true;
    }

    public void podlaczWagon(Wagon wagon) {
        this.wagony.add(wagon);
    }

    public List<Wagon> getWagony() {
        return wagony;
    }

    public Stacja getStacjaZrodlowa() {
        return stacjaZrodlowa;
    }

    public Stacja getStacjaDocelowa() {
        return stacjaDocelowa;
    }

    public void aktualizujPredkosc() throws RailException {
        int rand = (int)Math.random()*2;
        if(rand == 0) {
            predkosc*=0.97;
        }
        else {
            predkosc*=1.03;
            try {
                if(predkosc > 200.0) {
                    throw new RailException("Niebezpieczna Predkosc, zwalniam o 10%!");
                }
            }
            catch (RailException exception) {
                predkosc *= 0.9;
            }

        }
    }

    public double getPredkosc() {
        return predkosc;
    }
    @Override
    public boolean equals(Object lokomotywa) {
        return (this.ID == ((Lokomotywa)lokomotywa).ID);
    }
    @Override
    public String toString() {
        return "Lokomotywa " + nazwa + " ID: " + ID + "\nMax liczba wagonow: " + maxLiczbaWagonow + "\nMax Uciag: " + maxUciag +
            "\nMaxLiczbaWagonowElektrycznych: " + maxLiczbaWagonowElektrycznych + "\nStacja macierzysta: " + stacjaMacierzysta +
            "\nStacja Zrodlowa: " + stacjaZrodlowa + "\nStacja Docelowa: " + stacjaDocelowa + "\nBazowa predkosc: " + predkosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getID() {
        return ID;
    }
}
