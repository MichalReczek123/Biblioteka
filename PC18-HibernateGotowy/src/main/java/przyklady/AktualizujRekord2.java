package przyklady;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Department;
import model.Employee;

public class AktualizujRekord2 {

	public static void main(String[] args) {
		EntityManagerFactory emf = null;		
		EntityManager em = null;
		
		try {
			emf = Persistence.createEntityManagerFactory("hr");
			em = emf.createEntityManager();
			
			em.getTransaction().begin();
			
			Employee king = em.find(Employee.class, 100);
			System.out.println(king.getEmployeeId() + " " + king.getFirstName() + " " + king.getLastName());
			
			Employee kochhar = em.find(Employee.class, 101);
			System.out.println(kochhar.getEmployeeId() + " " + kochhar.getFirstName() + " " + kochhar.getLastName());
			
			Department dep = em.find(Department.class, 90);
			System.out.println("departament: " + dep.getDepartmentName());
			
			System.out.println(dep == king.getDepartment());
			System.out.println(dep == kochhar.getDepartment());
			System.out.println();
			
			System.out.println("Departamenty przed zmianą: "
					+ dep.getDepartmentName() + " "
					+ king.getDepartment().getDepartmentName() + " "
					+ kochhar.getDepartment().getDepartmentName());

			king.getDepartment().setDepartmentName("Nowa nazwa");
			// king.getDepartment().setDepartmentName("Executive");
			
			System.out.println("Departamenty po zmianie: "
					+ dep.getDepartmentName() + " "
					+ king.getDepartment().getDepartmentName() + " "
					+ kochhar.getDepartment().getDepartmentName());

			System.out.println(dep == king.getDepartment());
			System.out.println(dep == kochhar.getDepartment());

			em.getTransaction().commit();
			System.out.println("Zapisane");
		} finally {
			if(em != null)
				em.close();
			if(emf != null)
				emf.close();
		}
	}

}
