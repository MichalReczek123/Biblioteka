package przyklady;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Employee;

public class Criteria1 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hr");
		EntityManager em = emf.createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		cq.select(root);
		
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
