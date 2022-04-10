package przyklady;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Country;

// Wracając do prostszych rzeczy: Wynik native query można bez problemu odczytać jako normalną encję.
public class Odczyt16_Native_Entity {
	
	public static void main(String[] args) {
		final String sql = "SELECT * FROM countries";
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hr");
			em = emf.createEntityManager();
			Query query = em.createNativeQuery(sql, Country.class);
			List<Country> rows = (List<Country>)query.getResultList();
			for (Country country : rows) {
				System.out.println(country.getCountryId() + " " + country.getCountryName());
			}
			
		} finally {
			if(em != null) em.close();
			if(emf != null) emf.close();
		}
	}

}
