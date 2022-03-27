package watki.konta.v5_semafor;

import java.util.concurrent.Semaphore;

/* Użycie semaforów do takiej synchronizacji jest nietypowe
 * - w Javie są mechanizmy prostsze w użyciu.
 * Semafory bywają podstawowym mechanizmem synchronizacji na innych platformach,
 * ale w Javie nadają się tam, gdzie trzeba "liczyć zasoby" (zob. Afrykarium3).
 */
class Konto {
	private final Semaphore mutex = new Semaphore(1);
	private final Semaphore kasa = new Semaphore(0);
	private int iluCzekaNaKase = 0;

	private final int numer;
	protected int saldo;
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
		
		try {
			mutex.acquire();
			saldo += kwota;
			if(iluCzekaNaKase > 0) {
				kasa.release();
			} else {
				mutex.release();
			}
		} catch (InterruptedException e) {
			mutex.release();
		}
	}

	public void wyplata(int kwota) throws BrakSrodkow {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " w wypłacie");
		}
		
		try {
			mutex.acquire();
			if(kwota > saldo) {
				throw new BrakSrodkow("Brak środków na koncie nr " + numer);
			}
			
			saldo -= kwota;
		} catch (InterruptedException e) {
		} finally {
			mutex.release();
		}
	}

	public void wyplataCzekaj(int kwota) {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " w wypłacie");
		}
		
		try {
			mutex.acquire();
			while (kwota > saldo) {
				iluCzekaNaKase++;
				mutex.release();
				kasa.acquire();
				iluCzekaNaKase--;
			}
			saldo -= kwota;
		} catch (InterruptedException e) {
		} finally {
			mutex.release();
		}	
	}
}

