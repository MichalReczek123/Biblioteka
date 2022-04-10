package przyklady;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Odczyt08_Tablica {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hr");
			em = emf.createEntityManager();
		
			TypedQuery<Object[]> query = em.createQuery("SELECT e.firstName, e.lastName, e.job.jobTitle, e.salary, e.department.departmentName, e.department.location.city FROM Employee e", Object[].class);
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
