package watki.gotowe.util;

import java.util.concurrent.CountDownLatch;

public class Odliczanie {

	public static void main(String[] args) {
		final CountDownLatch guzik = new CountDownLatch(10);
		
		Thread rakieta = new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("Rakieta w przygotowaniu");
					Thread.sleep(750);
					System.out.println("Rakieta przygotowana...");
					guzik.await();
					System.out.println("Start!!!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread kontrola = new Thread(new Runnable() {
			public void run() {
				System.out.println("Kontrola zaczyna odliczanie");
				while(guzik.getCount() > 0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					guzik.countDown();
					System.out.println("> " + guzik.getCount());
		}	}	});
		

		System.out.println("Ruszamy");
		rakieta.start();
		kontrola.start();
		System.out.println("Koniec main");
	}
}
