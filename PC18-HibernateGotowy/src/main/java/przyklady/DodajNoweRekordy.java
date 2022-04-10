package przyklady;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Country;
import model.Location;
import model.Region;

public class DodajNoweRekordy {

	public static void main(String[] args) {
		EntityManagerFactory emf = null;		
		EntityManager em = null;
		
		try {
			emf = Persistence.createEntityManagerFactory("hr");
			em = emf.createEntityManager();
			
			em.getTransaction().begin();
			
			// Utworzyć nowy kraj (my podajemy id) i nową lokalizację (id zostanie wygenerowane).
			
			Region region = em.find(Region.class, 1);
			Country polska = new Country();
			polska.setCountryId("PL");
			polska.setCountryName("Polska");
			polska.setRegion(region);
			em.persist(polska);
			
			// Dla lokalizacji nie podajemy ID - ono zostanie wygenerowane z sekwencji.
			Location loc = new Location();
			loc.setCountry(polska);
			loc.setStreetAddress("Jasna");
			loc.setCity("Warszawa");
			loc.setPostalCode("00-123");
			
			System.out.println("id przed dodaniem " + loc.getLocationId());
			em.persist(loc);
			System.out.println("id po dodaniu " + loc.getLocationId());
			
			em.getTransaction().commit();
			System.out.println("Zapisano.");
			
		} finally {
			if(em != null)
				em.close();
			if(emf != null)
				emf.close();
		}
	}
}
