package watki.gotowe.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Bariery {
	static final int N = 6;
	static volatile boolean koniec = false;

	public static void main(String[] args) {
		final CyclicBarrier bariera = new CyclicBarrier(4);
		
		class MojRunnable implements Runnable {
			public void run() {
				while(!koniec) {
					try {
						System.out.println("Czeka    "+Thread.currentThread().getId());
						bariera.await();
						System.out.println("Doczekal "+Thread.currentThread().getId());
						Thread.sleep(400+Thread.currentThread().getId()*100);
					} catch (InterruptedException e) {
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			}
		}

		MojRunnable runable = new MojRunnable();

		for(int i = 0; i < N; i++) {
			new Thread(runable).start();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
		}
		System.out.println("main: reset");
		koniec = true;
		bariera.reset();
		System.out.println("Koniec");
	}
}
