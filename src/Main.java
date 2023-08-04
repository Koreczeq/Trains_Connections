import LinieKolejowe.Polaczenie;
import LinieKolejowe.RailException;
import LinieKolejowe.Stacja;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws RailException, InterruptedException {
        Scanner input = new Scanner(System.in);
        int wybor = 0;
        Menu menu = new Menu();
        while (wybor != 16) {
            menu.pokazMenu();
            wybor = input.nextInt();
            switch (wybor) {
                case 1:
                    menu.dodajStacje();
                    break;
                case 2:
                    menu.wyswietlStacje();
                    break;
                case 3:
                    menu.dodajPolaczenie();
                    break;
                case 4:
                    menu.wyswietlPolaczenia();
                    break;
                case 5:
                    menu.dodajLokomotywe();
                    break;
                case 6:
                    menu.wyswietlLokomotywy();
                    break;
                case 7:
                    menu.dodajWagon();
                    break;
                case 8:
                    menu.wyswietlWagony();
                    break;
                case 9:
                    menu.stworzSklad();
                    break;
                case 10:
                    menu.DolaczWagonDoSkladu();
                    break;
                case 11:
                    menu.wyswietlSklady();
                    break;
                case 12:
                    menu.wyswietlPlanowanaTraseSkladu();
                    break;
                case 13:
                    menu.wyswietlSasiadujaceStacje();
                    break;
                case 14:
                    menu.wystartujSklad();
                    break;
                case 15:
                    menu.pokazLokalizacjeSkladu();
                    break;
            }
        }
        menu.dolaczWatki();
    }


}