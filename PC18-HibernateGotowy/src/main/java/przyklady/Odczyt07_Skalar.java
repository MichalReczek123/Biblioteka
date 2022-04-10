package przyklady;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Odczyt07_Skalar {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hr");
			em = emf.createEntityManager();
		
			TypedQuery<String> query = em.createQuery("SELECT e.lastName FROM Employee e ORDER BY 1", String.class);
			// też działa:
			// TypedQuery<String> query = em.createQuery("SELECT lastName FROM Employee ORDER BY lastName", String.class);
			List<String> names = query.getResultList();
			for (String name : names) {
				System.out.println(name);
			}
		} finally {
			if(em != null)	em.close();
			if(emf != null)	emf.close();
		}
	}

}
