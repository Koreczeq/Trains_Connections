package LinieKolejowe;

import java.util.List;

public class Polaczenie {
    private int dlugosc;
    private Stacja stacjaPoczatkowa;
    private Stacja stacjaKoncowa;

    public Polaczenie(int dlugosc, Stacja stacjaPoczatkowa, Stacja stacjaKoncowa) {
        this.dlugosc = dlugosc;
        this.stacjaPoczatkowa = stacjaPoczatkowa;
        this.stacjaKoncowa = stacjaKoncowa;
        stacjaPoczatkowa.dodajPolaczenie(this);
        stacjaKoncowa.dodajPolaczenie(this);
    }


    public Stacja getStacjaPoczatkowa() {
        return stacjaPoczatkowa;
    }

    public Stacja getStacjaKoncowa() {
        return stacjaKoncowa;
    }
    public Stacja zwrocNastepnaStacje(Stacja stacjaPoczatkowa) {
        Stacja stacjaKolejna;
        if(this.getStacjaPoczatkowa().equals(stacjaPoczatkowa))
            stacjaKolejna = this.getStacjaKoncowa();
        else
            stacjaKolejna = this.getStacjaPoczatkowa();
        return stacjaKolejna;
    }

    @Override
    public String toString() {
        return "Polaczenie pomiedzy: " + this.stacjaPoczatkowa + " a: " + this.stacjaKoncowa + " o odleglosci: " + this.dlugosc + "km!";
    }

    public int getDlugosc() {
        return this.dlugosc;
    }

    @Override
    public boolean equals(Object poloczenie) {
        if(this.stacjaPoczatkowa == null || this.stacjaKoncowa == null || ((Polaczenie)poloczenie).stacjaPoczatkowa == null || ((Polaczenie)poloczenie).stacjaKoncowa == null)
            return false;
        if(this.stacjaPoczatkowa.equals(((Polaczenie)poloczenie).stacjaPoczatkowa) && this.stacjaKoncowa.equals(((Polaczenie)poloczenie).stacjaKoncowa))
            return true;
        if(this.stacjaPoczatkowa.equals(((Polaczenie)poloczenie).stacjaKoncowa) && this.stacjaKoncowa.equals(((Polaczenie)poloczenie).stacjaPoczatkowa))
            return true;
        return false;
    }
}
