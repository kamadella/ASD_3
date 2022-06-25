import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Main {


    //binarnie szukam czy dalo sie wziac jakas poprzednia rezerwacje -> (nlogn)
    static public int Szukanie(Struct_Booking lista_rezerwacji[], int numer_rezerwacji) {

        // przypisuje granice 
        int lewy = 0;
        int prawy = numer_rezerwacji - 1; // szukamy wsrod rezerwacji ktore są wcześniej niz moja stąd -1
        int srodek;

        while (lewy <= prawy) {
            srodek = (lewy + prawy) / 2; // wyznaczam srodek
            
            // jezeli poczatek mojej aktualnie badanej rezerwacji jest pozniej niz koniec
            // srodkowej rozpatrywanej
            if (lista_rezerwacji[numer_rezerwacji].poczatek >= lista_rezerwacji[srodek].koniec) {
                
                // jezeli poczatek mojej aktualnie badanej rezerwacji jest pozniej niz koniec
                // srodkowej rozpatrywanej +1
                if (lista_rezerwacji[numer_rezerwacji].poczatek >= lista_rezerwacji[srodek + 1].koniec) {
                    lewy = srodek + 1; // wtedy zacznij rozpatrywac na nowo zaczynajac od tego miejsca
                
                } else // to znaczy ze jest to najblizsza mozliwa wczesniejsza rezerwacja
                    return srodek; // zwracam wartosc najblizszej mozliwej poprzedniej rezerwacji
            
            } else // jezeli nie to znaczy ze trzeba rozpatrzyc lewa polowe
                prawy = srodek - 1;
        }

        return -1; // jezeli wczesniej nie mozna bylo nic zarezerwowac to zwracam -1
    }

    


// zwraca maksymalną sume przychodu
    static public int znajdowanie_max_przychodu(Struct_Booking lista_rezerwacji[], int ilosc_rezerwacji) {
        // sortowanie rezerwacji ze wzgledu na koniec czasu
        Arrays.sort(lista_rezerwacji, new KoniecComparator());

        /*
         * System.out.println("Dane po posortowaniu"); for (int i = 0; i <
         * ilosc_rezerwacji; i++) { lista_rezerwacji[i].showData(); }
         */

        // Tworze nowa tablice do przechowywania wynikow
        // tablica ta przechowuje sume przychodu
        int tablica[] = new int[ilosc_rezerwacji];
        tablica[0] = lista_rezerwacji[0].przychod; // miejsce 0 jest po prostu tym samym

        // zapełnianie tablicy zaczynamy od 1 bo 0 określiłam wczesniej
        for (int i = 1; i < ilosc_rezerwacji; i++) {
            int przychod = lista_rezerwacji[i].przychod; // przypisuje przychod z danej rezerwacji do inta
            int czy_poprzednia = Szukanie(lista_rezerwacji, i); // podaje liste i na której z kolei rezerwacji jestem
            if (czy_poprzednia != -1) {
                przychod = przychod + tablica[czy_poprzednia];
            }
            // przechowuje maksymalny przychod
            tablica[i] = Math.max(przychod, tablica[i - 1]);
            //czy większy jest z tą rezerwacją |czy| większy był bez tej rezerwacji
        }

        // na ostatnim miejscu w tablicy bedzie maxymalny przychod
        return tablica[ilosc_rezerwacji - 1]; // -1 żeby się indeks zgadzał bo tablica jest od 0
    }




    public static void main(String[] args) {
        Struct_Booking lista_rezerwacji[];
        lista_rezerwacji = new Struct_Booking[1];
        int ilosc_rezerwacji = 0;

        try {
            File pliczek_wejsciowy = new File("duzy1.txt");
            Scanner odczyt_z_pliku = new Scanner(pliczek_wejsciowy);
            ilosc_rezerwacji = odczyt_z_pliku.nextInt();

            lista_rezerwacji = new Struct_Booking[ilosc_rezerwacji];

            for (int i = 0; i < ilosc_rezerwacji; i++) {
                int p = odczyt_z_pliku.nextInt();
                int k = odczyt_z_pliku.nextInt();
                int pr = odczyt_z_pliku.nextInt();
                lista_rezerwacji[i] = new Struct_Booking(p, k, pr);
            }
            odczyt_z_pliku.close();
        } catch (FileNotFoundException x) {
            System.out.println("Podano zły pliczek - nie widzę go :(");
            System.exit(-1);
        }

        System.out.println("ilość rezerwacji: " + ilosc_rezerwacji);
        /*
         * System.out.println("Wszystkie rezerwacje:"); for (int i = 0; i <
         * ilosc_rezerwacji; i++) { lista_rezerwacji[i].showData(); }
         */

        long start = System.currentTimeMillis(); // zaczyna mierzyć czas
        int max_przychod = znajdowanie_max_przychodu(lista_rezerwacji, ilosc_rezerwacji);
        System.out.println(" ");
        System.out.println("Maksymalny mozliwy do uzyskania dochod to: " + max_przychod);

        long stop = System.currentTimeMillis(); // zatrzymuje pomiar czasu
        System.out.println("Czas wykonania algorytmu: " + (stop - start) + " ms");
        System.out.println(" ");

        try {
            PrintWriter zapis_do_pliku = new PrintWriter("out.txt");
            zapis_do_pliku.print(max_przychod);
            zapis_do_pliku.close();
        } catch (IOException x) {
            System.out.println("blad zapisu wyniku- próbuj dalej");
            System.exit(-1);
        }

    }
}
