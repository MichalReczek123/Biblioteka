package kalkulator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Kalkulator7")
public class Kalkulator7 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = ustawNaglowkiIPobierzWritera(response);
		poczatek(out);
		formularz(out);
		wyswietlHistorie(request, out);
		koniec(out);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = ustawNaglowkiIPobierzWritera(response);
		poczatek(out);
		formularz(out);
		obsluzParametryIWyswietlWynik(request, out);
		wyswietlHistorie(request, out);
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
		out.println("<title>Kalkulator 7</title>");
		out.println("<link rel='stylesheet' type='text/css' href='styl.css'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Kalkulator 7</h1>");
	}

	private void koniec(PrintWriter out) {
		out.println("</body>");
		out.println("</html>");
	}

	private void formularz(PrintWriter out) {
		out.println("<form method='post'>");
		out.println("<label for='liczba1'>Liczba 1: </label>");
		out.println("<input type='text' name='liczba1'/> <br/>");
		out.println("<label for='liczba2'>Liczba 2: </label>");
		out.println("<input type='text' name='liczba2'/> <br/>");
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
		String parametr1 = request.getParameter("liczba1");
		String parametr2 = request.getParameter("liczba2");
		String operacja = request.getParameter("operacja");

		if (parametr1 != null && parametr2 != null && operacja != null) {
			try {
				long liczba1 = Long.parseLong(parametr1);
				long liczba2 = Long.parseLong(parametr2);
				long wynik = LogikaKalkulatora.oblicz(liczba1, liczba2, operacja);
				out.println("<div class='wynik'>");
				out.printf("%d %s %d = <strong>%d</strong>", liczba1, operacja, liczba2, wynik);
				out.println("</div>");
				List<String> historia = pobierzHistorieZSesji(request);
				historia.add(String.format("%d %s %d = %d", liczba1, operacja, liczba2, wynik));
			} catch (NumberFormatException e) {
				out.printf("<div class='error'>Niepoprawny format liczby %s</div>", e.getMessage());
			} catch (Exception e) {
				out.printf("<div class='error'>Inny wyjątek %s</div>", e);
			}
		}
	}

	private void wyswietlHistorie(HttpServletRequest request, PrintWriter out) {
		out.println("<h3>Historia</h3>");
		out.println("<ul class='historia'>");
		List<String> historia = pobierzHistorieZSesji(request);
		synchronized (historia) {
			for (String s : historia) {
				out.printf("<li>%s</li>\n", s);
			}
		}
		out.println("</ul>");
	}
	
	private List<String> pobierzHistorieZSesji(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (List<String>) session.getAttribute(ListenerHistorii.HISTORIA_KLIENTA);
	}
}
