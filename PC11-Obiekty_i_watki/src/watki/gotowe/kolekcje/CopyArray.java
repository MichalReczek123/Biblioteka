package watki.gotowe.kolekcje;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyArray {

	public static void main(String[] args) {
		// Kolekcje "CopyOnWrite" kopiują całą swoją _wewnętrzną_ tablicę gdy tylko ktokolwiek cokolwiek modyfikuje.
		// Jeśli wcześniej został utworzony iterator ("ktoś czyta kolekcję"),
		// to NIE jest on unieważniany, tylko daje obraz kolekcji sprzed zmiany ("snapshot").
		// Iteratory NIE wyrzucają ConcurrrentModificationException.
		// Iteratory dają dostęp tylko do odczytu.
		
		List<String> lista = new CopyOnWriteArrayList<>();
		lista.add("Ala");
		lista.add("Basia");
		lista.add("Celina");		
		Iterator<String> it1 = lista.iterator();

		lista.add("Dorota");
		lista.add("Eliza");
		Iterator<String> it2 = lista.iterator();

		lista.add("Felicja");
		lista.add("Grażyna");
		
		System.out.print("it1: ");
		while(it1.hasNext()) {
			System.out.print(it1.next() + " ");
		}
		System.out.println();

		System.out.print("it2: ");
		while(it2.hasNext()) {
			System.out.print(it2.next() + " ");
			// it2.remove(); // EXN
		}
		System.out.println();

		System.out.print("lista: ");
		for(String x : lista) {
			System.out.print(x + " ");
		}
		System.out.println();
	}

}
