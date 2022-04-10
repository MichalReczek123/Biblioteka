package com.example.demo;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// @Component
public class GeneratorImpl2 implements Generator {
	private long licznik = 0;

	@Override
	public List<Integer> generujListe(int zakres, int ile) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		
		List<Integer> liczby = new LinkedList<>();
		for (int i = 0; i < ile; i++) {
			liczby.add(random.nextInt(zakres));
			licznik++;
		}
		return liczby;
	}
	
	@Override
	public long stanLicznika() {
		return licznik;
	}
	
}
