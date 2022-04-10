package com.example.demo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneratorController {
	
	@RequestMapping("/generator")
	public String generuj(Integer zakres, Integer ile, Model model) {
		if(zakres != null && ile != null) {
			Random random = new Random();
			List<Integer> liczby = new LinkedList<>();
			for (int i = 0; i < ile; i++) {
				liczby.add(random.nextInt(zakres));
			}
			model.addAttribute("liczby", liczby);
		}
		return "generator.html";
	}
	
}
