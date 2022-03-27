package kalkulator;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Kalkulator")
public class Kalkulator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Odczytanie parametru przysłanego w zapytaniu, zawsze jako String.
		// W przypadku braku parametru wynikime jest null.
		String parametr1 = request.getParameter("liczba1");
		String parametr2 = request.getParameter("liczba2");
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Kalkulator</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form>");
		out.println("<table><tr>");		
		out.println("<td><label for='liczba1'>Pierwsza liczba:</label></td>");
		out.println("<td><input name='liczba1' type='text'></td>");
		out.println("</tr><tr>");
		out.println("<td><label for='liczba2'>Druga liczba:</label></td>");
		out.println("<td><input name='liczba2' type='text'></td>");
		out.println("</tr><tr>");
		out.println("<td colspan='2'><button>Oblicz</button></td>");
		out.println("</tr></table>");
		out.println("</form>");
		
		if(parametr1 != null && parametr2 != null) {
			try {
				int liczba1 = Integer.parseInt(parametr1);
				int liczba2 = Integer.parseInt(parametr2);
				int wynik = liczba1 + liczba2;
				out.println("<div>Wynik: <strong>" + wynik + "</strong></div>");
			} catch (NumberFormatException e) {
				out.println("<div>Niepoprawny format liczby</div>");
			}
		}
		
		out.println("</body>");
		out.println("</html>");
	}

}
