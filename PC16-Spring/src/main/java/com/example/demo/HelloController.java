package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@Autowired
	private Generator generator;

	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello world";
	}
	
	
	@RequestMapping("/licznik-generatora")
	@ResponseBody
	public String licznik() {
		return "Generator wylosował dotąd " + generator.stanLicznika() + " liczb";
	}
	
	// Najprostszy sposób, aby uzyskać parametr zapytania, to dopisać do metody
	// parametr o takiej nazwie nazwie. Jest to parametr opcjonalny; gdy nie zostanie podany, Spring przekaże tu wartość null.
	@RequestMapping("/witaj")
	@ResponseBody
	public String powitaj(String imie) {
		return "Witaj " + imie;
	}
	
	// Jeśli chcemy opisać dodatkowe szczegóły, to przed parametrami możemy dodać adnotację @RequestParam
    // Można wtedy podać inną nazwę parametru HTTP niż parametru w Javie,
	// podać wartość domyślną, napisać czy parametr jest obowiązkowy, czy opcjonalny (gdy jest adnotacja, to domyślnie jest obowiązkowy).
	// http://localhost:8080/powtorz?txt=Ala%20ma%20kota.%20&ile=10
	// http://localhost:8080/powtorz?txt=ABCDEF
	@RequestMapping("/powtorz")
	@ResponseBody
	public String powtorz(
			@RequestParam("txt") String tekst,
			@RequestParam(name="ile", defaultValue="1") int ileRazy) {
		return tekst.repeat(ileRazy);
	}

}









