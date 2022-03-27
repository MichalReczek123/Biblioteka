package watki.konta.v3_synchronizacja_blokow;

import java.time.LocalDate;

class Przeploty {

	public static void main(String[] args) {
		final int N = 100_000;
		final int KWOTA = 10;
		
		Osoba ala = new Osoba("Ala", "Kowalska", LocalDate.now());
		Konto konto = new Konto(1, 1000_000, ala);
		
		System.out.println(konto);
		
		Thread wplacacz = new Thread(() -> {
				for(int i = 0 ; i < N; i++) {
					konto.wplata(KWOTA);
				}
		});
		
		Thread wyplacacz = new Thread(() -> {
				for(int i = 0; i < N; i++) {
					try {
						konto.wyplata(KWOTA);
					} catch (BrakSrodkow e) {
						System.err.println(e.getMessage());
					}
				}
		});
		
		System.out.println("Uruchamiam wątki");
		wplacacz.start();
		wyplacacz.start();

		System.out.println("Czekam na zakończenie");
		try {
			wplacacz.join();
			wyplacacz.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Wątki zakończone, konto na końcu:");
		System.out.println(konto);
	}

}
