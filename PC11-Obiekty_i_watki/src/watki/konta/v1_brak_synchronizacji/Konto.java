package watki.konta.v1_brak_synchronizacji;

class Konto {
	private final int numer;
	private int saldo;
	private Osoba wlasciciel;
	
	public Konto(int numer, int saldo, Osoba wlasciciel) {
		this.numer = numer;
		this.saldo = saldo;
		this.wlasciciel = wlasciciel;
	}
	

	public Osoba getWlasciciel() {
		return wlasciciel;
	}

	public void setWlasciciel(Osoba wlasciciel) {
		this.wlasciciel = wlasciciel;
	}

	public int getNumer() {
		return numer;
	}

	public int getSaldo() {
		return saldo;
	}
	
	public String toString() {
		return "Konto nr " + numer + ", saldo: " + saldo + ", wł.: " + wlasciciel;			
	}
	
	public void wplata(int kwota) {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " we wpłacie");
		}
		
		saldo += kwota;
	}

	public void wyplata(int kwota) throws BrakSrodkow {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " w wypłacie");
		}
		if(kwota > saldo) {
			throw new BrakSrodkow("Brak środków na koncie nr " + numer);
		}
		
		saldo -= kwota;
	}

}

