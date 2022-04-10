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

public class Criteria3 {

	public static void main(String[] args) {
		BigDecimal minSalary = new BigDecimal(4000);
		String szukaneMiasto = "Seattle";
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hr");
		EntityManager em = emf.createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		cq.select(root);
		cq.where(cb.and(cb.ge(root.get("salary"), minSalary),
					cb.equal(root.get("department").get("location").get("city"), szukaneMiasto)));
		cq.orderBy(cb.desc(root.get("salary")));
		
		TypedQuery<Employee> query = em.createQuery(cq);
		
		List<Employee> lista = query.getResultList();
		System.out.println("Odczytano " + lista.size() + " rekordów.");
		for (Employee emp : lista) {
			String miasto = emp.getDepartment() == null ? "-" : emp.getDepartment().getLocation().getCity();
			
			System.out.printf("%-15s %-15s %-10s %8s %s\n", emp.getFirstName(), emp.getLastName(),
					miasto, emp.getSalary(), emp.getJob().getJobTitle());
		}
		
		em.close();
		emf.close();
	}

}
