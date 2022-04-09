package kalkulator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Kalkulator4")
public class Kalkulator4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// W tej wersji wiemy, że GET ma wyświetlić pusty formularz, a POST to jest wykonanie obliczenia.
	// Teraz (bez użycia if-a) óżnicujemy logikę między wersją GET i POST
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = ustawNaglowkiIPobierzWritera(response);
		poczatek(out);
		formularz(out);
		koniec(out);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = ustawNaglowkiIPobierzWritera(response);
		poczatek(out);
		formularz(out);
		obsluzParametryIWyswietlWynik(request, out);
		koniec(out);
	}

	private PrintWriter ustawNaglowkiIPobierzWritera(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		return out;
	}

	private void poczatek(PrintWriter out) {
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Kalkulator 4</title>");
		out.println("<link rel='stylesheet' type='text/css' href='styl.css'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Kalkulator 4</h1>");
	}

	private void koniec(PrintWriter out) {
		out.println("</body>");
		out.println("</html>");
	}

	private void formularz(PrintWriter out) {
		out.println("<form method='post'>");
		out.println("<label for='liczba1'>Liczba 1: </label>");
		out.println("<input type='number' name='liczba1'/> <br/>");
		out.println("<label for='liczba2'>Liczba 2: </label>");
		out.println("<input type='number' name='liczba2'/> <br/>");
		out.println("<label for='operacja'>Wybierz działanie: </label>");
		out.println("<select name='operacja'>");
		out.println("<option value='+'>+</option>");
		out.println("<option value='-'>-</option>");
		out.println("<option value='*'>×</option>");
		out.println("<option value='/'>÷</option>");
		out.println("</select><br/>");
		out.println("<button>Oblicz</button>");
		out.println("</form>");
	}

	private void obsluzParametryIWyswietlWynik(HttpServletRequest request, PrintWriter out) {
		String param1 = request.getParameter("liczba1");
		String param2 = request.getParameter("liczba2");
		String operacja = request.getParameter("operacja");

		if (param1 != null && param2 != null && operacja != null) {
			try {
				long liczba1 = Long.parseLong(param1);
				long liczba2 = Long.parseLong(param2);
				long wynik = LogikaKalkulatora.oblicz(liczba1, liczba2, operacja);
				out.println("<div class='wynik'>");
				out.printf("%d %s %d = <strong>%d</strong>", liczba1, operacja, liczba2, wynik);
				out.println("</div>");
			} catch (NumberFormatException e) {
				out.printf("<div class='error'>Niepoprawny format liczby %s</div>", e.getMessage());
			} catch (Exception e) {
				out.printf("<div class='error'>Inny wyjątek %s</div>", e);
			}
		}
	}
	
}
