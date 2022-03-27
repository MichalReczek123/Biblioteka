package watki.gotowe.kolekcje;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProdKons2 {
	private static final int ILE_RAZY = 30;
	private BlockingQueue<Integer> kolejka = new LinkedBlockingQueue<>();

	public static void main(String[] args) {
		ProdKons2 program = new ProdKons2();
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
					System.out.println("                        K: ... Pobrałem " + x);
					Thread.sleep(2*x);
				} catch (InterruptedException e) {
				}
			}
		}
	}
}
