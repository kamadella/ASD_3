public class Struct_Booking {
    int poczatek, koniec, przychod;
 
    Struct_Booking(int poczatek, int koniec, int przychod)
    {
        this.poczatek = poczatek;
        this.koniec = koniec;
        this.przychod = przychod;
    }

    public void showData(){
        System.out.print("poczatek = "+poczatek+ "  " + " koniec = "+koniec+ "  " + " przychod = "+przychod);
        System.out.println();
      }
}
