package sklep.koszyk;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class KoszykListener implements HttpSessionListener, ServletContextListener {
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		session.setMaxInactiveInterval(30);
		System.out.println("Początek sesji " + session.getId());
		session.setAttribute("koszyk", new Koszyk());
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		System.out.println("Koniec sesji " + session.getId());
	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Uruchomienie aplikacji");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Zakończenie aplikacji");
	}

}
