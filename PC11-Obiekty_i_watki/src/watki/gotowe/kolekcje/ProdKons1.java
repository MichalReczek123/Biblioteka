package watki.gotowe.kolekcje;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

// Kolejki blokujące mają dodatkowo metody take i put, które działają w sposób blokujący.

public class ProdKons1 {
	private static final int ILE_RAZY = 30;
	private static final int N = 5;
	private BlockingQueue<Integer> kolejka = new ArrayBlockingQueue<>(N);
	
	public static void main(String[] args) {
		ProdKons1 program = new ProdKons1();
		program.dzialaj();
	}
	
	private void dzialaj() {
		Thread producent = new Thread(new Producent());
		Thread konsument = new Thread(new Konsument());
		
		System.out.println("Startujemy");
		producent.start();
		konsument.start();

		try {
			producent.join();
		} catch (InterruptedException e) {
		}
		try {
			konsument.join();
		} catch (InterruptedException e) {
		}
		System.out.println("Koniec. size="+kolejka.size());
	}

	private class Producent implements Runnable {
		public void run() {
			for(int i=1; i<=ILE_RAZY; i++) {
				int x = (int) (Math.random() * 1000);
				System.out.println("P: wstawiam " + x);
				try {
					kolejka.put(x);
					System.out.println("P: Wstawiłem, size=" + kolejka.size() );
					Thread.sleep(300 + x);
				} catch (InterruptedException e) {
				}
			}
			
		}
	}

	private class Konsument implements Runnable {
		public void run() {
			for(int i=1; i<=ILE_RAZY; i++) {
				try {
					Thread.sleep(100);
					System.out.println("                        K: Biorę...");
					int x = kolejka.take();
					//int x = kolejka.poll(100, TimeUnit.DAYS);
					System.out.println("                        K: ... Pobrałem " + x);
					Thread.sleep(2*x);
				} catch (InterruptedException e) {
				}
			}
			
		}
	}
}
