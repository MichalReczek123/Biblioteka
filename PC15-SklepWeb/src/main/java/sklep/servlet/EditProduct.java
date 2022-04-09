package sklep.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sklep.db.DBConnection;
import sklep.db.DBException;
import sklep.db.ProductDAO;
import sklep.db.RecordNotFound;
import sklep.db.SklepException;
import sklep.model.Product;

@WebServlet("/EditProduct")
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametrId = request.getParameter("productId");
		if(parametrId != null) {
			int id = Integer.parseInt(parametrId);
			try(DBConnection db = DBConnection.open()) {
				ProductDAO productDAO = db.productDAO();
				Product product = productDAO.findById(id);
				request.setAttribute("product", product);
				
			} catch (DBException | RecordNotFound e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("product_form.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String parametrId = request.getParameter("productId");
		Integer productId = (parametrId == null || parametrId.isEmpty()) ? null : Integer.valueOf(parametrId);

		String parametrPrice = request.getParameter("price");
		BigDecimal price = new BigDecimal(parametrPrice);

		String parametrVat = request.getParameter("vat");
		BigDecimal vat = new BigDecimal(parametrVat);

		String name = request.getParameter("name");
		String description = request.getParameter("description");

		Product product = new Product(productId, name, price, vat, description);
		try (DBConnection db = DBConnection.open()) {
			ProductDAO productDAO = db.productDAO();
			productDAO.save(product);
			db.commit();
		} catch (SklepException e) {
			e.printStackTrace();
		}
		request.setAttribute("product", product);

		RequestDispatcher dispatcher = request.getRequestDispatcher("product_form.jsp");
		dispatcher.forward(request, response);
	}

}
