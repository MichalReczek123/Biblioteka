package watki.gotowe.ilustracje_nie_do_uruchamiania;

public class InstancyjnaIStatyczna {
	
	synchronized void instancyjna1() {
		
	}

	synchronized void instancyjna2() {
		// instancyjne synchronizują się na poszczególnych obiektach
	}

	static synchronized void statyczna1() {
		
	}
	static synchronized void statyczna2() {
		// statyczne wzajemnie się synchronizują
		// na obiekcie InstancyjnaIStatyczna.class
		// statyczne i instancyjne wzajemnie się nie synchronizują
	}
	
	
	void metoda() {
		// gdybym chciał się zsynchronizować z metodami statycznymi, to mogę tak:
		synchronized(InstancyjnaIStatyczna.class) {
			
		}
		
		// tak jak
		synchronized(this) {
			// jest synchronizacją na bieżącym obiekcie
			// z tym że tutaj i tak nic nie robi, bo jesteśmy w metodzie synchronized
		}
	}

}
