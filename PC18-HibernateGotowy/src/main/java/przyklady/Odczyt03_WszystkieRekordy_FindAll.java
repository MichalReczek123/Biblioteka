package przyklady;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Employee;

public class Odczyt03_WszystkieRekordy_FindAll {

	public static void main(String[] args) {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hr");
			em = emf.createEntityManager();

			TypedQuery<Employee> query = em.createNamedQuery("Employee.findAll", Employee.class);
			// Wczytuje wszystkie dane od razu do pamięci
			List<Employee> lista = query.getResultList();
			System.out.println("Ilość rekordów " + lista.size());
			for (Employee employee : lista) {
				System.out.println(employee.getFirstName() + " " + employee.getLastName());
			}
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
		}
	}
}
