package przyklady;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Product;

public class Przyklad2_Find {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sklep");
		EntityManager em = emf.createEntityManager();
		System.out.println("Mam EntityManagera " + em);
		
		while(true) {
			System.out.print("Podaj id produktu: ");
			int szukaneId = scanner.nextInt();
			if(szukaneId == 0) {
				break;
			}
		
			Product product = em.find(Product.class, szukaneId);
			if(product == null) {
				System.out.println("Nie ma takiego produktu");
			} else {
				System.out.println("Jest produkt " + product);
				System.out.println(product.getProductName() + " za cenę " + product.getPrice());
				System.out.println(product.getDescription());
			}
		}
		
		em.close();
		emf.close();
		scanner.close();
	}

}
