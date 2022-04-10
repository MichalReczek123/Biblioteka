package przyklady;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Employee;

public class Criteria2 {

	public static void main(String[] args) {
		BigDecimal minSalary = new BigDecimal(10000);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hr");
		EntityManager em = emf.createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		// podajmey klasę dla wyników
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		
		// podajemy encję / tabelę źródłową, od której zapytanie startuje
		Root<Employee> root = cq.from(Employee.class);
		
		// tak jakby dopisywanie kolejnych fragmentów zapytania
		cq.select(root);
		cq.where(cb.ge(root.get("salary"), minSalary));
		cq.orderBy(cb.desc(root.get("salary")));
		
		// Tutaj zastosowałem tworzenie kryteriów "dynamicznie" na podstawie nazw kolumn
		// Istnieje też możliwość wygenerowanie "metamodelu" i posługiwania się nim
		
		TypedQuery<Employee> query = em.createQuery(cq);
		
		List<Employee> lista = query.getResultList();
		System.out.println("Odczytano " + lista.size() + " rekordów.");
		for (Employee emp : lista) {
			System.out.printf("%-15s %-15s %8s %s\n", emp.getFirstName(), emp.getLastName(),
					emp.getSalary(), emp.getJob().getJobTitle());
		}
		
		em.close();
		emf.close();
	}

}
