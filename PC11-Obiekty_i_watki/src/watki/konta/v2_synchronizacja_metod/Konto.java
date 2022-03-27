package watki.konta.v2_synchronizacja_metod;

class Konto {
	private final int numer;
	private int saldo;
	private Osoba wlasciciel;
	
	public Konto(int numer, int saldo, Osoba wlasciciel) {
		this.numer = numer;
		this.saldo = saldo;
		this.wlasciciel = wlasciciel;
	}

	public synchronized Osoba getWlasciciel() {
		return wlasciciel;
	}

	public synchronized void setWlasciciel(Osoba wlasciciel) {
		this.wlasciciel = wlasciciel;
	}

	public int getNumer() {
		return numer;
	}

	public synchronized int getSaldo() {
		return saldo;
	}
	
	public synchronized void wplata(int kwota) {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " we wpłacie");
		}
		
		saldo += kwota;
		notify();
		
		// System.out.println("I jeszcze coś robię");
		// saldo --; // potrącić prowizję
		
		// wątek obudzony z notify musi jeszcze poczekać, aż ja skończę tu robotę
		// bo to jeszcze ja zajmuję sekcje "synchorized"
	}

	public synchronized void wyplata(int kwota) throws BrakSrodkow {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " w wypłacie");
		}
		if(kwota > saldo) {
			throw new BrakSrodkow("Brak środków na koncie nr " + numer);
		}
		
		saldo -= kwota;
	}
	
	public synchronized void wyplataCzekaj(int kwota) {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " w wypłacie");
		}
		try {
			while(kwota > saldo) {
				wait();
				// po obudzeniu (notify) ten wątek na normalnych prawach
				// czeka na dostęp do sekcji synchronizowanej
				// 1) wątek, który zrobił notify, musi wyjść
				// 2) może pojawić się trzeci wątek, który "wepchenie się" do metody synchronizowanej,
				// zanim zrobi to TEN wątek obudzony z wait-a
				// Również z tego powodu warunek oczekiwania należy sprawdzić ponownie po obudzeniu
				// Standardowy zapis - pętla while.
			}
			saldo -= kwota;
			notify(); // "budzenie kaskadowe"
			
		} catch (InterruptedException e) {
			// Taki wyjątek pojawi się gdy podczas gdy wątek A śpi (w wait, sleep itp.)
			// inny wątek B wywoła na wątku A metodę interrupt
			// Zazwyczaj robi się to, gdy kończy się program albo anuluje wątki, które nie będą potrzebne.
			
			// interrupt nie jest normalnym zakończeniem oczekiwania. Warunek logiczny nie został spełniony
			// - w takiej sytuacji nie powinniśmy podejmować akcji zmieniających stan,
			// ani nie powinnismy kontynuować czekania.
			System.err.println(e);
		}
	}
	
	public String toString() {
		return "Konto nr " + getNumer() + ", saldo: " + getSaldo() + ", wł.: " + getWlasciciel();			
	}
}

