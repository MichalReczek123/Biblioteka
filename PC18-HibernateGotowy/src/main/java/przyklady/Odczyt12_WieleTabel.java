package przyklady;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Employee;

public class Odczyt12_WieleTabel {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hr");
			em = emf.createEntityManager();
		
			TypedQuery<Object[]> query = em.createQuery(
						"SELECT DISTINCT emp, loc.city"
								+ " FROM Employee emp, Location loc"
								+ " WHERE substring(emp.lastName, 1, 1) = substring(loc.city, 1, 1)"
								+ " ORDER BY emp.lastName, emp.firstName",
					Object[].class);
			List<Object[]> results = query.getResultList();
			System.out.println(results.size());
			for (Object[] tab : results) {
				Employee emp = (Employee)tab[0];
				String city = (String)tab[1];
				System.out.printf("%-20s %-20s %-20s\n", emp.getFirstName(), emp.getLastName(), city);
			}
		} finally {
			if(em != null) em.close();
			if(emf != null) emf.close();
		}
	}
}
