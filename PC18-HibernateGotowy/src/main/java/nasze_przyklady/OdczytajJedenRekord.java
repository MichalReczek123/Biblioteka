package nasze_przyklady;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Employee;

public class OdczytajJedenRekord {

	public static void main(String[] args) {
		int id = 100;
		
		System.out.println("Startujemy...");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hr");
		System.out.println("EMF: " + emf);
		
		EntityManager em = emf.createEntityManager();
		System.out.println("EM : " + em);
		
		Employee emp = em.find(Employee.class, id);
		if(emp == null) {
			System.out.println("Nie ma takiego rekordu " + id);
		} else {
			System.out.println("Znaleziono: " + emp);
			System.out.println(emp.getFirstName() + " " + emp.getLastName() + " " + emp.getSalary());
		}
		
		
		em.close();
		emf.close();

	}

}
