package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TimeController {
	// Gdy metoda kontrolera posiada adnotację @ResponseBody, wtedy to, co metoda zwraca, jest odsyłane klientowi jako odpowiedź na żądanie
	// W zależności od typu wynikowego, Spring używa róznych formatów danych.
	
	// wersja 1 - zwracamy obiekt LocalDateTime - Spring wyśle go jako format JSON
	@RequestMapping("/time1")
	@ResponseBody
	public LocalDateTime time1() {
		return LocalDateTime.now();
	}
	
	// wersja 2 - za pomocą toString zwracamy jako zwykły tekst
	// Spring jednak opisze typ wyniku jako text/html
	@RequestMapping("/time2")
	@ResponseBody
	public String time2() {
		return LocalDateTime.now().toString();
	}
	
	// wersja 3 - za pomocą DateTimeFormatter zwrócimy sformatowaną wersję daty i czasu jako String
	// jednocześnie do adnotacji dopiszemy produces=text/plain , do przeglądarki zostanie wysłana taka informacja
	private static final DateTimeFormatter FORMAT_DATY
     	= DateTimeFormatter.ofPattern("EEEE, dd MMMM, D 'dzień roku' YYYY, 'godzina' HH:mm:ss", new Locale("pl", "PL"));
	
	@RequestMapping(path="/time3", produces="text/plain")
	@ResponseBody
	public String time3() {
		return LocalDateTime.now().format(FORMAT_DATY);
	}

	// wersja 4 - zwracamy HTML, tworząc go w Javie
	@RequestMapping(path="/time4", produces="text/html")
	@ResponseBody
	public String time4() {
		LocalDateTime dt = LocalDateTime.now();
		return String.format("<html><body><h1>Data i czas</h1>"
                + "<p>Teraz jest godzina <strong style='color:purple'>%s</strong></p>"
				+ "<p>Dzisiejsza data: <strong style='color:blue'>%s</strong></p>"
                + "<p style='color: green'>%s</p>"
                + "</body></html>",
                dt.toLocalTime(),
                dt.toLocalDate(),
                dt.format(FORMAT_DATY));
	}
	
	// Gdy metoda w kontrolerze w wyniku zwraca String, a nie posiada adnotacji @ResponseBody,
	// to Spring traktuje zwracaną wartość jak nazwę szablonu i (w zależności od wybranej technologii: JSP / Thymeleaf / FreeMarker)
	// w odp. miejscu szuka pliku z szablonem i generuje wynik za pomocą tego szablonu.
	
	// Gdy do szablonu chcemy przekazać jakieś dane, to dodajemy je jako atrybuty do modelu.
	// Najprościej zrobić to poprzez parametr model. Są też inne sposoby poprzez adnotacje, ale o tym później...
	@RequestMapping("/time5")
	public String time5(Model model) {
		LocalDateTime obiekt = LocalDateTime.now();
		model.addAttribute("dt", obiekt);
		return "show_time5.html";
	}
	
	@RequestMapping("/time6")
	public String time6(Model model) {
		LocalDateTime obiekt = LocalDateTime.now();
		model.addAttribute("dt", obiekt);
		return "show_time6.html";
	}
	
}

// Spring MVC - Model View Controller
