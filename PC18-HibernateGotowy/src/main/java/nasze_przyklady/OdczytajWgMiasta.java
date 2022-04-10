package nasze_przyklady;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Employee;

public class OdczytajWgMiasta {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Startujemy...");

		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hr");
			em = emf.createEntityManager();
			while (true) {
				System.out.println("Podaj nazwę miasta (enter aby zakończyć)");
				String miasto = sc.nextLine().trim();
				if (miasto.isEmpty())
					break;

				TypedQuery<Employee> query = em.createQuery(
						"SELECT emp FROM Employee emp WHERE emp.department.location.city = :parametrMiasto",
						Employee.class);
				query.setParameter("parametrMiasto", miasto);

				List<Employee> lista = query.getResultList();

				System.out.println("Odczytano " + lista.size() + " rekordów.");
				for (Employee emp : lista) {
					System.out.printf("%-15s %-15s %8s %s\n", emp.getFirstName(), emp.getLastName(), emp.getSalary(),
							emp.getJob().getJobTitle());
				}
			} 
		} finally {
			em.close();
			emf.close();
		}
	}

}
