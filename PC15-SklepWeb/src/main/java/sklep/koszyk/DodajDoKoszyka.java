package sklep.koszyk;

import java.io.IOException;
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
import sklep.model.Product;

@WebServlet("/DodajDoKoszyka")
public class DodajDoKoszyka extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String parametrId = request.getParameter("id");
			int id = Integer.parseInt(parametrId);
			try(DBConnection db = DBConnection.open()) {
				ProductDAO productDAO = db.productDAO();
				Product product = productDAO.findById(id);
				
				HttpSession sesja = request.getSession();
				Koszyk koszyk = (Koszyk) sesja.getAttribute("koszyk");
				if(koszyk == null) {
					koszyk = new Koszyk();
					sesja.setAttribute("koszyk", koszyk);
				}
				koszyk.addProduct(product);
				// modyfikujemy obiekt, który już jest w sesji, nie musimy po zmianie ponownie robić setAttribute
			}
			
		} catch (DBException | RecordNotFound | NumberFormatException e) {
			// ignorujemy ewentualne błędy
		}
		
		response.sendRedirect("wypisz_produkty8.jsp");
	}

}
