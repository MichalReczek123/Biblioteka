package com.example.demo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GeneratorImpl {
	private long licznik = 0;

	public List<Integer> generujListe(int zakres, int ile) {
		Random random = new Random();
		List<Integer> liczby = new LinkedList<>();
		for (int i = 0; i < ile; i++) {
			liczby.add(random.nextInt(zakres));
			licznik++;
		}
		return liczby;
	}
	
	public long stanLicznika() {
		return licznik;
	}
	
}
