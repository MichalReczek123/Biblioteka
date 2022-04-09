package kalkulator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Kalkulator2")
public class Kalkulator2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Kalkulator serwletowy</title>");
		out.println("<link rel='stylesheet' type='text/css' href='styl.css'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Kalkulator 2</h1>");
		out.println("<form>");
		out.println("<label for='liczba1'>Liczba 1:</label>");
		out.println("<input name='liczba1' type='text'>");
		out.println("<br>");
		out.println("<select name='operacja'>");
		out.println("<option value='+'>dodawanie</option>");
		out.println("<option value='-'>odejmowanie</option>");
		out.println("<option value='*'>mnożenie</option>");
		out.println("<option value='/'>dzielenie</option>");
		out.println("</select>");
		out.println("<br>");
		out.println("<label for='liczba2'>Liczba 2:</label>");
		out.println("<input name='liczba2' type='text'>");
		out.println("<br>");
		out.println("<button>Oblicz</button>");
		out.println("</form>");
		
		String param1 = request.getParameter("liczba1");
		String param2 = request.getParameter("liczba2");
		String operacja = request.getParameter("operacja");
		
		if (operacja != null && param1 != null && param2 != null) {
			try {
				long liczba1 = Long.parseLong(param1);
				long liczba2 = Long.parseLong(param2);
				long wynik = LogikaKalkulatora.oblicz(liczba1, liczba2, operacja);
				out.printf("<div class='wynik'>%d %s %d = <strong>%d</strong></div>",
						liczba1, operacja, liczba2, wynik);
			} catch (NumberFormatException e) {
				out.println("<div class='error'>Niepoprawny format liczby</div>");
			} catch (Exception e) {
				out.printf("<div class='error'>Inny błąd: <code>%s</code></div>", e);
			}
		}
		out.println("</body>");
		out.println("</html>");
	}
}
