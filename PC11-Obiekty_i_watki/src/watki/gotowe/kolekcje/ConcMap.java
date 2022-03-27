package watki.gotowe.kolekcje;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/* W tym przykładzie porównuję szybkość działania ConcurrentHashMap i zwykłwej HashMap opakowanej w synchronizedMap.
 * Program tworzy N wątków, które operują na słowniku String→Integer w taki sposób, że
 * - losują liczbę od 1 do K - po konwersji na tekst staje się ona kluczem w słowniku,
 * - wykonują operację modyfikacji zawartości słownika pod tym kluczem; aby koszt losowania itp. nie zaszumił kosztu samej mapy, operacja jest powtarzana kilkukrotnie z tym samym kluczem.
 */
public class ConcMap {
	// liczba wątków
	private static final int N = 16;
	
	// wielkość słownika
	private static final int K = 100;
	
	// ilość powtórzeń jednej operacji
	private static final int P = 50;

	// ilość powtórzeń całości
	private static final int R = 10_000;

	// odkomentuj jedną z wersji i sprawdź
	//private final Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());
	private final Map<String, Integer> map = new ConcurrentHashMap<>();
	//private final ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
	

	private void dzialaj() {
		final ThreadLocalRandom random = ThreadLocalRandom.current();
		for(int r=0; r < R; r++) {
			final int k = random.nextInt(K);
			final int d = random.nextInt(1000) - 500;
			final String key = String.valueOf(k);
			for(int p = 0; p < P; p++) {
				Integer v = map.merge(key, d, (x, y) -> x+y);
				//System.out.println(r + " " + p);
			}
		}
	}
	
	public static void main(String[] args) {
		ConcMap instance = new ConcMap();
		ExecutorService pool = Executors.newFixedThreadPool(N);
		System.out.println("Start");
		long start = System.currentTimeMillis();
		for(int i = 0; i < N; i++) {
			pool.submit(instance::dzialaj);
		}
		
		try {
			pool.shutdown();
			pool.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("Czas: " + (end - start));
	}

}
