package przyklady;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dto.MiniEmp;

public class Odczyt09_DTO {
	// Przykład zastosowania techniki (wzorca projektowego?...) DTO (data transfer object).
	// Klasa DTO pełni rolę pomocniczą, nie stanowi głównego modelu aplikacji.
	// Obiekty tej klasy służą przekazywaniu parametrów, zwracaniu wyników z metod,
	// np. zbieraniu danych z formularza webowego lub - tak jak tutaj - zbieraniu wyników zapytania bazodanowego.
	// Technicznie rzecz biorąc: w JPQL można użyć NEW aby stworzyć obiekt za pomocą konstruktora.
	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hr");
			em = emf.createEntityManager();
			TypedQuery<MiniEmp> query = em.createQuery("SELECT new dto.MiniEmp(e.firstName, e.lastName, e.job.jobTitle, e.salary) FROM Employee e", MiniEmp.class);
			List<MiniEmp> emps = query.getResultList();
			for (MiniEmp emp : emps) {
				System.out.println(emp);
			}
		} finally {
			if(em != null)	em.close();
			if(emf != null)	emf.close();
		}
	}
}
