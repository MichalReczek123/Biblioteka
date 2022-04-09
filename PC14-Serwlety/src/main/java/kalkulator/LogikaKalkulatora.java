package kalkulator;

public class LogikaKalkulatora {
	
	public static long oblicz(long liczba1, long liczba2, String operacja) {
		switch(operacja) {
		case "+": return liczba1 + liczba2;
		case "-": return liczba1 - liczba2;
		case "*": return liczba1 * liczba2;
		case "/": return liczba1 / liczba2;
		default : throw new IllegalArgumentException("Nieznana operacja " + operacja);
		}
	}

}
