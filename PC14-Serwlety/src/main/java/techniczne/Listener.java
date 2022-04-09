package techniczne;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import beans.InfoBean;

@WebListener
public class Listener implements ServletContextListener, HttpSessionListener, ServletRequestListener {

	// wykonywane po uruchomieniu aplikacji na serwerze
    public void contextInitialized(ServletContextEvent ev)  {
    	System.out.println("start aplikacji");
    	
    	// dodaję atrybut do kontekstu aplikacji (do "application scope")
    	InfoBean obiekt = new InfoBean();
    	obiekt.setLicznik(500);
    	obiekt.setNapis("servlet context");
    	ev.getServletContext().setAttribute("licznik-app", obiekt);
    }

    // wykonywane podczas "kontrolowanego" zamykania aplikacji
    public void contextDestroyed(ServletContextEvent ev)  { 
    	System.out.println("koniec aplikacji");
    }

    // gdy jest tworzona nowa sesja
    public void sessionCreated(HttpSessionEvent ev)  { 
    	System.out.println("utworzenie sesji " + ev.getSession().getId());
    	
    	// timeout sesji w sekundach
    	// ev.getSession().setMaxInactiveInterval(60);

    	InfoBean obiekt = new InfoBean();
    	obiekt.setLicznik(600);
    	obiekt.setNapis("sesja");
    	ev.getSession().setAttribute("licznik-ses", obiekt);
    }

    public void sessionDestroyed(HttpSessionEvent ev) {
    	System.out.println("koniec sesji " + ev.getSession().getId());
    }

    public void requestInitialized(ServletRequestEvent ev) {
    	System.out.println("przyszedł request");
    	
    	InfoBean obiekt = new InfoBean();
    	obiekt.setLicznik(700);
    	obiekt.setNapis("request");
    	ev.getServletRequest().setAttribute("licznik-req", obiekt);
    }

    public void requestDestroyed(ServletRequestEvent ev)  { 
    	System.out.println("obsługa requestu zakończona");
    }
}
