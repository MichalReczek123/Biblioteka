package com.example.demo;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneratorController {
	
	private GeneratorImpl generator = new GeneratorImpl();
	
	@RequestMapping("/generator")
	public String generuj(Integer zakres, Integer ile, Model model) {
		if(zakres != null && ile != null) {
			List<Integer> liczby = generator.generujListe(zakres, ile);
			model.addAttribute("liczby", liczby);
		}
		return "generator.html";
	}


	
}
