package watki.gotowe.util;

import java.util.concurrent.Phaser;

public class Fazery2 {
	static boolean koniec = false;
	static final int N = 3;
	

	public static void main(String[] args) {
		final Phaser ph = new Phaser(3);
		
		class Watek implements Runnable {
			private int nastFaza;
			public Watek(boolean parzyste) {
				nastFaza = parzyste ? 2 : 1;
			}

			public void run() {
				while(!koniec) {
					try {
						Thread.sleep(500+Thread.currentThread().getId()*100);
						int faza = ph.arrive();
						System.out.println("Przybylem  "+Thread.currentThread().getId() + " czekam na "+nastFaza);
						ph.awaitAdvance(faza);
						System.out.println("Doczekalem "+Thread.currentThread().getId() + " faza "+ph.getPhase());
						nastFaza += 2;
					} catch (InterruptedException e) {
					}
					
				}
			}
		}

		for(int i = 0; i < N; i++) {
			new Thread(new Watek(true)).start();
			new Thread(new Watek(false)).start();
			try {
				Thread.sleep(200);
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
