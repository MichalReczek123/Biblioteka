package klasy;

import java.time.LocalDate;

public class Przyklad {

    public static void main(String[] args) {
        Osoba ala = new Osoba("Ala", "Kowalska", LocalDate.of(1999, 3, 4));
        System.out.println(ala);
        System.out.println(ala.getDataUrodzenia());

        Konto kontoAli = new Konto(1, 1000, ala);
        System.out.println(kontoAli);
        kontoAli.wplata(400);
        System.out.println(kontoAli);

        try {
            kontoAli.wyplata(2000);
            System.out.println("Udało się wypłacić");
        } catch (BrakSrodkow e) {
            System.out.println("Nie udało się wypłacić");
        }
        System.out.println(kontoAli);
    }
}
