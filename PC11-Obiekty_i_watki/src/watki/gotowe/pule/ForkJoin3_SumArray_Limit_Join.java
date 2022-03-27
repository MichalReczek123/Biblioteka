package watki.gotowe.pule;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ThreadLocalRandom;

public class ForkJoin3_SumArray_Limit_Join {
	
	static class SumArray extends ForkJoinTask<Long> {
		private Long result;
		private byte[] array;
		private int from, to;
		
		public SumArray(byte[] array, int from, int to) {
			this.array = array;
			this.from = from;
			this.to = to;
		}

		@Override
		public Long getRawResult() {
			return result;
		}

		@Override
		protected void setRawResult(Long value) {
			this.result = value;
		}

		@Override
		protected boolean exec() {
			if(to - from <= 10_000_000) {
				Long x = sumaSekwencyjnie(array, from, to);
				this.setRawResult(x);
			} else {
				int middle = (from + to) / 2;
				SumArray left = new SumArray(array, from, middle);
				SumArray right = new SumArray(array, middle, to);
				left.fork();
				right.fork();
				
				Long part1 = left.join();
				Long part2 = right.join();
				setRawResult(part1 + part2);
				
				// join nie deklaruje żadnych wyjątków, a get deklaruje Interrupted i ExecutionException
			}
			return true;
		}
	}

	public static void main(String[] args) {
		byte[] tab = new byte[160_000_000];

		final ThreadLocalRandom random = ThreadLocalRandom.current();
		System.out.println("Losowanie...");
		for(int i = 0; i < tab.length; i++)
			tab[i] = (byte)random.nextInt(256);
		Long result;
		
		final ForkJoinPool pool = new ForkJoinPool(8);
		System.out.println("\nLiczenie sekwencyjne:");
		long start = System.currentTimeMillis();
		result = sumaSekwencyjnie(tab);
		long stop = System.currentTimeMillis();
		System.out.println("Result = " + result + " , czas = " + (stop - start));

		System.out.println("\nLiczenie fork/join:");
		SumArray task = new SumArray(tab, 0, tab.length);
		System.out.println("Start");
		start = System.currentTimeMillis();
		result = pool.invoke(task);
		stop = System.currentTimeMillis();
		System.out.println("Result = " + result + " , czas = " + (stop - start));
		
	}

	private static Long sumaSekwencyjnie(byte[] tab, int from, int to) {
		long suma = 0;
		while(from < to)
			suma += tab[from++];
		
		return suma;
	}
	
	private static Long sumaSekwencyjnie(byte[] tab) {
		return sumaSekwencyjnie(tab, 0, tab.length);
	}
}
