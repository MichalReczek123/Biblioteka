package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class KalkulatorController {

	@GetMapping("/kalkulator")
	public String kalkulatorGet() {
		// dla zapytania GET tylko wyświetlamy stronę
		return "kalkulator.html";
	}
	
	@PostMapping("/kalkulator")
	public String kalkulatorPost(int liczba1, int liczba2, String operacja, Model model) {
		int wynik = oblicz(liczba1, liczba2, operacja);
		model.addAttribute("wynik", wynik);
		return "kalkulator.html";
	}
	
	private int oblicz(int liczba1, int liczba2, String operacja) {
		switch (operacja) {
			case "+": return liczba1 + liczba2;
			case "-": return liczba1 - liczba2;
			case "*": return liczba1 * liczba2;
			case "/": return liczba1 / liczba2;
			case "%": return liczba1 % liczba2;
			default : return 0;
		}
	}
	
}
