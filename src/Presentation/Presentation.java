package Presentation;
import LinieKolejowe.*;

import java.util.ArrayList;
import java.util.List;

public class Presentation {
    static List<Stacja> stacje = new ArrayList<>();
    static List<Polaczenie> polaczenia = new ArrayList<>();
    static List<Lokomotywa> lokomotywy = new ArrayList<>();
    static List<Wagon> wagony = new ArrayList<>();
    static List<Sklad> sklady = new ArrayList<>();
    public static void main(String args[]) throws RailException, InterruptedException {
        dodajDane();
        wyswietlDane();
        for(Sklad s : sklady) {
            System.out.println("Planowana trasa skladu: ");
            for(Polaczenie p : s.getAktualnaTrasa()) {
                System.out.println(p);
            }
        }

        for(Sklad s : sklady) {
            System.out.println("Uruchomienie skladu: ");
            s.start();
        }
        for(int i = 0; i < 30; i++) {
            for(Sklad s : sklady) {
                s.wyswietlPolozenie();
                Thread.sleep(1000);
            }
        }

        for(Sklad s : sklady) {
                s.zakonczTrase();
        }



    }

    public static void dodajDane() throws RailException {
        stacje.add(new Stacja("Wawa"));
        stacje.add(new Stacja("Krk"));
        stacje.add(new Stacja("Lublin"));
        stacje.add(new Stacja("Zamosc"));
        stacje.add(new Stacja("Gdynia"));
        stacje.add(new Stacja("Katowice"));
        stacje.add(new Stacja("Lodz"));
        stacje.add(new Stacja("Wroclaw"));

        polaczenia.add(new Polaczenie(300,stacje.get(0),stacje.get(1)));
        polaczenia.add(new Polaczenie(300,stacje.get(1),stacje.get(2)));
        polaczenia.add(new Polaczenie(300,stacje.get(2),stacje.get(3)));
        polaczenia.add(new Polaczenie(300,stacje.get(3),stacje.get(4)));
        polaczenia.add(new Polaczenie(300,stacje.get(4),stacje.get(5)));
        polaczenia.add(new Polaczenie(300,stacje.get(5),stacje.get(6)));
        polaczenia.add(new Polaczenie(300,stacje.get(6),stacje.get(7)));
        polaczenia.add(new Polaczenie(300,stacje.get(0),stacje.get(3)));
        polaczenia.add(new Polaczenie(300,stacje.get(3),stacje.get(7)));
        polaczenia.add(new Polaczenie(300,stacje.get(2),stacje.get(5)));
        polaczenia.add(new Polaczenie(300,stacje.get(1),stacje.get(6)));

        lokomotywy.add(new Lokomotywa("lok1", 10,10000,5, stacje.get(0),stacje.get(0),stacje.get(2),100.0));
        lokomotywy.add(new Lokomotywa("lok2", 10,10000,5, stacje.get(0),stacje.get(0),stacje.get(7),100.0));
        lokomotywy.add(new Lokomotywa("lok3", 10,10000,5, stacje.get(0),stacje.get(3),stacje.get(6),100.0));

        wagony.add(new WagonPasazerski(1000,1000,100,false));
        wagony.add(new WagonPasazerski(1000,1000,100,false));
        wagony.add(new WagonPasazerski(1000,1000,100,false));
        wagony.add(new WagonPasazerski(1000,1000,100,false));
        wagony.add(new WagonTowarowyPodstawowy(false, 1000,1000,10000));
        wagony.add(new WagonTowarowyPodstawowy(false, 1000,1000,10000));
        wagony.add(new WagonTowarowyPodstawowy(false, 1000,1000,10000));
        wagony.add(new WagonTowarowyPodstawowy(false, 1000,1000,10000));

        sklady.add(new Sklad(lokomotywy.get(0)));
        sklady.add(new Sklad(lokomotywy.get(1)));
        sklady.add(new Sklad(lokomotywy.get(2)));

        sklady.get(0).dolaczWagon(wagony.get(0));
        sklady.get(0).dolaczWagon(wagony.get(1));
        sklady.get(0).dolaczWagon(wagony.get(2));
        sklady.get(1).dolaczWagon(wagony.get(3));
        sklady.get(1).dolaczWagon(wagony.get(4));
        sklady.get(1).dolaczWagon(wagony.get(5));
        sklady.get(2).dolaczWagon(wagony.get(6));
        sklady.get(2).dolaczWagon(wagony.get(7));

    }

    public static void wyswietlDane() {
        for(Stacja s :stacje) {
            System.out.println(s);
        }
        for(Polaczenie p : polaczenia) {
            System.out.println(p);
        }
        for(Sklad s : sklady) {
            System.out.println(s);
        }
    }

}
