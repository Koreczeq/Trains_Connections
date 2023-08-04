package LinieKolejowe;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Stacja {
    private String nazwa;

    private List<Polaczenie> polaczenia = new LinkedList<>();

    public Stacja(String nazwa) {
        this.nazwa = nazwa;
    }
    public void dodajPolaczenie(Polaczenie polaczenie) {
        this.polaczenia.add(polaczenie);
    }

    public List<Polaczenie> getPolaczenia() {
        return this.polaczenia;
    }
    public String getNazwa() {
        return this.nazwa;
    }

    public void wyswietlSasiednieStacje() {
        for(Polaczenie pol : polaczenia) {
            System.out.println(pol.zwrocNastepnaStacje(this));
        }
    }

    @Override
    public String toString() {
        return "Stacja: " + this.nazwa;
    }

    @Override
    public boolean equals(Object stacja) {
        return (Objects.equals(this.nazwa, ((Stacja) stacja).nazwa));
    }
}
