package watki.gotowe.util;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomowyLicznik {
	private static AtomicInteger licznik;
	private static final int N = 10_000_000;
	
	private static class Watek implements Runnable {
		public void run() {
			for(int i=0; i<N; i++) {
				licznik.addAndGet(3);  // +=
				licznik.addAndGet(-3); // -=

//				Number n = licznik; // OK				
//				licznik.incrementAndGet();// ++x
//				licznik.decrementAndGet();// --x
//				licznik.getAndIncrement(); // x++
//				licznik.addAndGet(3); // +=
//				licznik.getAndAdd(3); // try{return x} finally {x+=3}
//				licznik.get();
//				licznik.set(5);
//				licznik.compareAndSet(50, 1000); // tylko jeśli aktualna wartość to 50, to ustaw ową wartość 1000
				
				// Aby użycie takiego obiektu było poprawne, należy wykonywać operacje "jednym wywołaniem"
				// np. poprawne jest
//				licznik.addAndGet(5);
				// a niepoprawne byłoby
//				int x = licznik.get();
//				licznik.set(x + 5);
				
				// poprawne:
//				licznik.compareAndSet(100, 0);
				// niepoprawne
//				if(licznik.get() == 100) {
//					licznik.set(0);
//				}
			}
		}
	}

	public static void main(String[] args) {
		licznik = new AtomicInteger(10000);
		System.out.println(licznik);
		
		Thread th1 = new Thread(new Watek());
		Thread th2 = new Thread(new Watek());
		
		th1.start();
		th2.start();
		
		System.out.println("Uruchomiłem");
		
		try {
			th1.join();
			th2.join();
		} catch(InterruptedException e) {
		}
		
		System.out.println("Wątki zakończone");
		System.out.println(licznik.get());		
	}
}
