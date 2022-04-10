package przyklady;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Employee;

public class AktualizujRekord1 {

	public static void main(String[] args) {
		int id = 100;
		int zmiana = 200;
		
		EntityManagerFactory emf = null;		
		EntityManager em = null;
		
		try {
			emf = Persistence.createEntityManagerFactory("hr");
			em = emf.createEntityManager();
			
			em.getTransaction().begin();
			
			Employee emp = em.find(Employee.class, id);
			if(emp == null) {
				System.out.println("nie ma");
				return;
			}
			
			System.out.println("Pensja przed zmianą: " + emp.getSalary());
			emp.setSalary(emp.getSalary().add(BigDecimal.valueOf(zmiana)));
			System.out.println("Pensja po zmianie: " + emp.getSalary());
			
			// flush wykonuje operacje po stronie bazy danych, ale ich nie komituje
			//em.flush();
			
			// jeśli robimy commit, to nie potrzebujemy flush
			em.getTransaction().commit();
			System.out.println("Zapisane");
		} finally {
			if(em != null)
				em.close();
			if(emf != null)
				emf.close();
		}
	}

}
