package watki.gotowe.pule;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;
/* Przykład z dokumentacji klasy RecursiveAction - sortowanie tablicy */
public class ForkJoin5_SortArray_RecursiveAction {
	
	 static class SortTask extends RecursiveAction {
		final long[] array;
		final int lo, hi;

		SortTask(long[] array, int lo, int hi) {
			this.array = array;
			this.lo = lo;
			this.hi = hi;
		}

		SortTask(long[] array) {
			this(array, 0, array.length);
		}

		protected void compute() {
			if (hi - lo < THRESHOLD)
				sortSequentially(lo, hi);
			else {
				int mid = (lo + hi) >>> 1;
				invokeAll(new SortTask(array, lo, mid), new SortTask(array, mid, hi));
				merge(lo, mid, hi);
			}
		}

		// implementation details follow:
		static final int THRESHOLD = 1000;

		void sortSequentially(int lo, int hi) {
			Arrays.sort(array, lo, hi);
		}

		void merge(int lo, int mid, int hi) {
			long[] buf = Arrays.copyOfRange(array, lo, mid);
			for (int i = 0, j = lo, k = mid; i < buf.length; j++)
				array[j] = (k == hi || buf[i] < array[k]) ? buf[i++] : array[k++];
		}
	}

	public static void main(String[] args) {
		long[] tab = new long[80_000_000];

		final ThreadLocalRandom random = ThreadLocalRandom.current();
		System.out.println("Losowanie...");
		for(int i = 0; i < tab.length; i++)
			tab[i] = random.nextInt(256);
		
		final ForkJoinPool pool = new ForkJoinPool(8);
		SortTask task = new SortTask(tab);
		System.out.println("Start");
		long start = System.currentTimeMillis();
		pool.invoke(task);
		long stop = System.currentTimeMillis();
		System.out.println("OK, czas = " + (stop - start));
		
	}
}
