package watki.konta1;

public class CzekanieNaKase2 {
    public static void main(String[] args) {
        Osoba ala = new Osoba("Ala", "Kowalska", "1999-03-04");
        Konto konto = new Konto(1, 2200, ala);

        Thread wplacacz = new Thread(() -> {
            try {
                while(true) {
                    Thread.sleep(5000);
                    konto.wplata(1000);
                    System.out.println("wpłaciłem 1000, teraz saldo = " + konto.getSaldo());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread wyplacacz = new Thread(() -> {
            try {
                while(true) {
                    try {
                        Thread.sleep(500);
                        konto.wyplata2(200);
                        System.out.println("wypłaciłem 200, teraz saldo = " + konto.getSaldo());
                    } catch (BrakSrodkow e) {
                        System.err.println("BRAK ŚRODKÓW");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Na początku saldo: " + konto.getSaldo());
        System.out.println("Uruchamiam wątki");

        wplacacz.start();
        wyplacacz.start();
    }
}
