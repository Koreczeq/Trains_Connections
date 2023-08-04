package LinieKolejowe;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class GrafPolaczen {

    public Deque<Polaczenie> znajdzPolaczenie(Stacja stacjaPoczatkowa, Stacja stacjaKoncowa) {
        Deque<Polaczenie> trasa = new ArrayDeque<>();
        if(stacjaPoczatkowa == null || stacjaKoncowa == null) {
            System.out.println("Podano bledne stacje!");
            return null;
        }
        if(stacjaPoczatkowa.equals(stacjaKoncowa))
            return null;
        List<Stacja> odwiedzoneStacje = new ArrayList<>();
        Deque<Stacja> stacjeDoOdwiedzenia = new ArrayDeque<>();
        odwiedzoneStacje.add(stacjaPoczatkowa);
        stacjeDoOdwiedzenia.add(stacjaPoczatkowa);
        Stacja stacjaKolejna = stacjaPoczatkowa;
        while(!stacjeDoOdwiedzenia.isEmpty()) {
            Polaczenie polaczenie = przeszukiwanieWGlab(stacjaKolejna, odwiedzoneStacje);
            if(polaczenie != null) {
                trasa.addLast(polaczenie);
                stacjaKolejna = polaczenie.zwrocNastepnaStacje(odwiedzoneStacje.get(odwiedzoneStacje.size()-1));
                odwiedzoneStacje.add(stacjaKolejna);
                stacjeDoOdwiedzenia.add(stacjaKolejna);
                if(stacjaKolejna.equals(stacjaKoncowa))
                    return trasa;
            }
            else {
                stacjaKolejna = stacjeDoOdwiedzenia.getLast();
                stacjeDoOdwiedzenia.removeLast();
                trasa.removeLast();
            }
        }
        //return trasa;
        System.out.println("Nie udalo sie znalezc polaczenia");
        return null;
    }

    private Polaczenie przeszukiwanieWGlab(Stacja stacjaPoczatkowa, List<Stacja> odwiedzoneStacje) {
        for(Polaczenie polaczenie : stacjaPoczatkowa.getPolaczenia()) {
            boolean czyOdwiedzone = false;
            Stacja stacjaKolejna = polaczenie.zwrocNastepnaStacje(stacjaPoczatkowa);
            for(Stacja stacja : odwiedzoneStacje) {
                if((stacjaKolejna.equals(stacja)))
                    czyOdwiedzone = true;
            }
            if(!czyOdwiedzone)
                return polaczenie;
        }
        return null;
    }

}
