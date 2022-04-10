package nasze_przyklady;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Employee;

public class OdczytajWszystkich1 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hr");
		EntityManager em = emf.createEntityManager();
	
		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);

		List<Employee> lista = query.getResultList();
		for(Employee emp : lista) {
			System.out.println(emp.getFirstName() + " " + emp.getLastName() + " " + emp.getSalary());
		}
		
		em.close();
		emf.close();
	}

}
