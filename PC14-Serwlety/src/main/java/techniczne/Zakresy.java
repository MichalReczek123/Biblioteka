package techniczne;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.InfoBean;

@WebServlet("/Zakresy")
public class Zakresy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Standard nie gwarantuje nam żadnego zachowania jeśli chodzi o zmienne instancyjne w serwletach.
	// Nie wiemy kiedy i ile obiektów klasy serwlet będzie tworzył serwer.
	// W praktyce zazwyczaj tworzony jest jeden wspólny obiekt dla wszystkich zapytań, ale nie możemy tego zakładać.
	private InfoBean instancyjna = new InfoBean();
	
	// Zmienna statyczna będzie istniała do restartu serwera albo redeploy aplikacji.
	private static InfoBean statyczna = new InfoBean();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InfoBean lokalna = new InfoBean();
		
		HttpSession sesja = request.getSession();
		
		InfoBean req = (InfoBean) request.getAttribute("licznik-req");
		InfoBean ses = (InfoBean) sesja.getAttribute("licznik-ses");
		InfoBean app = (InfoBean) getServletContext().getAttribute("licznik-app");
		
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		out.println("lokalna    : " + lokalna.getLicznik());
		out.println("instancyjna: " + instancyjna.getLicznik());
		out.println("statyczna  : " + statyczna.getLicznik());
		out.println();
		out.println("request    : " + req.getLicznik());
		out.println("sesja      : " + ses.getLicznik());
		out.println("aplikacja  : " + app.getLicznik());
	}

}
