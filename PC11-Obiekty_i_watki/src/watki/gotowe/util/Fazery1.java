package watki.gotowe.util;

import java.util.concurrent.Phaser;

public class Fazery1 {
	static boolean koniec = false;
	static final int N = 3;
	

	public static void main(String[] args) {
		final Phaser ph = new Phaser(3);
		
		class Watek implements Runnable {

			public void run() {
				while(!koniec) {
					try {
						System.out.println("Czeka    "+Thread.currentThread().getId());
						ph.arriveAndAwaitAdvance();
						System.out.println("Doczekal "+Thread.currentThread().getId() + "  faza "+ph.getPhase());
						Thread.sleep(400+Thread.currentThread().getId()*100);
					} catch (InterruptedException e) {
					}
					
				}
			}
		}

		Watek runable = new Watek();

		for(int i = 0; i < N; i++) {
			new Thread(runable).start();
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
		}
		koniec = true;
		ph.forceTermination();
		System.out.println("Koniec");
	}
}


