package com.example.demo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

// Adnotacja @Component (ale równie dobrze @Service, @Repository, @Controller, @RestController, ...)
// mówi Springowi:
// - utwórz obiekt tej klasy (singleton),
// - dawaj referencję do tego obiektu, gdy ktoś (inna klasa) będzie chciał go wstrzyknąć.
// Wszystkie odwołania będą prowadzić do tego samego samego obiektu - o to chodzi w schemacie "singleton".
// (chyba że użyjemy @Scope itp...)

// @Component
public class GeneratorImpl implements Generator {
	private long licznik = 0;
	private Random random = new Random();

	@Override
	public List<Integer> generujListe(int zakres, int ile) {
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
