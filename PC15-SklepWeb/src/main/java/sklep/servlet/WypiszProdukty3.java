package sklep.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sklep.db.DBConnection;
import sklep.db.DBException;
import sklep.db.ProductDAO;
import sklep.model.Product;

@WebServlet("/WypiszProdukty3")
public class WypiszProdukty3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sklep</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Lista produktów</h1>");
		
		try(DBConnection db = DBConnection.open()) {
			ProductDAO productDAO = db.productDAO();			
			List<Product> products = productDAO.readAll();
			for (Product product : products) {
				out.println(product.getHtml());
			}
		} catch (DBException e) {
			out.println("<pre>");
			e.printStackTrace(out);
			out.println("</pre>");
		}
		out.println("</body>");
		out.println("</html>");
	}
}
