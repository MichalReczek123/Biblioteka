package przyklady;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Department;
import model.Employee;

public class Odczyt02_JedenRekord_InneTabele {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hr");
			em = emf.createEntityManager();
		
			System.out.println("Udało się połączyć, em = " + em);
			
			while(true) {
				System.out.print("Podaj id pracownika: ");
				if(!sc.hasNextInt())
					break;
				int id = sc.nextInt();
				Employee emp = em.find(Employee.class, id);
				
				if(emp == null) {
					System.out.println("nie znaleziono");
				} else {
					System.out.println(emp.getFirstName() + " " + emp.getLastName() + " $" + emp.getSalary());
					System.out.println("job: " + emp.getJob().getJobTitle());
					Department department = emp.getDepartment();
					if (department != null) {
						System.out.println("departament: " + department.getDepartmentName());
						System.out.println("adres: " + department.getLocation().getStreetAddress() + " "
								+ department.getLocation().getCity());
					}
					System.out.println();
				}
			}
		} finally {
			if(em != null)
				em.close();
			if(emf != null)
				emf.close();
		}
	}
}
