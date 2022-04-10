package nasze_przyklady;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Employee;

public class Odczytaj_wg_Nazwisk {

	public static void main(String[] args) {
		String kogoSzukam = "King";
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hr");
		EntityManager em = emf.createEntityManager();
	
//		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE lastName = ?1", Employee.class);
//		query.setParameter(1, kogoSzukam);

		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE lastName = :nazwisko", Employee.class);
		query.setParameter("nazwisko", kogoSzukam);
		
		List<Employee> lista = query.getResultList();
		for(Employee emp : lista) {
			System.out.println(emp.getFirstName() + " " + emp.getLastName() + " " + emp.getSalary());
		}
		
		em.close();
		emf.close();
	}

}
