package przyklady;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Product;

public class Przyklad3_FindAll {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sklep");
		EntityManager em = emf.createEntityManager();
		
		// Metoda createNamedQuery odwołuje się do jednego z predefiniowanych zapytań,
		// które są zdefiniowane w jednej z klas encji w adnotacji @NamedQuery
		// Tutaj korzystamy z zapytania zdefiniowanego w klasie Product - zobacz tam...
		TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
		List<Product> products = query.getResultList();
		for (Product product : products) {
			System.out.println(product.getProductName() + " za cenę " + product.getPrice() + ", " + product.getDescription());
		}
		em.close();
		emf.close();
	}

}
