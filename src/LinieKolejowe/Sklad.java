package LinieKolejowe;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Sklad extends Thread{
    public Lokomotywa getLokomotywa() {
        return lokomotywa;
    }
    private Lokomotywa lokomotywa;
    private List<Wagon> wagony = new ArrayList<>();
    private GrafPolaczen grafPolaczen = new GrafPolaczen();

    private Deque<Polaczenie> aktualnaTrasa = new ArrayDeque<>();
    public Polaczenie aktualnePolaczenie;
    public Stacja aktualnaStacja;
    private int dlugoscTrasy;
    private int dlugoscPokonanaWOdcinku;
    private int dlugoscPokonanychOdcinkow;

    private boolean running = true;

    public Sklad(Lokomotywa lokomotywa){
        this.lokomotywa = lokomotywa;
        aktualnaStacja = lokomotywa.getStacjaZrodlowa();
    }

    public void dolaczWagon(Wagon wagon) throws RailException {
        try {
            if(this.lokomotywa.czyMoznaDolaczycWagon(wagon) && wagon.getLokomotywa() == null) {
                wagon.podlaczWagon(this.lokomotywa);
                lokomotywa.podlaczWagon(wagon);
                this.wagony = this.lokomotywa.getWagony();
            }
            else {
                throw new RailException("Wagon nie moze zostac dolaczony!!!");
            }
        }
        catch (RailException exception) {
            System.out.println("Wagon nie zostal dolaczony!!");
        }

    }

    public int zwrocProcentPokonagoOdcinka() {
        double czescPokonanegoOdcinka;
        try {
           czescPokonanegoOdcinka = ((double) dlugoscPokonanaWOdcinku /(double)aktualnePolaczenie.getDlugosc());
        } catch (ArithmeticException exception1) {
            System.out.println("Nie mozna dzielic przez zero");
            return 0;
        } catch (NullPointerException exception) {
            System.out.println("Nie znaleziono polaczenia!");
            return 0;
        }
            return (int)(czescPokonanegoOdcinka*100);
    }

    public int zwrocProcentPokonejTrasy() {
        double czescPokonanejTrasy;
        try{
            czescPokonanejTrasy = ((double)(dlugoscPokonanychOdcinkow+ dlugoscPokonanaWOdcinku)/(double) dlugoscTrasy);
        } catch (ArithmeticException exception) {
            System.out.println("Nie mozna dzielic przez zero");
            return 0;
        }

        return  (int)(czescPokonanejTrasy*100);
    }

    public void wyswietlPolozenie() {
        if(aktualnePolaczenie == null) {
            System.out.println("Sklad o ID: " + lokomotywa.getID() + " znajduje sie na stacji: " + aktualnaStacja);
        }
        else {
            System.out.println("Sklad o ID: " + lokomotywa.getID() + " znajduje sie w " + zwrocProcentPokonagoOdcinka() + "% odcinku: " + aktualnePolaczenie + "");
        }
    }

    public Deque<Polaczenie> getAktualnaTrasa() {
        return grafPolaczen.znajdzPolaczenie(lokomotywa.getStacjaZrodlowa(),lokomotywa.getStacjaDocelowa());
    }

    public void zakonczTrase() {
        running = false;
    }

    public void przejazd() throws RailException, InterruptedException {
        running = true;
        aktualnaTrasa.clear();
        aktualnaTrasa = grafPolaczen.znajdzPolaczenie(lokomotywa.getStacjaZrodlowa(),lokomotywa.getStacjaDocelowa());
        aktualnePolaczenie = aktualnaTrasa.getFirst();
        aktualnaTrasa.removeFirst();
        aktualnaStacja = lokomotywa.getStacjaZrodlowa();
        dlugoscPokonanaWOdcinku = 0;
        dlugoscPokonanychOdcinkow = 0;
        dlugoscTrasy = 0;
        for(Polaczenie p : aktualnaTrasa) {
            dlugoscTrasy += p.getDlugosc();
        }
        while(aktualnaStacja != lokomotywa.getStacjaDocelowa()) {
            lokomotywa.aktualizujPredkosc();
            dlugoscPokonanaWOdcinku += (int)lokomotywa.getPredkosc();
            if(dlugoscPokonanaWOdcinku > aktualnePolaczenie.getDlugosc()) {
                dlugoscPokonanychOdcinkow += aktualnePolaczenie.getDlugosc();
                aktualnaStacja = aktualnePolaczenie.zwrocNastepnaStacje(aktualnaStacja);
                aktualnePolaczenie = null;
                dlugoscPokonanaWOdcinku = 0;
                if(aktualnaStacja == lokomotywa.getStacjaDocelowa())
                    break;
                Thread.sleep(2000);
                aktualnePolaczenie = aktualnaTrasa.getFirst();
                aktualnaTrasa.removeFirst();
            }
            else {
                Thread.sleep(1000);
            }

        }

    }

    @Override
    public void run() {
        while(running) {
            try {
                przejazd();
            } catch (RailException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Stacja temp = lokomotywa.getStacjaZrodlowa();
            lokomotywa.zmienStacjeZrodlowa(lokomotywa.getStacjaDocelowa());
            lokomotywa.zmienStacjeDocelowa(temp);
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        String sklad = ("Sklad: \n" + lokomotywa.toString());
        sklad += "\nWagony: ";
        for(Wagon w : wagony) {
            sklad += ("\n" + w.toString());
        }

        return sklad;
    }
}
