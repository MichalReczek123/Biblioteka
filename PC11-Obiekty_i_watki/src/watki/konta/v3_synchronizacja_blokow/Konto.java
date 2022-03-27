package watki.konta.v3_synchronizacja_blokow;

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
	
	public void wplata(int kwota) {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " we wpłacie");
		}
		
		synchronized(this) {
			saldo += kwota;
			this.notify();
		}
	}

	public void wyplata(int kwota) throws BrakSrodkow {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " w wypłacie");
		}
		
		synchronized(this) {
			if(kwota > saldo) {
				throw new BrakSrodkow("Brak środków na koncie nr " + numer);
			}
			saldo -= kwota;
		}
	}
	
	public void wyplataCzekaj(int kwota) {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " w wypłacie");
		}
		try {
			synchronized(this) {
				while(kwota > saldo) {
					this.wait();
				}
				saldo -= kwota;
				this.notify();
			}
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}
	
	public String toString() {
		return "Konto nr " + getNumer() + ", saldo: " + getSaldo() + ", wł.: " + getWlasciciel();			
	}
}

