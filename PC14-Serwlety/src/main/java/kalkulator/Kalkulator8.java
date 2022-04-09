package kalkulator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Kalkulator8")
public class Kalkulator8 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Technologia serwletów nie gwarantuje, że wszystkie zapytania będą obsługiwane przez ten sam obiekt serwletu.
	// Dlatego nie powinniśmy przechowywać istotnych danych w polach instancyjnych tej klasy.
	// Użycie zmiennej statycznej spowodowałoby, że będzie to poprawne, ale to jest uznawane za "brzydkie podejście".
	private static List<String> historiaStatyczna = Collections.synchronizedList(new ArrayList<>());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Kalkulator 8</title>");
		out.println("<link rel='stylesheet' type='text/css' href='styl.css'/>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Kalkulator 8</h1>");
		
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

		
		// Tutaj zakładamy, że listener przygotował historię działań globalną i klienta.
		
		// ServletContext to jest obiekt, który dostarcza informacji o bieżącej aplikacji na serwerze.
		// Pełni też rolę "globalnego schowka" - przechowuje obiekty w zakresie aplikacji. (scope="application")
		ServletContext servletContext = this.getServletContext();
		List<String> historiaGlobalna = (List<String>) servletContext.getAttribute(ListenerHistorii.HISTORIA_GLOBALNA);
		
		// Sesja jest schowkiem do przechowywania danych skojarzonych z konretnym klientem (np. koszyk w sklepie).
		HttpSession sesja = request.getSession();		
		List<String> historiaKlienta = (List<String>) sesja.getAttribute(ListenerHistorii.HISTORIA_KLIENTA);
		
		String parametr1 = request.getParameter("liczba1");
		String parametr2 = request.getParameter("liczba2");
		String operacja = request.getParameter("operacja");
		
		if(parametr1 != null && parametr2 != null && operacja != null) {
			try {
				long liczba1 = Long.parseLong(parametr1);
				long liczba2 = Long.parseLong(parametr2);
				long wynik = LogikaKalkulatora.oblicz(liczba1, liczba2, operacja);
				out.printf("<p>%d %s %d = <strong>%d</strong></p>", liczba1, operacja, liczba2, wynik);
				
				String tekst = String.format("%d %s %d = %d", liczba1, operacja, liczba2, wynik);
				historiaStatyczna.add(tekst);
				
				historiaGlobalna.add(tekst);
				historiaKlienta.add(tekst);
			} catch (NumberFormatException e) {
				out.printf("<div class='error'>Niepoprawny format liczby %s</div>", e.getMessage());
			} catch (Exception e) {
				out.printf("<div class='error'>Inny wyjątek %s</div>", e);

			}
		}
		
		out.println("<h3>Historia działań (static):</h3>");
		out.println("<ul>");
		// Gdy na liście wykonujemy wiele operacji, np. w pętli, to powinniśmy synchronizować cały ten fragment.
		synchronized(historiaStatyczna) {
			for (String dzialanie : historiaStatyczna) {
				out.println("<li>" + dzialanie + "</li>");
			}
		}
		out.println("</ul>");
		
		out.println("<h3>Historia działań (ServletContext):</h3>");
		out.println("<ul>");
		synchronized(historiaGlobalna) {
			for (String dzialanie : historiaGlobalna) {
				out.println("<li>" + dzialanie + "</li>");
			}
		}
		out.println("</ul>");
		
		out.println("<h3>Historia działań klienta (HttpSession):</h3>");
		out.println("<ul>");
		synchronized(historiaKlienta) {
			for (String dzialanie : historiaKlienta) {
				out.println("<li>" + dzialanie + "</li>");
			}
		}
		out.println("</ul>");
		
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
 