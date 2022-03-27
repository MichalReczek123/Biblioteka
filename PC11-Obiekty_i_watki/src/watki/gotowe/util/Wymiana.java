package watki.gotowe.util;

import java.util.concurrent.Exchanger;

public class Wymiana {

	public static void main(String[] args) {

		final Exchanger<String> schowek = new Exchanger<>();
		
		Thread th1 = new Thread(new Runnable() {
			public void run() {
				String imie = "Ala";

				System.out.printf("Jestem %s w wątku %d%n", imie, Thread.currentThread().getId());
				try {
					Thread.sleep(100);
					String twojeImie = schowek.exchange(imie);
					System.out.printf("Wątek %d, twoje imię to %s %n", Thread.currentThread().getId(), twojeImie);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread th2 = new Thread(new Runnable() {
			public void run() {
				String imie = "Tomek";

				System.out.printf("Jestem %s w wątku %d%n", imie, Thread.currentThread().getId());
				try {
					Thread.sleep(3000);
					String twojeImie = schowek.exchange(imie);
					System.out.printf("Wątek %d, twoje imię to %s %n", Thread.currentThread().getId(), twojeImie);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		th1.start();
		th2.start();
	}
}
