package watki.gotowe.pule;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ThreadLocalRandom;

public class ForkJoin1_SumArray {
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
			if(to - from == 1) {
				this.setRawResult(Long.valueOf(array[from]));
			} else {
				int middle = (from + to) / 2;
				SumArray left = new SumArray(array, from, middle);
				SumArray right = new SumArray(array, middle, to);
				left.fork();
				right.fork();
				try {
					Long part1 = left.get();
					Long part2 = right.get();
					setRawResult(part1 + part2);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
					setRawResult(0L);
				}
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
		
		System.out.println("\nLiczenie sekwencyjne:");
		long start = System.currentTimeMillis();
		result = sumaSekwencyjnie(tab);
		long stop = System.currentTimeMillis();
		System.out.println("Result = " + result + " , czas = " + (stop - start));

		System.out.println("\nLiczenie fork/join:");
		final ForkJoinPool pool = new ForkJoinPool(8);
		SumArray task = new SumArray(tab, 0, tab.length);
		System.out.println("Start");
		start = System.currentTimeMillis();
		result = pool.invoke(task);
		stop = System.currentTimeMillis();
		System.out.println("Result = " + result + " , czas = " + (stop - start));
		
	}

	private static long sumaSekwencyjnie(byte[] tab, int from, int to) {
		long suma = 0;
		while(from < to)
			suma += tab[from++];
		
		return suma;
	}
	
	private static long sumaSekwencyjnie(byte[] tab) {
		return sumaSekwencyjnie(tab, 0, tab.length);
	}
}
