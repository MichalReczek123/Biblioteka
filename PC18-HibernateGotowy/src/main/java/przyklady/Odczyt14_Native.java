package przyklady;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

// Sensowny przykład native query.
// Pokazuję, że dzięki niemu można skorzystać z funkcji konkretnej bazy danych.
// Tutaj (z dokumentacją przed nosem ;) ) wykombinowałem zapytanie z "funkcjami okienkowymi"
// - funkcjonalność znana głównie w Oracle, dostępna także w PostgreSQL.
public class Odczyt14_Native {
	
	public static void main(String[] args) {
		final String sql = "SELECT employee_id AS \"id\", "
				+ "    department_name AS \"departmentName\", "
				+ "    first_name AS \"firstName\", "
				+ "    last_name AS \"lastName\", "
				+ "    job_title AS \"jobTitle\", "
				+ "    salary AS \"salary\", " + 
				"	round(avg(salary) OVER (PARTITION BY department_id), 2) AS \"depAvg\", " + 
				"	rank() OVER (PARTITION BY department_id ORDER BY salary DESC) AS \"depPos\", " + 
				"	rank() OVER (ORDER BY salary DESC) AS \"globalPos\" " + 
				"FROM departments d " + 
				"	INNER JOIN employees e USING(department_id) " + 
				"	INNER JOIN jobs j USING(job_id) " + 
				"ORDER BY 7 DESC, 2, 6 DESC, 4 ASC, 3 ASC";
		
		System.out.println(sql);
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hr");
			em = emf.createEntityManager();
			Query query = em.createNativeQuery(sql);
			List<?> rows = query.getResultList();
			for (Object row : rows) {
				if (row instanceof Object[]) {
					Object[] cols = (Object[]) row;
					System.out.println(Arrays.toString(cols));
				}
			}
			
		} finally {
			if(em != null) em.close();
			if(emf != null) emf.close();
		}
	}

}
