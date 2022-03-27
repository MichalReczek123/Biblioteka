package watki.konta.v4_locki;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Konto {
	private final Lock lock = new ReentrantLock();
	private final Condition czekanieNaKase = lock.newCondition();

	// można tworzyć osobne condition związane z oczekiwaniem na różne warunki
	private final Condition czekanieNaUdzielenieKredytu = lock.newCondition();

	private final int numer;
	protected int saldo;
	private Osoba wlasciciel;
	
	public Konto(int numer, int saldo, Osoba wlasciciel) {
		this.numer = numer;
		this.saldo = saldo;
		this.wlasciciel = wlasciciel;
	}
	
	public Osoba getWlasciciel() {
		try {
			lock.lock();
			return wlasciciel;
		} finally {
			lock.unlock();
		}
	}

	public void setWlasciciel(Osoba wlasciciel) {
		try {
			lock.lock();
			this.wlasciciel = wlasciciel;
		} finally {
			lock.unlock();
		}
	}

	public int getNumer() {
		return numer;
	}

	public int getSaldo() {
		try {
			lock.lock();
			return saldo;
		} finally {
			lock.unlock();
		}
	}
	
	public String toString() {
		return "Konto nr " + numer + ", saldo: " + saldo + ", wł.: " + wlasciciel;			
	}
	
	public void wplata(int kwota) {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " we wpłacie");
		}
		
		lock.lock();
		saldo += kwota;
		czekanieNaKase.signal();
		lock.unlock();
	}

	public void wyplata(int kwota) throws BrakSrodkow {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " w wypłacie");
		}
		
		lock.lock();
		try {
			if(kwota > saldo) {
				throw new BrakSrodkow("Brak środków na koncie nr " + numer);
			}
			
			saldo -= kwota;
		} finally {
			lock.unlock();
		}
	}

	public void wyplataCzekaj(int kwota) {
		if(kwota < 0) {
			throw new IllegalArgumentException("Ujemna kwota " + kwota + " w wypłacie");
		}
		
		lock.lock();
		try {
			while (kwota > saldo) {
				czekanieNaKase.await();
			}
			saldo -= kwota;
			czekanieNaKase.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}

