package przyklady;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Odczyt10_Group {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hr");
			em = emf.createEntityManager();
			TypedQuery<Object[]> query = em.createQuery("SELECT e.job.jobTitle, count(*), avg(e.salary) FROM Employee e GROUP BY e.job.jobTitle", Object[].class);
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
