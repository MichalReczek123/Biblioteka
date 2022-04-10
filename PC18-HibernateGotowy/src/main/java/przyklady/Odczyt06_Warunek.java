package przyklady;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import model.Employee;

public class Odczyt06_Warunek {

	public static void main(String[] args) {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hr");
			em = emf.createEntityManager();
			
			String kogoSzukam = JOptionPane.showInputDialog("Podaj kod stanowiska");
			
			// to działało:
			// TypedQuery<Employee> query = em.createQuery("SELECT emp FROM Employee emp WHERE job.jobId = ?1", Employee.class);
			// query.setParameter(1, kogoSzukam);

			// ale to już nie ("legacy parameter style"):
			// TypedQuery<Employee> query = em.createQuery("SELECT emp FROM Employee emp WHERE job.jobId = ?", Employee.class);
			// query.setParameter(1, kogoSzukam);

			// wszystkie trzy zapisy zadziałały:
			// TypedQuery<Employee> query = em.createQuery("SELECT emp FROM Employee emp WHERE job_id = :kto", Employee.class);
			// TypedQuery<Employee> query = em.createQuery("SELECT emp FROM Employee emp WHERE job.jobId = :kto", Employee.class);
			TypedQuery<Employee> query = em.createQuery("SELECT emp FROM Employee emp WHERE emp.job.jobId = :kto", Employee.class);
			query.setParameter("kto", kogoSzukam);
			
			List<Employee> lista = query.getResultList();
			System.out.println("Ilość rekordów " + lista.size());
			for (Employee employee : lista) {
				System.out.println(employee.getFirstName() + " " + employee.getLastName());
			}
			
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
		}
	}
}
