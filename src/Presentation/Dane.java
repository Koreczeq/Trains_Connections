package Presentation;

import LinieKolejowe.*;

import java.util.ArrayList;
import java.util.List;

public class Dane {
    List<Lokomotywa> lokomotywy = new ArrayList<>();
    List<Wagon> wagony = new ArrayList<>();
    List<Sklad> sklady = new ArrayList<>();
    List<Stacja> stacje = new ArrayList<>();
    List<Polaczenie> polaczenia = new ArrayList<>();

    List<String> nazwy = List.of("Warszawa", "Krakow", "Gdynia", "Gdansk", "Sopot", "Katowice", "Konin", "Kalisz", "Poznan", "Wroclaw", "Lodz", "Szczecin",
            "Bydgoszcz", "Lublin", "Bialystok", "Czestochowa", "Radom", "Torun", "Sosnowiec", "Kielce", "Rzeszow", "Gliwice", "Zabrze", "Olsztyn", "BielskoBiala", "Bytom",
            "ZielonaGora", "Rybnik", "RudaSlaska", "Opole", "Tychy", "GorzowWielkopolski", "DabrowaGornicza", "Elblag", "Plock", "Walbrzych", "Wloclawek", "Tarnow",
            "Chorzow", "Koszalin", "Legnica", "Grudziadz", "Jaworzno", "Slupsk", "JastrzebieZdroj", "NowySacz", "JeleniaGora", "Siedlce", "Myslowice", "PiotrkowTrybunalski",
            "Pila", "Inowroclaw", "Lubin", "OstrowWielkopolski", "Suwalki", "OstrowiecSwietokrzyski", "Gniezno", "Stargard", "Glogow", "SiemianowiceSlaskie", "Pabianice",
            "Leszno", "Zamosc", "Lomza", "Chelm", "TomaszowMazowiecki", "Zory", "Elk", "Pruszkow", "TarnowskieGory", "Przemysl", "StalowaWola", "KedzierzynKozle",
            "Mielec", "Tczew", "Belchatow", "BialaPodlaska", "Swidnica", "Bedzin", "Zgierz", "PiekarySlaskie", "Raciborz", "Legionowo", "Ostroleka", "Swietochowice",
            "Wejherowo", "Zawiercie", "Rumia", "Starachowice", " WodzislawSlaski", "Piaseczno", "StarogardGdanski", "Pulawy", "Tarnobrzeg", "Krosno", "Kolobrzeg",
            "Radomsko", "Debica", "SkarzyskoKamienna", "Otwock");

    public Dane() throws RailException {
        dodajStacje();
        dodajPolaczenia();
        dodajLokomotywy();
        dodajWagony();
        utworzSklady();
    }
    public void dodajStacje() {

        for (String s : nazwy) {
            Stacja stacja = new Stacja(s);
            stacje.add(stacja);
        }
    }
    public void dodajPolaczenia() {
        for(int i = 0; i < 200; i++) {
            boolean polaczenieIstnieje = false;
            int num1 = (int)(Math.random()*stacje.size());
            int num2 = (int)(Math.random()*stacje.size());
            int dlugosc = (int)(Math.random()*200 + 200);
            while(num1 == num2)
                num2 = (int)(Math.random()*stacje.size());
            Polaczenie pol = new Polaczenie(dlugosc,stacje.get(num1),stacje.get(num2));
            for(Polaczenie p : polaczenia) {
                if(pol.equals(p))
                    polaczenieIstnieje = true;
                    break;
            }
            if(polaczenieIstnieje)
                i--;
            else
                polaczenia.add(pol);
        }
    }

    public void dodajLokomotywy() {
        for(int i = 0; i<25; i++){
            int num1 = (int)(Math.random()*stacje.size());
            int num2 = (int)(Math.random()*stacje.size());
            int num3 = (int)(Math.random()*stacje.size());
            while(num2 == num3)
                num3 = (int)(Math.random()*stacje.size());
            int maxLiczbaWagonow = (int)(Math.random()*10+5);
            int maxUciag= (int)(Math.random()*10000+10000);
            int maxLiczbaWagonowElektrycznych = (int)(Math.random()*maxLiczbaWagonow);
            double predkosc = Math.random()*100 + 50;
            String nazwa = "lokomotywa" + i;
            Lokomotywa lokomotywa = new Lokomotywa(nazwa, maxLiczbaWagonow,maxUciag,maxLiczbaWagonowElektrycznych,stacje.get(num1),stacje.get(num2),stacje.get(num3),predkosc);
            lokomotywy.add(lokomotywa);
        }
    }

    public void dodajWagony() {
        for(int i = 0; i < 100; i++) {
            int wagaWagonu = (int)(Math.random()*1000 + 500);
            int wagaNetto = (int)(Math.random()*4000);
            int liczbaMiejsc = (int)(Math.random()*100 + 50);
            boolean czyPrzedzialy = !(Math.random() < 0.5);

            WagonPasazerski wagonPasazerski = new WagonPasazerski(wagaWagonu,wagaNetto,liczbaMiejsc,czyPrzedzialy);
            wagony.add(wagonPasazerski);
        }

        for(int i = 0; i < 100; i++) {
            int wagaWagonu = (int)(Math.random()*1000 + 500);
            int wagaNetto = (int)(Math.random()*4000);
            int maxUciag = (int)(Math.random()*2000 + 4000);

            WagonTowarowyPodstawowy wagonTowarowyPodstawowy = new WagonTowarowyPodstawowy(false, wagaWagonu,wagaNetto,maxUciag);
            wagony.add(wagonTowarowyPodstawowy);
        }
    }

    public void utworzSklady() throws RailException {
        for (Lokomotywa lokomotywa : lokomotywy) {
            Sklad sklad = new Sklad(lokomotywa);
            sklady.add(sklad);
        }
        for(Wagon wagon : wagony) {
            int num = (int)(Math.random()* sklady.size());
            sklady.get(num).dolaczWagon(wagon);
        }
    }

    public void wyswietlStacje() {
        for(Stacja s : stacje)
            System.out.println(s);
    }

    public void wyswietlPolaczenia() {
        for(Polaczenie p : polaczenia)
            System.out.println(p);
    }

    public void wyswietlLokomotywy() {
        for(Lokomotywa l : lokomotywy) {
            System.out.println(l);
        }
    }

    public void wyswietlWagony() {
        for(Wagon w : wagony) {
            System.out.println(w);
        }
    }
    public void wyswietlSklady() {
        for(Sklad s : sklady) {
            System.out.println(s);
        }
    }
}
