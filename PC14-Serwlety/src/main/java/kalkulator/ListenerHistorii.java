package kalkulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class ListenerHistorii implements HttpSessionListener, ServletContextListener {
	public static final String HISTORIA_KLIENTA = "historia_klienta";
	public static final String HISTORIA_GLOBALNA = "historia_globalna";

	public void sessionCreated(HttpSessionEvent se) {
		// Gdy podłączy się nowy klient, jest tworzona nowa sesja, a my do tej sesji
		// dodajemy pustą listę "historia_dzialan"
		List<String> historia = Collections.synchronizedList(new ArrayList<>());
		se.getSession().setAttribute(HISTORIA_KLIENTA, historia);
		se.getSession().setMaxInactiveInterval(60);
		System.out.println("Do sesji została dodana pusta historia");
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("Koniec sesji");
	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		List<String> historiaGlobalna = Collections.synchronizedList(new ArrayList<>());
		servletContext.setAttribute(HISTORIA_GLOBALNA, historiaGlobalna);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("aplikacja kończy się");
		List<String> historiaGlobalna = (List<String>) sce.getServletContext().getAttribute(HISTORIA_GLOBALNA);
		System.out.println("W sumie wykonano " + historiaGlobalna.size() + " działań");
	}

	// W serwletach mamy trzy obiekty (tak jakby na trzech poziomach), w których za pomocą setAttribute / getAttribute można przechowywać dane
	// ServletContext - dane globalne
	// HttpSession - dane sesji, skojarzone z konkretnym klientem
	// HttpRequest - dane potrzebne podczas obsługi pojedynczego zapytania
}
