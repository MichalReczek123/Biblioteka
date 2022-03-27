package watki.konta.v6_synchronizacja_na_zwyklych_obiektach;

import java.util.ArrayList;
import java.util.List;

/* W dawniej napisanych programach Javy można spotkać się z techniką,
 * że do synchronizacji używa się zwykłych obiektów (klasa nieistotna - może być nawet Object.
 * Pozwala to osobno synchronizować wątki, które czekają z różnych powodów.
 * 
 * Obecnie lepszym rozwiązaniem jest użycie Lock-ów.
 */

class Konto {
	private final int numer;
	private int saldo;
	private Osoba wlasciciel;
	private List<String> wnioski = new ArrayList<>();
	
	// te obiekty istnieją tylko po to, aby wątki mogły "na nich czekać"
	private Object ochronaSalda = new Object();
	private Object ochronaWnioskow = new Object();
	
	public Konto(int numer, int saldo, Osoba wlasciciel) {
		this.numer = numer;
		this.saldo = saldo;
		this.wlasciciel = wlasciciel;
	}
	
	public String toString() {
		return "Konto nr " + numer + ", saldo: " + saldo + ", wł.: " + wlasciciel;			
	}
	
	public void wplata(int kwota) {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " we wpłacie");
		}
		
		synchronized(ochronaSalda) {
			saldo += kwota;
			ochronaSalda.notify();
		}
	}

	public void wyplata(int kwota) throws BrakSrodkow {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " w wypłacie");
		}
		
		synchronized(ochronaSalda) {
			if(kwota > saldo) {
				throw new BrakSrodkow("Brak środków na koncie nr " + numer);
			}
			
			saldo -= kwota;
		}
	}

	public void wyplataCzekaj(int kwota) throws BrakSrodkow {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " w wypłacie");
		}
		try {
			synchronized(ochronaSalda) {
				while(kwota > saldo) {
					ochronaSalda.wait();
				}
				
				saldo -= kwota;
				ochronaSalda.notify();
			}
		} catch (InterruptedException e) {
			// Jeśli ktoś przerwie za pomocą interrupt(), tzn. "nie czekaj już, daj sobie spokój", wtedy rezygnuję z wykonania operacji biznesowej
		}
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
		synchronized(ochronaSalda) {
			return saldo;
		}
	}
	
	public void zlozWniosekKredytowy(int suma) {
		synchronized(ochronaWnioskow) {
			wnioski.add("Wniosek o " + suma);
		}
	}
	
}
