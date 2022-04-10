package nasze_przyklady;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Employee;

public class ModyfikujRekord {

	private static final BigDecimal ZMIANA = new BigDecimal("100.00");

	public static void main(String[] args) {
		int id = 100;
		
		System.out.println("Startujemy...");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hr");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();

		Employee emp = em.find(Employee.class, id);
		if(emp == null) {
			System.out.println("Nie ma takiego rekordu " + id);
			return;
		}

		System.out.println("Znaleziono: " + emp);
		System.out.println(emp.getFirstName() + " " + emp.getLastName() + " " + emp.getSalary());
		
		emp.setLastName("KING");
		emp.setSalary(emp.getSalary().add(ZMIANA));
		emp.getDepartment().getLocation().setCity("Żyrardów");
		
		System.out.println(emp.getFirstName() + " " + emp.getLastName() + " " + emp.getSalary());
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		System.out.println("Kończymy...");
	}

}
