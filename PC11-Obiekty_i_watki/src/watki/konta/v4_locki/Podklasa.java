package watki.konta.v4_locki;

public class Podklasa extends Konto {

	public Podklasa(int numer, int saldo, Osoba wlasciciel) {
		super(numer, saldo, wlasciciel);
	}

	public void wplata(int kwota) {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " we wpłacie");
		}
		
		saldo += kwota;
	}
	
}
