package watki.konta.v2_synchronizacja_metod;

class Konto_Niepoprawnie {
	private final int numer;
	private int saldo;
	private Osoba wlasciciel;
	
	public Konto_Niepoprawnie(int numer, int saldo, Osoba wlasciciel) {
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
	
	public synchronized void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	
	public String toString() {
		return "Konto nr " + numer + ", saldo: " + saldo + ", wł.: " + wlasciciel;			
	}
	
	public void wplata(int kwota) {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " we wpłacie");
		}
		
		this.setSaldo(this.getSaldo() + kwota);
		
		
		// Nawet jeśli getter i setter są synchronizowane, to powyższy zapis jest niepoprawny
		// bo to się tłumaczy na
//		int stareSaldo = this.getSaldo();
//		int noweSaldo = stareSaldo + kwota;
		
// W TYM momencie inny wątek może też odczytać saldo, zmienić i zapisać
//		this.setSaldo(noweSaldo);
		
		// między getem a setem mogą wejść inne wątki
	}


	public void wyplata(int kwota) throws BrakSrodkow {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " w wypłacie");
		}
		if(kwota > saldo) {
			throw new BrakSrodkow("Brak środków na koncie nr " + numer);
		}
		
		this.setSaldo(this.getSaldo() - kwota);
	}

}

