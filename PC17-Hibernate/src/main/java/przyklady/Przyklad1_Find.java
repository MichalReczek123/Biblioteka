package przyklady;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Product;

public class Przyklad1_Find {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sklep");
		EntityManager em = emf.createEntityManager();
		System.out.println("Mam EntityManagera " + em);
		
		int szukaneId = 2;
		Product product = em.find(Product.class, szukaneId);
		if(product == null) {
			System.out.println("Nie ma takiego produktu");
		} else {
			System.out.println("Jest produkt " + product);
			System.out.println(product.getProductName() + " za cenę " + product.getPrice());
			System.out.println(product.getDescription());
		}
		
		em.close();
		emf.close();
	}

}
