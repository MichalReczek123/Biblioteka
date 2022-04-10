package com.example.demo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GeneratorKolejnychLiczb implements Generator {
	private long licznik = 0;

	@Override
	public List<Integer> generujListe(int zakres, int ile) {
		List<Integer> liczby = new LinkedList<>();
		for (int i = 0; i < ile; i++) {
			liczby.add(i % zakres);
			licznik++;
		}
		return liczby;
	}
	
	@Override
	public long stanLicznika() {
		return licznik;
	}
	
}
