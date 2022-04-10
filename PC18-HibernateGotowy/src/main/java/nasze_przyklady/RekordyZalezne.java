package nasze_przyklady;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Employee;
import model.Location;

public class RekordyZalezne {

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
			
			// TODO odczytaj nazwę departamentu, miasto, kraj, nazwę stanowiska (jobTitle)...
			
			// przy takim zapisie uwaga na nulle!
			System.out.println("Stanowisko: " + emp.getJob().getJobTitle());
			System.out.println("Departament: " + emp.getDepartment().getDepartmentName());
			Location location = emp.getDepartment().getLocation();
			System.out.println("Miasto i kraj: " + location.getCity() + " " + location.getCountry().getCountryName());
		}
		
		em.close();
		emf.close();
	}

}
