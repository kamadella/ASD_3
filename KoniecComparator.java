import java.util.Comparator;

//(n logn) bo to ulepszony quick sort

public class KoniecComparator implements Comparator<Struct_Booking> 
{
    public int compare(Struct_Booking x, Struct_Booking y) //pobieramy dwie rezerwacje żeby je ze soba porównać
    {
        if(x.koniec > y.koniec) return 1; //obiekt pierwszy jest większy dlatego zwracam 1
        if(x.koniec < y.koniec) return -1; //obiekt pierwszy jest mniejszy wiec zwracam -1
        return 0; //jeżeli obiekty są równe zwracamy 0

    }
}

// -1 (ujemna) – Obiekt pierwszy jest mniejszy
// 1 (dodatnia) – Obiekt pierwszy jest większy
// 0 – Obiekty są równe