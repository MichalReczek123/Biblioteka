package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneratorController {
	@Autowired
	private Generator generator;
	// Tutaj zmienna jest typu interfejs, aby do tej zmiennej można było wpisywać obiekty różnych klas.
	// Spring poszuka komponentu o typie zgodnym z tym interfejsem (znajdzie "jakąś implementację").
	// W zależności od konfiguracji aplikacji (czyli które klasy dodajemy dodajemy do projektu, a które usuwamy)
	// może to być czasami jedna implementacja, a czasami inna.
	
	@RequestMapping("/generator")
	public String generuj(Integer zakres, Integer ile, Model model) {
		if(zakres != null && ile != null) {
			List<Integer> liczby = generator.generujListe(zakres, ile);
			model.addAttribute("liczby", liczby);
		}
		return "generator.html";
	}


	
}
