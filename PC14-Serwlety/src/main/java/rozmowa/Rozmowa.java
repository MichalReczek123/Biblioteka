package rozmowa;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Rozmowa")
public class Rozmowa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String imie = request.getParameter("imie");
		String miasto = request.getParameter("miasto");
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Rozmowa HTML</h1>");
		
		if(imie == null) {
			out.println("<p style='color: magenta'>Przedstaw się...</p>");
		} else {
			out.print("<p style='color: blue'>Witaj " + imie);
			if(miasto != null && !miasto.isEmpty()) {
				out.print(" z miasta " + miasto);
			}
			out.print("</p>");
		}
		out.println("<form>");
		out.println("<label for='imie'>Podaj imię:</label>");
		out.println("<input name='imie' type='text'>");
		out.println("<br>");
		out.println("<label for='miasto'>Podaj miasto:</label>");
		out.println("<input name='miasto' type='text'>");
		out.println("<br>");
		out.println("<button>Wyślij</button>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
