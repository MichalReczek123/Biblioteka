package watki.konta.v5_semafor;

import java.io.IOException;

class WyplacanieZOczekiwaniem {

	static volatile boolean koniec = false;

	public static void main(String[] args) {
	   	 final Osoba ala = new Osoba("Ala", "Kowalska", "2001-01-01");
	   	 final Konto konto = new Konto(1, 1700, ala);   	 
	   	 
    	System.out.println("początek " + konto);

    	Thread wplacacz = new Thread(new Runnable() {
        	public void run() {
            	while (!koniec) {
                	konto.wplata(1000);
               	 
                	System.out.println("wpłacacz: wpłaciłem 1000, saldo = " + konto.getSaldo());
                	try {
                    	Thread.sleep(4000);
                	} catch (InterruptedException e) {
                	}
            	}
        	}
    	});
    	Thread wyplacacz = new Thread(new Runnable() {
        	public void run() {
            	while (!koniec) {
                   	konto.wyplataCzekaj(100);
                	System.out.println("wypłacacz: wypłaciłem 100, saldo = " + konto.getSaldo());
                	try {
                    	Thread.sleep(200);
                	} catch (InterruptedException e) {
                	}
            	}
        	}
    	});

    	wplacacz.start();
    	wyplacacz.start();

    	System.out.println("Wątki wystartowały");
    	System.out.println("Naciśnij enter aby zakończyć");
    	try {
        	System.in.read();
    	} catch (IOException e1) {
    	}
	
    	koniec = true;

    	try {
        	wplacacz.join();
        	wyplacacz.join();
    	} catch (InterruptedException e) {
        	System.err.println("INTERRUPTED");
    	}

    	System.out.println("na końcu: " + konto);
	}
}
