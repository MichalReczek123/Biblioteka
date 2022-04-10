package przyklady;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Customer;
import model.Order;
import model.OrderProduct;

public class Przyklad5_Referencje {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sklep");
		EntityManager em = emf.createEntityManager();
		
		// Ten przykład pokazuje, że z jednego obiektu wczytanego z bazy można zwykłymi metodami get???
		// przejść do obiektów zapisanych w innych tabelach.
		// Nie wymaga to pisania dodatkowych zapytań.
		
		while(true) {
			System.out.print("Podaj email klienta: ");
			String email = scanner.nextLine();
			if(email.isEmpty()) {
				break;
			}
		
			Customer customer = em.find(Customer.class, email);
			if(customer == null) {
				System.out.println("Nie ma takiego klienta");
			} else {
				System.out.println("Jest klient " + customer);
				System.out.println(customer.getCustomerName() + " z miasta " + customer.getCity());
				System.out.println("Zamówienia tego klienta:");
				for (Order order : customer.getOrders()) {
					System.out.println( " * " + order.getOrderId() + " z dnia " + order.getOrderDate());
					for (OrderProduct op : order.getOrderProducts()) {
						System.out.println("   - " + op.getProduct().getProductName() + " za " + op.getActualPrice());
					}
				}
				System.out.println();
			}
		}
		
		em.close();
		emf.close();
		scanner.close();
	}

}
