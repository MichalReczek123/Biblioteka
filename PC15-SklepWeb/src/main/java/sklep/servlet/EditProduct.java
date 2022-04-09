package sklep.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sklep.db.DBConnection;
import sklep.db.DBException;
import sklep.db.ProductDAO;
import sklep.db.RecordNotFound;
import sklep.koszyk.Koszyk;
import sklep.model.Product;

@WebServlet("/EditProduct")
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametrId = request.getParameter("id");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
