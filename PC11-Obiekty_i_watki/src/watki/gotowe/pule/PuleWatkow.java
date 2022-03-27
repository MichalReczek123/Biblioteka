package watki.gotowe.pule;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PuleWatkow {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final int N = 100; // liczba procedur do wykonania
		
		// ExecutorService pool = Executors.newSingleThreadExecutor();
		ExecutorService pool = Executors.newFixedThreadPool(20);
		// ExecutorService pool = Executors.newCachedThreadPool(); // tworzy wątek, gdy tylko brakuje robotników
		// ExecutorService pool = Executors.newWorkStealingPool(); // od Javy 8 - stara się wykorzystać wszystkie procesory
		// ExecutorService pool = Executors.newWorkStealingPool(2); // wersja z ograniczeniem współbieżności do pewnego poziomu
		// ScheduledExecutorService pool = Executors.newScheduledThreadPool(20); // pozwala planować zadania na przyszłość
		
		Procedura zadanie = new Procedura();
		System.out.println("Zaczynam zlecać...");
		for(int i = 0; i < N; i++) {
			pool.submit(zadanie);
			// pool.execute(zadanie);
			// pool.schedule(zadanie, 2, TimeUnit.SECONDS);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
		}
		
		System.out.println("Zlecilem wykonanie");
		
		pool.shutdown();
		//pool.shutdownNow();
		System.out.println("Po shutdown");
		//pool.submit(() -> {});
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
			System.out.println("Zakończyły się");
		} catch (InterruptedException e) {
		}
		System.out.println("Koniec main");
	}

	private static class Procedura implements Runnable {
		public void run() {
			long id = Thread.currentThread().getId();
			System.out.printf("Hej, tu watek %d%n", id);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println("interrupt w " + id);
			}
		}
	}
}
