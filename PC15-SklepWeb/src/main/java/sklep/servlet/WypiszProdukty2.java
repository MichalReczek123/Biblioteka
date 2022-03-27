package sklep.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sklep.db.CustomerDAO;
import sklep.db.DBConnection;
import sklep.db.DBException;
import sklep.db.ProductDAO;
import sklep.model.Product;

@WebServlet("/WypiszProdukty2")
public class WypiszProdukty2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("Początek...");
		
		try(DBConnection db = DBConnection.open()) {
			ProductDAO productDAO = db.productDAO();			
			List<Product> products = productDAO.readAll();
			for (Product product : products) {
				out.println(product);
			}
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

}


