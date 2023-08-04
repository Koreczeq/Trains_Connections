import LinieKolejowe.*;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private List<Stacja> stacje = new ArrayList<>();
    private List<Polaczenie> polaczenia = new ArrayList<>();

    private List<Lokomotywa> lokomotywy = new ArrayList<>();

    private List<Wagon> wagony = new ArrayList<>();

    private List<Sklad> sklady = new ArrayList<>();

    public Menu() {
    }
    public void dodajStacje() {
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj nazwe stacji: ");
        String nazwa = input.nextLine();
        if(znajdzStacje(nazwa) == null) {
            Stacja stacja = new Stacja(nazwa);
            stacje.add(stacja);
        }
        else {
            System.out.println("Stacja o podanej nazwie juz istnieje!");
        }
    };

    public void dodajPolaczenie() {
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj nazwe poczatkowej stacji: ");
        String nazwaStacjiPoczatkowej = input.nextLine();
        System.out.println("Podaj nazwe koncowej stacji: ");
        String nazwaStacjiKoncowej = input.nextLine();
        System.out.println("Podaj dlugosc polaczenia: ");
        int dlugosc = input.nextInt();
        Stacja stacjaPoczatkowa = znajdzStacje(nazwaStacjiPoczatkowej);
        Stacja stacjaKoncowa = znajdzStacje(nazwaStacjiKoncowej);
        if(stacjaPoczatkowa == null || stacjaKoncowa == null || dlugosc < 0) {
            System.out.println("Nie udalo sie utworzyc polaczenia!!");
        }
        else {
            Polaczenie polaczenie = new Polaczenie(dlugosc,stacjaPoczatkowa,stacjaKoncowa);
            for(Polaczenie p: polaczenia) {
                if(polaczenie.equals(p)) {
                    System.out.println("Podane polaczenie juz istnieje!!!");
                    return;
                }
            }
            polaczenia.add(polaczenie);
        }

    };

    private Stacja znajdzStacje(String nazwa) {
        for(Stacja s : stacje ) {
            if(s.getNazwa().equals(nazwa))
                return s;
        }
        return null;
    }

    public void wyswietlStacje() {
        System.out.println("Stacje: ");
        for(Stacja s : stacje) {
            System.out.println(s);
        }
    }
    public void wyswietlPolaczenia() {
        System.out.println("Polaczenia: ");
        for(Polaczenie p : polaczenia) {
            System.out.println(p);
        }
    }

    public void dodajLokomotywe() {
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj nazwe Lokomotywy: ");
        String nazwa = input.nextLine();
        System.out.println("Podaj max liczbe wagonow: ");
        int maxLiczbaWagonow = input.nextInt();
        System.out.println("Podaj max Uciag: ");
        int maxUciag = input.nextInt();
        System.out.println("Podaj max liczbe wagonow Elektrycznych: ");
        int maxLiczbaWagonowElektrycznych = input.nextInt();
        input.nextLine();
        System.out.println("Podaj nazwe stacji Macierzystej: ");
        String nazwaStacjiMacierzystej = input.nextLine();
        Stacja stacjaMacierzysta;
        if(znajdzStacje(nazwaStacjiMacierzystej) != null) {
            stacjaMacierzysta = znajdzStacje(nazwaStacjiMacierzystej);
        }
        else {
            System.out.println("Nie znaleziono podanej Stacji!");
            return;
        }
        System.out.println("Podaj nazwe stacji Zrodlowej: ");
        String nazwaStacjiZrodlowej = input.nextLine();
        Stacja stacjaZrodlowa;
        if(znajdzStacje(nazwaStacjiZrodlowej) != null) {
            stacjaZrodlowa = znajdzStacje(nazwaStacjiZrodlowej);
        }
        else {
            System.out.println("Nie znaleziono podanej Stacji!");
            return;
        }
        System.out.println("Podaj nazwe stacji Docelowej: ");
        String nazwaStacjiDocelowej = input.nextLine();
        Stacja stacjaDocelowa;
        if(znajdzStacje(nazwaStacjiDocelowej) != null) {
            stacjaDocelowa = znajdzStacje(nazwaStacjiDocelowej);
            if(stacjaZrodlowa.equals(stacjaDocelowa)) {
                System.out.println("Stacja Docelowa powinna byc inna od Stacji Zrodlowej");
                return;
            }
        }
        else {
            System.out.println("Nie znaleziono podanej Stacji!");
            return;
        }
        System.out.println("Podaj predkosc: ");
        double  predkosc = input.nextDouble();
        if(predkosc <= 0 || predkosc >= 200) {
            System.out.println("bledna predkosc");
            return;
        }
        Lokomotywa lokomotywa = new Lokomotywa(nazwa,maxLiczbaWagonow,maxUciag,maxLiczbaWagonowElektrycznych,stacjaMacierzysta,stacjaZrodlowa,stacjaDocelowa,predkosc);
        lokomotywy.add(lokomotywa);
    }
    public void wyswietlLokomotywy() {
        System.out.println("Lokomotywy: ");
        for(Lokomotywa l : lokomotywy) {
            System.out.println(l);
        }
    }

    public void dodajWagon() {
        Scanner input = new Scanner(System.in);
        System.out.println("Wybierz rodzaj wagonu: ");
        System.out.println("1.Wagon Pasazerski");
        System.out.println("2.Wagon Towarowy");
        int wybor = input.nextInt();
        if(wybor == 1) {
            WagonPasazerski wagonPasazerski;
            System.out.println("Podaj wage wagonu: ");
            int wagaWagonu = input.nextInt();
            System.out.println("Podaj wage netto: ");
            int wagaNetto = input.nextInt();
            System.out.println("Podaj liczbe miejsc: ");
            int liczbaMiejsc = input.nextInt();
            System.out.println("Podaj czy z przedzialami 1.tak 2.nie : ");
            int czyZPrzedzialami = input.nextInt();
            if(czyZPrzedzialami == 1) {
                wagonPasazerski = new WagonPasazerski(wagaWagonu,wagaNetto,liczbaMiejsc,true);
                wagony.add(wagonPasazerski);
            }
            else if (czyZPrzedzialami == 2) {
                wagonPasazerski = new WagonPasazerski(wagaWagonu,wagaNetto,liczbaMiejsc,false);
                wagony.add(wagonPasazerski);
            }
            else {
                System.out.println("Bledny Wybor!");
                return;
            }
        }
        else if(wybor == 2) {
            WagonTowarowyPodstawowy wagonTowarowyPodstawowy;
            System.out.println("Podaj wage wagonu: ");
            int wagaWagonu = input.nextInt();
            System.out.println("Podaj wage netto: ");
            int wagaNetto = input.nextInt();
            System.out.println("Podaj max Uciag: ");
            int maxUCiag = input.nextInt();
            wagonTowarowyPodstawowy = new WagonTowarowyPodstawowy(false, wagaWagonu,wagaNetto,maxUCiag);
            wagony.add(wagonTowarowyPodstawowy);
        }
        else {
            System.out.println("Bledny wybor!");
        }

    }

    public void wyswietlWagony() {
        System.out.println("Wagony:");
        for(Wagon w : wagony) {
            System.out.println(w);
        }
    }

    public void stworzSklad() {
        System.out.println("Podaj ID lokomotywy ktora ma jezdzic w tym skladzie:");
        Scanner input = new Scanner(System.in);
        int ID = input.nextInt();
        for(Sklad s : sklady) {
            if(s.getLokomotywa().getID() == ID) {
                System.out.println("Podana lokomotywa jest juz podlaczona do innego Skladu");
                return;
            }
        }
        for(Lokomotywa l : lokomotywy) {
            if(l.getID() == ID) {
                Sklad sklad = new Sklad(l);
                sklady.add(sklad);
                return;
            }
        }
        System.out.println("Nie udalo sie znalezc lokomotywy o podanym ID");
    }

    public void DolaczWagonDoSkladu() throws RailException {
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj ID wagonu ktory ma zostac dolaczony: ");
        int ID = input.nextInt();
        Wagon wagon = null;
        for(Wagon w : wagony) {
            if(w.getID() == ID) {
                wagon = w;
                break;
            }
        }
        if(wagon == null) {
            System.out.println("Nie udalo sie znalezc podanego wagonu!");
            return;
        }
        System.out.println("Podaj ID lokomotywy ktora jest podlaczona do tego skladu:");
        ID = input.nextInt();
        for(Sklad s : sklady) {
            if(s.getLokomotywa().getID() == ID) {
                s.dolaczWagon(wagon);
                return;
            }
        }
        System.out.println("Nie udalo sie znalezc skladu ktory ma lokomotywe o podanym ID!");
    }

    public void wyswietlSklady() {
        System.out.println("Sklady: ");
        for(Sklad s : sklady) {
            System.out.println(s);
        }
    }

    public void wyswietlPlanowanaTraseSkladu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj ID lokomotywy ktora jest podlaczona do tego skladu:");
        int ID = input.nextInt();
        for(Sklad s : sklady) {
            if(s.getLokomotywa().getID() == ID) {
                System.out.println("Planowana trasa: ");
                Deque<Polaczenie> planowanaTrasa = s.getAktualnaTrasa();
                try {
                    for(Polaczenie p : planowanaTrasa) {
                        System.out.println(p);
                    }
                } catch (NullPointerException exception) {
                    System.out.println("Nie znaleziono trasy");
                }

                return;
            }
        }
        System.out.println("Nie udalo sie znalezc skladu ktory ma lokomotywe o podanym ID!");
    }
    public void pokazMenu() {
        System.out.println("Wybierz opcje: ");
        System.out.println("1.Dodaj stacje");
        System.out.println("2.Wyswietl liste stacji");
        System.out.println("3.Dodaj polaczenie");
        System.out.println("4.Wyswietl liste polaczen");
        System.out.println("5.Utworz Lokomotywe");
        System.out.println("6.Wyswietl Lokomotywy");
        System.out.println("7.Utworz Wagon");
        System.out.println("8.Wyswietl Wagony");
        System.out.println("9.Stworz Sklad");
        System.out.println("10.Dodaj Wagon do skladu");
        System.out.println("11.Wyswietl sklady");
        System.out.println("12.Wyswietl Planowana trase dla skladu");
        System.out.println("13.Wyswietl Sasiadujace Stacje");
        System.out.println("14.Wystartuj sklad");
        System.out.println("15.Wyswietl lokalizacje skladu");
        System.out.println("16.Zamknij program");

    }

    public void wyswietlSasiadujaceStacje() {
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj nazwe stacji: ");
        String nazwa = input.nextLine();
        if(znajdzStacje(nazwa) == null) {
            System.out.println("Podana Stacja nie istnieje");
        }
        else {
            Stacja stacja = znajdzStacje(nazwa);
            stacja.wyswietlSasiednieStacje();
        }
    }

    public void wystartujSklad() {
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj ID lokomotywy ktora jest podlaczona do tego skladu:");
        int ID = input.nextInt();
        for(Sklad s : sklady) {
            if (s.getLokomotywa().getID() == ID) {
                s.start();
            }
        }
    }

    public void dolaczWatki()  {
        for(Sklad s : sklady) {
            s.zakonczTrase();
        }
    }

    public void pokazLokalizacjeSkladu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj ID lokomotywy ktora jest podlaczona do tego skladu:");
        int ID = input.nextInt();
        for(Sklad s : sklady) {
            if (s.getLokomotywa().getID() == ID) {
                s.wyswietlPolozenie();
            }
        }
    }
}
