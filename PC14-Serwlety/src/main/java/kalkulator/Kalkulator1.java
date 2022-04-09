package kalkulator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Kalkulator1")
public class Kalkulator1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Kalkulator serwletowy</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Kalkulator 1</h1>");
		out.println("<form>");
		out.println("<label for='liczba1'>Liczba 1:</label>");
		out.println("<input name='liczba1' type='text'>");
		out.println("<br>");
		out.println("<label for='liczba2'>Liczba 2:</label>");
		out.println("<input name='liczba2' type='text'>");
		out.println("<br>");
		out.println("<button>Oblicz</button>");
		out.println("</form>");
		
		String param1 = request.getParameter("liczba1");
		String param2 = request.getParameter("liczba2");
		
		if (param1 != null && param2 != null) {
			try {
				long liczba1 = Long.parseLong(param1);
				long liczba2 = Long.parseLong(param2);
				long wynik = liczba1 + liczba2;
				out.println("<div class='wynik'>Suma: " + wynik + "</div>");
			} catch (NumberFormatException e) {
				out.println("<div class='error'>Niepoprawny format liczby</div>");
			}
		}
		out.println("</body>");
		out.println("</html>");
		
	}

}








