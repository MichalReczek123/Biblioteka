package przyklady;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

// Prosty przykład native query.
// Wynik odczytywany jako lista wierszy, każdy wiersz jako Object[].
public class Odczyt13_Native {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hr");
			em = emf.createEntityManager();
		
			Query query = em.createNativeQuery(
					"SELECT first_name || ' ' || last_name, job_title, salary FROM employees JOIN jobs USING(job_id) ORDER BY employee_id");
			
			List<?> rows = query.getResultList();
			for (Object row : rows) {
				// na wszelki wypadek sprawdzam, ale widzę, że Object[] działa
				if(row instanceof Object[]) {
					Object[] cols = (Object[]) row;
					System.out.println(cols[0] + " pracuje jako " + cols[1] + " i zarabia " + cols[2]);
				}
			}
			System.out.println();

			// Biorę pierwszy wiersz i (informacyjnie, dla nas podczas nauki) sprawdzam typy kolumn. Zauważmy, że kolumna salary jest mapowana na BigDecimal.
			System.out.println("Sprawdzę typy kolumn:");
			Object[] row = (Object[])rows.get(0);
			System.out.println(row[0].getClass() + " " + row[1].getClass() + " " + row[2].getClass());
		} finally {
			if(em != null) em.close();
			if(emf != null) emf.close();
		}
	}

}
