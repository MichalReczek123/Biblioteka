package sklep.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sklep.db.RecordNotFound;
import sklep.photo.PhotoUtil;

@WebServlet("/Photo")
public class Photo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametrId = request.getParameter("productId");
		if(parametrId == null) {
			return;
		}
		
		try {
			int id = Integer.parseInt(parametrId);
			byte[] bytes = PhotoUtil.readBytes(id);
			response.setContentType("image/jpeg");
			ServletOutputStream output = response.getOutputStream();
			output.write(bytes);
			output.close();
		} catch (RecordNotFound e) {
			response.setStatus(404);
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			response.getWriter().println("Nie ma zdjęcia dla produktu o nr " + parametrId);
		} catch (Exception e) {
			response.setStatus(500);
			e.printStackTrace();
		}
	}

}
