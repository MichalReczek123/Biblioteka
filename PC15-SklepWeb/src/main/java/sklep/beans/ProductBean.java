package sklep.beans;

import java.util.List;

import sklep.db.DBConnection;
import sklep.db.DBException;
import sklep.db.ProductDAO;
import sklep.model.Product;

public class ProductBean {
	
	public List<Product> getAllProducts() {
		try(DBConnection db = DBConnection.open()) {
			ProductDAO productDAO = db.productDAO();
			return productDAO.readAll();
		} catch (DBException e) {
			e.printStackTrace();
			return List.of();
		}
	}
	

}
