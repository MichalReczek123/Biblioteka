package przyklady;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Employee;

public class Odczyt01_JedenRekord {

	public static void main(String[] args) {
		System.out.println("Początek");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hr");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("Udało się połączyć, em = " + em);
		
		Employee emp = em.find(Employee.class, 100);
		if(emp == null) {
			System.out.println("nie znaleziono");
		} else {
			System.out.println(emp.getFirstName() + " " + emp.getLastName());
		}
		
		em.close();
		emf.close();
	}

}
