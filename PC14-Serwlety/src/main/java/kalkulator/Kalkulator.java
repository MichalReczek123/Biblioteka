package kalkulator;

import java.io.IOException;
import java.io.PrintWriter;

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
		String operacja = request.getParameter("operacja");
		
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
		out.println("<td><input name='liczba1' type='number'></td>");
		out.println("</tr><tr>");
		out.println("<td><label for='liczba2'>Druga liczba:</label></td>");
		out.println("<td><input name='liczba2' type='number'></td>");
		out.println("</tr><tr>");
		out.println("<td>");
		out.println("<select name='operacja'>");
		out.println("<option value='+'>+</option>");
		out.println("<option value='-'>-</option>");
		out.println("<option value='*'>×</option>");
		out.println("<option value='/'>÷</option>");
		out.println("</select>");
		out.println("</td>");
		out.println("<td><button>Oblicz</button></td>");
		out.println("</tr></table>");
		out.println("</form>");
		
		if(parametr1 != null && parametr2 != null && operacja != null) {
			try {
				int liczba1 = Integer.parseInt(parametr1);
				int liczba2 = Integer.parseInt(parametr2);
				int wynik = switch(operacja) {
					case "+" -> liczba1 + liczba2;
					case "-" -> liczba1 - liczba2;
					case "*" -> liczba1 * liczba2;
					case "/" -> liczba1 / liczba2;
					default -> 0;
				};
				out.printf("<div>%d %s %s = <strong>%d</strong></div>\n", liczba1, operacja, liczba2, wynik);
			} catch (NumberFormatException e) {
				out.println("<div>Niepoprawny format liczby</div>");
			}
		}
		
		out.println("</body>");
		out.println("</html>");
	}

}
