package kalkulator;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Kalkulator9")
public class Kalkulator9 extends HttpServlet  {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Tylko wyświetl formularz - po stronie Javy nie ma żadnej logiki do wykonania
		
		// Przekierowanie obsługi zapytania do innego elementu aplikacji (do serwletu, skryptu jsp, zwykłej strony...)
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/kalkulator_view.jsp");
		dispatcher.forward(request, response);
		// sterowanie tu wraca - można coś jeszcze zrobić
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obsługa wypełnionego formularza. Zanim przekażemy sterowanie do skryptu JSP, wcześniej wykonujemy działania "logiki".
		String parametr1 = request.getParameter("liczba1");
		String parametr2 = request.getParameter("liczba2");
		String operacja = request.getParameter("operacja");

		if (parametr1 != null && parametr2 != null && operacja != null) {
			try {
				long liczba1 = Long.parseLong(parametr1);
				long liczba2 = Long.parseLong(parametr2);
				long wynik = LogikaKalkulatora.oblicz(liczba1, liczba2, operacja);
				
				// Jeśli serwlet chce przekazać jakieś dane do JSP,
				// (patrząc bardziej ogólnie: z wcześniejszego etapu obsługi zapytania chcemy coś przekazać do następnego etapu)
				// możemy użyć obiektu request, który pełni rolę schowka podobnego do servletContext i HttpSession.
				request.setAttribute("wynik", wynik);
				
				String tekst = String.format("%d %s %d = %d", liczba1, operacja, liczba2, wynik);
				
				ServletContext servletContext = this.getServletContext();
				List<String> historiaGlobalna = (List<String>) servletContext.getAttribute(ListenerHistorii.HISTORIA_GLOBALNA);
				historiaGlobalna.add(tekst);
				
				HttpSession sesja = request.getSession();		
				List<String> historiaKlienta = (List<String>) sesja.getAttribute(ListenerHistorii.HISTORIA_KLIENTA);
				historiaKlienta.add(tekst);

			} catch (NumberFormatException e) {
				request.setAttribute("error", "Niepoprawny format liczby " + e.getMessage());
			} catch (Exception e) {
				request.setAttribute("error", String.valueOf(e));
			}
		}
		
		// Prezekazujemy dalszą obsługę zapytania do innego "zasobu", np. do pliku JSP
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/kalkulator_view.jsp");
		dispatcher.forward(request, response);
	}
	

}
