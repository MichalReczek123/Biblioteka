package watki.gotowe.rwlock;

import java.util.Random;

public class CzytelnicyPisarze_BezSynchronizacji {
	private int[] t = new int[1000];
	private volatile boolean jeszcze = true;
	
	public static void main(String[] args) {
		new CzytelnicyPisarze_BezSynchronizacji().dzialaj();
	}

	private void dzialaj() {
		System.out.println("Losuję liczby do tablicy");
		Random random = new Random();
		for(int i=0; i<t.length; i++) {
			t[i] = random.nextInt(1000);
		}

		int suma = 0;
		for(int x : t) {
			suma += x;
		}
		System.out.println("Suma na początku = " + suma);
		
		System.out.println("Uruchamiam wątki");
		Thread c1 = new Thread(new Czytelnik());
		Thread c2 = new Thread(new Czytelnik());
		Thread z1 = new Thread(new Zamieniacz());
		Thread z2 = new Thread(new Zamieniacz());
		
		c1.start();
		c2.start();
		z1.start();
		z2.start();
		
		System.out.println("Jadą");
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
		}
		jeszcze = false;
		System.out.println("Koniec");
		try {
			c1.join();
			c2.join();
			z1.join();
			z2.join();
		} catch (InterruptedException e) {
		}
		

		suma = 0;
		for(int x : t) {
			suma += x;
		}
		System.out.println("Suma na końcu = " + suma);
		
	}
	
	private class Zamieniacz implements Runnable {
		public void run() {
			Random random = new Random();
			
			while(jeszcze) {
				int i = random.nextInt(t.length);
				int j = random.nextInt(t.length);
				// swap
				int x = t[i];
				t[i] = t[j];
				t[j] = x;
			}
		}
	}

	private class Czytelnik implements Runnable {
		public void run() {
			while(jeszcze) {
				int suma = 0;
				for(int x : t) {
					suma += x;
				}
				System.out.println("Suma = " + suma);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}
		}
	}

}
