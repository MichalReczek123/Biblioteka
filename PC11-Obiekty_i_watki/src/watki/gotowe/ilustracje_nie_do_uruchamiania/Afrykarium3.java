package watki.gotowe.ilustracje_nie_do_uruchamiania;

import java.util.concurrent.Semaphore;

public class Afrykarium3 {
	// wolne miejsca w środku
	private Semaphore miejsca = new Semaphore(2000, true);

	public void wpuśćWycieczkę(int ilu) {
		try {
			miejsca.acquire(ilu);  // akademicko : P
		} catch(InterruptedException e) {
		}
		// otwórz bramki
	}

	public void zwiedzającyWychodzi() {
		miejsca.release();		// akademicko: V
	}
}
