package przyklady;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Odczyt11_Join {
	// Przykład JOIN zapisanego w składni JPQL - "przechodzenie po dowiązaniach".
	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hr");
			em = emf.createEntityManager();
			System.out.println("Odczyt...");
			TypedQuery<Object[]> query = em.createQuery("SELECT e.firstName, e.lastName, d.departmentName, l.city"
					+ " FROM Employee e LEFT JOIN e.department d LEFT JOIN d.location l", Object[].class);
			List<Object[]> emps = query.getResultList();
			for (Object[] emp : emps) {
				System.out.println(Arrays.toString(emp));
			}
		} finally {
			if(em != null)	em.close();
			if(emf != null)	emf.close();
		}
	}

}
