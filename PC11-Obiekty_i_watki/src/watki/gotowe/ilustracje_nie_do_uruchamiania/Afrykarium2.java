package watki.gotowe.ilustracje_nie_do_uruchamiania;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Afrykarium2 {
	// wolne miejsca w środku
	private int miejsca = 2000;
	
	// synchronizacja
	private Lock ochrona = new ReentrantLock();
	private Condition czekanieNaMiejsca = ochrona.newCondition();

	public void wpuśćWycieczkę(int ilu) {
		try {
			ochrona.lock();
			while(miejsca < ilu) {
				czekanieNaMiejsca.await();
			}
			miejsca -= ilu;
			// otwórz bramki
		} catch(InterruptedException e) {
		} finally {
			ochrona.unlock();
		}
	}

	public void zwiedzającyWychodzi() {
		try {
			ochrona.lock();
			miejsca++;
			czekanieNaMiejsca.signal();
		} finally {
			ochrona.unlock();
		}
	}
}
