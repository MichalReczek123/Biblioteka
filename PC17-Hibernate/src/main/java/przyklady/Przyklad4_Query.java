package przyklady;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Product;

public class Przyklad4_Query {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sklep");
		EntityManager em = emf.createEntityManager();
		
		// Tutaj samodzielnie przygotowuję zapytanie w języku JPQL (Java Persistence Query Language)
		// Jest to wzorowane na SQL, ale w treści odwołujemy się do nazw klas w Javie (Product)
		// i nazw pól tak, jak nazywają się one w Javie (np. byłoby productName).
		// Zestaw operacji funkcji jest ograniczony i niezależny od bazy danych.
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p ORDER BY p.price DESC", Product.class);
		List<Product> products = query.getResultList();
		for (Product product : products) {
			System.out.println(product.getProductName() + " za cenę " + product.getPrice() + ", " + product.getDescription());
		}
		em.close();
		emf.close();
	}

}
