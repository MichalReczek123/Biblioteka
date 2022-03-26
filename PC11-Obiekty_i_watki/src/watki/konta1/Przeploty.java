package watki.konta1;

public class Przeploty {
    private static final int ILE_RAZY = 100_000;
    private static final int KWOTA = 10;

    public static void main(String[] args) {
        Osoba ala = new Osoba("Ala", "Kowalska", "1999-03-04");
        Konto konto = new Konto(1, 1_000_000, ala);

        Thread wplacacz = new Thread(() -> {
            for(int i = 0; i < ILE_RAZY; i++) {
                konto.wplata(KWOTA);
            }
        });

        Thread wyplacacz = new Thread(() -> {
            for(int i = 0; i < ILE_RAZY; i++) {
                try {
                    konto.wyplata(KWOTA);
                } catch (BrakSrodkow e) {
                    System.err.println("BRAK ŚRODKÓW");
                }
            }
        });

        System.out.println("Na początku saldo: " + konto.getSaldo());
        System.out.println("Uruchamiam wątki");

        wplacacz.start();
        wyplacacz.start();

        // Czekamy na zakończenie wątków
        try {
            wplacacz.join();
            wyplacacz.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Wątki zakończone");
        System.out.println("Na końcu saldo: " + konto.getSaldo());
    }
}
